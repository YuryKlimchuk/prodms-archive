package com.hydroyura.prodms.archive;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hydroyura.prodms.archive.data.entities.DBPartChange;
import com.hydroyura.prodms.archive.data.entities.DBRate;
import com.hydroyura.prodms.archive.dto.DTOPartChange;
import com.hydroyura.prodms.archive.dto.DTORate;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class})
public class ArchiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArchiveApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<DBPartChange, DTOPartChange> propertyMapper = modelMapper.createTypeMap(DBPartChange.class, DTOPartChange.class);
		propertyMapper.addMappings(
				arg -> arg.map(src -> src.getKey().getVersion(), DTOPartChange::setVersion)
		);

		return modelMapper;
	}

	@Bean
	public ObjectMapper getObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		return objectMapper;
	}

}

package com.hydroyura.prodms.archive.configuration;

import com.hydroyura.prodms.archive.data.entities.DBPartChange;
import com.hydroyura.prodms.archive.data.entities.dto.DTOPartChange;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<DBPartChange, DTOPartChange> propertyMapper = modelMapper.createTypeMap(DBPartChange.class, DTOPartChange.class);
		/*
		propertyMapper.addMappings(
				arg -> arg.map(src -> src.getKey().getVersion(), DTOPartChange::setVersion)
		);
		*/
        return modelMapper;
    }

}

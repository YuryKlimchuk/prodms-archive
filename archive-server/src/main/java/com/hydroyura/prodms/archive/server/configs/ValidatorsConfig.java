package com.hydroyura.prodms.archive.server.configs;

import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitCreate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ValidatorsConfig {


    @Bean
    Map<Class<?>, Validator> validators(Collection<Validator> validatorsList) {
        Map<Class<?>, Validator> validatorsMap = new HashMap<>();

        validatorsMap.put(DTOUnitCreate.class, findValidator(validatorsList, DTOUnitCreate.class));

        return validatorsMap;
    }

    private Validator findValidator(Collection<Validator> validatorsList, Class<?> clazz) {
        return validatorsList
                .stream()
                .filter(validator -> validator.supports(clazz))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error while init validators"));
    }

}

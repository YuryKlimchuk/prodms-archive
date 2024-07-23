package com.hydroyura.prodms.archive.server.configs;

import com.hydroyura.prodms.archive.client.dtos.unit.request.UnitCreateReq;
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

        validatorsMap.put(UnitCreateReq.class, findValidator(validatorsList, UnitCreateReq.class));
        validatorsMap.put(String.class, findValidator(validatorsList, String.class));

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

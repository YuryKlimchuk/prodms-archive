package com.hydroyura.prodms.archive.server.services.validators.impl;

import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitCreate;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ValidatorDTOUnitCreate implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(DTOUnitCreate.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}

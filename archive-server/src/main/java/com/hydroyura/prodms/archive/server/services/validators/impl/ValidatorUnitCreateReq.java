package com.hydroyura.prodms.archive.server.services.validators.impl;

import com.hydroyura.prodms.archive.client.dtos.unit.request.UnitCreateReq;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ValidatorUnitCreateReq implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UnitCreateReq.class);
    }

    // TODO: add check for:
    //  -number regex
    //  -status, type in available values
    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "number", "validation.unit.create.req.number.null");
        ValidationUtils.rejectIfEmpty(errors, "name", "validation.unit.create.req.name.null");
        ValidationUtils.rejectIfEmpty(errors, "type", "validation.unit.create.req.type.null");
        ValidationUtils.rejectIfEmpty(errors, "status", "validation.unit.create.req.status.null");
    }
}

/*
    private String number;
    private String name;
    private String type;
    private String status;
    private String comment;
 */
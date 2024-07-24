package com.hydroyura.prodms.archive.server.services.validators.impl;

import com.hydroyura.prodms.archive.client.unit.request.UnitCreateReq;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.HashSet;
import java.util.Set;

@Component
public class ValidatorUnitCreateReq implements Validator {

    private final Set<String> AVAILABLE_STATUSES = new HashSet<>() {{
        add("DESIGN");
        add("TEST");
        add("PRODUCTION");
    }};

    private final Set<String> AVAILABLE_TYPES = new HashSet<>() {{
        add("PART");
        add("ASM");
        add("BUY");
    }};

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UnitCreateReq.class);
    }

    // TODO: add check for:
    //  -number regex
    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "number", "validation.unit.create.req.number.null");
        ValidationUtils.rejectIfEmpty(errors, "name", "validation.unit.create.req.name.null");
        ValidationUtils.rejectIfEmpty(errors, "type", "validation.unit.create.req.type.null");
        isStatusAvailable(target, errors);
        isTypeAvailable(target, errors);
    }

    private void isStatusAvailable(Object target, Errors errors) {
        String status = UnitCreateReq.class.cast(target).getStatus();
        if (!StringUtils.hasLength(status)) {
            ValidationUtils.rejectIfEmpty(errors, "status", "validation.unit.create.req.status.null");
        } else if (!AVAILABLE_STATUSES.contains(status)) {
            errors.rejectValue("status", "validation.unit.create.req.status.nonexistent");
        }
    }

    private void isTypeAvailable(Object target, Errors errors) {
        String type = UnitCreateReq.class.cast(target).getType();
        if (!StringUtils.hasLength(type)) {
            ValidationUtils.rejectIfEmpty(errors, "type", "validation.unit.create.req.type.null");
        } else if (!AVAILABLE_TYPES.contains(type)) {
            errors.rejectValue("type", "validation.unit.create.req.type.nonexistent");
        }
    }
}
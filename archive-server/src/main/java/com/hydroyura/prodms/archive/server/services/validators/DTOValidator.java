package com.hydroyura.prodms.archive.server.services.validators;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.SimpleErrors;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class DTOValidator {

    private Map<Class<?>, Validator> validators = new HashMap<>();

    public void validate(Object obj) {
        Validator validator = validators.get(validators);
        Errors errors = new SimpleErrors(obj);

        validator.validate(obj, errors);
        // TODO: custom exception
        if (errors.hasErrors()) throw new RuntimeException("");
    }

}

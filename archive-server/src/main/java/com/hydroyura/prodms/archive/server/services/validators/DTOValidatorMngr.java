package com.hydroyura.prodms.archive.server.services.validators;

import com.hydroyura.prodms.archive.server.models.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.SimpleErrors;
import org.springframework.validation.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class DTOValidatorMngr {

    @Autowired
    private Map<Class<?>, Validator> validators = new HashMap<>();


    public void validate(Object obj) {
        Errors errs = new SimpleErrors(obj);
        Optional
                .ofNullable(validators.get(obj.getClass()))
                .orElseThrow(() -> new RuntimeException("Necessary validator didn't find"))
                .validate(obj, errs);
        if (errs.hasErrors()) throw new ValidationException(errs);
    }

}

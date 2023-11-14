package com.hydroyura.prodms.archive.validators.part;

import com.hydroyura.prodms.archive.validators.part.sub.DTOPartNameValidator;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.Collection;

@Component(value = "DTOPartValidator")
public class DTOPartValidator extends AbstractDTOPartValidator {

    private Collection<Validator> fieldValidators;

    @Override
    public void validate(Object target, Errors errors) {
        fieldValidators.forEach(validator -> {
            ValidationUtils.invokeValidator(validator, target, errors);
        });
    }

    @PostConstruct
    void init() {
        fieldValidators = new ArrayList<>();
        fieldValidators.add(new DTOPartNameValidator());
    }

}

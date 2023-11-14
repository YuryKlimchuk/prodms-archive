package com.hydroyura.prodms.archive.validators.part;

import com.hydroyura.prodms.archive.data.entities.dto.DTOPart;
import org.springframework.validation.Validator;

public abstract class AbstractDTOPartValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(DTOPart.class);
    }

}

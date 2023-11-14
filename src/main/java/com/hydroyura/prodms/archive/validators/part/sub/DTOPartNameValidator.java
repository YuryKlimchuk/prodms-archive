package com.hydroyura.prodms.archive.validators.part.sub;

import com.hydroyura.prodms.archive.data.entities.dto.DTOPart;
import com.hydroyura.prodms.archive.validators.part.AbstractDTOPartValidator;
import org.springframework.validation.Errors;

public class DTOPartNameValidator extends AbstractDTOPartValidator {

    private String regex = "^[А-ЯЁ]{1}[а-яё]{2,}$";

    @Override
    public void validate(Object target, Errors errors) {
        String name = ((DTOPart) target).getName();

        if(name == null) {
            errors.reject("name.null");
            return;
        }

        name = name.trim();
        if(name.isEmpty()) {
            errors.reject("name.empty");
            return;
        }

        if(!name.matches(regex)) {
            errors.reject("name.notregex");
        }

        ((DTOPart) target).setName(name);
    }
}

package com.hydroyura.prodms.archive.validators;

import com.hydroyura.prodms.archive.data.entities.DBPartStatus;
import com.hydroyura.prodms.archive.data.entities.DBPartType;
import com.hydroyura.prodms.archive.dto.DTOPart;
import com.hydroyura.prodms.archive.validators.part.DTOPartValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.DataBinder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DTOPartValidatorTest {


    private DataBinder dataBinder;


    @BeforeEach
    void initValidator() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        DTOPartValidator validator = new DTOPartValidator();

        Method method = DTOPartValidator.class.getDeclaredMethod("init");
        method.setAccessible(true);
        method.invoke(validator);

        this.dataBinder = new DataBinder(createPart());
        dataBinder.setValidator(validator);
    }


    private DTOPart createPart() {
        DTOPart part = new DTOPart();
        part.setPdf("file.pdf");
        part.setOtherFile("N/A");
        part.setStatus(DBPartStatus.TEST);
        part.setType(DBPartType.PART);
        part.setNumber("PART_NUMBER");
        part.setName("PART_NAME");
        part.setUpdated(LocalDate.now());
        part.setCreated(LocalDate.now());
        part.setVersion(1L);
        return part;
    }

    @Test
    void test1() {
        ((DTOPart) dataBinder.getTarget()).setName("Юрец");
        dataBinder.validate();
        assertEquals(0, dataBinder.getBindingResult().getAllErrors().size());
    }

    @Test
    void messagesTest() {
        ((DTOPart) dataBinder.getTarget()).setName("ЮрЕц");
        dataBinder.validate();

        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("validation_messages");
        StringBuilder sb = new StringBuilder();
        dataBinder.getBindingResult().getAllErrors().stream()
                .forEach(e -> sb.append(messageSource.getMessage(e, Locale.UK)));

        assertTrue(sb.toString().contains("yury_test_error"));
    }
}
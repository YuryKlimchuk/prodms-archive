package com.hydroyura.prodms.archive.validators.part.sub;

import com.hydroyura.prodms.archive.dto.DTOPart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;

import static org.junit.jupiter.api.Assertions.*;

class DTOPartNameValidatorTest {

    DataBinder dataBinder;

    @BeforeEach
    void init() {
        Validator validator = new DTOPartNameValidator();
        this.dataBinder = new DataBinder(new DTOPart(), "dtopart");
        dataBinder.setValidator(validator);
    }

    @Test
    void nullTest() {
        ((DTOPart) dataBinder.getTarget()).setName(null);
        dataBinder.validate();
        assertEquals("name.null.dtopart", dataBinder.getBindingResult().getAllErrors().get(0).getCodes()[0]);
    }

    @Test
    void emptyTest1() {
        ((DTOPart) dataBinder.getTarget()).setName("");
        dataBinder.validate();
        assertEquals("name.empty.dtopart", dataBinder.getBindingResult().getAllErrors().get(0).getCodes()[0]);
    }

    @Test
    void emptyTest2() {
        ((DTOPart) dataBinder.getTarget()).setName("       ");
        dataBinder.validate();
        assertEquals("name.empty.dtopart", dataBinder.getBindingResult().getAllErrors().get(0).getCodes()[0]);
    }

    @Test
    void regexTest1() {
        ((DTOPart) dataBinder.getTarget()).setName("Part");
        dataBinder.validate();
        assertEquals("name.notregex.dtopart", dataBinder.getBindingResult().getAllErrors().get(0).getCodes()[0]);
    }

    @Test
    void regexTest2() {
        ((DTOPart) dataBinder.getTarget()).setName("кардан");
        dataBinder.validate();
        assertEquals("name.notregex.dtopart", dataBinder.getBindingResult().getAllErrors().get(0).getCodes()[0]);
    }

    @Test
    void regexTest3() {
        ((DTOPart) dataBinder.getTarget()).setName("КарДан");
        dataBinder.validate();
        assertEquals("name.notregex.dtopart", dataBinder.getBindingResult().getAllErrors().get(0).getCodes()[0]);
    }

    @Test
    void regexTest4() {
        ((DTOPart) dataBinder.getTarget()).setName("Вал");
        dataBinder.validate();
        assertEquals(0, dataBinder.getBindingResult().getAllErrors().size());
    }

    @Test
    void regexTest5() {
        ((DTOPart) dataBinder.getTarget()).setName("Кардан");
        dataBinder.validate();
        assertEquals(0, dataBinder.getBindingResult().getAllErrors().size());
    }





}
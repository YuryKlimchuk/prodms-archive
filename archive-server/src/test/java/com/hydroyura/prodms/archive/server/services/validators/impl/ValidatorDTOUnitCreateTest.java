package com.hydroyura.prodms.archive.server.services.validators.impl;

/*
class ValidatorDTOUnitCreateTest {
    /*
    ValidatorDTOUnitCreate validator = new ValidatorDTOUnitCreate();


    @Test
    void emptyCheckForNumber() {
        var dto1 = create(null, "name", "type", "status");
        Errors errs1 = validator.validateObject(dto1);
        assertEquals("validation.unit.create.number", errs1.getFieldError("number").getCode());

        var dto2 = create("", "name", "type", "status");
        Errors errs2 = validator.validateObject(dto1);
        assertEquals("validation.unit.create.number", errs2.getFieldError("number").getCode());
    }

    @Test
    void emptyCheckForName() {
        var dto1 = create("number", null, "type", "status");
        Errors errs1 = validator.validateObject(dto1);
        assertEquals("validation.unit.create.name", errs1.getFieldError("name").getCode());

        var dto2 = create("number", "", "type", "status");
        Errors errs2 = validator.validateObject(dto2);
        assertEquals("validation.unit.create.name", errs2.getFieldError("name").getCode());
    }

    @Test
    void emptyCheckForType() {
        var dto1 = create("number", "name", null, "status");
        Errors errs1 = validator.validateObject(dto1);
        assertEquals("validation.unit.create.type", errs1.getFieldError("type").getCode());

        var dto2 = create("number", "name", "", "status");
        Errors errs2 = validator.validateObject(dto2);
        assertEquals("validation.unit.create.type", errs2.getFieldError("type").getCode());
    }

    @Test
    void emptyCheckForStatus() {
        var dto1 = create("number", "name", "type", null);
        Errors errs1 = validator.validateObject(dto1);
        assertEquals("validation.unit.create.status", errs1.getFieldError("status").getCode());

        var dto2 = create("number", "name", "type", "");
        Errors errs2 = validator.validateObject(dto2);
        assertEquals("validation.unit.create.status", errs2.getFieldError("status").getCode());
    }

    DTOUnitCreate create(String number, String name, String type, String status) {
        var dto = new DTOUnitCreate()
                .setNumber(number)
                .setName(name)
                .setType(type)
                .setStatus(status);
        return dto;
    }
    */
//}
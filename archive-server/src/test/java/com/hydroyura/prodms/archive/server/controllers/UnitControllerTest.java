package com.hydroyura.prodms.archive.server.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.prodms.archive.client.dtos.unit.api.response.UnitCreated;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitCreate;
import com.hydroyura.prodms.archive.server.models.exceptions.DataBaseException;
import com.hydroyura.prodms.archive.server.models.exceptions.ValidationException;
import com.hydroyura.prodms.archive.server.services.processors.UnitProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.SimpleErrors;


import static com.hydroyura.prodms.archive.server.controllers.UnitController.UNKNOWN_ERROR;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UnitControllerTest {

    @Mock
    private UnitProcessor processor;

    @InjectMocks
    private UnitController controller;

    private ObjectMapper objectMapper = new ObjectMapper();


    private static DTOUnitCreate createDTOUnitCreate(String number) {
        DTOUnitCreate dto = new DTOUnitCreate()
                .setName("NAME")
                .setNumber(number)
                .setStatus("STATUS")
                .setType("TYPE");
        return dto;
    }

    @Test
    void create_CREATED() throws Exception {
        String number = "NUMBER";
        DTOUnitCreate dto = createDTOUnitCreate(number);
        Mockito
                .when(processor.create(dto))
                .thenReturn(number);

        ResponseEntity<?> response = controller.create(dto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(number, ((UnitCreated) response.getBody()).getNumber());
    }

    @Test
    void create_DATABASE_EXCEPTION() throws Exception {
        String number = "NUMBER";
        DTOUnitCreate dto = createDTOUnitCreate(number);
        Mockito
                .when(processor.create(dto))
                .thenThrow(new DataBaseException(new RuntimeException()));
        assertThrows(DataBaseException.class, () -> controller.create(dto));
    }

    @Test
    void create_VALIDATION_FAILED() throws Exception {
        String number = "NUMBER";
        DTOUnitCreate dto = createDTOUnitCreate(number);
        Mockito
                .when(processor.create(dto))
                .thenThrow(new ValidationException(new SimpleErrors(dto)));
        assertThrows(ValidationException.class, () -> controller.create(dto));
    }

}
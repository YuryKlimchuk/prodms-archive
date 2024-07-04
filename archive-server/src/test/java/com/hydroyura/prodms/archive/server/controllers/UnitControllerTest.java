package com.hydroyura.prodms.archive.server.controllers;

import com.hydroyura.prodms.archive.client.dtos.api.Response;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnit;
import com.hydroyura.prodms.archive.server.services.processors.UnitProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UnitControllerTest {

    @Mock
    private UnitProcessor processor;

    @InjectMocks
    private UnitController controller;

    @Test
    void findOne_OK() {
        String number = "TEST_NUMBER";
        DTOUnit unit = new DTOUnit()
                .setName("NAME")
                .setNumber("NUMBER")
                .setStatus("STATUS")
                .setType("TYPE");

        Mockito
                .when(processor.findOne(number))
                .thenReturn(Optional.of(unit));

        ResponseEntity<Response> response = controller.findOne(number);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(unit, response.getBody().getContent());
        assertEquals(ResponseStatus.SUCCESSFUL.name(), response.getBody().getStatus());
    }

    @Test
    void findOne_INTERNAL_SERVER_ERROR() {
        String number = "TEST_NUMBER";
        DTOUnit unit = new DTOUnit()
                .setName("NAME")
                .setNumber("NUMBER")
                .setStatus("STATUS")
                .setType("TYPE");

        Mockito
                .when(processor.findOne(number))
                .thenReturn(Optional.empty());

        ResponseEntity<Response> response = controller.findOne(number);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("UNKNOWN_ERROR", response.getBody().getContent());
        assertEquals(ResponseStatus.UNSUCCESSFUL.name(), response.getBody().getStatus());
    }

}
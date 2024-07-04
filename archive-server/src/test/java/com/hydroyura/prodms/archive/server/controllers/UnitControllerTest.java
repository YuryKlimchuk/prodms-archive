package com.hydroyura.prodms.archive.server.controllers;

import com.hydroyura.prodms.archive.client.dtos.api.Response;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnit;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitCreate;
import com.hydroyura.prodms.archive.server.services.processors.UnitProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static com.hydroyura.prodms.archive.server.controllers.UnitController.UNKNOWN_ERROR;
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
                .setNumber(number)
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

        Mockito
                .when(processor.findOne(number))
                .thenReturn(Optional.empty());

        ResponseEntity<Response> response = controller.findOne(number);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(UNKNOWN_ERROR, response.getBody().getContent());
        assertEquals(ResponseStatus.UNSUCCESSFUL.name(), response.getBody().getStatus());
    }

    @Test
    void create_CREATED() {
        String number = "NUMBER";
        DTOUnitCreate dto = new DTOUnitCreate()
                .setName("NAME")
                .setNumber(number)
                .setStatus("STATUS")
                .setType("TYPE");

        Mockito
                .when(processor.create(dto))
                .thenReturn(Optional.of(number));

        ResponseEntity<Response> response = controller.create(dto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(number, response.getBody().getContent());
        assertEquals(ResponseStatus.SUCCESSFUL.name(), response.getBody().getStatus());
    }

    @Test
    void create_INTERNAL_SERVER_ERROR() {
        String number = "NUMBER";
        DTOUnitCreate dto = new DTOUnitCreate()
                .setName("NAME")
                .setNumber(number)
                .setStatus("STATUS")
                .setType("TYPE");

        Mockito
                .when(processor.create(dto))
                .thenReturn(Optional.empty());

        ResponseEntity<Response> response = controller.create(dto);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(UNKNOWN_ERROR, response.getBody().getContent());
        assertEquals(ResponseStatus.UNSUCCESSFUL.name(), response.getBody().getStatus());
    }

    @Test
    void delete_OK() {
        String number = "NUMBER";

        Mockito
                .when(processor.delete(number))
                .thenReturn(Boolean.TRUE);

        ResponseEntity<Response> response = controller.delete(number);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(number, response.getBody().getContent());
        assertEquals(ResponseStatus.SUCCESSFUL.name(), response.getBody().getStatus());
    }

    @Test
    void delete_INTERNAL_SERVER_ERROR() {
        String number = "NUMBER";

        Mockito
                .when(processor.delete(number))
                .thenReturn(Boolean.FALSE);

        ResponseEntity<Response> response = controller.delete(number);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(UNKNOWN_ERROR, response.getBody().getContent());
        assertEquals(ResponseStatus.UNSUCCESSFUL.name(), response.getBody().getStatus());
    }
}
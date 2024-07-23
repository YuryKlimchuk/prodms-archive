package com.hydroyura.prodms.archive.server.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.prodms.archive.server.services.processors.UnitProcessor;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/*
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
public class UnitControllerTest {

    @Mock
    private UnitProcessor processor;

    @InjectMocks
    private UnitController controller;

    private ObjectMapper objectMapper = new ObjectMapper();

*/













    /*
    private static DTOUnitCreate createDTOUnitCreate(String number) {
        DTOUnitCreate dto = new DTOUnitCreate()
                .setName("NAME")
                .setNumber(number)
                .setStatus("STATUS")
                .setType("TYPE");
        return dto;
    }

    private static DTOUnit createDTOUnit(String number) {
        DTOUnit dto = new DTOUnit()
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
    */
    /*
    @Test
    void findOne_OK() throws Exception {
        String number = "NUMBER";
        DTOUnit dto = createDTOUnit(number);
        Mockito
                .when(processor.findOne(number))
                .thenReturn(Optional.of(dto));

        ResponseEntity<?> response = controller.findOne(number);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(number, ((DTOUnit) response.getBody()).getNumber());
    }

    @Test
    void findOne_BAD_REQUEST() throws Exception {
        String number = "NUMBER";
        DTOUnit dto = createDTOUnit(number);
        Mockito
                .when(processor.findOne(number))
                .thenThrow(new ValidationException(new SimpleErrors(dto)));
        assertThrows(ValidationException.class, () -> controller.findOne(number));
    }

    @Test
    void findOne_NOT_FOUND() throws Exception {
        String number = "NUMBER";
        DTOUnit dto = createDTOUnit(number);
        Mockito
                .when(processor.findOne(number))
                .thenReturn(Optional.empty());
        ResponseEntity<?> response = controller.findOne(number);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void findOne_DATABASE_EXCEPTION() throws Exception {
        String number = "NUMBER";
        DTOUnit dto = createDTOUnit(number);
        Mockito
                .when(processor.findOne(number))
                .thenThrow(new DataBaseException(new RuntimeException()));
        assertThrows(DataBaseException.class, () -> controller.findOne(number));
    }
    */
//}
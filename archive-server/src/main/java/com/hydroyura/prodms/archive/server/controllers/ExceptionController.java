package com.hydroyura.prodms.archive.server.controllers;

import com.hydroyura.prodms.archive.client.dtos.api.Response;
import com.hydroyura.prodms.archive.server.models.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = ValidationException.class)
    ResponseEntity<Response> validateError(ValidationException ex) {
        Response response = new Response();
        response.setStatus("VALIDATION_FAIL");
        List<String> errors = ex.getErrors().getFieldErrors().stream().map(err -> err.toString()).toList();
        response.setContent(errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}

package com.hydroyura.prodms.archive.server.controllers;

import com.hydroyura.prodms.archive.server.models.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = ValidationException.class)
    ResponseEntity<?> validateError(ValidationException ex) {
        int a = 1;

        ResponseEntity<String> response = new ResponseEntity<>("validation error", HttpStatus.BAD_REQUEST);
        return response;
    }

}

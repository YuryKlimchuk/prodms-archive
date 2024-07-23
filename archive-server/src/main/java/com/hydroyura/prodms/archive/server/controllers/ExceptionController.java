package com.hydroyura.prodms.archive.server.controllers;

import com.hydroyura.prodms.archive.client.dtos.api.ApiError;
import com.hydroyura.prodms.archive.server.models.exceptions.RequestBodyValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = RequestBodyValidationException.class)
    ResponseEntity<ApiError> validateError(RequestBodyValidationException ex) {
        ApiError error = new ApiError();
        error.setDescription("Validation error");
        Set<String> errors = ex.getErrors().getFieldErrors()
                .stream()
                .map(err -> err.toString())
                .collect(Collectors.toSet());
        error.setErrors(errors);
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}

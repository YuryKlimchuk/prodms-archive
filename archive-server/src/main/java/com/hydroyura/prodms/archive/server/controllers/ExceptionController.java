package com.hydroyura.prodms.archive.server.controllers;

import com.hydroyura.prodms.archive.client.ClientErrorRes;
import com.hydroyura.prodms.archive.client.ServerErrorRes;
import com.hydroyura.prodms.archive.server.models.exceptions.InternalServerException;
import com.hydroyura.prodms.archive.server.models.exceptions.RequestBodyValidationException;
import com.hydroyura.prodms.archive.server.models.exceptions.UnitNumberDuplicationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerMapping;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = UnitNumberDuplicationException.class)
    ResponseEntity<ClientErrorRes> unitNumberDuplicationError(UnitNumberDuplicationException e, HttpServletRequest req) {
        ClientErrorRes body = new ClientErrorRes();
        body.setTimestamp(LocalDateTime.now(ZoneId.of("UTC")).toString());
        body.setPath(req.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString());
        body.getErrors().add("Unit with number = [%s] has already existed".formatted(e.getNumber()));
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RequestBodyValidationException.class)
    ResponseEntity<ClientErrorRes> requestBodyValidationError(RequestBodyValidationException e, HttpServletRequest req) {
        ClientErrorRes body = new ClientErrorRes();
        body.setTimestamp(LocalDateTime.now(ZoneId.of("UTC")).toString());
        body.setPath(req.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString());

        Collection<String> errors = e.getErrors().getAllErrors()
                .stream()
                .flatMap(err -> Arrays.stream(err.getCodes()))
                .collect(Collectors.toCollection(ArrayList::new));
        body.getErrors().addAll(errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerException.class)
    ResponseEntity<ServerErrorRes> internalServerError(InternalServerException e, HttpServletRequest req) {
        e.printStackTrace();
        ServerErrorRes body = new ServerErrorRes();
        body.setTimestamp(LocalDateTime.now(ZoneId.of("UTC")).toString());
        body.setPath(req.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString());
        body.setMsg("Unknown server error. Write to email admin@admin");

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

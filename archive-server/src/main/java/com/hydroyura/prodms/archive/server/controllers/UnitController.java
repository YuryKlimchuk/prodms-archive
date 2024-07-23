package com.hydroyura.prodms.archive.server.controllers;

import com.hydroyura.prodms.archive.client.dtos.api.Response;
import com.hydroyura.prodms.archive.client.dtos.unit.api.response.UnitCreated;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnit;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitCreate;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitUpdate;
import com.hydroyura.prodms.archive.client.dtos.unit.filter.FilterUnit;
import com.hydroyura.prodms.archive.client.dtos.unit.request.UnitCreateReq;
import com.hydroyura.prodms.archive.client.dtos.unit.response.UnitCreateRes;
import com.hydroyura.prodms.archive.server.services.processors.UnitProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;


@RestController
@RequestMapping(value = "/v1/units", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class UnitController extends BaseController {

    public static String UNKNOWN_ERROR = "Unknown api error";

    private static final UnitCreateReq UNIT_CREATE_REQ_DEFAULT = new UnitCreateReq();

    @Autowired
    private UnitProcessor unitProcessor;

    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<?> create(@RequestBody Optional<UnitCreateReq> dto) {
        UnitCreateRes body = unitProcessor.create(dto.orElse(UNIT_CREATE_REQ_DEFAULT));
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }















/*
    public ResponseEntity<?> findOne(@PathVariable String number) {
        Optional<DTOUnit> result = unitProcessor.findOne(number);
        return result.isPresent()
                ? new ResponseEntity<>(result.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Response> delete(@PathVariable String number) {
        Boolean result = unitProcessor.delete(number);
        Response response = new Response();
        ResponseEntity<Response> responseEntity;
        if (result) {
            response.setStatus(ResponseStatus.SUCCESSFUL.name());
            response.setContent(number);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus(ResponseStatus.UNSUCCESSFUL.name());
            response.setContent(UNKNOWN_ERROR);
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    public ResponseEntity<Response> findMany(FilterUnit filterUnit) {
        Collection<DTOUnit> result = unitProcessor.findMany(filterUnit);
        Response response = new Response();
        ResponseEntity<Response> responseEntity;
        if (!result.isEmpty()) {
            response.setStatus(ResponseStatus.SUCCESSFUL.name());
            response.setContent(result);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus(ResponseStatus.UNSUCCESSFUL.name());
            response.setContent(UNKNOWN_ERROR);
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    public ResponseEntity<Response> update(@RequestBody DTOUnitUpdate dto, @PathVariable String number) {
        Boolean result = unitProcessor.update(dto);
        Response response = new Response();
        ResponseEntity<Response> responseEntity;
        if (result) {
            response.setStatus(ResponseStatus.SUCCESSFUL.name());
            response.setContent(number);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus(ResponseStatus.UNSUCCESSFUL.name());
            response.setContent(UNKNOWN_ERROR);
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
*/
}
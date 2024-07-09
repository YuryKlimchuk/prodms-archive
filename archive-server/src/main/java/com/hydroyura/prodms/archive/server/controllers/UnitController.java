package com.hydroyura.prodms.archive.server.controllers;

import com.hydroyura.prodms.archive.client.dtos.api.Response;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnit;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitCreate;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitUpdate;
import com.hydroyura.prodms.archive.client.dtos.unit.filter.FilterUnit;
import com.hydroyura.prodms.archive.server.services.processors.UnitProcessor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;


@RestController
@RequestMapping(value = "/api/v1/units")
public class UnitController extends BaseController {

    public static String UNKNOWN_ERROR = "UNKNOWN_ERROR";

    @Autowired
    private UnitProcessor unitProcessor;

    @Operation(summary = "Create new unit")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Unit has been created"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Unit object is not valid"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Server error while handling request"
            )
    })
    @RequestMapping(method = RequestMethod.POST, value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> create(@RequestBody DTOUnitCreate dto) {
        Optional<String> result = unitProcessor.create(dto);
        Response response = new Response();
        ResponseEntity<Response> responseEntity;
        if (result.isPresent()) {
            response.setStatus(ResponseStatus.SUCCESSFUL.name());
            response.setContent(result.get());
            responseEntity = new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            response.setStatus(ResponseStatus.UNSUCCESSFUL.name());
            response.setContent(UNKNOWN_ERROR);
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @Operation(summary = "Find unit by number")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Unit has been found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Number is not valid"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Unit with given number has been not found"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Server error while handling request"
            )
    })
    @RequestMapping(method = RequestMethod.GET, value = {"/{number}"}, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Response> findOne(@PathVariable String number) {
        Optional<DTOUnit> result = unitProcessor.findOne(number);
        Response response = new Response();
        ResponseEntity<Response> responseEntity;
        if (result.isPresent()) {
            response.setStatus(ResponseStatus.SUCCESSFUL.name());
            response.setContent(result.get());
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus(ResponseStatus.UNSUCCESSFUL.name());
            response.setContent(UNKNOWN_ERROR);
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @Operation(summary = "Delete unit by number")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Unit has been deleted"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Number is not valid"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Unit with given number has been not found"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Server error while handling request"
            )
    })
    @RequestMapping(method = RequestMethod.DELETE, value = {"/{number}"}, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.TEXT_PLAIN_VALUE)
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

    @Operation(summary = "Find units by filter")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Units have been found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Filter is not valid"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Units with given number has been not found"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Server error while handling request"
            )
    })
    @RequestMapping(method = RequestMethod.GET, value = "", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @Operation(summary = "Update unit")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Units have been found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Filter is not valid"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Units with given number has been not found"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Server error while handling request"
            )
    })
    @RequestMapping(method = RequestMethod.PUT, value = {"/{number}"}, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
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

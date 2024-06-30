package com.hydroyura.prodms.archive.server.controllers;

import com.hydroyura.prodms.archive.client.dtos.api.Response;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitCreate;
import com.hydroyura.prodms.archive.server.services.processors.UnitProcessor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping(value = "/api/v1/units")
public class UnitController extends BaseController {

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
    @RequestMapping(method = RequestMethod.POST, value = {"", "/"}, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> create(@RequestBody DTOUnitCreate dto) {
        Optional<String> result = unitProcessor.create(dto);

        return null;
    }

}

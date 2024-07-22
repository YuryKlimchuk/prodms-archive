package com.hydroyura.prodms.archive.server.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.hydroyura.prodms.archive.client.dtos.api.Response;
import com.hydroyura.prodms.archive.client.dtos.api.ApiError;
import com.hydroyura.prodms.archive.client.dtos.unit.api.response.UnitCreated;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnit;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitCreate;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitUpdate;
import com.hydroyura.prodms.archive.client.dtos.unit.filter.FilterUnit;
import com.hydroyura.prodms.archive.server.services.processors.UnitProcessor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
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
@RequestMapping(value = "/v1/units")
public class UnitController extends BaseController {

    public static String UNKNOWN_ERROR = "Unknown api error";

    @Autowired
    private UnitProcessor unitProcessor;

    @Operation(summary = "To create a new unit entity")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Successful result",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UnitCreated.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Unit with number = [RGR100-00001] has created",
                                                    value = """
                                                                {
                                                                    "number": "RGR100-00001"
                                                                }
                                                            """
                                            )
                                    }
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Validation failed",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Example of wrong entity (with empty field)",
                                                    value = """
                                                                {
                                                                    "description": "Entity didn't pass validation",
                                                                    "errors": [
                                                                        "Filed [name] mustn't be empty",
                                                                        "Filed [number] mustn't be empty"
                                                                    ]
                                                                }
                                                            """
                                            )
                                    }
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Unknown error while handling request",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Example with unknown error",
                                                    value = """
                                                                {
                                                                    "description": "While handle request error occurred",
                                                                    "errors": []
                                                                }
                                                            """
                                            )
                                    }
                            )
                    }
            )
    })
    @RequestMapping(method = RequestMethod.POST, value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = DTOUnitCreate.class),
            examples = {
                @ExampleObject(
                        name = "With comment",
                        value = """
                                {
                                    "number": "RGR100-00001",
                                    "name": "Pin",
                                    "type": "PART",
                                    "status": "DESIGN",
                                    "comment": "Pin with thread M12x1.5"
                                
                                }
                            """
                ),
                @ExampleObject(
                        name = "Without comment",
                        value = """
                                    {
                                        "number": "RGR100-11001",
                                        "name": "Body",
                                        "type": "PART",
                                        "status": "DESIGN"
                                    }
                                """
                ),
            }

    )) DTOUnitCreate dto) {
        String result = unitProcessor.create(dto);
        UnitCreated created = new UnitCreated().setNumber(result);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Operation(summary = "Find unit by number")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Unit has been found",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = DTOUnit.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Example of unit data",
                                                    value = """
                                                                {
                                                                    "number": "RGR100-00001",
                                                                    "name": "Body",
                                                                    "type": "PART",
                                                                    "status": "DESIGN"
                                                                }
                                                            """
                                            )
                                    }
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Number is not valid",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Example of wrong number",
                                                    value = """
                                                                {
                                                                    "description": "Number didn't pass validation",
                                                                    "errors": [
                                                                        "[number] doesn't correspond to regex"
                                                                    ]
                                                                }
                                                            """
                                            )
                                    }
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Unit with given number has been not found",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Object.class)
                            )
                        }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Unknown error while handling request",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Example with unknown error",
                                                    value = """
                                                                {
                                                                    "description": "While handle request error occurred",
                                                                    "errors": []
                                                                }
                                                            """
                                            )
                                    }
                            )
                    }
            )
    })
    @RequestMapping(method = RequestMethod.GET, value = {"/{number}"}, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> findOne(@PathVariable String number) {
        Optional<DTOUnit> result = unitProcessor.findOne(number);
        return result.isPresent()
                ? new ResponseEntity<>(result.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Delete unit by number")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Deleted"
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
                    description = "Unknown error while handling request"
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
                    responseCode = "500",
                    description = "Unknown error while handling request"
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
                    description = "Updated"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Units with given number has been not found"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Unknown error while handling request"
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

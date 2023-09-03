package com.hydroyura.prodms.archive.restcontrollers;

import com.hydroyura.prodms.archive.dto.DTOPart;
import com.hydroyura.prodms.archive.dto.DTORate;
import com.hydroyura.prodms.archive.restcontrollers.examples.ApiResponseExampleForSuccessGetId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public interface IPartRestController {

    @RequestMapping(value = "/api/v1/parts", method = RequestMethod.GET)
    ResponseEntity<ApiResponse> getAll(@RequestParam(required = false) Map<String, String> filter);

    @RequestMapping(value = "/api/v1/parts", method = RequestMethod.POST)
    ResponseEntity<ApiResponse> createPart(@RequestBody DTOPart item);

    @Operation(
            summary = "Get part by number",
            description = "Get part by number",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Part with present number was found, success operation",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseExampleForSuccessGetId.class)
                            )
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "400",
                            description = "Part with present number was not found"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500")
            })
    @RequestMapping(value = "/api/v1/parts/{number}", method = RequestMethod.GET)
    ResponseEntity<ApiResponse> getItemById(@PathVariable(name = "number") String number);

    @RequestMapping(value = "/api/v1/parts/{number}", method = RequestMethod.DELETE)
    ResponseEntity<ApiResponse> delete(@PathVariable(name = "number") String number);

    @RequestMapping(value = "/api/v1/parts/{number}", method = RequestMethod.PUT)
    ResponseEntity<ApiResponse> update(@PathVariable(name = "number") String number, @RequestBody DTOPart modifiedItem);

    @RequestMapping(value = "/api/v1/parts/{number}/changes", method = RequestMethod.GET)
    ResponseEntity<ApiResponse> getChangesByItemNumber(@PathVariable(name = "number") String number);

    @RequestMapping(value = "/api/v1/parts/{number}/rates", method = RequestMethod.GET)
    ResponseEntity<ApiResponse> getRatesByAssemblyNumber(@PathVariable(name = "number") String number);

    @RequestMapping(value = "/api/v1/parts/{number}/rates", method = RequestMethod.POST)
    ResponseEntity<ApiResponse> createRate(@PathVariable(name = "number") String number, @RequestBody DTORate rate);

    @RequestMapping(value = "/api/v1/parts/{number}/rates/{sub-number}", method = RequestMethod.DELETE)
    ResponseEntity<ApiResponse> deleteRate(@PathVariable(name = "number") String number, @PathVariable(name = "sub-number") String subNumber);

    @RequestMapping(value = "/api/v1/parts/{number}/rates/{sub-number}", method = RequestMethod.PUT)
    ResponseEntity<ApiResponse> changeRateCount(@PathVariable(name = "number") String number, @PathVariable(name = "sub-number") String subNumber, @RequestParam int newCount);

    @RequestMapping(value = "/api/v1/parts/{number}/rates/{sub-number}/replacement", method = RequestMethod.POST)
    ResponseEntity<ApiResponse> addReplacement(@PathVariable(name = "number") String number, @PathVariable(name = "sub-number") String subNumber, @RequestBody String replacementNumber);

    @RequestMapping(value = "/api/v1/parts/{number}/rates/{sub-number}/replacement", method = RequestMethod.PUT)
    ResponseEntity<ApiResponse> updateReplacementNumber(@PathVariable(name = "number") String number, @PathVariable(name = "sub-number") String subNumber, @RequestBody String replacementNumber);

    @RequestMapping(value = "/api/v1/parts/{number}/rates/{sub-number}/replacement", method = RequestMethod.DELETE)
    ResponseEntity<ApiResponse> removeReplacement(@PathVariable(name = "number") String number, @PathVariable(name = "sub-number") String subNumber, @PathVariable(name = "replacement-number") String replacementNumber);

    @RequestMapping(value = "/api/v1/parts/{number}/rates/expanded", method = RequestMethod.GET)
    ResponseEntity<ApiResponse> getExpandedRatesByAssemblyNumber(@PathVariable(name = "number") String number);

    @RequestMapping(value = "/api/v1/parts/{number}/assemblies", method = RequestMethod.GET)
    ResponseEntity<ApiResponse> getAssembliesByPartNumber(@PathVariable(name = "number") String number);

}
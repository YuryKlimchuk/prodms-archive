package com.hydroyura.prodms.archive.restcontrollers;

import com.hydroyura.prodms.archive.dto.DTOPart;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public interface IPartRestController {

    @RequestMapping(value = "/api/v1/parts/{number}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ApiResponse> getItemById(@PathVariable(name = "number") String number);

    @RequestMapping(value = "/api/v1/parts", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ApiResponse> create(@RequestBody DTOPart item);

    @RequestMapping(value = "/api/v1/parts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ApiResponse> getAll(@RequestParam(required = false) Map<String, String> filter);

    @RequestMapping(value = "/api/v1/parts/{number}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ApiResponse> delete(@PathVariable(name = "number") String number);

    @RequestMapping(value = "/api/v1/parts/{number}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ApiResponse> update(@PathVariable(name = "number") String number, @RequestBody DTOPart modifiedItem);

    @RequestMapping(value = "/api/v1/parts/{number}/changes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ApiResponse> getChangesByItemNumber(@PathVariable(name = "number") String number);

    @RequestMapping(value = "/api/v1/parts/{number}/all-rates", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ApiResponse> getAllRatesByAssemblyNumber(@PathVariable(name = "number") String number);

}
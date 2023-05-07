package com.hydroyura.prodms.archive.restcontrollers;

import com.hydroyura.prodms.archive.dto.DTOPart;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface IPartRestController {

    ResponseEntity<ApiResponse> getItemById(@PathVariable(name = "id") String rawId);

    ResponseEntity<ApiResponse> create(@RequestBody DTOPart item);

    ResponseEntity<ApiResponse> getAll(@RequestParam(required = false) Map<String, String> filter);

    ResponseEntity<ApiResponse> delete(@PathVariable(name = "id") String rawId);

    ResponseEntity<ApiResponse> update(@PathVariable(name = "number") String number, @RequestBody DTOPart modifiedItem);

    ResponseEntity<ApiResponse> getChangesByItemNumber(@PathVariable(name = "number") String number);

    ResponseEntity<ApiResponse> getAllRatesByAssemblyNumber(@PathVariable(name = "number") String number);

}

/*


    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") String rawId, @RequestBody DTO modifiedItem);



 */
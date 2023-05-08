package com.hydroyura.prodms.archive.restcontrollers;

import com.hydroyura.prodms.archive.dto.DTOPart;
import com.hydroyura.prodms.archive.dto.DTOPartChange;
import com.hydroyura.prodms.archive.dto.DTORate;
import com.hydroyura.prodms.archive.services.changes.IPartChangeService;
import com.hydroyura.prodms.archive.services.parts.IPartService;
import com.hydroyura.prodms.archive.services.rates.IRateService;
import com.hydroyura.prodms.archive.services.rates.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@RestController
public class PartRestController extends AbstractRestController implements IPartRestController {

    @Autowired @Qualifier(value = "PartService")
    private IPartService<DTOPart, String> partService;

    @Autowired @Qualifier(value = "PartChangeService")
    private IPartChangeService partChangeService;

    @Autowired @Qualifier(value = "RateService")
    private IRateService rateService;

    @Autowired @Qualifier(value = "DTOPartValidator")
    private Validator validator;

    @Override
    public ResponseEntity<ApiResponse> getItemById(String id) {
        logger.warn("Attempt to receive item with ID = {}", id);
        ApiResponse response = new ApiResponse();
        Optional<DTOPart> part;
        try {
            part = partService.getItemById(id);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Error has occurred during fetching data from DB");
            logger.warn("Internal server error while receiving item with ID = {}", id);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(part.isEmpty()) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Entity with current ID was not found");
            logger.warn("Item with ID = {}, => Not found", id);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        response.setStatus(HttpStatus.OK.value());
        response.setMessage("success");
        response.setObject(part.get());
        logger.warn("Item with ID = {} => received from DB successfully", id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse> create(DTOPart item) {
        logger.warn("Attempt to create item with ID = {}", item.getNumber());
        ApiResponse response = new ApiResponse();

        DataBinder dataBinder = new DataBinder(item);
        dataBinder.setValidator(validator);
        dataBinder.validate();

        if(dataBinder.getBindingResult().hasErrors()) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Item has not passed validation");
            logger.warn("Attempt to create item with ID = {}, => validation failed", item.getNumber());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Optional<DTOPart> savedDTO = null;
        try {
            savedDTO = partService.create(item);
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("Item was created successfully");
            response.setObject(savedDTO.get());
            logger.warn("Item with ID = {} => created successfully", item.getNumber());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Error has occurred while creating item");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            logger.warn(" Internal server error while creating item with ID = {}", item.getNumber());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getAll(Map<String, String> filter) {
        logger.warn("Attempt to receive items by filter = {}", filter);
        ApiResponse response = new ApiResponse();
        Collection<DTOPart> items = null;
        try {
            items = partService.getAllByFilter(filter);
        } catch (Exception e) {
            logger.warn("Error has been during request to DB [getAll], exception text => {}", e);
            e.printStackTrace();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            logger.warn(" Internal server error while receiving items by filter");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setStatus(HttpStatus.OK.value());
        response.setObject(items);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin", "*");
        ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(response, responseHeaders, HttpStatus.OK);
        logger.warn("Item with ID = {} => receiving items from DB successfully");
        return responseEntity;
    }

    @Override
    public ResponseEntity<ApiResponse> delete(String number) {
        logger.warn("Attempt to delete item with ID = {}", number);
        ApiResponse response = new ApiResponse();

        Optional<DTOPart> result = partService.delete(number);

        if(result.isEmpty()) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        response.setObject(result.get());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // FIXME: check if exist
    @Override
    public ResponseEntity<ApiResponse> update(String number, DTOPart modifiedItem) {
        logger.warn("Attempt to update item with ID = {}", number);
        ApiResponse response = new ApiResponse();

        DataBinder dataBinder = new DataBinder(modifiedItem);
        dataBinder.setValidator(validator);
        dataBinder.validate();

        if(dataBinder.getBindingResult().hasErrors()) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Object has not passed validation");
            logger.warn("Attempt to update item with ID = {}, => validation failed", modifiedItem.getNumber());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Optional<DTOPart> updatedDTO = null;
        try {
            updatedDTO = partService.update(modifiedItem);
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Object was update successfully");
            response.setObject(updatedDTO.get());
            logger.warn("Item with ID = {} => received from DB successfully", modifiedItem.getNumber());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Error has occurred while update entity to DB");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            logger.warn("Internal server error while update item with ID = {}", modifiedItem.getNumber());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getChangesByItemNumber(String number) {
        logger.warn("Attempt to receive changes for item with ID = {}", number);
        ApiResponse response = new ApiResponse();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Changes success");

        Collection<DTOPartChange> changes = partChangeService.getChanges(number);
        response.setObject(changes);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse> getAllRatesByAssemblyNumber(String number) {
        logger.warn("Attempt to receive all rates for assembly with ID = {}", number);
        ApiResponse response = new ApiResponse();
        Collection<DTORate> rates = rateService.getAllRates(number);

        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Get rates success");
        response.setObject(rates);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}

package com.hydroyura.prodms.archive.restcontrollers;

import com.hydroyura.prodms.archive.data.entities.dto.DTOPart;
import com.hydroyura.prodms.archive.data.entities.dto.DTOPartChange;
import com.hydroyura.prodms.archive.data.entities.dto.DTORate;
import com.hydroyura.prodms.archive.services.changes.IPartChangeService;
import com.hydroyura.prodms.archive.services.parts.IPartService;
import com.hydroyura.prodms.archive.services.rates.IRateService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

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
        ApiResponse response = new ApiResponse();
        Optional<DTOPart> part;
        try {
            part = partService.getItemById(id);
        } catch (Exception e) {
            response.setMessage("SERVER_ERROR");
            logger.info("Internal server error while receiving item with ID = {}", id);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(part.isEmpty()) {
            response.setMessage("PART_NOT_FOUND");
            logger.info("Item with ID = {}, => Not found", id);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        response.setMessage("SUCCESS_OPERATION");
        response.setObject(part.get());
        logger.info("Item with ID = {} => received from DB successfully", id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse> createPart(DTOPart item) {
        logger.warn("Attempt to create item with ID = {}", item.getNumber());
        ApiResponse response = new ApiResponse();

        DataBinder dataBinder = new DataBinder(item);
        dataBinder.setValidator(validator);
        dataBinder.validate();

        if(dataBinder.getBindingResult().hasErrors()) {
            response.setMessage("Item has not passed validation");
            logger.warn("Attempt to create item with ID = {}, => validation failed", item.getNumber());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Optional<DTOPart> savedDTO = null;
        try {
            savedDTO = partService.create(item);
            response.setMessage("Item was created successfully");
            response.setObject(savedDTO.get());
            logger.warn("Item with ID = {} => created successfully", item.getNumber());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Error has occurred while creating item");
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
            logger.warn(" Internal server error while receiving items by filter");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
            response.setMessage("Object has not passed validation");
            logger.warn("Attempt to update item with ID = {}, => validation failed", modifiedItem.getNumber());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Optional<DTOPart> updatedDTO = null;
        try {
            updatedDTO = partService.update(modifiedItem);
            response.setMessage("Object was update successfully");
            response.setObject(updatedDTO.get());
            logger.warn("Item with ID = {} => received from DB successfully", modifiedItem.getNumber());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Error has occurred while update entity to DB");
            logger.warn("Internal server error while update item with ID = {}", modifiedItem.getNumber());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getChangesByItemNumber(String number) {
        logger.warn("Attempt to receive changes for item with ID = {}", number);
        ApiResponse response = new ApiResponse();
        response.setMessage("Changes success");

        Collection<DTOPartChange> changes = partChangeService.getChanges(number);
        response.setObject(changes);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse> getAssembliesByPartNumber(String number) {
        ApiResponse response = new ApiResponse();
        Optional<DTOPart> part = partService.getItemById(number);

        if (part.isEmpty()) {
            response.setMessage("element with id[" + number + "] not found");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Collection<DTOPart> assemblies = rateService.getAssemblies(number);
        response.setMessage("element found");
        response.setObject(assemblies);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse> getRatesByAssemblyNumber(String number) {
        logger.warn("Attempt to receive all rates for assembly with ID = {}", number);
        ApiResponse response = new ApiResponse();
        Collection<DTORate> rates = rateService.getRates(number);
        //TODO: add check if assembly exist
        response.setMessage("Get rates success");
        response.setObject(rates);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse> getAssembliesByElementNumber(String number) {
        logger.warn("Attempt to receive all rates for assembly with ID = {}", number);
        ApiResponse response = new ApiResponse();
        //Collection<DTORate> assemblies = rateService.getAssemblies(number);

        //response.setStatus(HttpStatus.OK.value());
        response.setMessage("Get rates success");
        //response.setObject(assemblies);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // TODO: add logic for bad cases
    @Override
    public ResponseEntity<ApiResponse> createRate(String number, DTORate rate) {
        ApiResponse response = new ApiResponse();
        response.setMessage("SUCCESS");
        response.setObject(rateService.create(number, rate.getElement().getNumber(), rate.getCount()).get());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ApiResponse> deleteRate(String number, String subNumber) {
        ApiResponse response = new ApiResponse();

        if (rateService.delete(number, subNumber)) {
            response.setMessage("SUCCESS_DELETED");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.setMessage("NOT_DELETED");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ApiResponse> changeRateCount(String number, String subNumber, int newCount) {
        ApiResponse response = new ApiResponse();

        if (rateService.changeCount(number, subNumber, newCount)) {
            response.setMessage("SUCCESS_UPDATED_COUNT");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.setMessage("NOT_UPDATED");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ApiResponse> addReplacement(String number, String subNumber, String replacementNumber) {
        ApiResponse response = new ApiResponse();

        if (rateService.addReplacement(number, subNumber, replacementNumber)) {
            response.setMessage("SUCCESS_ADDED_REPLACEMENT");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.setMessage("SERVER_ERROR");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ApiResponse> updateReplacementPriority(String number, String subNumber, String replacementNumber, int priority) {
        ApiResponse response = new ApiResponse();

        rateService.updateReplacementPriority(number, subNumber, replacementNumber, priority);


        response.setMessage("SUCCESS_UPDATED_REPLACEMENT");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse> removeReplacement(String number, String subNumber, String replacementNumber) {
        ApiResponse response = new ApiResponse();

        if (rateService.removeReplacement(number, subNumber, replacementNumber)) {
            response.setMessage("SUCCESS_REMOVED_REPLACEMENT");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.setMessage("SERVER_ERROR");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ApiResponse> getExpandedRatesByAssemblyNumber(String number) {
        return null;
    }

    @ModelAttribute
    private void getRequestLog(HttpServletRequest request) {
        Map<String, Object> pathVars = (Map<String, Object>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String method = request.getMethod();
        String uriPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        String params = request.getQueryString();

        logger.info("Start handling"
                + " [" + method + "] request to ["
                + uriPattern
                + "] with params=["
                + ((params == null) ? "no params" : params)
                + "] and path vars=["
                + ((pathVars == null) ? "no path vars" : pathVars) + "]"
        );
    }

}

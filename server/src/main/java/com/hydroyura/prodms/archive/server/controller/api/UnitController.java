package com.hydroyura.prodms.archive.server.controller.api;

import static com.hydroyura.prodms.archive.server.SharedConstants.REQUEST_ATTR_UUID_KEY;
import static com.hydroyura.prodms.archive.server.SharedConstants.REQUEST_TIMESTAMP_KEY;
import static com.hydroyura.prodms.archive.server.SharedConstants.RESPONSE_ERROR_MSG_ENTITY_NOT_FOUND;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.hydroyura.prodms.archive.client.model.api.ApiRes;
import com.hydroyura.prodms.archive.client.model.req.CreateUnitReq;
import com.hydroyura.prodms.archive.client.model.req.ListUnitsReq;
import com.hydroyura.prodms.archive.client.model.req.PatchUnitReq;
import com.hydroyura.prodms.archive.server.controller.swagger.UnitDocumentedController;
import com.hydroyura.prodms.archive.server.service.UnitService;
import com.hydroyura.prodms.archive.server.validation.ValidationManager;
import com.hydroyura.prodms.archive.server.validation.enums.NumberKey;
import com.hydroyura.prodms.archive.server.validation.model.WrapNumber;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(
    value = "/api/v1/units",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
public class UnitController implements UnitDocumentedController {

    private final ValidationManager validationManager;
    private final UnitService unitService;


    @Override
    @RequestMapping(method = GET, value = "/{number}")
    public ResponseEntity<ApiRes<?>> get(@PathVariable String number, HttpServletRequest request) {
        validationManager.validate(new WrapNumber<>(number, String.class, NumberKey.UNIT), WrapNumber.class);
        var body = buildApiRes(request);
        var data = unitService.get(number);
        if (data.isPresent()) {
            body.setData(data.get());
            return new ResponseEntity<>(body, HttpStatus.OK);
        } else {
            body.getErrors().add(RESPONSE_ERROR_MSG_ENTITY_NOT_FOUND.formatted(number));
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @RequestMapping(method = GET, value = "")
    public ResponseEntity<ApiRes<?>> list(ListUnitsReq req, HttpServletRequest request) {
        validationManager.validate(req, ListUnitsReq.class);
        var body = buildApiRes(request);
        var res = unitService.list(req);
        body.setData(res);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = POST, value = "")
    public ResponseEntity<ApiRes<?>> create(@RequestBody CreateUnitReq req, HttpServletRequest request) {
        validationManager.validate(req, CreateUnitReq.class);
        var body = buildApiRes(request);
        unitService.create(req);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = DELETE, value = "/{number}")
    public ResponseEntity<ApiRes<?>> delete(@PathVariable String number, HttpServletRequest request) {
        validationManager.validate(new WrapNumber<>(number, String.class, NumberKey.UNIT), WrapNumber.class);
        var body = buildApiRes(request);
        var data = unitService.delete(number);
        if (data.isPresent()) {
            return new ResponseEntity<>(body, HttpStatus.OK);
        } else {
            body.getErrors().add(RESPONSE_ERROR_MSG_ENTITY_NOT_FOUND.formatted(number));
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @RequestMapping(method = PATCH, value = "/{number}")
    public ResponseEntity<ApiRes<?>> patch(@PathVariable String number,
                                              @RequestBody PatchUnitReq req,
                                              HttpServletRequest request) {
        validationManager.validate(req, PatchUnitReq.class);
        var body = buildApiRes(request);
        var data = unitService.patch(number, req);
        if (data.isPresent()) {
            return new ResponseEntity<>(body, HttpStatus.OK);
        } else {
            body.getErrors().add(RESPONSE_ERROR_MSG_ENTITY_NOT_FOUND.formatted(number));
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }
    }


    private static UUID extractRequestUUID(HttpServletRequest request) {
        return Optional
            .ofNullable(request.getAttribute(REQUEST_ATTR_UUID_KEY))
            .map(UUID.class::cast)
            .orElseThrow(RuntimeException::new);
    }

    private static Timestamp extractRequestTimestamp(HttpServletRequest request) {
        return Optional
            .ofNullable(request.getAttribute(REQUEST_TIMESTAMP_KEY))
            .map(Timestamp.class::cast)
            .orElseThrow(RuntimeException::new);
    }

    private static <T> ApiRes<T> buildApiRes(HttpServletRequest request) {
        ApiRes<T> apiRes = new ApiRes<>();
        apiRes.setId(extractRequestUUID(request));
        apiRes.setTimestamp(extractRequestTimestamp(request));
        return apiRes;
    }

}

package restcontrollers.examples;

import com.hydroyura.prodms.archive.data.entities.dto.DTOPart;
import restcontrollers.ApiResponse;


public class ApiResponseExampleForSuccessGetId extends ApiResponse {

    public ApiResponseExampleForSuccessGetId() {
        setMessage("SUCCESS_OPERATION");
        setInfo(null);
        DTOPart part = new DTOPart();
        part.setName("TEST_NAME");
        part.setNumber("TEST_NUMBER");
        setObject(part);
    }

}

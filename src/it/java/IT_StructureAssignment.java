import static org.hamcrest.core.Is.is;

import config.BaseClass;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.HttpStatus;
import utils.Endpoint;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IT_StructureAssignment extends BaseClass {

  @Test
  public void test01_DeleteStructureAssignment() {

    Map<String, Object> request = new HashMap<>();
    request.put("transactionType", "SWAP_CHECKS");

    givenHeader()
        .body(writeToString(request))
        .delete(Endpoint.STRUCTURE_ASSIGNMENT.getUrl());

    request.clear();
    request.put("transactionType", "TRANSFER_CASH");

    givenHeader()
        .body(writeToString(request))
        .delete(Endpoint.STRUCTURE_ASSIGNMENT.getUrl());

    request.clear();
    request.put("transactionType", "PAYROLL_CUSTOMER");

    givenHeader()
        .body(writeToString(request))
        .delete(Endpoint.STRUCTURE_ASSIGNMENT.getUrl());

  }
  @Test
  public void test02_CreateNewStructureAssignment() {

    Map<String, Object> request = new HashMap<>();
    request.clear();
    request.put("transactionType", "SWAP_CHECKS");
    request.put("structureType", "QUEUE");

    Response createStructureAssignment = givenHeader()
        .body(writeToString(request))
        .post(Endpoint.STRUCTURE_ASSIGNMENT.getUrl());

    createStructureAssignment.then()
        .statusCode(HttpStatus.OK.value())
        .body("message", is("Structure assignment was created successfully."));
  }

}

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
public class IT_TransactionSuccess extends BaseClass {

  @Test
  public void test01_CreateNewTransaction() {
    Map<String, Object> request = new HashMap<>();
    request.put("name", "ewqeqw");
    request.put("weight", 100);
    request.put("inputDate", "2019-09-09");
    request.put("transactionType", "SWAP_CHECKS");

    Response transactionResponse =  givenHeader()
            .body(writeToString(request))
            .post(Endpoint.TRANSACTION.getUrl());

    transactionResponse.then()
        .statusCode(HttpStatus.OK.value())
        .body("message", is("Transaction was created successfully."));
  }

  @Test
  public void test02_GetTransactionByWeight() {
    Map<String, Object> request = new HashMap<>();
    request.put("param", 100);

    Response transactionResponse =  givenHeader()
        .queryParams(request).when()
        .get(Endpoint.TRANSACTION_WEIGHT.getUrl());

    transactionResponse.then()
        .statusCode(HttpStatus.OK.value())
        .body("[0].weight", is(Integer.valueOf(100)));
  }

  @Test
  public void test03_GetTransactionByType() {
    Map<String, Object> request = new HashMap<>();
    request.put("param", "SWAP_CHECKS");

    Response transactionResponse =  givenHeader()
        .queryParams(request).when()
        .get(Endpoint.TRANSACTION_TYPE.getUrl());

    transactionResponse.then()
        .statusCode(HttpStatus.OK.value())
        .body("[0].transactionType", is("SWAP_CHECKS"));
  }

}

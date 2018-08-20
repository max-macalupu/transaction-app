import static org.hamcrest.core.Is.is;

import config.BaseClass;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import utils.Endpoint;

public class IT_StoreConfig extends BaseClass {

  @Test
  public void testStoreConfig() {
    Map<String, Object> request = new HashMap<>();
    request.put("storageType", "SQL");

    Response updateStorageType = givenHeader()
        .body(writeToString(request))
        .put(Endpoint.CONFIG_STORE.getUrl());

    updateStorageType.then()
        .statusCode(HttpStatus.OK.value())
        .body("message", is("StorageType was updated successfully."))
    ;
  }

}

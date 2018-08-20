package config;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.junit.BeforeClass;

/***
 * This is a base class to support generic functions
 * and constants for all api test.
 */
public class BaseClass {

  @BeforeClass
  public static void setUp() {
    RestAssured.baseURI = "http://127.0.0.1";
    RestAssured.port = 8081;

    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);

  }

  private Map<String, Object> getDefaultHeader() {
    Map<String, Object> headers = new HashMap<>();
    headers.put("X-Requested-With", "XMLHttpRequest");
    headers.put("X-cs-session-id", true);
    headers.put("User-Agent", "iOSAppV2");
    return headers;
  }

  protected synchronized RequestSpecification givenHeader() {
    return given().contentType(ContentType.JSON)
        .headers(getDefaultHeader());
  }

  protected RequestSpecification givenHeader(String sessionId) {
    Map<String, Object> headers = new HashMap<>();
    headers.put("X-Requested-With", "XMLHttpRequest");
    headers.put("User-Agent", "iOSAppV2");
    headers.put("X-cs-session-id", sessionId);

    return given().contentType(ContentType.JSON)
        .headers(headers);
  }
  
  /**
   * This method returns the headers needed for browser based authentication.
   */
  protected RequestSpecification givenHeaderBrowserBased() {
    Map<String, Object> headers = new HashMap<>();
    headers.put("X-Requested-With", "XMLHttpRequest");
    headers.put("User-Agent", "iOSAppV2");
    return given().contentType(ContentType.JSON)
        .headers(headers);
  }
  
  protected RequestSpecification givenMapHeader(Map<String, Object> headerMap) {
    Map<String, Object> headers = new HashMap<>();
    headers.put("X-Requested-With", "XMLHttpRequest");
    headers.put("User-Agent", "iOSAppV2");
    headerMap.forEach((k, v) -> headers.put(k, v));
    return given().contentType(ContentType.JSON)
        .headers(headers);
  }

  private String jsonValueFromMap(Map map) {
    return new JSONObject(map).toJSONString();
  }

  protected String writeToString(Map<String, Object> mapData) {
    return jsonValueFromMap(mapData);
  }
}
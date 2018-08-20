package utils;

public enum Endpoint {

  TRANSACTION("/v1/module/transaction"),
  TRANSACTION_TYPE("/v1/module/transaction/type"),
  TRANSACTION_WEIGHT("/v1/module/transaction/weight"),
  CONFIG_STORE("/v1/configuration/storage"),
  STRUCTURE_ASSIGNMENT("/v1/structure/assignment")

  ;

  private String url;

  Endpoint(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }
}

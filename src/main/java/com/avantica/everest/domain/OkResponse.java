package com.avantica.everest.domain;

public class OkResponse {

  private String message;

  public OkResponse(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}

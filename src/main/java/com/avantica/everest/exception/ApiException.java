package com.avantica.everest.exception;

/***
 * This class is used to manage all custom exception
 * that happens in transaction app.
 */
public class ApiException extends RuntimeException {

  private String message;

  public ApiException(String message) {
    super(message);
    this.message = message;
  }
}

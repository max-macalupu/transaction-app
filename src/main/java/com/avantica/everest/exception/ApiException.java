package com.avantica.everest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/***
 * This class is used to manage all custom exception
 * that happens in transaction app.
 */
@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class ApiException extends RuntimeException {

  private String message;

  public ApiException(String message) {
    super(message);
    this.message = message;
  }
}

package com.avantica.everest.exception;

/***
 * This is a generic exception that is used to handle
 * generic errors.
 */
public class InvalidOperationException extends ApiException {

  public InvalidOperationException(String message) {
    super(message);
  }
}

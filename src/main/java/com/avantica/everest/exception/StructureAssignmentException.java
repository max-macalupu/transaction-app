package com.avantica.everest.exception;

/***
 *
 */
public class StructureAssignmentException extends RuntimeException {

  private String message;

  public StructureAssignmentException(String message) {
    super(message);
    this.message = message;
  }
}

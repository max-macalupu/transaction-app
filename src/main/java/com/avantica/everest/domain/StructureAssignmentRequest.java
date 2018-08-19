package com.avantica.everest.domain;

import com.avantica.everest.model.type.DataStructureType;
import com.avantica.everest.model.type.TransactionType;

/***
 * This class is used to assign some
 * TransactionType with DataStructureType.
 */
public class StructureAssignmentRequest {

  private TransactionType transactionType;
  private DataStructureType structureType;

  public StructureAssignmentRequest() {
  }

  public TransactionType getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(TransactionType transactionType) {
    this.transactionType = transactionType;
  }

  public DataStructureType getStructureType() {
    return structureType;
  }

  public void setStructureType(DataStructureType structureType) {
    this.structureType = structureType;
  }
}
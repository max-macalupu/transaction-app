package com.avantica.everest.domain;

import com.avantica.everest.model.type.TransactionType;

public class GetTransactionRequest {

  private TransactionType transactionType;
  private Integer weight;

  public GetTransactionRequest() {
  }

  public TransactionType getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(TransactionType transactionType) {
    this.transactionType = transactionType;
  }

  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }
}
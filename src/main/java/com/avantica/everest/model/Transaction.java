package com.avantica.everest.model;

import com.avantica.everest.model.type.TransactionType;
import java.io.Serializable;
import java.util.Date;

/***
 * This class is used to mapping the
 * transaction into java class.
 */
public class Transaction implements Serializable {

  private Long id;
  private String name;
  private Integer weight;
  private Date inputDate;
  private TransactionType transactionType;

  public Transaction() {
  }

  public Long getId() { return id;  }

  public void setId(Long id) {  this.id = id; }

  public String getName() { return name;  }

  public void setName(String name) {  this.name = name; }

  public Integer getWeight() {  return weight;  }

  public void setWeight(Integer weight) { this.weight = weight; }

  public Date getInputDate() {  return inputDate; }

  public void setInputDate(Date inputDate) {  this.inputDate = inputDate; }

  public TransactionType getTransactionType() { return transactionType; }

  public void setTransactionType(TransactionType transactionType) {
    this.transactionType = transactionType;
  }

  @Override
  public String toString() {
    return "Transaction{" +
        "name='" + name + '\'' +
        ", weight=" + weight +
        ", inputDate=" + inputDate +
        ", transactionType=" + transactionType +
        '}';
  }
}
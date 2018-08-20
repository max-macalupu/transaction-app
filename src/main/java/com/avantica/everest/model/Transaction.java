package com.avantica.everest.model;

import com.avantica.everest.model.type.TransactionType;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

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

  public Transaction(Long id) {
    this.id = id;
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Transaction that = (Transaction) o;
    return Objects.equals(id, that.id);
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

  /***
   * this class is used to use the builder pattern.
   */
  public static class Builder {
    private Long id;
    private String name;
    private Integer weight;
    private Date inputDate;
    private TransactionType transactionType;

    public Builder id(Long id) {
      this.id = id;
      return this;
    }
    public Builder name(String name) {
      this.name = name;
      return this;
    }
    public Builder weight(Integer weight) {
      this.weight = weight;
      return this;
    }
    public Builder inputDate(Date inputDate) {
      this.inputDate = inputDate;
      return this;
    }
    public Builder transactionType(TransactionType transactionType) {
      this.transactionType = transactionType;
      return this;
    }

    public Transaction build() {
      Transaction transaction = new Transaction();
      transaction.setId(this.id);
      transaction.setTransactionType(this.transactionType);
      transaction.setWeight(this.weight);
      transaction.setName(this.name);
      transaction.setInputDate(this.inputDate);
      return transaction;
    }
  }
}
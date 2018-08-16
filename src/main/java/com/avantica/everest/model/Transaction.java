package com.avantica.everest.model;

import com.avantica.everest.model.type.TransactionType;
import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {

  private String name;
  private Integer weight;
  private Date inputDate;
  private TransactionType transactionType;

}

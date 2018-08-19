package com.avantica.everest.dao;

import com.avantica.everest.model.Transaction;
import java.util.List;

public interface TransactionDao {

  public Transaction create(Transaction transaction);

  public void update(Transaction transaction);

  public void delete(Transaction transaction);

  public List<Transaction> getAllTransactions() ;

  public Transaction findById(Long id);
}

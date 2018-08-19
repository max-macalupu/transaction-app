package com.avantica.everest.dao;

import com.avantica.everest.model.Transaction;
import java.util.List;
import org.springframework.stereotype.Repository;

/***
 * this class is used to manage all transaction using mongo
 * in the dao layer.
 */
@Repository
public class TransactionMongoDaoImpl implements TransactionDao {

  @Override
  public Transaction create(Transaction transaction) {
    return null;
  }

  @Override
  public void update(Transaction transaction) {

  }

  @Override
  public void delete(Transaction transaction) {

  }

  @Override
  public List<Transaction> getAllTransactions() {
    return null;
  }

  @Override
  public Transaction findById(Long id) {
    return null;
  }
}

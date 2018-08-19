package com.avantica.everest.dao;

import com.avantica.everest.model.Transaction;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/***
 * This class is used to manage
 * all transaction in the dao layer using sql.
 */
@Repository
@Qualifier("transactionSql")
public class TransactionSqlDaoImpl implements TransactionDao {

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

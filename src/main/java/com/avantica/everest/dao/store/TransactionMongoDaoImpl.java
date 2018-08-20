package com.avantica.everest.dao.store;

import com.avantica.everest.dao.TransactionDao;
import com.avantica.everest.model.Transaction;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/***
 * this class is used to manage all transaction using mongo
 * in the dao layer.
 */
@Repository
@Qualifier("transactionMongo")
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

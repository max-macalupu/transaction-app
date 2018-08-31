package com.avantica.everest.dao.store;

import com.avantica.everest.dao.TransactionDao;
import com.avantica.everest.model.Transaction;
import com.avantica.everest.model.type.DataStructureType;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/***
 * This class is used to manage
 * all transaction in the dao layer using sql.
 */
@Repository
@Qualifier("transactionSql")
public class TransactionSqlDaoImpl extends AbstractTransactionStore implements TransactionDao {

  @Override
  public Transaction create(Transaction transaction) {
    save(transaction);
    //here we should persist in database
    return transaction;
  }

  @Override
  public void update(Transaction transaction) {

  }

  @Override
  public void delete(Transaction transaction) {

  }

  @Override
  public List<Transaction> getAllTransactions() {
    return getAll();
  }

  @Override
  public Transaction findById(Long id,
      DataStructureType structureType) {
    return super.findById(id, structureType);
  }
}

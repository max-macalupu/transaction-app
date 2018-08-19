package com.avantica.everest.service;

import static java.util.Arrays.asList;
import com.avantica.everest.dao.TransactionDao;
import com.avantica.everest.exception.ApiException;
import com.avantica.everest.model.Transaction;
import com.avantica.everest.model.type.TransactionType;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sun.plugin.dom.core.Node;

/***
 * This class is used to manage all transaction
 * in the service layer.
 */
@Service
public class TransactionService {

  private static final Logger logger  =
      LoggerFactory.getLogger(TransactionService.class);

  @Autowired
  @Qualifier("transactionSql")
  private TransactionDao transactionDao;
  @Autowired
  private StructureAssignmentService structureAssignmentService;

  /***
   * This method is used to find transaction by
   * transaction type.
   * @param transactionType
   * @return
   */
  public List<Transaction> findByTransactionType(TransactionType transactionType) {
    logger.debug("Find Transaction by TransactionType: {}.", transactionType);

    Predicate<Transaction> predicate = x -> transactionType.equals(x.getTransactionType());
    return findByFilters(asList(predicate));
  }

  /***
   * This method is used to find all transactions by
   * weight.
   * @param weight
   * @return
   */
  public List<Transaction> findByWeight(Integer weight) {
    logger.debug("Finding transaction by weight: {}.", weight);

    Predicate<Transaction> predicate = x -> weight.equals(x.getWeight());
    return findByFilters(asList(predicate));
  }

  /***
   * This method is used to find transaction by
   * filters.
   * @param predicateList
   * @return
   */
  public List<Transaction> findByFilters(List<Predicate> predicateList) {
    Predicate predicates = predicateList.stream().reduce(p -> true, Predicate::and);
    List<Transaction> transactionList = transactionDao.getAllTransactions();
    return (List<Transaction>) transactionList
        .stream().filter(predicates)
        .collect(Collectors.toList());
  }

  /***
   * This method is used to create new
   * transaction into dao layer.
   * @param transaction
   * @return
   */
  public Transaction create(Transaction transaction) {
    logger.info("Creating new transaction: {}", transaction);

    transaction = transactionDao.create(transaction);
    return transaction;
  }

  /***
   * This method is used to delete a transaction
   * using the id.
   * @param id
   */
  public void delete(Long id) {
    logger.info("Deleting transaction with id: {}.", id);

    Transaction transaction = transactionDao.findById(id);

    if (transaction == null) {
      logger.error("Transaction with id: {} does not exist.", id);
      throw new ApiException("Transaction does not exist.");
    }

    transactionDao.delete(transaction);
  }
}

package com.avantica.everest.service;

import static java.util.Arrays.asList;
import com.avantica.everest.dao.TransactionDao;
import com.avantica.everest.exception.ApiException;
import com.avantica.everest.model.Transaction;
import com.avantica.everest.model.type.DataStructureType;
import com.avantica.everest.model.type.TransactionType;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
  @Autowired
  private StorageConfigService storageConfigService;

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
   * This method is used to return all transaction from
   * dao layer.
   * @return
   */
  public List<Transaction> getPendingTransaction() {
    return transactionDao.getAllTransactions();
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
    if (CollectionUtils.isEmpty(transactionList)) return new ArrayList<>();
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

    if (storageConfigService.getCurrentStorage() == null) {
      throw new ApiException("Storage configuration has not been created yed.");
    }

    transaction = transactionDao.create(transaction);
    return transaction;
  }

  /***
   * This method is used to delete a transaction
   * using the id.
   * @param id
   * @param transactionType
   */
  public void delete(Long id, TransactionType transactionType) {
    logger.info("Deleting transaction with id: {}.", id);

    DataStructureType structureType = structureAssignmentService.findByType(transactionType);
    Transaction transaction = transactionDao.findById(id, structureType);

    if (transaction == null) {
      logger.error("Transaction with id: {} does not exist.", id);
      throw new ApiException("Transaction does not exist.");
    }

    transactionDao.delete(transaction);
  }
}

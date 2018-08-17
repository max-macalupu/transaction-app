package com.avantica.everest.service;

import com.avantica.everest.dao.StructureAssignmentDao;
import com.avantica.everest.exception.StructureAssignmentException;
import com.avantica.everest.model.Transaction;
import com.avantica.everest.model.type.DataStructureType;
import com.avantica.everest.model.type.TransactionType;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * This class is used to manage the Transaction type
 * with the Data structure.
 */
@Service
public class StructureAssignmentService {

  private static final Logger logger = LoggerFactory.getLogger(StructureAssignmentService.class);

  @Autowired
  private StructureAssignmentDao structureAssignmentDao;

  @Autowired
  private TransactionService transactionService;

  /***
   * This method is to create new structure assignment.
   * @param transactionType
   * @param structureType
   */
  public void create(TransactionType transactionType, DataStructureType structureType) {
    structureAssignmentDao.create(transactionType, structureType);
  }

  /***
   * This method is used to delete the transaction type.
   * @param transactionType
   */
  public void delete(TransactionType transactionType) {
    structureAssignmentDao.delete(transactionType);
  }

  /***
   * This method is used to update the transaction type.
   * @param transactionType
   * @param structureType
   */
  public void update(TransactionType transactionType, DataStructureType structureType) {
    List<Transaction> transactionList = transactionService.findTransactionByType(transactionType);
    if (transactionList.isEmpty()) {
      structureAssignmentDao.update(transactionType, structureType);
    }

    logger.error("Structure assignment can not be update due to pending transaction already exist. "
        + "TransactionType: {}, PendingTransactions: {}.", transactionType, transactionList.size());
    throw new StructureAssignmentException("Structure assignment can not be update because "
        + "there are pending transactions.");
  }

  /***
   * This method is used to list all structure assignments.
   * @return
   */
  public Map list() {
    return structureAssignmentDao.list();
  }

  /***
   * This method is used to return DataStructureType from transactionType.
   * @param transactionType
   * @return
   */
  public DataStructureType findBy(TransactionType transactionType) {
    return (DataStructureType) list().get(transactionType);
  }
}
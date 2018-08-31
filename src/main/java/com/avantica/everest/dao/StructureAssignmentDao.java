package com.avantica.everest.dao;

import static org.springframework.util.ObjectUtils.isEmpty;

import com.avantica.everest.exception.ApiException;
import com.avantica.everest.model.type.DataStructureType;
import com.avantica.everest.model.type.TransactionType;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/***
 * This class is used to persist the
 * information into the data access object.
 */
@Repository
public class StructureAssignmentDao {

  private static final Logger logger = LoggerFactory.getLogger(StructureAssignmentDao.class);

  /**
   * This Map store all the transactionType with his own DataStructure.
   */
  private ConcurrentHashMap<TransactionType, DataStructureType> structureAssignmentStore;

  @PostConstruct
  public void init() {
    structureAssignmentStore = new ConcurrentHashMap<>();
  }

  /***
   *
   * @param txType
   * @param structureType
   */
  public TransactionType create(TransactionType txType, DataStructureType structureType) {
    logger.debug("Creating new StructureAssignment, TxType: {}, StructureType: {}",
        txType, structureType);

    if (exists(txType)) {
      logger.error("Error creating new Structure assignment, TxType already exist: {}.",
          txType);
      throw new ApiException("You can not create Structure assignment "
          + "because they already exist.");
    }

    structureAssignmentStore.put(txType, structureType);
    return txType;
  }

  /***
   * This method is used to update the structure assignment.
   * @param txType
   * @param structureType
   */
  public TransactionType update(TransactionType txType, DataStructureType structureType) {
    logger.info("Structure assignment is updated, txType: {}, structureType: {}.",
        txType, structureType);

    if (isEmpty(txType) || isEmpty(structureType)) {
      logger.error("Error updating Structure assignment, values should not be null. TxType: {}, "
          + "DataStructure: {}.", txType, structureType);
      throw new ApiException("Invalid parameters, TxType and DataStructureType "
          + "should not be null.");
    }

    if (!exists(txType)) {
      logger.error("Error updating Structure assignment, TxType does not exist in the store: {}."
          , txType);
      throw new ApiException("Transaction type can not be "
          + "updated because does not exist in the store yet.");
    }

    structureAssignmentStore.put(txType, structureType);
    return txType;
  }

  /***
   * This method is used to delete Structure assignment.
   * @param transactionType
   */
  public TransactionType delete(TransactionType transactionType) {
    logger.info("Structure assignment gonna be deleted, TxType: {}.", transactionType);
    if (transactionType == null) {
      structureAssignmentStore.clear();
      return transactionType;
    }

    if (!exists(transactionType)) {
      logger.error("Structure assignment can not be delete because it does not exist. TxType: {}."
          , transactionType);
      throw new ApiException("Structure assignment can not be delete due to "
          + "it not exist in the store.");
    }

    structureAssignmentStore.remove(transactionType);
    return transactionType;
  }

  /***
   * This maps return all structure assignment store.
   * @return
   */
  public Map list() {
    return structureAssignmentStore;
  }


  /***
   * This method is being used to check if transaction type already
   * exist or not in the structure assignment store.
   * @param transactionType
   * @return
   */
  private boolean exists(TransactionType transactionType) {
    return this.structureAssignmentStore.get(transactionType) != null;
  }
}
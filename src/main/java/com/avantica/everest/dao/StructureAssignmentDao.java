package com.avantica.everest.dao;

import com.avantica.everest.exception.StructureAssignmentException;
import com.avantica.everest.model.type.DataStructureType;
import com.avantica.everest.model.type.TransactionType;
import java.util.HashMap;
import java.util.Map;
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
  private Map<TransactionType, DataStructureType> structureAssignmentStore;

  @PostConstruct
  public void init() {
    structureAssignmentStore = new HashMap<>();
  }

  /***
   *
   * @param txType
   * @param structureType
   */
  public void create(TransactionType txType, DataStructureType structureType) {
    logger.debug("Creating new StructureAssignment, TxType: {}, StructureType: {}",
        txType, structureType);

    if (existStructureAssignment(txType)) {
      logger.error("Error creating new Structure assignment, TxType already exist: {}.",
          txType);
      throw new StructureAssignmentException("You can not create Structure assignment "
          + "because they already exist.");
    }
  }


  private boolean existStructureAssignment(TransactionType transactionType) {
    return this.structureAssignmentStore.get(transactionType) != null;
  }

}

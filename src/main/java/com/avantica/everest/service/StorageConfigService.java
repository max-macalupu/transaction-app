package com.avantica.everest.service;

import com.avantica.everest.exception.ApiException;
import com.avantica.everest.model.Transaction;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/***
 * This class is used to manage if
 * store is made with Sql or mongodb.
 */
@Service
public class StorageConfigService {

  private static final Logger logger = LoggerFactory.getLogger(StorageConfigService.class);


  @Autowired
  private TransactionService transactionService;

  /**
   * This enum is used to storage
   * the current persistence type.
   */
  public enum StorageType {
    SQL, MONGO;
  }

  private StorageType storageType;

  /***
   * This method is used to update
   * store type
   * @param storageType
   * @return
   */
  public StorageType update(StorageType storageType) {
    List<Transaction> pendingTransactions = transactionService.getPendingTransaction();
    if (!CollectionUtils.isEmpty(pendingTransactions)) {
      logger.error("Storage can not be updated, pending transaction already exists.");
      throw new ApiException("Storage can not be updated, pending transaction already exists.");
    }
    this.storageType = storageType;
    return this.storageType;
  }

  /***
   * This method is used to clean the storage.
   */
  public void cleanStorage() {
    List<Transaction> pendingTransactions = transactionService.getPendingTransaction();
    if (!CollectionUtils.isEmpty(pendingTransactions)) {
      logger.error("Storage can not be cleaned, pending transaction already exists.");
      throw new ApiException("Storage can not be cleaned, pending transaction already exists.");
    }
    this.storageType = null;
  }

  /**
   * This method is used to get the current storage.
   * @return
   */
  public StorageType getCurrentStorage() {
    return this.storageType;
  }

  /***
   * This method is used to return all
   * storage list.
   * @return
   */
  public List<StorageType> getStorageList() {
    return Arrays.asList(StorageType.values());
  }
}

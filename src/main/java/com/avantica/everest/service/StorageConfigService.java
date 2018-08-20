package com.avantica.everest.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

/***
 * This class is used to manage if
 * store is made with Sql or mongodb.
 */
@Service
public class StorageConfigService {

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
    this.storageType = storageType;
    return this.storageType;
  }

  /***
   * This method is used to clean the storage.
   */
  public void cleanStorage() {
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

package com.avantica.everest.domain;

import com.avantica.everest.service.StorageConfigService.StorageType;

/***
 * This class is used to send storage configuration
 * in the request.
 */
public class StorageConfigRequest {

  private StorageType storageType;

  public StorageConfigRequest() {
  }

  public StorageType getStorageType() {
    return storageType;
  }

  public void setStorageType(
      StorageType storageType) {
    this.storageType = storageType;
  }
}

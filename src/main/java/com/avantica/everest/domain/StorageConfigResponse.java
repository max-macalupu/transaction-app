package com.avantica.everest.domain;

import com.avantica.everest.service.StorageConfigService.StorageType;

/***
 * This class is used to return the
 * storage information;
 */
public class StorageConfigResponse {

  private StorageType storageType;

  public StorageConfigResponse(
      StorageType storageType) {
    this.storageType = storageType;
  }

  public StorageType getStorageType() {
    return storageType;
  }

  public void setStorageType(
      StorageType storageType) {
    this.storageType = storageType;
  }
}

package com.avantica.everest.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.avantica.everest.service.StorageConfigService.StorageType;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class StoreConfigurationTest {

  @Autowired
  private StorageConfigService storageConfigService;

  @Test
  public void testGetCurrentStoreAfterUpdateItShouldBeNotEmpty() {
    StorageType storeType = StorageType.SQL;
    storageConfigService.update(storeType);

    StorageType storageType = storageConfigService.getCurrentStorage();
    assertNotNull(storageType);
    assertEquals(storageType, StorageType.SQL);
  }

  @Test
  public void testCleanStorageShouldReturnEmpty() {
    StorageType storeType = StorageType.MONGO;
    storageConfigService.update(storeType);

    storageConfigService.cleanStorage();

    StorageType storageType = storageConfigService.getCurrentStorage();
    assertNull(storageType);
  }

  @Test
  public void testGetAllStorageListShouldNotBeEmpty() {
    List<StorageType> storageTypeList = storageConfigService.getStorageList();

    assertNotNull(storageTypeList);
    assertEquals(storageTypeList.get(0), StorageType.SQL);
    assertEquals(storageTypeList.get(1), StorageType.MONGO);
  }
}
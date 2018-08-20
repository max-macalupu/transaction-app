package com.avantica.everest.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.avantica.everest.service.StoreConfigurationService.StorageType;
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
  private StoreConfigurationService storeConfigurationService;

  @Test
  public void testGetCurrentStoreAfterUpdateItShouldBeNotEmpty() {
    StorageType storeType = StorageType.SQL;
    storeConfigurationService.update(storeType);

    StorageType storageType = storeConfigurationService.getCurrentStorage();
    assertNotNull(storageType);
    assertEquals(storageType, StorageType.SQL);
  }

  @Test
  public void testCleanStorageShouldReturnEmpty() {
    StorageType storeType = StorageType.MONGO;
    storeConfigurationService.update(storeType);

    storeConfigurationService.cleanStorage();

    StorageType storageType = storeConfigurationService.getCurrentStorage();
    assertNull(storageType);
  }
}
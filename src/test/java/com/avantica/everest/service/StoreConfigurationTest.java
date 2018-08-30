package com.avantica.everest.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.avantica.everest.exception.ApiException;
import com.avantica.everest.model.Transaction;
import com.avantica.everest.service.StorageConfigService.StorageType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StoreConfigurationTest {

  @InjectMocks
  private StorageConfigService storageConfigService;
  @Mock
  private TransactionService transactionService;
  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  /***
   * This test is used when storage needs be updated
   * and pending transactions are empty.
   */
  @Test
  public void testUpdateStorageAndNotPendingTransaction() {

    List<Transaction> emptyMockTransactions = new ArrayList<>();
    when(transactionService.getPendingTransaction()).thenReturn(emptyMockTransactions);

    StorageType storeType = StorageType.SQL;
    storageConfigService.update(storeType);

    StorageType storageType = storageConfigService.getCurrentStorage();
    assertNotNull(storageType);
    assertEquals(storageType, StorageType.SQL);
    verify(transactionService, times(1)).getPendingTransaction();
  }

  /***
   * This test is used when storage needs be updated and
   * there are pending transaction yet.
   */
  @Test
  public void testUpdateStorageAndPendingTransaction() {
    expectedException.expect(ApiException.class);
    expectedException.expectMessage("Storage can not be updated, pending transaction already exists.");

    List<Transaction> mockTransactions = getPendingTransactionMock();
    when(transactionService.getPendingTransaction()).thenReturn(mockTransactions);

    StorageType storageType = StorageType.SQL;
    storageConfigService.update(storageType);
  }

  /***
   * This method is used to return transaction list as mock.
   * @return
   */
  private List<Transaction> getPendingTransactionMock() {
    Transaction transaction = new Transaction.Builder().weight(100).build();
    Transaction transaction_2 = new Transaction.Builder().weight(100).build();
    Transaction transaction_3 = new Transaction.Builder().weight(100).build();
    return Arrays.asList(transaction, transaction_2, transaction_3);
  }

  /***
   * This test is used when storage needs be cleaned and
   * pending transactions are empty.
   */
  @Test
  public void testCleanStorageAndEmptyTransactions() {
    List<Transaction> emptyTransactions = new ArrayList<>();
    when(transactionService.getPendingTransaction())
        .thenReturn(emptyTransactions);

    StorageType storeType = StorageType.MONGO;
    storageConfigService.update(storeType);

    storageConfigService.cleanStorage();

    StorageType storageType = storageConfigService.getCurrentStorage();
    assertNull(storageType);
    verify(transactionService, times(2))
        .getPendingTransaction();
  }

  /***
   * This test is used to check the clean storage when
   * there are pending transactions yet.
   */
  @Test
  public void testCleanStorageAndPendingTransactions() {
    expectedException.expect(ApiException.class);
    expectedException.expectMessage("Storage can not be cleaned, pending transaction already exists.");

    List<Transaction> mockTransactions = getPendingTransactionMock();
    when(transactionService.getPendingTransaction())
        .thenReturn(mockTransactions);

    storageConfigService.cleanStorage();
  }

  /***
   * This test is used to fetch all storage in order
   * to the user can choice the best one.
   */
  @Test
  public void testGetAllStorageListShouldNotBeEmpty() {
    List<StorageType> storageTypeList = storageConfigService.getStorageList();

    assertNotNull(storageTypeList);
    assertEquals(storageTypeList.get(0), StorageType.SQL);
    assertEquals(storageTypeList.get(1), StorageType.MONGO);
  }
}
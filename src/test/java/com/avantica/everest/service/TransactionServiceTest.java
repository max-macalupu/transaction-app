package com.avantica.everest.service;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.avantica.everest.dao.TransactionDao;
import com.avantica.everest.exception.ApiException;
import com.avantica.everest.model.Transaction;
import com.avantica.everest.model.type.TransactionType;
import com.avantica.everest.service.StorageConfigService.StorageType;
import java.util.Date;
import java.util.List;
import org.junit.Before;
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
public class TransactionServiceTest {

  @InjectMocks
  private TransactionService transactionService;
  @Mock
  private TransactionDao transactionDao;
  @Mock
  private StorageConfigService storageConfigService;
  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Before
  public void init() {
    Transaction transactionMock = getTransactionMock();
    when(transactionDao.create(any())).thenReturn(transactionMock);
  }

  private Transaction getTransactionMock() {
    Transaction transaction = new Transaction();
    transaction.setInputDate(new Date());
    transaction.setId(100L);
    transaction.setName("Transaction_name");
    transaction.setWeight(50);
    return transaction;
  }

  @Test
  public void testCreateTransactionShouldCallCreateDao() {
    when(storageConfigService.getCurrentStorage()).thenReturn(StorageType.SQL);
    Transaction transaction = new Transaction();
    transaction = transactionService.create(transaction);

    verify(transactionDao, times(1)).create(any());

    assertNotNull(transaction);
    assertEquals(transaction.getId(), new Long(100L));
    assertEquals(transaction.getName(), "Transaction_name");
    assertEquals(transaction.getWeight(), new Integer(50));
  }

  @Test(expected = ApiException.class)
  public void testDeleteTransactionNotExistShouldThrowException() {
    when(transactionDao.findById(any(), any())).thenReturn(null);
    transactionService.delete(100L, TransactionType.SWAP_CHECKS);
  }

  @Test
  public void testDeleteTransactionShouldCallDeleteDaoMethod() {
    Transaction transactionMock = new Transaction();
    when(transactionDao.findById(any(), any())).thenReturn(transactionMock);

    transactionService.delete(100L, TransactionType.SWAP_CHECKS);

    verify(transactionDao, times(1)).delete(any());
  }

  @Test
  public void testFindByTypeShouldReturnTransactionsWithUniqueType() {
    List<Transaction> mockTransactionList = getTransactionsMock();
    when(transactionDao.getAllTransactions()).thenReturn(mockTransactionList);

    TransactionType type = TransactionType.TRANSFER_CASH;

    List<Transaction> transactionList = transactionService.findByTransactionType(type);

    Transaction transactionResult = transactionList
        .stream()
        .findAny()
        .get();

    assertNotNull(transactionList);
    assertThat(transactionList.size(), greaterThanOrEqualTo(0));
    assertEquals(transactionResult.getTransactionType(), TransactionType.TRANSFER_CASH);

  }

  @Test
  public void testFindByWeightShouldReturnTransactionWith50Weight() {
    List<Transaction> mockTransactionList = getTransactionsMock();
    when(transactionDao.getAllTransactions()).thenReturn(mockTransactionList);
    List<Transaction> transactionList = transactionService.findByWeight(50);

    Transaction transactionResult = transactionList
        .stream()
        .findAny().get();

    assertNotNull(transactionResult);
    assertThat(transactionList.size(), greaterThanOrEqualTo(0));
    assertEquals(transactionResult.getWeight(), new Integer(50));
  }

  private List<Transaction> getTransactionsMock() {

    Transaction transaction = new Transaction();
    transaction.setId(10L);
    transaction.setWeight(10);
    transaction.setTransactionType(TransactionType.TRANSFER_CASH);

    Transaction transaction_2 = new Transaction();
    transaction_2.setId(10L);
    transaction_2.setWeight(10);
    transaction_2.setTransactionType(TransactionType.PAYROLL_CUSTOMER);

    Transaction transaction_3 = new Transaction();
    transaction_3.setId(10L);
    transaction_3.setWeight(10);
    transaction_3.setTransactionType(TransactionType.SWAP_CHECKS);

    Transaction transaction_4 = new Transaction();
    transaction_4.setId(10L);
    transaction_4.setWeight(50);
    transaction_4.setTransactionType(TransactionType.SWAP_CHECKS);

    return asList(transaction, transaction_2, transaction_3, transaction_4);
  }
}

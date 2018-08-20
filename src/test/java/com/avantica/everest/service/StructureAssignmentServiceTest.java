package com.avantica.everest.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.avantica.everest.dao.StructureAssignmentDao;
import com.avantica.everest.exception.ApiException;
import com.avantica.everest.model.Transaction;
import com.avantica.everest.model.type.DataStructureType;
import com.avantica.everest.model.type.TransactionType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class StructureAssignmentServiceTest {

  @InjectMocks
  private StructureAssignmentService structureAssignmentService;
  @Mock
  private StructureAssignmentDao structureAssignmentDao;
  @Mock
  private TransactionService transactionService;

  @Before
  public void init() {
    TransactionType transactionTypeMock = mockTransactionType();
    when(structureAssignmentDao.create(any(), any())).thenReturn(transactionTypeMock);
  }

  @Test
  public void testCreateNewStructureAssignmentTest() {
    TransactionType transactionType = TransactionType.PAYROLL_CUSTOMER;
    DataStructureType structureType = DataStructureType.BINARY_TREE;

    TransactionType transactionType_ = structureAssignmentService.create(transactionType, structureType);
    verify(structureAssignmentDao, times(1)).create(any(), any());
    assertNotNull(transactionType_);
    assertEquals(transactionType_, TransactionType.PAYROLL_CUSTOMER);
  }

  @Test
  public void testDeleteStructureAssignmentTest() {
    when(structureAssignmentDao.delete(any())).thenReturn(TransactionType.PAYROLL_CUSTOMER);
    TransactionType transactionType_ = TransactionType.PAYROLL_CUSTOMER;

    TransactionType transactionType = structureAssignmentService.delete(transactionType_);

    verify(structureAssignmentDao, times(1)).delete(any());
    assertNotNull(transactionType);
    assertEquals(transactionType, TransactionType.PAYROLL_CUSTOMER);
  }

  @Test(expected = ApiException.class)
  public void testUpdateStructureAssignmentWithPendingTransactionShouldReturnException() {
    List<Transaction> mockTransactions = mockTransactionList();
    when(transactionService.findByTransactionType(any())).thenReturn(mockTransactions);

    TransactionType transactionType = TransactionType.SWAP_CHECKS;
    DataStructureType structureType = DataStructureType.BINARY_TREE;
    structureAssignmentService.update(transactionType, structureType);
    verify(structureAssignmentDao, times(1)).update(any(), any());
  }

  @Test
  public void testUpdateStructureAssignmentWithEmptyTransactionShouldPassSuccess() {
    ArrayList<Transaction> emptyList = new ArrayList<>();
    when(transactionService.findByTransactionType(any())).thenReturn(emptyList);

    TransactionType transactionType = TransactionType.SWAP_CHECKS;
    DataStructureType structureType = DataStructureType.BINARY_TREE;
    TransactionType type = structureAssignmentService.update(transactionType, structureType);

    verify(structureAssignmentDao, times(1)).update(any(), any());
    assertNotNull(type);
    assertEquals(type, TransactionType.SWAP_CHECKS);
  }

  @Test
  public void testListStructureAssignmentShouldReturnData() {
    Map emptyMap = new HashMap();
    when(structureAssignmentDao.list()).thenReturn(emptyMap);

    Map map = structureAssignmentService.list();

    verify(structureAssignmentDao, times(1)).list();
    assertNotNull(map);
  }

  private List<Transaction> mockTransactionList() {
    Transaction transaction = new Transaction();
    transaction.setTransactionType(TransactionType.SWAP_CHECKS);
    transaction.setWeight(10);
    transaction.setId(100L);
    transaction.setName("transaction_name");
    transaction.setInputDate(new Date());
    return Arrays.asList(transaction);
  }

  private TransactionType mockTransactionType() {
    return TransactionType.PAYROLL_CUSTOMER;
  }
}
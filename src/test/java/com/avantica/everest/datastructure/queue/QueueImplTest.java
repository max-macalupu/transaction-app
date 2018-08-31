package com.avantica.everest.datastructure.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import com.avantica.everest.model.Transaction;
import com.avantica.everest.model.type.TransactionType;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class QueueImplTest {

  private Queue<Transaction> queue;

  @Before
  public void init() {
    queue = new QueueImpl<Transaction>();
  }

  @Test
  public void queueTransactionShouldBeAsPeek() {
    Transaction mockTransaction = getMockTransaction();
    queue.enqueue(mockTransaction);

    Transaction transaction = queue.peek();

    assertNotNull(transaction);
    assertEquals(transaction.getId(), new Long(100L));
    assertEquals(transaction.getWeight(), new Integer(10));
    assertEquals(transaction.getName(), "transaction_mock");
    assertEquals(transaction.getTransactionType(), TransactionType.SWAP_CHECKS);
  }

  @Test(expected = NoSuchElementException.class)
  public void testPeekEmptyQueueShouldReturnException() {
    Transaction mockTransaction = getMockTransaction();
    queue.enqueue(mockTransaction);
    Transaction transaction = queue.dequeue();

    assertNotNull(mockTransaction);
    assertEquals(transaction.getId(), new Long(100L));
    assertEquals(transaction.getWeight(), new Integer(10));
    assertEquals(transaction.getName(), "transaction_mock");
    assertEquals(transaction.getTransactionType(), TransactionType.SWAP_CHECKS);

    queue.peek();
  }

  @Test
  public void testListAllQueueAfterMakeSomeInsertionsShouldReturnNotEmpty() {
    queue.enqueue(getMockTransaction());
    queue.enqueue(getMockTransaction());
    queue.enqueue(getMockTransaction());

    List<Transaction> transactionList = Arrays.asList(queue.list());
    assertNotNull(transactionList);
    assertEquals(transactionList.size(), 3);
  }

  @Test
  public void testFindTransactionAfterBeEnqueueShouldBeFound() {
    queue.enqueue(getMockTransaction());

    Transaction transaction = new Transaction.Builder().weight(10).build();
    Transaction foundTransaction = queue.find(transaction);

    assertNotNull(foundTransaction);
    assertEquals(foundTransaction.getWeight(), new Integer(10));
    assertEquals(foundTransaction.getName(), "transaction_mock");
    assertEquals(foundTransaction.getTransactionType(), TransactionType.SWAP_CHECKS);
  }

  private Transaction getMockTransaction() {
    Transaction transaction = new Transaction();
    transaction.setInputDate(new Date());
    transaction.setName("transaction_mock");
    transaction.setWeight(10);
    transaction.setId(100L);
    transaction.setTransactionType(TransactionType.SWAP_CHECKS);
    return transaction;
  }
}
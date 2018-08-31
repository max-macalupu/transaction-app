package com.avantica.everest.datastructure.tree;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import com.avantica.everest.exception.InvalidOperationException;
import com.avantica.everest.model.Transaction;
import com.avantica.everest.model.type.TransactionType;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TreeImplTest {

  private BinaryTree<Transaction> binaryTree;
  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Before
  public void init() {
    this.binaryTree = new BinaryTreeImpl();
  }

  /***
   * This test is used when new transaction is inserted in
   * binary tree.
   */
  @Test
  public void insertTransactionTree() {
    Transaction transactionMock = getTransactionMock();
    binaryTree.insert(transactionMock);

    Transaction transaction = new Transaction.Builder().weight(100).build();
    transaction = binaryTree.find(transaction);

    assertNotNull(transaction);
    assertEquals(transaction.getTransactionType(), TransactionType.SWAP_CHECKS);
    assertEquals(transaction.getName(), "transactionOne");

  }

  /***
   * This test is used when duplicated transactions are
   * being inserted in the binary tree, so invalidException
   * is expected.
   */
  @Test
  public void insertDuplicateElementsShouldThrowInvalidException() {
    expectedException.expect(InvalidOperationException.class);
    expectedException.expectMessage("Invalid operation, Object to be inserted already "
          + "exist in the tree.");
    Transaction transaction = getTransactionMock();
    Transaction duplicatedTransaction = getTransactionMock();

    binaryTree.insert(transaction);
    binaryTree.insert(duplicatedTransaction);
  }

  /***
   * This test is used when transacions is deleted
   * from the binary tree.
   */
  @Test
  public void deleteTransactionTree() {
    Transaction transaction = new Transaction.Builder().weight(100).build();
    Transaction secondTransaction = new Transaction.Builder().weight(200).build();
    Transaction thirdTransaction = new Transaction.Builder().weight(300).build();
    Transaction fourthTransaction = new Transaction.Builder().weight(400).build();

    binaryTree.insert(transaction);
    binaryTree.insert(secondTransaction);
    binaryTree.insert(thirdTransaction);
    binaryTree.insert(fourthTransaction);

    binaryTree.delete(secondTransaction);
    binaryTree.delete(fourthTransaction);

    List<Transaction> transactionList = binaryTree.findAll();

    assertNotNull(transactionList);
    assertThat(transactionList.size(), is(2));
    assertThat(transactionList.get(0).getWeight(), is(100));
    assertThat(transactionList.get(1).getWeight(), is(300));
  }


  /***
   * This test is used when multiple transactions are being
   * inserted then some of them are deleted then another ones
   * are being inserted and one is deleted.
   */
  @Test
  public void deleteTransactionWithMultipleDeletes() {
    Transaction transaction = new Transaction.Builder().weight(650).build();
    Transaction secondTransaction = new Transaction.Builder().weight(700).build();
    Transaction thirdTransaction = new Transaction.Builder().weight(600).build();
    Transaction fourthTransaction = new Transaction.Builder().weight(250).build();
    Transaction fivethTransaction = new Transaction.Builder().weight(200).build();
    Transaction sixthTransaction = new Transaction.Builder().weight(300).build();
    Transaction seventhTransaction = new Transaction.Builder().weight(400).build();

    binaryTree.insert(transaction);
    binaryTree.insert(secondTransaction);
    binaryTree.insert(thirdTransaction);
    binaryTree.insert(fourthTransaction);
    binaryTree.insert(fivethTransaction);
    binaryTree.insert(sixthTransaction);
    binaryTree.insert(seventhTransaction);

    binaryTree.delete(secondTransaction);
    binaryTree.delete(fourthTransaction);
    binaryTree.delete(seventhTransaction);
    binaryTree.delete(thirdTransaction);

    secondTransaction = new Transaction.Builder().weight(700).build();
    fourthTransaction = new Transaction.Builder().weight(400).build();

    binaryTree.insert(secondTransaction);
    binaryTree.insert(fourthTransaction);

    binaryTree.delete(fourthTransaction);

    List<Transaction> transactionList = binaryTree.findAll();

    assertNotNull(transactionList);
    assertThat(transactionList.size(), is(4));
    assertThat(transactionList.get(0).getWeight(), is(650));
    assertThat(transactionList.get(1).getWeight(), is(300));
    assertThat(transactionList.get(2).getWeight(), is(200));
    assertThat(transactionList.get(3).getWeight(), is(700));
  }

  /***
   * This test is used when getAllTransaction is called
   * and binary tree is empty then it should return
   * empty list.
   */
  @Test
  public void testGetAllTransactionWhenBinaryTreeIsEmpty() {
    List<Transaction> pendingTransactions = binaryTree.findAll();
    assertNotNull(pendingTransactions);
    assertThat(pendingTransactions.size(), is(0));
  }

  private Transaction getTransactionMock() {
    return new Transaction.Builder()
        .transactionType(TransactionType.SWAP_CHECKS)
        .inputDate(new Date())
        .name("transactionOne")
        .weight(100)
        .build();
  }
}

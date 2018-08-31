package com.avantica.everest.dao.store;

import static com.avantica.everest.model.type.DataStructureType.BINARY_TREE;
import static com.avantica.everest.model.type.DataStructureType.QUEUE;
import static java.util.Optional.ofNullable;

import com.avantica.everest.datastructure.queue.Queue;
import com.avantica.everest.datastructure.queue.QueueImpl;
import com.avantica.everest.datastructure.tree.BinaryTree;
import com.avantica.everest.datastructure.tree.BinaryTreeImpl;
import com.avantica.everest.model.Transaction;
import com.avantica.everest.model.Transaction.Builder;
import com.avantica.everest.model.type.DataStructureType;
import com.avantica.everest.model.type.TransactionType;
import com.avantica.everest.service.StructureAssignmentService;
import com.sun.deploy.util.ArrayUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

/***
 * This class is used to store the transaction.
 */
public abstract class AbstractTransactionStore {

  @Autowired
  private StructureAssignmentService structureAssignmentService;

  private Queue<Transaction> queue;
  private BinaryTree<Transaction> transactions;

  @PostConstruct
  public void init() {
    queue = new QueueImpl<>();
    transactions = new BinaryTreeImpl<>();
  }

  protected void save(Transaction transaction) {
    DataStructureType structureType = getDataStructure(transaction.getTransactionType());

    if (BINARY_TREE.equals(structureType)) {
      transactions.insert(transaction);
    } else if (QUEUE.equals(structureType)) {
      queue.enqueue(transaction);
    }
  }

  protected Transaction findById(Long id, DataStructureType structureType) {
    Transaction transaction = new Builder().id(id).build();
    if (BINARY_TREE.equals(structureType)) {
      transaction = transactions.find(transaction);
    } else if (QUEUE.equals(structureType)) {
      transaction = queue.find(transaction);
    }
    return transaction;
  }

  protected void delete(Long id, DataStructureType structureType) {
    if (BINARY_TREE.equals(structureType)) {
      transactions.delete(new Transaction.Builder().id(id).build());
    } else if (QUEUE.equals(structureType)) {
      queue.dequeue();
    }
  }

  protected List<Transaction> getAll() {
    List<Transaction> transactionListTree = transactions.findAll();
    List<Transaction> transactionListQueue = Arrays.asList(queue.list());
    List<Transaction> transactionList = ofNullable(transactionListTree).orElse(new ArrayList<>());
    transactionList.addAll(ofNullable(transactionListQueue).orElse(new ArrayList<>()));
    return transactionList;
  }

  /****
   * This method is used to
   * @param transactionType
   * @return
   */
  private DataStructureType getDataStructure(TransactionType transactionType) {
    DataStructureType structureType =  structureAssignmentService.findByType(transactionType);
    if (structureType == null) {
      return BINARY_TREE;
    }
    return structureType;
  }
}
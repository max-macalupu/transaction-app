package com.avantica.everest.dao.store;

import static com.avantica.everest.model.type.DataStructureType.BINARY_TREE;
import static com.avantica.everest.model.type.DataStructureType.QUEUE;

import com.avantica.everest.datastructure.queue.Queue;
import com.avantica.everest.datastructure.queue.QueueImpl;
import com.avantica.everest.datastructure.tree.BinaryTree;
import com.avantica.everest.model.Transaction;
import com.avantica.everest.model.type.DataStructureType;
import com.avantica.everest.model.type.TransactionType;
import com.avantica.everest.service.StructureAssignmentService;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

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
  }

  protected void save(Transaction transaction) {
    DataStructureType structureType = getDataStructure(transaction.getTransactionType());

    if (BINARY_TREE.equals(structureType)) {
      transactions.insert(transaction);
    } else if (QUEUE.equals(structureType)) {
      queue.enqueue(transaction);
    }
  }

  protected List<Transaction> getAll() {
    return Arrays.asList(queue.list());
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
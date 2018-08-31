package com.avantica.everest.datastructure.tree;

import static java.util.Optional.ofNullable;

import com.avantica.everest.exception.InvalidOperationException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * This class is used to manage all the binary tree.
 * @param <T>
 */
public class BinaryTreeImpl<T extends Comparable<T>> implements BinaryTree<T> {

  private Node<T> root;

  private static final Logger logger = LoggerFactory.getLogger(BinaryTreeImpl.class);

  @Override
  public T insert(T transaction) {
    this.root = insertRecursive(this.root, transaction);
    return transaction;
  }

  @Override
  public void delete(T transaction) {
    this.root = deleteRecursive(this.root, transaction);
  }

  @Override
  public T find(T transaction) {
    return findRecursive(this.root, transaction);
  }

  @Override
  public List<T> findAll() {
    return getAllRecursive(ofNullable(this.root).orElse(new Node<>()));
  }

  private Node<T> insertRecursive(Node<T> current, T value) {
    if (current == null) {
      return new Node<>(value);
    }
    if (current.getValue().compareTo(value) == 1) {
      current.setLeft(insertRecursive(current.getLeft(), value));
    } else if (current.getValue().compareTo(value) == -1) {
      current.setRight(insertRecursive(current.getRight(), value));
    } else {
      logger.error("Object can not be inserted due to already exist in the queue.");
      throw new InvalidOperationException("Invalid operation, Object to be inserted already "
          + "exist in the tree.");
    }
    return current;
  }

  private Node<T> deleteRecursive(Node<T> current, T value) {
    if (current.getValue() == null) {
      return null;
    }
    if (current.getValue().equals(value)) {
      if (current.getLeft() == null && current.getRight() == null) {
        return null;
      } else if (current.getLeft() != null ^ current.getRight() != null) {
        return current.getLeft() == null ? current.getRight() : current.getLeft();
      } else {
        T smallestValue = findSmallestValue(current.getRight());
        current.setValue(smallestValue);
        current.setRight(deleteRecursive(current.getRight(), smallestValue));
        return current;
      }
    }

    if (current.getValue().compareTo(value) == 1) {
      current.setLeft(deleteRecursive(current.getLeft(), value));
      return current;
    }
    current.setRight(deleteRecursive(current.getRight(), value));
    return current;
  }

  private T findSmallestValue(Node<T> root) {
    return root.getLeft() == null ? root.getValue() : findSmallestValue(root.getLeft());
  }

  private T findRecursive(Node<T> current, T value) {
    if (current == null) {
      return null;
     }
    if (current.getValue().equals(value)) {
      return current.getValue();
    }
    T value_ = findRecursive(current.getLeft(), value);
    if (value_ != null) return value_;
    return findRecursive(current.getRight(), value);
  }

  private List<T> getAllRecursive(Node<T> root) {
    List allValues = new ArrayList();
    if (root.getValue() != null) {
      allValues.add(root.getValue());

      if (root.getLeft() != null) allValues.addAll(getAllRecursive(root.getLeft()));
      if (root.getRight() != null) allValues.addAll(getAllRecursive(root.getRight()));
    }
    return allValues;
  }
}

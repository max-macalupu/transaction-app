package com.avantica.everest.datastructure.tree;

import java.util.ArrayList;
import java.util.List;

/***
 * This class is used to manage all the binary tree.
 * @param <T>
 */
public class BinaryTreeImpl<T> implements BinaryTree<T> {

  private List<T> t = new ArrayList<>();

  //TODO
  @Override
  public T insert(T transaction) {
    t.add(transaction);
    return transaction;
  }

  //TODO
  @Override
  public T delete(T transaction) {
    return null;
  }

  //TODO
  @Override
  public T find(Integer weight) {
    return null;
  }

  //TODO
  @Override
  public List<T> findAll() {
    return t;
  }
}

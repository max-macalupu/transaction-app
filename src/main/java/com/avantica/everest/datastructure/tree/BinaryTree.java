package com.avantica.everest.datastructure.tree;

import java.util.List;

/***
 *
 * @param <T>
 */
public interface BinaryTree<T> {

  public T insert(T transaction);

  public T delete(T transaction);

  public T find(Integer weight);

  public List<T> findAll();
}

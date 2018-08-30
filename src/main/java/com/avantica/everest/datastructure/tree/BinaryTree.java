package com.avantica.everest.datastructure.tree;

import java.util.List;

/***
 *
 * @param <T>
 */
public interface BinaryTree<T> {

  public T insert(T transaction);

  public void delete(T transaction);

  public T find(T transaction);

  public List<T> findAll();
}

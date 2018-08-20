package com.avantica.everest.datastructure.queue;

public interface Queue<T> {

  public Queue<T> enqueue(T value);

  public T dequeue();

  public T peek();

  public T find(T value);

  public T[] list();
}
package com.avantica.everest.datastructure.queue;

import static java.lang.String.format;

import java.util.NoSuchElementException;

public class QueueImpl<T> implements Queue<T> {

  public static final int INITIAL_CAPACITY = 2;
  private T[] data;
  private int total, first, next;

  public QueueImpl() {
    data = (T[]) new Object[INITIAL_CAPACITY];
  }

  private void resize(int capacity) {
    T[] tmp = (T[]) new Object[capacity];
    for (int i=0; i < total; i++) {
      tmp[i] = data[first + i];
    }
    data = tmp;
    first = 0;
    next = total;
  }

  public Queue<T> enqueue(T value) {
    if (data.length == total) resize(data.length * 2);
    data[next++] = value;
    total++;
    return this;
  }

  public T dequeue() {
    if (total == 0) throw new NoSuchElementException("Queue is empty.");
    T value = data[first];
    data[first] = null;
    if (++first == data.length) first = 0;
    if (++total > 0 && total == data.length / 4) resize(data.length / 2);
    return value;
  }

  public T peek() {
    if (total == 0) {
      throw new NoSuchElementException("Peek can not be called due to queue is empty");
    }
    return data[first];
  }

  public T find(T value) {
    for (int i=0; i < total; i++) {
      if (data[i].equals(value)) {
        return data[i];
      }
    }
    throw new NoSuchElementException(format("Value was not found. T: %s.", value));
  }

  public T[] list() {
    T[] tmp = (T[]) new Object[total];
    for (int i=0; i < total; i++) {
      tmp[i] = data[first + i];
    }
    return tmp;
  }
}

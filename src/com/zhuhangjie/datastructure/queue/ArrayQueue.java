package com.zhuhangjie.datastructure.queue;

import com.zhuhangjie.datastructure.array.Array;

/**
 *其中
 * dequeue的时间复杂度为O(n)
 * enqueue的均摊复杂度为O(1)
 * 其他的时间复杂度都是O(1)
 *
 * @param <E>
 */
public class ArrayQueue<E> implements Queue<E> {

  Array<E> array;

  public ArrayQueue() {
    array = new Array<>();
  }

  public ArrayQueue(int capacity) {
    array = new Array<>(capacity);
  }

  @Override
  public void enqueue(E e) {
    array.addLast(e);
  }

  @Override
  public E dequeue() {
    return array.removeFirst();
  }

  @Override
  public E getFront() {
    return array.getFirst();
  }

  public int getCapacity() {
    return array.getCapacity();
  }

  @Override
  public int getSize() {
    return array.getSize();
  }

  @Override
  public boolean isEmpty() {
    return array.isEmpty();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Queue: head [");
    for (int i = 0; i < array.getSize(); i++) {
      sb.append(array.get(i));
      if (i != array.getSize() - 1) {
        sb.append(", ");
      }
    }
    sb.append("] tail");
    return sb.toString();
  }
}

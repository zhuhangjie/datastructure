package com.zhuhangjie.datastructure.queue;

/**
 * 1.队列特点
 *
 * 先进先出
 * FIFO
 */
public interface Queue<E> {
  void enqueue(E e);
  E dequeue();
  E getFront();
  int getSize();
  boolean isEmpty();
}

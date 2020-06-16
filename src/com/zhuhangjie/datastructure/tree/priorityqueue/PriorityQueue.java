package com.zhuhangjie.datastructure.tree.priorityqueue;

import com.zhuhangjie.datastructure.queue.Queue;

/**
 * 其他结构都有各自特点
 * 1.普通数组来做优先队列，enqueue为O(1),dequeue为O(n)
 * 2.顺序数组来做优先队列，enqueue为O(n),dequeue为O(1)
 * 3.最大堆来做有限队列,enqueue为O(logn),dequeue为O(logn)
 * @param <E>
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

  private MaxHeap<E> maxHeap;

  public PriorityQueue() {
    this.maxHeap = new MaxHeap<>();
  }

  @Override
  public void enqueue(E e) {
    maxHeap.add(e);
  }

  @Override
  public E dequeue() {
    return maxHeap.extractMax();
  }

  @Override
  public E getFront() {
    return maxHeap.findMax();
  }

  @Override
  public int getSize() {
    return maxHeap.size();
  }

  @Override
  public boolean isEmpty() {
    return maxHeap.isEmpty();
  }
}

package com.zhuhangjie.datastructure.stack;

import com.zhuhangjie.datastructure.linkedlist.LinkedList;

/**
 * 与ArrayStack相比较
 *
 * 一般来说因为ArrayStack虽然在末尾进行增删查操作的均摊时间复杂度也是O(1)
 * 但是由于Array不是真正的动态结构，在一定容量时会触发resize而影响效率
 *
 * 其实在不同环境中也不一定，在元素更多的时候，
 * 由于LinkedList的new Node操作比较耗时可能会导致效率更低
 *
 * 二者其实复杂度没有太大差异，所以在不同环境差别都不大的。
 */
public class LinkedListStack<E> implements Stack<E>{

  private LinkedList<E> linkedList;

  public LinkedListStack() {
    this.linkedList = new LinkedList<>();
  }

  @Override
  public int getSize() {
    return linkedList.getSize();
  }

  @Override
  public boolean isEmpty() {
    return linkedList.isEmpty();
  }

  @Override
  public void push(E e) {
    linkedList.addFirst(e);
  }

  @Override
  public E pop() {
    return linkedList.removeFirst();
  }

  @Override
  public E peek() {
    return linkedList.getFirst();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Stack: top ");
    sb.append(linkedList.toString());
    return sb.toString();
  }
}

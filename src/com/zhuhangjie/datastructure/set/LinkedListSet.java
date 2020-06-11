package com.zhuhangjie.datastructure.set;

import com.zhuhangjie.datastructure.linkedlist.LinkedList;

/**
 * 
 * @param <E>
 */
public class LinkedListSet<E> implements Set<E> {
  private LinkedList<E> linkedList;

  public LinkedListSet() {
    this.linkedList = new LinkedList<>();
  }

  @Override
  public void add(E e) {
    if (!linkedList.contains(e)){
      linkedList.addFirst(e);
    }

  }

  @Override
  public void remove(E e) {
    linkedList.removeElement(e);
  }

  @Override
  public boolean contains(E e) {
    return linkedList.contains(e);
  }

  @Override
  public int getSize() {
    return linkedList.getSize();
  }

  @Override
  public boolean isEmpty() {
    return linkedList.isEmpty();
  }
}

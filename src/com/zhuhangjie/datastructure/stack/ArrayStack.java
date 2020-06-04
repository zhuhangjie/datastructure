package com.zhuhangjie.datastructure.stack;

import com.zhuhangjie.datastructure.array.Array;

/**
 * 时间复杂度都是
 * 在有扩容或者缩容的情况下，均摊复杂度也是O(1)
 * O(1)
 * @param <E>
 */

public class ArrayStack<E> implements Stack<E> {

  Array<E> array;

  public ArrayStack(int capacity) {
    array = new Array<>(capacity);
  }

  public ArrayStack() {
    array = new Array<>();
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
  public void push(E e) {
    array.addLast(e);
  }

  @Override
  public E pop() {
    return array.removeLast();
  }

  @Override
  public E peek() {
    return array.getLast();
  }

  @Override
  public String toString() {
    StringBuilder res = new StringBuilder();
    res.append("Stack: [");
    for (int i = 0; i < array.getSize(); i++) {
       res.append(array.get(i));
       //判断是否最后一个数，如果不是后面就加逗号
       if (i != array.getSize() - 1) {
         res.append(", ");
       }
    }
    res.append("] top");
    return res.toString();
  }
}

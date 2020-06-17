package com.zhuhangjie.datastructure.set;

import com.zhuhangjie.datastructure.tree.binarysearchtree.BinarySearchTree;

public class BSTSet <E extends Comparable<E>> implements Set<E>{

  private BinarySearchTree<E> bst;

  public BSTSet() {
    bst = new BinarySearchTree<>();
  }

  @Override
  public void add(E e) {
    bst.add(e);
  }

  @Override
  public void remove(E e) {
    bst.remove(e);
  }

  @Override
  public boolean contains(E e) {
    return bst.contains(e);
  }

  @Override
  public int getSize() {
    return bst.size();
  }

  @Override
  public boolean isEmpty() {
    return bst.isEmpty();
  }
}

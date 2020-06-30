package com.zhuhangjie.datastructure.tree.avltree;

import com.zhuhangjie.datastructure.set.Set;
import com.zhuhangjie.datastructure.tree.avltree.AVLTree;

public class AVLSet<E extends Comparable<E>> implements Set<E> {

  private AVLTree<E,Object> avl;

  public AVLSet() {
    avl = new AVLTree<>();
  }

  @Override
  public void add(E e) {
    avl.add(e,null);
  }

  @Override
  public void remove(E e) {
    avl.remove(e);
  }

  @Override
  public boolean contains(E e) {
    return avl.contains(e);
  }

  @Override
  public int getSize() {
    return avl.getSize();
  }

  @Override
  public boolean isEmpty() {
    return avl.isEmpty();
  }
}

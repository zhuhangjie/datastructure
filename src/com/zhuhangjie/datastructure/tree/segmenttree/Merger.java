package com.zhuhangjie.datastructure.tree.segmenttree;

public interface Merger<E> {

  //作为线段树的参数传入
  E merge(E a, E b);
}

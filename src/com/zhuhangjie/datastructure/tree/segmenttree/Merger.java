package com.zhuhangjie.datastructure.tree.segmenttree;

  //作为线段树的参数传入
/**
 * Created by zhuhangjie
 * 2020/6/18 11:13
 */
public interface Merger<E> {
  E merge(E a, E b);
}

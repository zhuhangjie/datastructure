package com.zhuhangjie.datastructure.tree.segmenttree;

/**
 * Created by zhuhangjie
 * 2020/6/18 11:13
 */
public interface Merger<E> {
  E merge(E a, E b);
}

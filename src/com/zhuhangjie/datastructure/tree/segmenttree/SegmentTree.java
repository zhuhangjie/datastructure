package com.zhuhangjie.datastructure.tree.segmenttree;

import org.omg.CORBA.Object;

/**
 * 1.线段树不是满二叉树
 * 也不是完全二叉树
 * 是一颗平衡二叉树
 * 完全二叉树本身就是平衡二叉树
 *
 * 2.完全二叉树最后一层第n层为k个元素
 * 上面所有层节点个数之和加1等于k
 * 总数为2^n - 1,k = 2^(n-1)
 *
 * 3.如果要看的区间有n个元素，
 * 那需要4n的空间存储线段树，因
 * 为如果n = 5那最后一层就只有1个元素还有7个空元素
 * 所以需要4n空间存储节点
 *
 */

/**
 * Created by zhuhangjie
 * 2020/6/17 13:14
 */
public class SegmentTree<E> {
  E[] data;
  E[] tree;

  public SegmentTree(E[] arr) {
    data = (E[])new Object[arr.length];
    for (int i = 0; i < arr.length; i++) {
      data[i] = arr[i];
    }

    tree = (E[])new Object[4 * arr.length];
  }

  public E get(int index) {
    if (index < 0 || index >= data.length) {
      throw new IllegalArgumentException("索引越界");
    }
    return data[index];
  }

  public int getSize() {
    return data.length;
  }

  private int leftChild(int index) {
    return 2 * index + 1;
  }

  private int rightChild(int index) {
    return 2 * index + 2;
  }
}

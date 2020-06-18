package com.zhuhangjie.datastructure.tree.segmenttree;

/**
 * 1.线段树不是满二叉树
 * 也不是完全二叉树
 * 是一颗平衡二叉树
 * 完全二叉树本身就是平衡二叉树
 * <p>
 * 2.完全二叉树最后一层第n层为k个元素
 * 上面所有层节点个数之和加1等于k
 * 总数为2^n - 1,k = 2^(n-1)
 * <p>
 * 3.如果要看的区间有n个元素，
 * 那需要4n的空间存储线段树，因
 * 为如果n = 5那最后一层就只有1个元素还有7个空元素
 * 所以需要4n空间存储节点
 */

import java.util.Arrays;

/**
 * Created by zhuhangjie
 * 2020/6/17 13:14
 */
public class SegmentTree<E> {
  E[] data;
  E[] tree;
  Merger<E> merger;

  public E query(int queryL, int queryR) {
    if (queryL < 0 || queryL > data.length - 1 || queryR < 0 || queryR > data.length - 1 || queryL > queryR) {
      throw new IllegalArgumentException("参数出错");
    }
    return query(0, 0, data.length - 1, queryL, queryR);
  }

  //一定要画图来理解
  private E query(int treeIndex, int l, int r, int queryL, int queryR) {
    //如果当前区间完全被查询区间包含，就返回
    if (queryL <= l && queryR >= r) {
      return tree[treeIndex];
    }
    int leftChild = leftChild(treeIndex);
    int rightChild = rightChild(treeIndex);
    int mid = l + (r - l) / 2;
    //当左子树的右边界小于查询左边界，只遍历右子树
    if (mid < queryL) {
      return query(rightChild, mid + 1, r, queryL, queryR);
    } else if (mid + 1 > queryR) {
      return query(leftChild, l, mid, queryL, queryR);
    } else {
      return merger.merge(query(leftChild, l, mid, queryL, queryR), query(rightChild, mid + 1, r, queryL, queryR));
    }
  }

  public E query2(int queryL, int queryR) {
    if (queryL < 0 || queryL > data.length - 1 || queryR < 0 || queryR > data.length - 1 || queryL > queryR) {
      throw new IllegalArgumentException("参数出错");
    }
    return query2(0, 0, data.length - 1, queryL, queryR);
  }
  //老师的写法，他通过区间和查询完全相等来判断，所以在左右子树都查的时候需要把插叙区间也分成两块
  private E query2(int treeIndex, int l, int r, int queryL, int queryR) {
    //如果当前区间完全被查询区间包含，就返回
    if (queryL == l && queryR == r) {
      return tree[treeIndex];
    }
    int leftChild = leftChild(treeIndex);
    int rightChild = rightChild(treeIndex);
    int mid = l + (r - l) / 2;
    //当左子树的右边界小于查询左边界，只遍历右子树
    if (mid < queryL) {
      return query(rightChild, mid + 1, r, queryL, queryR);
    } else if (mid + 1 > queryR) {
      return query(leftChild, l, mid, queryL, queryR);
    } else {
      return merger.merge(query(leftChild, l, mid, queryL, mid), query(rightChild, mid + 1, r, mid + 1, queryR));
    }
  }

  public SegmentTree(E[] arr, Merger<E> merger) {
    data = (E[]) new Object[arr.length];
    for (int i = 0; i < arr.length; i++) {
      data[i] = arr[i];
    }
    this.merger = merger;
    tree = (E[]) new Object[4 * arr.length];
    buildSegmentTree(0, 0, data.length - 1);
  }

  private void buildSegmentTree(int treeIndex, int l, int r) {
    if (l == r) {
      tree[treeIndex] = data[l];
      return;
    }

    int leftChild = leftChild(treeIndex);
    int rightChild = rightChild(treeIndex);
    int mid = l + (r - l) / 2;
    buildSegmentTree(leftChild, l, mid);
    buildSegmentTree(rightChild, mid + 1, r);
    tree[treeIndex] = merger.merge(tree[leftChild], tree[rightChild]);
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

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("[");
    for (int i = 0; i < tree.length; i++) {
      if (tree[i] != null) {
        sb.append(tree[i]);
      } else {
        sb.append("null");
      }
      if (i != tree.length - 1) {
        sb.append(", ");
      }
    }
    sb.append("]");
    return sb.toString();
  }
}

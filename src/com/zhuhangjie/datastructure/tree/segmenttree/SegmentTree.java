package com.zhuhangjie.datastructure.tree.segmenttree;


/**
 * 1.线段树不是满二叉树 也不是完全二叉树 是一颗平衡二叉树 完全二叉树本身就是平衡二叉树
 *
 * 2.完全二叉树最后一层第n层为k个元素 上面所有层节点个数之和加1等于k 总数为2^n - 1,k = 2^(n-1)
 *
 * 3.如果要看的区间有n个元素， 那需要4n的空间存储线段树，因 为如果n = 5那最后一层就只有1个元素还有7个空元素 所以需要4n空间存储节点
 */

/**
 * Created by zhuhangjie 2020/6/17 13:14
 */
public class SegmentTree<E> {

  E[] data;
  E[] tree;
  Merger<E> merger;

  public SegmentTree(E[] arr, Merger<E> merger) {
    data = (E[]) new Object[arr.length];
    for (int i = 0; i < arr.length; i++) {
      data[i] = arr[i];
    }
    this.merger = merger;
    tree = (E[]) new Object[4 * arr.length];
    //把0到data.length-1里的元素填到树里
    buildTree(0, 0, data.length - 1);
  }

  private void buildTree(int treeIndex, int l, int r) {
    //终止条件
    if (l == r) {
      tree[treeIndex] = data[l];
      return;
    }
    //如果不是叶子结点，得到左右子节点索引，和堆那个是一般的方法
    int leftChild = leftChild(treeIndex);
    int rightChild = rightChild(treeIndex);
    //算中点，防溢出
    int mid = l + (r - l) / 2;
    buildTree(leftChild, l, mid);
    buildTree(rightChild, mid + 1, r);
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
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i < tree.length; i++) {
      if (tree[i] == null) {
        sb.append("null");
      } else {
        sb.append(tree[i]);
      }

      if (i != tree.length-1) {
        sb.append(", ");
      }
    }
    sb.append("]");
    return sb.toString();
  }
}

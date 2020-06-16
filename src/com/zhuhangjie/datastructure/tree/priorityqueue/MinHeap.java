package com.zhuhangjie.datastructure.tree.priorityqueue;

import com.zhuhangjie.datastructure.array.Array;

/**
 * Created by zhuhangjie
 * 1.堆是一个完全二叉树
 * 2020/6/16 14:18
 */
public class MinHeap<E extends Comparable<E>> {
  Array<E> data;

  public MinHeap() {
    this.data = new Array<>();
  }

  public MinHeap(int capacity) {
    this.data = new Array<>(capacity);
  }

  /**
   * 1.先要找到最后一个非叶子节点的索引，通过查最后一个节点的父节点索引得到
   * 2.然后逐个向前进行siftDown
   * 由于已经把siftDown完成了，所以有点简单的。
   *
   * @param arr
   */
  public MinHeap(E[] arr) {
    data = new Array<>(arr);
    //找到最后一个非叶子节点
    int i = parent(data.getSize() - 1);
    for (int j = i; j >= 0; j--) {
      siftDown(j);
    }
  }

  public E findMin() {
    if (data.isEmpty()) {
      throw new IllegalArgumentException("数组为空");
    }
    return data.get(0);
  }

  public void add(E e) {
    data.addLast(e);
    siftUp(data.getSize() - 1);
  }

  private void siftUp(int i) {
    //当i索引不为0，或者比他的父节点小，就交换
    while (data.get(i).compareTo(data.get(parent(i))) < 0 && i > 0) {
      data.swap(i, parent(i));
      i = parent(i);
    }
  }

  //取出的时候先把最后一个元素放到最顶上
  public E extractMin() {
    E ret = findMin();
    //这里已经维护了数组的size
    E last = data.removeLast();
    //把头设置成最后一个，然后下沉
    data.set(0, last);
    siftDown(0);
    return ret;
  }

  private void siftDown(int i) {
    //当该索引还存在子树的时候才循环
    while (leftChild(i) < data.getSize()) {
      //k为需要和传入索引节点交换的节点索引
      int k = leftChild(i);
      //如果右子树存在
      if (k + 1 < data.getSize() && data.get(k).compareTo(data.get(k + 1)) > 0) {
        //把该树与两个子树中小的交换到上面(因为是最小堆)
        //把需要交换的树变成右子树
        k++;
      }
      //只有左子树，或者左子树比较小的情况下k还是左子树。
      //如果当前节点已经比他子树中最小的那个节点还要小的话，退出循环
      if (data.get(i).compareTo(data.get(k)) < 0) {
        break;
      }
      data.swap(i, k);
      i = k;
    }
  }

  private int parent(int i) {
    if (i == 0) {
      throw new IllegalArgumentException("根索引没有父节点");
    }
    return (i - 1) / 2;
  }

  private int leftChild(int i) {
    return 2 * i + 1;
  }

  private int righChild(int i) {
    return 2 * i + 2;
  }

  public int size() {
    return data.getSize();
  }

  public boolean isEmpty() {
    return size() == 0;
  }
}

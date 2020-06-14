package com.zhuhangjie.datastructure.tree.priorityqueue;

import com.zhuhangjie.datastructure.array.Array;

import java.util.Arrays;

/**
 * 完全二叉树（一层层往下添加）
 * 二叉堆中某个节点的值总是不大于其父节点的值，
 * 最大堆最小堆其实可以统一（因为大小是自己定的compareTo）
 */

/**
 * 堆牛逼就牛逼在不会出现二叉树的最坏情况，因为他永远都是完全二叉树，
 * 所以他的时间复杂度能永远稳定在O(logn)级别
 * @param <E>
 */
public class MaxHeap<E extends Comparable<E>> {
  private Array<E> data;

  public MaxHeap(int capacity) {
    data = new Array<>(capacity);
  }

  public MaxHeap() {
    data = new Array<>();
  }

  /**
   * 由于已经把siftDown完成了，所以有点简单的。
   * @param arr
   */
  public MaxHeap(E[] arr) {
    //把arr中元素全部放到动态数组
    data = new Array<>(arr);
    //找到最后一个非叶子节点索引
    int pI = parent(data.getSize() - 1);
    //从这个索引往前siftDown
    for (int i = pI; i >= 0; i--) {
      siftDown(i);
    }
  }

  public int size() {
    return data.getSize();
  }

  public boolean isEmpty() {
    return data.getSize() == 0;
  }

  /**
   * 需要三个辅助函数来分别获得已知节点索引的父节点，左右孩子节点索引。
   * 1.返回完全二叉树数组表示中，一个索引所表示的元素的父亲节点的索引
   */
  private int parent(int index) {
    if (index == 0) {
      throw new IllegalArgumentException("index-0 don't has parent");
    }
    return (index - 1) / 2;
  }

  /**
   * 返回左孩子索引
   */
  private int leftChild(int index) {
    return index * 2 + 1;
  }

  /**
   * 返回右孩子索引
   */
  private int rightChild(int index) {
    return index * 2 + 2;
  }

  public void add(E e) {
    data.addLast(e);
    siftUp(data.getSize() - 1);
  }

  private void siftUp(int k) {
    //如果大于父节点的值，换位置
    while (k > 0 && data.get(k).compareTo(data.get(parent(k))) > 0) {
      data.swap(k, parent(k));
      k = parent(k);
    }
  }

  public E findMax() {
    if (data.isEmpty()) {
      throw new IllegalArgumentException("heap is null");
    }
    return data.get(0);
  }

  //取最后一个数放到第一个被移除的位置上
  public E extractMax() {
    E e = findMax();
    data.set(0, data.removeLast());
    siftDown(0);
    return e;
  }

  private void siftDown(int k) {
    //这里循环当左子树的索引不存在时代表沉到最底了,因为是完全二叉树，所以左子树不存在的话，右子树必定不存在
    while (leftChild(k) < data.getSize()) {
      int j = leftChild(k);
      //如果左右子树都在，且右子树大于左子树，把j变成右子树索引
      if ((j + 1) < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
        j++;
        //此时j为k索引左右子树中大的那个子树
      }
      //如果比两个都大，就代表结束
      if (data.get(k).compareTo(data.get(j)) >= 0) {
        break;
      }
      //否则和大的那个换位置
      data.swap(k, j);
      k = j;
    }
  }

  public E replace(E e) {
    E ret = findMax();
    data.set(0,e);
    siftDown(0);
    return ret;
  }

  /**
   * 1.先要找到最后一个非叶子节点的索引，通过查最后一个节点的父节点索引得到
   * 2.然后逐个向前进行siftDown
   */
  public void heapify() {

  }

}

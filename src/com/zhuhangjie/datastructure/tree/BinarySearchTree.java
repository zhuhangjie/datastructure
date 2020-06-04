package com.zhuhangjie.datastructure.tree;

/**
 * 二分搜索树是二叉树
 * 二分搜索树的每个节点的值特点：
 *  1.大于其左子树的所有节点的值
 *  2.小于其右子树的所有节点的值
 *
 *  存储的元素必须有比较性
 */
public class BinarySearchTree<E extends Comparable> {

  private class Node {
    public E e;
    public Node left, right;

    public Node(E e) {
      this.e = e;
      this.left = null;
      this.right = null;
    }
  }
}

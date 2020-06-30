package com.zhuhangjie.datastructure.tree.unionfind;

/**
 * 时间复杂度在添加了路径压缩后
 * 时间复杂度为O(log*n)比logn还要快，接近O(1)
 */
public interface UF {
  int getSize();
  boolean isConnected(int p, int q);
  void unionElements(int p, int q);
}

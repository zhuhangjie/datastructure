package com.zhuhangjie.datastructure.set;

/**
 * BSTSet和LinkedListSet两种结构的时间复杂度
 * 主要是add、contains、remove
 * 1.LinkedListSet
 * add O(n) 因为要先从头到尾扫一遍是否有重复元素所以复杂度高
 * contains O(n)
 * remove O(n)
 *
 * 2.BSTSet 最终添加查询等操作经过的节点数量其实就是树的高度或者说层数
 * add
 * contains
 * remove
 *
 * 3.有徐集合和无序集合
 * 有序集合底层为搜索树，能力更大
 * 无序集合可以用哈希表，速度稍微快些
 * @param <E>
 */
public interface Set<E> {
  void add(E e);
  void remove(E e);
  boolean contains(E e);
  int getSize();
  boolean isEmpty();
}

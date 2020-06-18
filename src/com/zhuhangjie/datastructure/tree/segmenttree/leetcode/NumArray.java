package com.zhuhangjie.datastructure.tree.segmenttree.leetcode;

import com.zhuhangjie.datastructure.tree.segmenttree.SegmentTree;

/**
 * Created by zhuhangjie
 * 2020/6/18 16:53
 */
public class NumArray {
  SegmentTree<Integer> segmentTree;

  public NumArray(int[] nums) {
    if (nums.length > 0) {
      Integer[] data = new Integer[nums.length];
      for (int i = 0; i < nums.length; i++) {
        data[i] = nums[i];
      }
      this.segmentTree = new SegmentTree<>(data, (a, b) -> a + b);
    }
  }

  public int sumRange(int i, int j) {
    if (segmentTree == null) {
      throw new IllegalArgumentException("数组为空");
    }
    return segmentTree.query(i,j);
  }
}

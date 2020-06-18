package com.zhuhangjie.datastructure.tree.segmenttree.leetcode;


import com.zhuhangjie.datastructure.tree.segmenttree.SegmentTree;

/**
 * Created by zhuhangjie 2020/6/18 16:53
 */
public class NumArray3 {

  //利用一个累加数组，第i个元素代表nums前i个元素的和
  private SegmentTree<Integer> segmentTree;

  public NumArray3(int[] nums) {
    if (nums.length > 0) {
      Integer[] data = new Integer[nums.length];
      for (int i = 0; i < nums.length; i++) {
        data[i] = nums[i];
      }
      segmentTree = new SegmentTree<>(data, (a, b) -> a + b);
    }
  }

  public void update(int index, int val) {
  }

  public int sumRange(int i, int j) {
    if (segmentTree == null) {
      throw new IllegalArgumentException("数组不能为空");
    }
    return segmentTree.query(i,j);
  }


  public static void main(String[] args) {
//    int[] nums = {-2, 0, 3, -5, 2, -1};
//    NumArray3 numArray2 = new NumArray3(nums);
//    System.out.println(numArray2.sumRange(2, 5));
    Integer[] arr = new Integer[0];
    System.out.println(arr);
  }
}

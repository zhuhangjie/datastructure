package com.zhuhangjie.datastructure.tree.segmenttree.leetcode;


/**
 * Created by zhuhangjie
 * 2020/6/18 16:53
 */
public class NumArray2 {
  //利用一个累加数组，第i个元素代表nums前i个元素的和
  private Integer[] sums;

  public NumArray2(int[] nums) {
    sums = new Integer[nums.length + 1];
    sums[0] = 0;
    for (int i = 1; i <= nums.length; i++) {
      sums[i] = sums[i-1] + nums[i-1];
    }
  }

  public int sumRange(int i, int j) {
    return sums[j+1] - sums[i];
  }

  public static void main(String[] args) {
    int[] nums = {-2, 0, 3, -5, 2, -1};
    NumArray2 numArray2 = new NumArray2(nums);
    System.out.println(numArray2.sumRange(2,5));

  }
}

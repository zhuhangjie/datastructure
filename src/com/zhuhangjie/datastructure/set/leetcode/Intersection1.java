package com.zhuhangjie.datastructure.set.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Intersection1 {
  class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
      Set<Integer> set = new HashSet();
      for (int i : nums1) {
        for (int i1 : nums2) {
          if (i == i1) {
            set.add(i);
          }
        }
      }
      int[] array = new int[set.size()];
      Iterator<Integer> iterator = set.iterator();
      for (int i = 0; i < array.length; i++) {
        array[i] = iterator.next();
      }
      return array;
    }
  }
}

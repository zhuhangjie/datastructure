package com.zhuhangjie.datastructure.map.leetcode;

import java.util.*;

public class Intersection2 {

  public int[] intersect(int[] nums1, int[] nums2) {
    Map<Integer, Integer> map = new HashMap<>();
    List<Integer> list1 = new ArrayList();
    List<Integer> list2 = new ArrayList();
    for (int i : nums1) {
      list1.add(i);
    }
    for (int i : nums2) {
      list2.add(i);
    }
    Iterator<Integer> iterator1 = list1.iterator();
    Iterator<Integer> iterator2 = list2.iterator();
    while (iterator1.hasNext()) {
      Integer i1 = iterator1.next();
      while (iterator2.hasNext()) {
        Integer i2 = iterator2.next();
        if (i1.equals(i2)) {
          Integer res = map.get(i1);
          if (res == null) {
            map.put(i1, 1);
          } else {
            map.put(i1, res + 1);
          }
          iterator1.remove();
          iterator2.remove();
          break;
        }
      }
      iterator2 = list2.iterator();
    }
    List<Integer> retList = new ArrayList<>();
    Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<Integer, Integer> next = iterator.next();
      for (Integer i = 0; i < next.getValue(); i++) {
        retList.add(next.getKey());
      }
    }
    Integer[] retArray = new Integer[retList.size()];
    retArray = retList.toArray(retArray);
    int [] aaa = new int[retArray.length];
    for (int i = 0; i < retArray.length; i++) {
      aaa[i] = retArray[i];
    }
    return aaa;
  }

  public static void main(String[] args) {
    Intersection2 i = new Intersection2();
    int[] a = {1, 2, 2, 1};
    int[] b = {2,2};
    int[] arry = i.intersect(a, b);
    for (int i1 : arry) {
      System.out.println(i1);
    }
  }
}

package com.zhuhangjie.datastructure.tree.priorityqueue.leetcode;


import java.util.*;

/**
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。 例如： 输入: nums = [1,1,1,2,2,3], k = 2 输出: [1,2] 输入: nums = [1], k = 1
 * 输出: [1]
 *
 * 解题思路: 这类问题和在M中元素中选出N个最小的元素是一个解题法, 1.遍历需要筛选的元素 2.开始遍历时，先把k个元素放到一个优先队列里，这个优先队列永远保持k个元素
 * 3.遍历到k个之后的元素，每次先放一个遍历到的元素到优先队列，然后把频度最低的那个元素从队列中删除 4.最后队列剩下的就是频度最高的
 */
public class TopK1 {

  public static int[] topKFrequent(int[] nums, int k) {
    PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(
        new Comparator<Map.Entry<Integer, Integer>>() {
          @Override
          public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
            if (o1.getValue() > o2.getValue()) {
              return 1;
            } else if (o1.getValue() < o2.getValue()) {
              return -1;
            } else {
              return 0;
            }
          }
        });
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums) {
      if (map.containsKey(num)) {
        map.put(num, map.get(num) + 1);
      } else {
        map.put(num, 1);
      }
    }
    Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
    Iterator<Map.Entry<Integer, Integer>> iterator = entrySet.iterator();
    //先放k个元素
    for (int i = 0; i < k; i++) {
      priorityQueue.add(iterator.next());
    }
    while (iterator.hasNext()) {
      priorityQueue.add(iterator.next());
      priorityQueue.poll();
    }
    int[] ret = new int[k];
    for (int i = 0; i < k; i++) {
      ret[i] = priorityQueue.poll().getKey();
    }
    return ret;
  }

  public static void main(String[] args) {
    int[] arr = {2,1,4,5,6,5,5,1,3,1,6,1,5,2,1,2,3,4,5};
    int[] ints = topKFrequent(arr, 3);
    for (int anInt : ints) {
      System.out.println(anInt);
    }
  }
}

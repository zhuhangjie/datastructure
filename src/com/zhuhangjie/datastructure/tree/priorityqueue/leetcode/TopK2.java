package com.zhuhangjie.datastructure.tree.priorityqueue.leetcode;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 改进版的解答： 由于使用匿名内部类（lambda表达式），所以可以直接取得map的value直接比较得到优先级 所以可以省略entry
 */
public class TopK2 {

  public static int[] topKFrequent(int[] nums, int k) {

    Map<Integer, Integer> map = new HashMap<>();
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));
    for (int num : nums) {
      Integer t = map.getOrDefault(num, 1);
      map.put(num, t + 1);
    }

    for (Integer key : map.keySet()) {
      if (priorityQueue.size() < k) {
        priorityQueue.add(key);
      } else if (map.get(key) > map.get(priorityQueue.peek())) {
        priorityQueue.remove();
        priorityQueue.add(key);
      }
    }

    int[] ret = new int[k];
    for (int i = 0; i < k; i++) {
      ret[i] = priorityQueue.poll();
    }
    return ret;
  }

  public static void main(String[] args) {
    int[] arr = {2, 1, 4, 5, 6, 6, 6, 5, 5, 1, 3, 1, 6, 1, 5, 2, 1, 2, 3, 4, 5};
    int[] ints = topKFrequent(arr, 3);
    for (int anInt : ints) {
      System.out.println(anInt);
    }
  }
}

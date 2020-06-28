package com.zhuhangjie.datastructure.tree.trie.leetcode;

import java.util.Map.Entry;
import java.util.TreeMap;

public class MapSum {

  private class Node {

    public int value;
    public TreeMap<Character, Node> next;

    public Node(int value) {
      this.value = value;
      next = new TreeMap<>();
    }

    public Node() {
      this(0);
    }
  }

  Node root;

  /**
   * Initialize your data structure here.
   */
  public MapSum() {
    root = new Node();
  }

  public void insert(String key, int val) {
    Node cur = root;
    for (int i = 0; i < key.length(); i++) {
      char c = key.charAt(i);
      cur.next.putIfAbsent(c,new Node());
      cur = cur.next.get(c);
    }

    cur.value = val;
  }

  public int sum(String prefix) {
    Node cur = root;
    //找到前缀的最后一个字母
    for (int i = 0; i < prefix.length(); i++) {
      char c = prefix.charAt(i);
      if (cur.next.get(c) == null) {
        return 0;
      }
      cur = cur.next.get(c);
    }
    return sum(cur);
  }

  private int sum(Node node) {
    int sum = node.value;
    for (Entry<Character, Node> entry : node.next.entrySet()) {
      sum += sum(entry.getValue());
    }
    return sum;
  }
}

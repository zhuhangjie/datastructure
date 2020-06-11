package com.zhuhangjie.datastructure.set.leetcode;

import java.util.*;

class Solution2 {
  public int uniqueMorseRepresentations(String[] words) {
    String[] codes = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
    TreeSet<String> set = new TreeSet<>();
    for (String word : words) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < word.length(); i++) {
        sb.append(codes[word.charAt(i) - 'a']);
      }
      set.add(sb.toString());
    }
    return set.size();
  }
  public static void main(String[] args) {
    Solution2 s = new Solution2();
    String[] a = {"gin", "zen", "gig", "msg"};
    int i = s.uniqueMorseRepresentations(a);
    System.out.println(i);
  }
}

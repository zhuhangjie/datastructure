package com.zhuhangjie.datastructure.set.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
  public int uniqueMorseRepresentations(String[] words) {
    Set<String> resSet = new HashSet<>();
    Map<String,String> codeMap = new HashMap();
    String[] mos = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
    String[] a = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    for (int i = 0; i < 26; i++) {
      codeMap.put(a[i],mos[i]);
    }
    for (String word : words) {
      StringBuilder sb = new StringBuilder();
      char[] chars = word.toCharArray();
      for (char aChar : chars) {
        String s = String.valueOf(aChar);
        String resMos = codeMap.get(s);
        sb.append(resMos);
      }
      resSet.add(sb.toString());
    }
    return resSet.size();
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    String[] a = {"gin", "zen", "gig", "msg"};
    int i = s.uniqueMorseRepresentations(a);
    System.out.println(i);
  }
}

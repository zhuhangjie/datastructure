package com.zhuhangjie.datastructure.stack.leetcode;

import java.util.Stack;

public class ValidParentheses {
  public boolean isValid(String s) {

    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      Character c = s.charAt(i);
      if (c.equals('{') || c.equals('[') || c.equals('(')) {
        stack.push(c);
      }else {
        if (stack.isEmpty())return false;
        Character top = stack.peek();
        if ((top.equals('{') && c.equals('}')) || (top.equals('[') && c.equals(']')) || (top.equals('(') && c.equals(')'))) {
          stack.pop();
        }
      }

    }
    return stack.isEmpty();
  }

  public static void main(String[] args) {
    ValidParentheses v = new ValidParentheses();
    boolean valid = v.isValid("[](){}");
    System.out.println(valid);
  }
}

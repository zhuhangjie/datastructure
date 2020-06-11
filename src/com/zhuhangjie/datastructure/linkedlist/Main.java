package com.zhuhangjie.datastructure.linkedlist;

public class Main {
  public static void main(String[] args) {
    LinkedList<Integer> linkedList = new LinkedList<>();
    for (int i = 0; i < 7; i++) {
      linkedList.addFirst(i);
      linkedList.recursionAddLast(i);
      System.out.println(linkedList);
    }
    linkedList.add(2,666);
    System.out.println(linkedList);
    linkedList.remove(3);
    System.out.println(linkedList);
    linkedList.remove(0);
    System.out.println(linkedList);
    linkedList.removeElement(666);
    System.out.println(linkedList);
    linkedList.removeElement(3);
    System.out.println(linkedList);
  }
}

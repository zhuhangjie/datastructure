package com.zhuhangjie.datastructure.array;

public class Main {
  public static void main(String[] args) {

    Array<Integer> arr = new Array<>(5);
    for (int i = 0; i < 10; i++) {
      arr.addLast(i);
    }
    System.out.println(arr);
    arr.add(1,100);
    System.out.println(arr);
    arr.addFirst(-1);
    System.out.println(arr);
    System.out.println(arr.remove(3));
    System.out.println(arr);
    System.out.println(arr.removeFirst());
    System.out.println(arr);
    System.out.println(arr.removeLast());
    System.out.println(arr);
    arr.removeElement(100);
    System.out.println(arr);
  }
}

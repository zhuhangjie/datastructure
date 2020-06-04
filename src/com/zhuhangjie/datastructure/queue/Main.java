package com.zhuhangjie.datastructure.queue;

public class Main {
  public static void main(String[] args) {
    //Queue<Integer> queue = new LoopQueue<>();
    Queue<Integer> queue = new LinkedListQueue<>();

    for (int i = 0; i < 21; i++) {
      queue.enqueue(i);
      System.out.println(queue);
    }

    for (int i = 0; i < 16; i++) {
      queue.dequeue();
      System.out.println(queue);
    }
    System.out.println(queue);
  }
}

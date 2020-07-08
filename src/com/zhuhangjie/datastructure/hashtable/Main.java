package com.zhuhangjie.datastructure.hashtable;

import java.util.HashMap;
import java.util.HashSet;

public class Main {

  public static void main(String[] args) {
    // 注意！！！！Java中的hashCode其实是个int值，所以是有正负的。
    // 虽然索引是非负的，但这是合理的，因为我们通常是以整型模一个素数来获取索引，
    // 而这个素数又是通过哈希表来决定的，所以要有哈希表才有把整型转换成索引的策略。
    int a = 42;
    System.out.println(((Integer)a).hashCode());
    int b = -42;
    System.out.println(((Integer)b).hashCode());
    double c = 3.1415926;
    System.out.println(((Double)c).hashCode());
    String d = "zhuhangjie";
    System.out.println(d.hashCode());

    Student student = new Student(4,6,"Hangjie","zhu");
    System.out.println(student.hashCode());

    new HashSet<>().add(student);
    new HashMap<Student,Integer>().put(student,100);
    
    Student student2 = new Student(4,6,"Hangjie","zhu");
    System.out.println(student2.hashCode());
  }
}

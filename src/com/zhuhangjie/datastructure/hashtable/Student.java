package com.zhuhangjie.datastructure.hashtable;

import java.util.Objects;

public class Student {

  int grade;
  int cls;
  String firstName;
  String lastName;

  public Student(int grade, int cls, String firstName, String lastName) {
    this.grade = grade;
    this.cls = cls;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  //如果没有覆盖hashCode方法，就会使用Object类的hashCode方法，根据对象在内存的地址，转换成整型
  @Override
  public int hashCode() {
    int B = 31;
    int hash = 0;
    //转换为一个B进制的数
    //如果整型溢出，又会回到最小，结果依然是整型
    hash = hash * B + grade;
    hash = hash * B + cls;
    hash = hash * B + firstName.toLowerCase().hashCode();
    hash = hash * B + lastName.toLowerCase().hashCode();
    return hash;
  }

  @Override
  public boolean equals(Object o) {
    //先看是不是同一个引用
    if (this == o) {
      return true;
    }
    //再看是不是空，或者二者的类文件是否一样。为空或者class不一样都不行。
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Student student = (Student) o;
    //再一一比较每一个属性
    return grade == student.grade &&
        cls == student.cls &&
        Objects.equals(firstName, student.firstName) &&
        Objects.equals(lastName, student.lastName);
  }
}

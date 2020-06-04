package com.zhuhangjie.datastructure.stack;

/**
 * 1.栈的基本介绍
 *
 * 栈也是一种数据结构
 * 相比数组，栈对应的操作是数组的子集
 * 只能从一端添加元素，也只能从一端取出元素
 * 这一端叫做栈顶
 */

/**
 * 2.栈的特性及应用
 *
 * 先进后出 Last In First Out (LIFO)
 * （1）无处不在的Undo操作(撤销)
 * （2）程序调用的系统栈，记录函数调用的位置，比如A2,B2,进入的时候入栈，调用函数结束的时候出栈
 * 也就是当一个子函数结束时能自动回到母函数调用子函数的位置。
 */
public interface Stack<E>{
  int getSize();
  boolean isEmpty();
  void push(E e);
  E pop();
  E peek();
}

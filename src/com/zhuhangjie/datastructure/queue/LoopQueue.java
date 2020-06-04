package com.zhuhangjie.datastructure.queue;


/**
 * 为了解决数组队列出队时间复杂度问题，引入了循环队列
 * 循环队列就是用两个标志来记录队列的头和尾，这里定义为front：队列第一个元素的索引，tail：下一个待插入元素的位置。
 * 队列为空时front == tail = 0，但是如果设front == tail代表队列已满的话就分不清是空还是满了
 * 所以就牺牲一个元素的空间，用tail + 1 == front代表队列已满，更精确的说是(tail + 1)/capacity == front
 * 循环队列关键的循环就是每次插入元素维护tail的时候，使用的是(tail + 1)/capacity来计算tail的下一个位置，使其能循环。
 *
 * 复杂度上：
 * 入队和出队都是O(1)均摊复杂度，相比于数组队列要快很多
 * 其他操作也都是O(1)
 */
public class LoopQueue<E> implements Queue<E>{

  private E[] data;
  private int front,tail;
  private int size;


  public LoopQueue() {
    this(10);
  }

  public LoopQueue(int capacity) {
    data = (E[])new Object[capacity + 1];
  }

  public int getCapacity() {
    return data.length - 1;
  }

  @Override
  public void enqueue(E e) {
    //判断当前数组是否满
    if ((tail + 1) % data.length == front) {
      resize(getCapacity() * 2);
    }
    data[tail] = e;
    tail = (tail + 1) % data.length;
    size++;
  }

  @Override
  public E dequeue() {
    if (isEmpty()) {
      throw new IllegalArgumentException("不能从空队列取值");
    }
    E ret = data[front];
    data[front] = null;
    front = (front + 1) % data.length;
    size--;
    if (size == getCapacity() /4 && getCapacity() / 2 != 0) {
      resize(getCapacity() / 2);
    }
    return ret;
  }

  private void resize(int capacity) {
    E[] newArr = (E[])new Object[capacity + 1];
    for (int i = 0; i < size; i++) {
      newArr[i] = data[(front + i) % data.length];
    }
    data = newArr;
    front = 0;
    tail = size;
  }

  @Override
  public E getFront() {
    if (isEmpty()) {
      throw new IllegalArgumentException("不能从空队列取值");
    }
    return data[front];
  }

  @Override
  public int getSize() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return front == tail;
  }

  @Override
  public String toString() {
    StringBuilder res = new StringBuilder();
    res.append(String.format("Queue:size=%d,capacity=%d ", size, getCapacity()));
    res.append("front [");
    for (int i = front; i != tail; i = (i + 1) % data.length) {
      res.append(data[i]);
      if (i + 1 != tail) {
        res.append(", ");
      }
    }
    res.append("] tail");
    return res.toString();
  }
}

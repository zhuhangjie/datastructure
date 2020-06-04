package com.zhuhangjie.datastructure.queue;


/**
 * 1.这个类因为只用到头和尾，不会涉及到中间的步骤操作
 * 所以不需要虚拟头节点来统一操作
 *
 * 2.作为数组会有enqueue和dequeue两个最重要的操作
 * 链表头增加删除查看本来就是O(1)复杂度的操作，但是链表尾这些操作都为O(n)
 * 我们可以在记录head头的同时新添一个tail来记录尾巴
 * 但是我们会发现在tail进行添加操作复杂度为O(1)而进行删除操作复杂度为O(n)，因为是单向链表。
 * 因此我们可以把改造后的链表头当做队列头，链表的尾巴代表队列的尾巴，这样不管是enqueue还是dequeue都是O(1)复杂度
 *
 * @param <E>
 */
public class LinkedListQueue<E> implements Queue<E> {

  private class Node{
    public E e;
    public Node next;

    public Node(E e, Node next) {
      this.e = e;
      this.next = next;
    }

    public Node() {
      this(null, null);
    }

    public Node(E e) {
      this(e,null);
    }

    @Override
    public String toString() {
      return e.toString();
    }
  }

  private Node head, tail;

  private int size;

  public LinkedListQueue() {
    head = null;
    tail = null;
    size = 0;
  }

  @Override
  public void enqueue(E e) {

    //当添加第一个元素的时候头尾节点都是这个元素
    if (tail == null) {
      tail = new Node(e);
      head = tail;
    } else {
      tail.next = new Node(e);
      tail = tail.next;
    }
    size++;
  }

  @Override
  public E dequeue() {
    if (isEmpty()) {
      throw new IllegalArgumentException("空队列不能出队");
    }
    Node retNode = head;
    head = head.next;
    retNode.next = null;
    if (head == null) {
      tail = null;
    }
    size--;
    return retNode.e;
  }

  @Override
  public E getFront() {
    return head.e;
  }

  @Override
  public int getSize() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("Queue: front ");
    Node cur = head;
    while (cur != null) {
      sb.append(cur.e + "->");
      cur = cur.next;
    }
    sb.append("Null tail");
    return sb.toString();
  }
}

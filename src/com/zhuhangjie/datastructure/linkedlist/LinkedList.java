package com.zhuhangjie.datastructure.linkedlist;

/**
 * 1.链表是一种真正的动态结构
 */

/**
 * 2.特点
 * 优点：真正的动态
 * 缺点：与数组相比丧失了随机访问能力，因此不适用于索引有语意
 */

/**
 * 3.复杂度分析
 * (1)增
 * addLast(e) O(n)
 * addFirst(e) O(1)
 * add(index,e) O(n/2) = O（n）
 * (2)删
 * removeLast() O(n)
 * removeFirst() O(1)
 * remove（index） O(n/2) = O（n）
 * (3)改
 * set(index,e) O(n)
 * (4)查
 * get(index) O(n)
 * contains(e) O(n)
 * 总结：增删改查全是O(n)级别的，有点坑
 * 只对头操作都是O(1)级别的，所以用链表不用改和随机查。
 * 一般用对头增，对头删对头查，这尼玛不就是栈吗
 */
public class LinkedList<E> {

  private class Node {
    public E e;
    public Node next;

    public Node(E e, Node next) {
      this.e = e;
      this.next = next;
    }

    public Node(E e) {
      this(e, null);
    }

    public Node() {
      this(null, null);
    }

    @Override
    public String toString() {
      return e.toString();
    }
  }

  //添加一个虚拟头节点，作为0索引节点之前的一个虚拟节点，对用户是不存在的。
  private Node dummyHead;
  private int size;

  public LinkedList() {
    //初始化一个空的Node作为虚拟头节点
    this.dummyHead = new Node(null, null);
    this.size = 0;
  }

  public int getSize() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void addFirst(E e) {
    add(0, e);
    size++;
  }

  public void recursionAddLast(E e) {
    dummyHead.next = recursionAddLast(dummyHead.next,e);
  }

  private Node recursionAddLast(Node node, E e) {
    if (node == null) {
      size++;
      return new Node(e);
    }
     node.next = recursionAddLast(node.next, e);
    return node;
  }

  //插入操作是针对要插入索引前一个节点进行的
  public void add(int index, E e) {
    if (index < 0 || index > size) {
      throw new IllegalArgumentException("index 不能小于0或者大于size");
    }
    //这里一开始就在第第一个位置上，所以后面的index要-1，有点奇怪，反正就是规律。
    //针对上一条注解，改为虚拟头节点之后就正常了，从0的prv到index的prv正好移动index次，牛逼！！
    Node prv = dummyHead;
    for (int i = 0; i < index; i++) {
      prv = prv.next;
    }
    prv.next = new Node(e, prv.next);
    size++;

  }

  public void addLast(E e) {
    add(size, e);
  }

  public E get(int index) {
    if (index < 0 || index >= size) {
      throw new IllegalArgumentException("index 不能小于0或者大于size");
    }
    Node cur = dummyHead.next;
    for (int i = 0; i < index; i++) {
      cur = cur.next;
    }
    return cur.e;
  }

  public E getFirst() {
    return get(0);
  }

  public E getLast() {
    return get(size-1);
  }

  public void set(int index, E e) {
    if (index < 0 || index > size) {
      throw new IllegalArgumentException("index 不能小于0或者大于size");
    }

    Node cur = dummyHead.next;
    for (int i = 0; i < index; i++) {
      cur = cur.next;
    }
    cur.e = e;
  }

  public boolean contains(E e) {
    Node cur = dummyHead.next;
    while (cur != null) {
      if (cur.e.equals(e)) {
        return true;
      }
      cur = cur.next;
    }
    return false;
  }

  public E remove(int index) {
    if (index < 0 || index >= size) {
      throw new IllegalArgumentException("index 不能小于0或者大于size");
    }
    Node prv = dummyHead;
    for (int i = 0; i < index; i++) {
      prv = prv.next;
    }
    Node retNode = prv.next;
    prv.next = retNode.next;
    retNode.next = null;
    size--;
    return retNode.e;
  }

  public E removeFirst() {
    return remove(0);
  }

  public E removeLast() {
    return remove(size - 1);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    //这种写法和上面的while是一样的
    for (Node cur = dummyHead.next; cur != null;cur = cur.next) {
      sb.append(cur.e + "->");
    }
    sb.append("NULL");
    return sb.toString();
  }
}

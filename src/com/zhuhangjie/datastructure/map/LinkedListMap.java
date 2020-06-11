package com.zhuhangjie.datastructure.map;


import java.util.ArrayList;

/**
 * @param <K>
 * @param <V>
 */
public class LinkedListMap<K,V> implements Map<K,V>{

  private class Node {
    public K key;
    public V value;
    public Node next;

    public Node() {
      this(null, null, null);
    }
    public Node(K key) {
      this(key, null , null);
    }
    public Node(K key, V value) {
      this(key, value , null);
    }

    public Node(K key, V value, Node next) {
      this.key = key;
      this.value = value;
      this.next = next;
    }
    public String toString() {
      return  this.key + ":" + this.value;
    }
  }

  private Node dummyHead;
  private int size;

  public LinkedListMap() {
    this.dummyHead = new Node();
    this.size = 0;
  }

  /**
   * 工具方法，使用这个方法来实现contains，get等方法会异常方便
   * @param key
   * @return
   */
  private Node getNode(K key) {
    Node cur = dummyHead.next;
    while (cur != null) {
      if (cur.key.equals(key)){
        return cur;
      }
      cur = cur.next;
    }
    return null;
  }

  @Override
  public boolean contains(K key) {
    //从dummyHead后面一个元素开始检查
    return getNode(key) != null;
  }

  @Override
  public V get(K key) {
    Node node = getNode(key);
    return node == null ? null : node.value;
  }

  /**
   * key必须是唯一的！
   * @param key
   * @param value
   */
  @Override
  public void add(K key, V value) {
    Node node = getNode(key);
    if (node == null) {
      //如果不存在就添加
      dummyHead.next = new Node(key,value,dummyHead.next);
      size++;
    } else {
      //如果存在就修改
      node.value = value;
    }
  }

  @Override
  public void set(K key, V newValue) {
    Node node = getNode(key);
    if (node == null) {
      //如果不存在就添加
      throw new IllegalArgumentException("key not exist!");
    } else {
      //如果存在就修改
      node.value = newValue;
    }
  }

  @Override
  public V remove(K key) {
    Node pre = dummyHead;
    while (pre.next != null) {
      if (pre.next.key.equals(key)) {
        break;
      }
        pre = pre.next;
    }
    if (pre.next != null) {
      Node delNode = pre.next;
      pre.next = delNode.next;
      delNode.next = null;
      size--;
      return delNode.value;
    }
    return null;
  }

  @Override
  public int getSize() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  //测试
  public static void main(String[] args){

    System.out.println("Pride and Prejudice");

    ArrayList<String> words = new ArrayList<>();
    if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
      System.out.println("Total words: " + words.size());

      LinkedListMap<String, Integer> map = new LinkedListMap<>();
      for (String word : words) {
        if (map.contains(word))
          map.set(word, map.get(word) + 1);
        else
          map.add(word, 1);
      }

      System.out.println("Total different words: " + map.getSize());
      System.out.println("Frequency of PRIDE: " + map.get("pride"));
      System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
    }

    System.out.println();
  }
}

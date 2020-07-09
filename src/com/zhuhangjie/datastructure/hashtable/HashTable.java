package com.zhuhangjie.datastructure.hashtable;



import java.util.TreeMap;

public class HashTable<K, V> {

  private TreeMap<K, V>[] hashtable;
  private int M;
  private int size;

  public HashTable(int M) {
    this.M = M;
    //这里创建不能用泛型
    hashtable = new TreeMap[M];
    for (int i = 0; i < M; i++) {
      hashtable[i] = new TreeMap<>();
    }
    size = 0;
  }

  public HashTable() {
    this(97);
  }

  //将key的hash转化成当前类能用的索引
  private int hash(K key) {
    //结果为key的hash值的绝对值再对M取模
    return (key.hashCode() & 0x7fffffff) % M;
  }

  public void put(K key,V value) {
    //先找到key转化为索引后对应的treeMap
    TreeMap<K,V> map = hashtable[hash(key)];
    //存在就更新，不存在就添加
    map.put(key,value);
    //如果是新增，还要维护下size
    if (!map.containsKey(key)) {
      size++;
    }
  }

  public V remove(K key) {
    TreeMap<K,V> map = hashtable[hash(key)];
    V ret = null;
    if (map.containsKey(key)) {
      ret = map.remove(key);
      size--;
    }
    return ret;
  }

  public V get(K key) {
    return hashtable[hash(key)].get(key);
  }

  private boolean contains(K key) {
    return hashtable[hash(key)].containsKey(key);
  }

  public int getSize(){
    return size;
  }
}

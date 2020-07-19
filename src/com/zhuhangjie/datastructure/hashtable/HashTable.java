package com.zhuhangjie.datastructure.hashtable;


import java.util.Map.Entry;
import java.util.TreeMap;

public class HashTable<K, V> {

  private static final int upperTol = 10;
  private static final int lowerTol = 2;
  private final int[] capacity = {};
  private int capacityIndex = 0;

  private TreeMap<K, V>[] hashtable;
  private int M;
  private int size;

  public HashTable() {
    this.M = capacity[capacityIndex];
    //这里创建不能用泛型
    hashtable = new TreeMap[M];
    for (int i = 0; i < M; i++) {
      hashtable[i] = new TreeMap<>();
    }
    size = 0;
  }

  //将key的hash转化成当前类能用的索引
  private int hash(K key) {
    //结果为key的hash值的绝对值再对M取模
    return (key.hashCode() & 0x7fffffff) % M;
  }

  public void put(K key, V value) {
    //先找到key转化为索引后对应的treeMap
    TreeMap<K, V> map = hashtable[hash(key)];
    //存在就更新，不存在就添加
    map.put(key, value);
    //如果是新增，还要维护下size
    if (!map.containsKey(key)) {
      size++;
    }
    if (size >= M * upperTol && capacityIndex + 1 < capacity.length) {
      this.capacityIndex++;
      resize(capacity[capacityIndex]);
    }
  }

  public V remove(K key) {
    TreeMap<K, V> map = hashtable[hash(key)];
    V ret = null;
    if (map.containsKey(key)) {
      ret = map.remove(key);
      size--;
    }
    if (size < M * lowerTol && capacityIndex - 1 >= 0) {
      capacityIndex--;
      resize(capacity[capacityIndex]);
    }
    return ret;
  }

  private void resize(int newM) {
    TreeMap<K, V>[] newHashtable = new TreeMap[newM];
    //初始化新的哈希表
    for (int i = 0; i < newM; i++) {
      newHashtable[i] = new TreeMap();
    }
    //老的M记录下，遍历老哈希表要用
    int oldM = this.M;
    //更新一下M值,因为下面的hash()方法会用到M
    this.M = newM;
    //遍历老的哈希表，把entry取出来再放到新的里面
    for (int i = 0; i < oldM; i++) {
      TreeMap<K, V> map = hashtable[i];
      for (Entry<K, V> entry : map.entrySet()) {
        newHashtable[hash(entry.getKey())].put(entry.getKey(), entry.getValue());
      }
    }
    this.hashtable = newHashtable;
  }

  public V get(K key) {
    return hashtable[hash(key)].get(key);
  }

  private boolean contains(K key) {
    return hashtable[hash(key)].containsKey(key);
  }

  public int getSize() {
    return size;
  }
}

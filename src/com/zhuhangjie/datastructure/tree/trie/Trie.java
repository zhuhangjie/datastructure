package com.zhuhangjie.datastructure.tree.trie;

import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 1.特点时间复杂度只和查询或者添加的单词的长度有关，所以数据越大优势越大（无敌！！）。
 * 2.最大的问题就是空间，查询效率奇高的代价就是空间！因为每个节点都会存放一个treemap，
 * 对此可以使用Compressed Trie（压缩字典树，缺点不易维护）
 * 3.三分搜索树Trie
 */
public class Trie {

  private class Node {

    public boolean isWord;
    public TreeMap<Character, Node> next;

    public Node(boolean isWord) {
      this.isWord = isWord;
      next = new TreeMap<>();
    }

    public Node() {
      this(false);
    }
  }

  private Node root;
  private int size;

  public Trie() {
    this.root = new Node();
    this.size = 0;
  }

  public int getSize() {
    return size;
  }

  public void add(String word) {
    Node cur = root;

    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      //如果当前节点下一个节点没有这个单词就创建一个新的节点代表该单词
      cur.next.putIfAbsent(c, new Node());
      //设置当前节点为下一个字母代表的节点
      cur = cur.next.get(c);
    }
    //查看最后一个单词是否已经存在过，如果不存在，就设置为true
    if (!cur.isWord) {
      cur.isWord = true;
      size++;
    }
  }

  //逻辑和添加基本一样
  public boolean contains(String word) {
    Node cur = root;

    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      //如果当前节点下一个节点没有这个单词就创建一个新的节点代表该单词
      if (cur.next.get(c) == null) {
        return false;
      }
      //设置当前节点为下一个字母代表的节点
      cur = cur.next.get(c);
    }

    return cur.isWord;
  }

  public boolean isPrefix(String prefix) {
    Node cur = root;
    for (int i = 0; i < prefix.length(); i++) {
      char c = prefix.charAt(i);
      if (cur.next.get(c) == null) {
        return false;
      }
      cur = cur.next.get(c);
    }
    return true;
  }

  public void addWithRecursion(String word) {
    addWithRecursion(root, word, 0);
  }

  private void addWithRecursion(Node root, String word, int index) {
    //查询递归是否结束,如果结束的话判断isWord
    if (index == word.length()) {
      if (!root.isWord) {
        root.isWord = true;
        size++;
      }
      return;
    }
    //不是最后一个节点，就-正常插入
    char c = word.charAt(index);
    root.next.putIfAbsent(c, new Node());
    addWithRecursion(root.next.get(c), word, index + 1);
  }

  //匹配查询，"."代表任意
  public boolean search(String word) {
    return match(root, word, 0);
  }

  private boolean match(Node node, String word, int index) {
    if (word.length() == index) {
      return node.isWord;
    }

    char c = word.charAt(index);
    if (c != '.') {
      if (node.next.get(c) == null) {
        return false;
      }
      return match(node.next.get(c), word, index + 1);

    } else {
      for (Entry<Character, Node> characterNodeEntry : node.next.entrySet()) {
        if (match(characterNodeEntry.getValue(), word, index + 1)) {
          return true;
        }
      }
      return false;
    }
  }

  public static void main(String[] args) {
    Trie trie = new Trie();
    //["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
    //[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],[""]]
    trie.add("bad");
    trie.add("dad");
    trie.add("mad");
    System.out.println(trie.search("pad"));
    System.out.println(trie.search("bad"));
    System.out.println(trie.search(".ad"));
    System.out.println(trie.search("b.."));
  }
}

package com.zhuhangjie.datastructure.tree.avltree;

import com.zhuhangjie.datastructure.array.Array;
import com.zhuhangjie.datastructure.map.Map;
import java.util.ArrayList;
import java.util.logging.Level;
/**
 * 1.平衡二叉树定义 每个节点的左子树和右子树的高度差不能超过1
 */

/**
 * Created by zhuhangjie 2020/6/12 15:37
 */
public class AVLTree<K extends Comparable<K>, V> implements Map<K, V> {

  //每个需要添加一个高度
  private class Node {

    public K key;
    public V value;
    public int height;
    public Node left, right;

    public Node(K key, V value) {
      this.key = key;
      this.value = value;
      this.left = null;
      this.right = null;
      this.height = 1;
    }

    public String toString() {
      return key + ":" + value;
    }
  }

  private Node root;
  private int size;

  public AVLTree() {
    this.root = null;
    this.size = 0;
  }

  //辅助方法判断当前树是否二叉树
  private boolean isBST(Node node) {
    if (node == null) {
      return true;
    }
    //思路是根据二叉树特点，总序遍历能得到升序数列
    ArrayList<K> keyList = new ArrayList<>();
    inOrder(root, keyList);
    for (int i = 1; i < keyList.size(); i++) {
      if (keyList.get(i).compareTo(keyList.get(i - 1)) < 0) {
        return false;
      }
    }
    return true;
  }

  private void inOrder(Node node, ArrayList list) {
    if (node == null) {
      return;
    }
    inOrder(node.left, list);
    list.add(node.key);
    inOrder(node.right, list);
  }

  //辅助方法判断当前树是否平衡
  private boolean isBalanced() {
    return isBalanced(root);
  }

  private boolean isBalanced(Node node) {
    if (node == null) {
      return true;
    }
    if (Math.abs(getBalanceFactor(node)) > 1) {
      return false;
    }
    return isBalanced(node.left) && isBalanced(node.right);
  }

  //辅助方法获得平衡因子
  public int getBalanceFactor(Node node) {
    if (node == null) {
      return 0;
    }
    return getHeight(node.left) - getHeight(node.right);
  }

  //辅助方法获得高度
  private int getHeight(Node node) {
    if (node == null) {
      return 0;
    }
    return node.height;
  }

  @Override
  public void add(K key, V value) {
    root = add(root, key, value);
  }

  //需要维护每个节点的高度
  private Node add(Node node, K key, V value) {
    //为空了就直接插入
    if (node == null) {
      size++;
      return new Node(key, value);
    }
    //否则比较递归路径。这里要针对相同的key判断
    if (key.compareTo(node.key) < 0) {
      node.left = add(node.left, key, value);
    } else if (key.compareTo(node.key) < 0) {
      node.right = add(node.right, key, value);
    } else {
      node.value = value;
    }
    //在子树递归完之后可以计算当前树的高度,为左右子树中最大高度+1
    node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    //判断平衡因子是否大于1
    int balanceFactor = getBalanceFactor(node);
    if (Math.abs(balanceFactor) > 1) {
      System.out.println("不平衡" + balanceFactor);
    }
    //当该节点左子树高度大于右子树，且左子树节点的左子树高度大于等于右子树，进行左旋转
    if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
      return rightRotate(node);
    }
    return node;
  }

  /**
   * 对于一个不平衡的树y -> x -> z
   */
  private Node rightRotate(Node y) {
    //获得当前节点的左子树
    Node x = y.left;
    //把当前节点的左子树变成原来左子树的右子树
    y.left = x.right;
    //把做子树的右子树变成当前节点,两部搞定
    x.right = y;
    //别忘记更新height值,先更新y节点height值再更新x的height值
    y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

    x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
    //最后返回原来的左子树
    return x;
  }

  /**
   * 和链表一样，写一个辅助方法来帮助实现其他方法
   */
  private Node getNode(Node node, K key) {
    if (node == null) {
      return null;
    }

    if (key.compareTo(node.key) < 0) {
      return getNode(node.left, key);
    } else if (key.compareTo(node.key) > 0) {
      return getNode(node.right, key);
    } else {
      return node;
    }
  }

  @Override
  public boolean contains(K key) {
    return getNode(root, key) != null;
  }

  @Override
  public V get(K key) {
    Node node = getNode(root, key);
    return node == null ? null : node.value;
  }

  @Override
  public void set(K key, V newValue) {
    Node node = getNode(root, key);
    if (node != null) {
      node.value = newValue;
    } else {
      throw new IllegalArgumentException("key not exist");
    }
  }

  //找到最小值
  private Node minimum(Node node) {
    if (node.left == null) {
      return node;
    }
    return minimum(node.left);
  }

  private Node removeMin(Node node) {
    //左子树为空，代表当前为最小值
    if (node.left == null) {
      Node rightNode = node.right;
      node.right = null;
      size--;
      return rightNode;
    }
    node.left = removeMin(node.left);
    return node;
  }

  /**
   * 这倒霉方法很麻烦要借助其他两个方法
   */
  @Override
  public V remove(K key) {
    Node node = getNode(root, key);
    if (node == null) {
      throw new IllegalArgumentException("key is not exist");
    }
    root = remove(root, key);
    return node.value;
  }

  public Node remove(Node node, K key) {
    if (node == null) {
      return null;
    }
    //找到待删除节点
    if (node.key.equals(key)) {
      Node left = node.left;
      Node right = node.right;
      node.left = node.right = null;
      if (right == null) {
        return left;
      } else if (left == null) {
        return right;
      } else {
        Node newNode = minimum(right);
        //删除最小节点后的右子树
        Node newRight = removeMin(right);
        newNode.left = left;
        newNode.right = newRight;
        //这里需要size--但是上面的removeMin中已经有减了但是实际没减，所以这里也不用减了
        //size--;
        return newNode;
      }
    } else if (key.compareTo(node.key) < 0) {
      node.left = remove(node.left, key);
    } else {
      node.right = remove(node.right, key);
    }

    return node;
  }


  @Override
  public int getSize() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }
}

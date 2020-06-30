package com.zhuhangjie.datastructure.tree.avltree;

import com.zhuhangjie.datastructure.FileOperation;
import com.zhuhangjie.datastructure.map.Map;
import java.util.ArrayList;
/**
 * 1.平衡二叉树定义 每个节点的左子树和右子树的高度差不能超过1
 * 2. AVL树保持平衡的方法是：在加入元素时，在递归后序判断当前节点平衡因子
 * 如果平衡因子大于1，就根据四种不平衡的情况对当前节点操作，四种不同情况分别是:
 * LL、RR、LR、RL。
 * LL:右旋转。
 * RR：左旋转。
 * LR：左旋转再右旋转。
 * RL：右旋转再左旋转。
 */

/**
 * Created by zhuhangjie 2020/6/12 15:37
 */
public class AVLTree<K extends Comparable<K>, V>{

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
  public boolean isBST() {
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
  public boolean isBalanced() {
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
  private int getBalanceFactor(Node node) {
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

  /**
   * 平衡总共分四种情况
   * @param key
   * @param value
   */
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
    } else if (key.compareTo(node.key) > 0) {
      node.right = add(node.right, key, value);
    } else {
      node.value = value;
    }
    //在子树递归完之后可以计算当前树的高度,为左右子树中最大高度+1
    node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    //判断平衡因子是否大于1
    int balanceFactor = getBalanceFactor(node);
    //根据四种情况维护平衡
    //LL
    if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
      return rightRotate(node);
    }
    //RR
    if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
      return leftRotate(node);
    }
    //LR
    if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
      node.left = leftRotate(node.left);
      return rightRotate(node);
    }
    //RL
    if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
      node.right = rightRotate(node.right);
      return leftRotate(node);
    }

    return node;
  }

  /**   右旋转
   *    对节点y进行向右旋转操作，返回旋转后新的根节点x
   *           y                              x
   *          / \                           /   \
   *         x   T4     向右旋转 (y)        z     y
   *        / \       - - - - - - - ->    / \   / \
   *       z   T3                       T1  T2 T3 T4
   *      / \
   *    T1   T2
   *
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
   *  对节点y进行向左旋转操作，返回旋转后新的根节点x
   *     y                             x
   *   /  \                          /   \
   *  T1   x      向左旋转 (y)       y     z
   *      / \   - - - - - - - ->   / \   / \
   *    T2  z                     T1 T2 T3 T4
   *       / \
   *      T3 T4
   * @param y
   * @return
   */
  private Node leftRotate(Node y) {
    //获得当前节点的右
    Node x = y.right;
    //把当前节点的左子树变成原来左子树的右子树
    y.right = x.left;
    //把做子树的右子树变成当前节点,两部搞定
    x.left = y;
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

  public boolean contains(K key) {
    return getNode(root, key) != null;
  }

  public V get(K key) {
    Node node = getNode(root, key);
    return node == null ? null : node.value;
  }

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

  /**
   * 这倒霉方法很麻烦要借助其他两个方法
   */
  public V remove(K key) {
    Node node = getNode(root, key);
    if (node != null) {
      root = remove(root, key);
      return node.value;
    }
    return null;
  }

  public Node remove(Node node, K key) {
    if (node == null) {
      return null;
    }
    //找到待删除节点
    Node retNode = null;
    if (node.key.equals(key)) {
      Node left = node.left;
      Node right = node.right;
      node.left = node.right = null;
      if (right == null) {
        retNode = left;
      } else if (left == null) {
        retNode = right;
      } else {
        Node successor = minimum(right);
        //删除最小节点后的右子树,注意！！！小bug！！这一步可能会打破平衡，所以需要改写,
        //successor.right = removeMin(right);

        //由于之前的minimum已经计算出了最小值，所以可以直接复用当前的递归方法。
        //从右子树中删除我们上一步找到的最小值newNode.key，结果和removeMin()方法是一样的。
        successor.right = remove(right,successor.key);
        successor.left = left;
        //这里需要size--但是上面的removeMin中已经有减了但是实际没减，所以这里也不用减了
        //size--;
        node.left = node.right = null;
        retNode = successor;
      }
    } else if (key.compareTo(node.key) < 0) {
      node.left = remove(node.left, key);
      retNode = node;
    } else {
      node.right = remove(node.right, key);
      retNode = node;
    }
    //防止空指针异常
    if (retNode == null) return null;

    //在子树递归完之后可以计算当前树的高度,为左右子树中最大高度+1
    retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;
    //判断平衡因子是否大于1
    int balanceFactor = getBalanceFactor(retNode);
    //根据四种情况维护平衡
    //LL
    if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
      return rightRotate(retNode);
    }
    //RR
    if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
      return leftRotate(retNode);
    }
    //LR
    if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
      retNode.left = leftRotate(retNode.left);
      return rightRotate(retNode);
    }
    //RL
    if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
      retNode.right = rightRotate(retNode.right);
      return leftRotate(retNode);
    }

    return retNode;
  }


  public int getSize() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }


  public static void main(String[] args){

    System.out.println("Pride and Prejudice");

    ArrayList<String> words = new ArrayList<>();
    if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
      System.out.println("Total words: " + words.size());

      AVLTree<String, Integer> map = new AVLTree<>();
      for (String word : words) {
        if (map.contains(word))
          map.set(word, map.get(word) + 1);
        else
          map.add(word, 1);
      }

      System.out.println("Total different words: " + map.getSize());
      System.out.println("Frequency of PRIDE: " + map.get("pride"));
      System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));

      System.out.println("is BST : " + map.isBST());
      System.out.println("is Balanced : " + map.isBalanced());

      for (String word: words) {
        map.remove(word);
        if (!map.isBST() || !map.isBalanced()) {
          throw new RuntimeException("Error");
        }
      }
    }

    System.out.println();
  }
}

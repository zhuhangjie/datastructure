package com.zhuhangjie.datastructure.tree.binarysearchtree;

/**
 * 一、特点
 * 二分搜索树是二叉树
 * 二分搜索树的每个节点的值特点：
 * 1.大于其左子树的所有节点的值
 * 2.小于其右子树的所有节点的值
 * <p>
 * 存储的元素必须有比较性
 */


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二、前序，中序和后续的深入理解()
 * treeOrder.png图所示，其实前中后序遍历的顺序都是一样的，只是输出时机不同。
 *
 */

/**
 * 可以给每个根维护一个size值，使得实现rank和select更加容易。
 * @param <E>
 */
public class BinarySearchTree<E extends Comparable> {

  private class Node {
    public E e;
    public Node left, right;

    public Node(E e) {
      this.e = e;
      this.left = null;
      this.right = null;
    }
  }

  private Node root;
  private int size;

  public BinarySearchTree() {
    this.root = null;
    this.size = 0;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void add(E e) {
    root = add(root, e);
  }

  /**
   * 这个是二分搜索树中的大BOSS，最复杂的一个操作
   * 删除真正的难点是删除一个左右子树都不为空的节点
   * 使用Hibbard提出的-Hibbard Deletion，如图deleteNodeWithTowChildren.png
   * 其实有左子树的最大值当做新节点效果也是一样的。
   * @return
   */
  public void remove(E e) {
    if (size == 0) {
      throw new IllegalArgumentException("树不能为空");
    }
    root = remove(root, e);
  }

  private Node remove(Node node, E e) {
    if (node == null) {
      return null;
    }
    //如果找到了待删除那个节点
    if (node.e.equals(e)) {
      //记录他的左子树

      if (node.right == null) {
        //如果右子树为空直接返回左子树根节点作为代替被删除节点
        Node leftNode = node.left;
        node.left = null;
        size--;
        return leftNode;
      }

      if (node.left == null) {
        //如果左子树为空直接返回左子树根节点作为代替被删除节点
        Node rightNode = node.right;
        node.right = null;
        size--;
        return rightNode;
      }

      //获得右子树最小节点
      Node newNode = minimum(node.right);
      //记录被删除最小值后的右子树，这里面有个size--但是元素实际还在所以后面要size++
      newNode.right = removeMin(node.right);
      //size++;
      newNode.left = node.left;
      node.left = node.right = null;
      //这里不用维护size，因为在上面removeMin(node.right)已经减过一次了（虽然减的是那个新的节点，就当抵消了）很微妙！。
      //size--;
      return newNode;

    } else if (e.compareTo(node.e) < 0) {
      node.left = remove(node.left, e);
    } else {
      node.right = remove(node.right, e);
    }
    return node;
  }

  public E removeMax() {
    E ret = maximum();
    root = removeMax(root);
    return ret;
  }

  private Node removeMax(Node node) {
    //如果这个节点是最大节点
    if (node.right == null) {
      //如果该节点有右子树，需要用右子树根节点代替该被删除节点
      Node leftNode = node.left;
      node.left = null;
      size--;
      return leftNode;
    }
    //如果不是最小节点
    node.right = removeMax(node.right);
    return node;
  }

  /**
   * 这里其实有个疑惑，为什么要先得到最小值，再删除最小值呢这不是相当于2遍操作
   * 其实在写的时候会发现，递归只能通过返回值传递一种信息
   * 要得到被删的值，返回值就必须是被删除的那个节点，但是这样就不能用返回值把删除这个操作连结起来了。
   * 所以递归方法还是要返回被删除后的树的根节点，才能在递归调用中记录删除操作，从而顺利删除。
   * @return
   */
  public E removeMin() {
    E ret = minimum();
    root = removeMin(root);
    return ret;
  }

  /**
   * 返回删除最小节点后的node
   * @param node
   * @return
   */
  private Node removeMin(Node node) {
    //如果这个节点是最小节点
    if (node.left == null) {
      //如果该节点有右子树，需要用右子树根节点代替该被删除节点
      Node rightNode = node.right;
      node.right = null;
      size--;
      return rightNode;
    }
    //如果不是最小节点
    node.left = removeMin(node.left);
    return node;
  }


  public E maximum() {
    Node cur = root;
    while (cur.right != null){
        cur = cur.right;
    }
    return cur.e;
  }

  /**
   * 找到最小值
   */
  public E minimum() {
    if (size == 0) {
      throw new IllegalArgumentException("树不能为空");
    }
    return minimum(root).e;
  }

  private Node minimum(Node node) {
    //如果这个节点是最小节点，就返回
    if (node.left == null) {
      return node;
    }
    return minimum(node.left);
  }

  /**
   * 层序遍历，也就是广度遍历，需要用队列
   * 广度优先遍历往往能更快找到问题解，常用语算法的最短路径问题
   * 图的遍历区别就是要记录一下前一个节点是否被访问过
   */
  public void levelOrder() {
    Queue<Node> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      Node node = queue.remove();
      System.out.println(node.e);

      if (node.left != null) {
        queue.add(node.left);
      }
      if (node.right != null) {
        queue.add(node.right);
      }
    }
  }

  public void preOrderNR(){
    Stack<Node> stack = new Stack<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      Node node = stack.pop();
      System.out.println(node.e);
      if (node.right != null){
        stack.push(node.right);
      }
      if (node.left != null) {
        stack.push(node.left);
      }
    }
  }

  public Node add(Node node, E e) {
    //向以node为根的二叉树插入元素e,返回插入e后的根节点
    //由于node是本地变量，直接给node复制new Node对树不会有影响，所以要把结果返回到上一层，由上一层将结果和树关联
    if (node == null) {
      //终止条件
      size++;
      return new Node(e);
    }

    //递归调用,同时把结果关联
    if (e.compareTo(node.e) < 0) {
      node.left = add(node.left, e);
    } else {
      node.right = add(node.right, e);
    }

    return node;
  }

  public boolean contains(E e) {
    return contains(root, e);
  }

  public boolean contains(Node node, E e) {
    if (node == null) {
      return false;
    } else if (node.e.equals(e)) {
      return true;
    } else if (e.compareTo(node.e) < 0) {
      return contains(node.left, e);
    } else {
      return contains(node.right, e);
    }
  }

  public void preOrder() {
    preOrder(root);
  }
  //前序遍历，就是先打印该节点，再打印前子树和后子树
  private void preOrder(Node node) {
    if (node == null) {
      return;
    }
    System.out.println(node.e);
    preOrder(node.left);
    preOrder(node.right);
  }

  //中序遍历结果就是把所有元素进行排序，所以二分搜索树有时也被叫做排序树
  public void inOrder() {
    inOrder(root);
  }

  private void inOrder(Node node) {
    if (node == null) {
      return;
    }
    inOrder(node.left);
    System.out.println(node.e);
    inOrder(node.right);
  }

  public void postOrder() {
    postOrder(root);
  }

  private void postOrder(Node node) {
    if (node == null) {
      return;
    }
    postOrder(node.left);
    postOrder(node.right);
    System.out.println(node.e);
  }
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    generateBSTString(root, 0, sb);
    return sb.toString();
  }

  private void generateBSTString(Node node, int depth, StringBuilder sb) {
    //如果为空就输出一下深度然后返回
    if (node == null) {
      sb.append(generateDepthString(depth) + "null\n");
      return;
    }
    sb.append(generateDepthString(depth) + node.e + "\n");
    generateBSTString(node.left, depth + 1, sb);
    generateBSTString(node.right, depth + 1, sb);
  }

  public String generateDepthString(int depth) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < depth; i++) {
      sb.append("--");
    }
    return sb.toString();
  }
//  臃肿的方法
//  public void add(E e) {
//    if (root == null) {
//      root = new Node(e);
//      size++;
//    } else {
//      add(root, e);
//    }
//  }

//  public void add(Node node, E e) {
//    递归终止情况
//
//    向以node为根的二叉树插入元素
//    这种方式有点臃肿不优雅，但是因为终止判断时，是给node.left或者node.right复制，所以能连接上新插入的值。
//    if (e.equals(node.e)) return;
//    else if (e.compareTo(node.e) < 0 && node.left == null) {
//      node.left = new Node(e);
//      size++;
//      return;
//    }else if (e.compareTo(node.e) > 0 && node.right == null) {
//      node.right = new Node(e);
//      size++;
//      return;
//    }
//
//    //递归调用
//    if (e.compareTo(node.e) < 0) {
//      add(node.left, e);
//    } else {
//      add(node.right, e);
//    }
//
//  }
}

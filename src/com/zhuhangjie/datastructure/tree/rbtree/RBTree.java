package com.zhuhangjie.datastructure.tree.rbtree;


/**
 * 1.红黑树的特点 （1）所有节点不是红色就是黑色 （2）根节点为黑色 （3）叶子结点为黑色 （4）红色节点的子节点为黑色 （5）从根节点到任意叶子节点经过的黑色节点的数量是一样的
 *
 * 2.与AVL树相对比 （1）查询：由于最大的查询负责读可能到达O(2logn)所以比AVL树稍微慢一点 （2）添加：红黑树旋转次数较少所以快些 （2）删除：同上
 */

public class RBTree<K extends Comparable<K>, V> {

  public static final boolean RED = true;
  public static final boolean BLACK = false;

  private class Node {

    public K key;
    public V value;
    public Node left, right;
    public boolean color;

    //一个新节点设置为红色，因为一个新节点一定会去找一个叶子节点融合。
    public Node(K key, V value) {
      this.key = key;
      this.value = value;
      this.left = null;
      this.right = null;
      this.color = RED;
    }

    public String toString() {
      return key + ":" + value;
    }
  }

  private Node root;
  private int size;

  public RBTree() {
    this.root = null;
    this.size = 0;
  }

  private boolean isRed(Node node){
    if (node == null){
      return BLACK;
    }
    return node.color;
  }

  //颜色翻转
  private void flipColors(Node node) {
    node.color = RED;
    node.left.color = BLACK;
    node.right.color = BLACK;
  }

  /**  左旋转
   *      node                     x
   *     /   \     左旋转         /  \
   *    T1   x   --------->   node   T3
   *        / \              /   \
   *       T2 T3            T1   T2
   *   终极理解窍门：把这种情况当做在2-3树种往一个2节点添加1个节点变成3节点的过程
   */
  private Node leftRotate(Node node){
    Node x = node.right;
    //左旋转
    node.right = x.left;
    x.left = node;

    //把新的根节点x颜色设置为node的颜色，有可能是红色，这样还要继续旋转。为什么呢？
    //因为原来node是根节点，而现在x是根节点，根节点的颜色不能变啊！！这就是大道理！根节点变色会麻烦
    x.color = node.color;
    //把被移到左边的node的颜色设置成红色，因为新加的节点尽管在右边，但是二者形成一个3节点。
    //左旋转并没有改变这个3节点的元素，而3节点左边的那个必定是红色。
    node.color = RED;

    return x;
  }

  /**  右旋转
   *        node                   x
   *       /   \     右旋转       /  \
   *      x    T2   ------->   y   node
   *     / \                       /  \
   *    y  T1                     T1  T2
   */

  private Node rightRotate(Node node) {
    Node x = node.left;

    //右旋转
    node.left = x.right;
    x.right = node;

    //维护根节点颜色，这里直接赋成黑色也是对的
    x.color = node.color;
    x.color = BLACK;
    //老实说是为了表示和根节点还是一个融合在一起的节点，所以用红色表示
    //牛逼！在老师的定义中从来没说只有左边的红色才要和父节点融合，红色本身代表的就是要和父节点融合。
    node.color = RED;

    return x;
  }
  public void add(K key, V value) {
    root = add(root, key, value);
    //如果一个红色节点被添加到根部的时候，将其变成黑色节点
    root.color = RED;
  }

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

    //根据图片左子树为黑（如果红的话就要翻转颜色了），右子树为红酒左旋转
    if (isRed(node.right) && !isRed(node.left)) {
      node = leftRotate(node);
    }
    //根据图片如果左子树红左子树的左子树也为红那就右旋转
    if (isRed(node.left) && isRed(node.left.left)) {
      node = rightRotate(node);
    }
    //根据图片如果左子树右子树都为红
    if (isRed(node.left) && isRed(node.right)) {
      flipColors(node);
    }

    return node;
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


  public int getSize() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }
}


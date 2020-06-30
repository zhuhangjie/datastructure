package com.zhuhangjie.datastructure.map;

import java.security.Key;

/**
 * Created by zhuhangjie
 * 2020/6/12 15:37
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public String toString() {
            return key + ":" + value;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
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
        return node;
    }

    /**
     * 和链表一样，写一个辅助方法来帮助实现其他方法
     *
     * @param node
     * @param key
     * @return
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
     *
     * @param key
     * @return
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
        } else if (key.compareTo(node.key) < 0){
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

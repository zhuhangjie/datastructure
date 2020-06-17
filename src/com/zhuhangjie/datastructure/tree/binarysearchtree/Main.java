package com.zhuhangjie.datastructure.tree.binarysearchtree;

public class Main {
  public static void main(String[] args) {
    BinarySearchTree<Integer> bst = new BinarySearchTree<>();
    int[] nums = {5, 3, 6, 8, 4, 2};
    for (int num : nums) {
      bst.add(num);
    }

    bst.remove(3);
    bst.remove(4);
    bst.remove(8);
//    binarysearchtree.preOrder();
//    System.out.println();
//    binarysearchtree.preOrderNR();
//    binarysearchtree.levelOrder();
//    System.out.println(binarysearchtree.removeMin());
//    System.out.println(binarysearchtree.removeMax());
   //System.out.println(binarysearchtree.maximum());
    bst.inOrder();
//    binarysearchtree.postOrder();

//    System.out.println(binarysearchtree);
  }
}

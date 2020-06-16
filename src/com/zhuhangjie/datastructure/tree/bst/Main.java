package com.zhuhangjie.datastructure.tree.bst;

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
//    bst.preOrder();
//    System.out.println();
//    bst.preOrderNR();
//    bst.levelOrder();
//    System.out.println(bst.removeMin());
//    System.out.println(bst.removeMax());
   //System.out.println(bst.maximum());
    bst.inOrder();
//    bst.postOrder();

//    System.out.println(bst);
  }
}

package com.zhuhangjie.datastructure.linkedlist.leetcode;

/**
 * 删除链表中等于给定值 val 的所有节点。
 *
 * 示例:
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 */

/**
 * 第三种解法使用递归
 */
public class Solution3 {
  public ListNode removeElements(ListNode node, int val) {

    //当第一个节点为空的时候直接返回空
    if (node == null){
      return node;
    }
    //当前节点的下一个节点，这种方式不管怎么样都会把递归进行下去
    node.next = removeElements(node.next, val);
    if (node.val == val){
      return node.next;
    } else {
      return node;
    }

  }


}

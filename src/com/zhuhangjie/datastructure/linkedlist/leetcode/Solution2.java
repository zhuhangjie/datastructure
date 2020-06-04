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
 * 第二种解法使用虚拟头节点,统一遍历操作,简单了至少一半代码！！无敌！
 */
public class Solution2 {
  public ListNode removeElements(ListNode head, int val) {
    //设置虚拟头节点，头结点里的值随便填，因为根本无法取到
    ListNode dummyHead = new ListNode(-1);
    dummyHead.next = head;

    ListNode prv = dummyHead;
    while (prv.next != null) {
      if (prv.next.val == val) {
        ListNode delNode = prv.next;
        prv.next = prv.next.next;
        delNode.next = null;
      }else {
        prv = prv.next;
      }
    }
    return dummyHead.next;
  }
}

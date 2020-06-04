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
 * 第一种解法简单粗暴从头往后遍历
 */
public class Solution1 {
  public ListNode removeElements(ListNode head, int val) {
    //1.因为head为待删除元素时，和后面的节点为待删除元素时，操作不一样，所以先处理所有头部待删除的情况。
    //注意这里的head相当于是一个本地变量，和下面的prv一样。
    while (head != null && head.val == val) {
        ListNode delNode = head;
        head = delNode.next;
        delNode.next = null;
    }
    //如果整个数组全为待删除元素，经过上述操作存在被删光可能性，所以要判空
    if (head == null) {
      return null;
    }
    //2.经过上述对头部的操作后，链表头部待删除元素全被删除，当前head肯定存在且不是待删除元素
    //进行正常遍历，结束后返回头节点就行了。
    ListNode prv = head;
    //从head的后一个节点开始检查，如果为空结束循环
    while (prv.next != null) {
      //如果为待删除元素
      if (prv.next.val == val) {
        //把prv的下一个元素元素变成下下个元素，并把中间忽略的那个删除。下次循环会校验新的下一个元素，也就是下下个元素。
        ListNode delNode = prv.next;
        prv.next = prv.next.next;
        delNode = null;
      }else {
        //如果不是待删除元素，就把prv往后移一格，这样不删除下一个元素。下次循环也会校验新的下一个元素，也就是下下个元素。
        prv = prv.next;
      }
    }

    return head;
  }
}

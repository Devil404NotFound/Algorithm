package com.ljp.leecode_cn.offer;

import com.ljp.leecode_cn.other.data_structure.ListNode;

/**
 * 剑指 Offer 24. 反转链表
 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。



 示例:

 输入: 1->2->3->4->5->NULL
 输出: 5->4->3->2->1->NULL


 限制：

 0 <= 节点个数 <= 5000
 */
public class _简单_24_反转链表 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        int[] arr = new int[]{1,2,3,4,5};
        head.createListNode(arr);
        reverseList(head).print();
    }

    /**
     *
     * @param head
     * @return
     * 执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    39.3 MB, 在所有 Java 提交中击败了91.59%的用户
     */
    public static ListNode reverseList(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode node, pre, next;
        node = head;
        pre = null;
        next = head.next;
        while(next != null){
            node.next = pre;
            pre = node;
            node = next;
            next = next.next;
        }
        node.next = pre;
        return node;
    }
}

package com.ljp.leecode_cn.offer;

import com.ljp.leecode_cn.other.data_structure.ListNode;

/** 2021.09.02重做
 * 剑指 Offer 22. 链表中倒数第k个节点
 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。



 示例：

 给定一个链表: 1->2->3->4->5, 和 k = 2.

 返回链表 4->5.
 */
public class _简单_22_链表中倒数第k个节点 {
    /**
    * @Author lijunpeng
    * @Date 2021/9/2 22:29
    * @Description
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    35.9 MB, 在所有 Java 提交中击败了97.48%的用户
     **/
    public ListNode getKthFromEnd(ListNode head, int k) {
        if(head == null){
            return null;
        }
        int size = 1;
        ListNode node = head;
        ListNode preK = head;
        for(int i = 0; node != null && i < k; i++){
            node = node.next;
        }
        while(node != null){
            preK = preK.next;
            node = node.next;
        }
        return preK;
    }
    /** 官方题解一：顺序查找
    * @Author lijunpeng
    * @Date 2021/9/2 22:31
    * @Description
    **/
    public ListNode getKthFromEnd2(ListNode head, int k) {
        int n = 0;
        ListNode node = null;

        for (node = head; node != null; node = node.next) {
            n++;
        }
        for (node = head; n > k; n--) {
            node = node.next;
        }

        return node;
    }
    /** 官方题解二：双指针
    * @Author lijunpeng
    * @Date 2021/9/2 22:32
    * @Description
    **/
    public ListNode getKthFromEnd3(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && k > 0) {
            fast = fast.next;
            k--;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }
}

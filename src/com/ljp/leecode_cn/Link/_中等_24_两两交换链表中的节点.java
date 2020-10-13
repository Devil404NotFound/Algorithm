package com.ljp.leecode_cn.Link;

import com.ljp.leecode_cn.other.data_structure.ListNode;
import com.ljp.leecode_cn.other.data_structure_operation.ListNodeOperation;

/**
 * @author ljp
 * @date 2020/10/13 15:57
 * 每日一题 2020.10.13
24. 两两交换链表中的节点
给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。



示例 1：


输入：head = [1,2,3,4]
输出：[2,1,4,3]
示例 2：

输入：head = []
输出：[]
示例 3：

输入：head = [1]
输出：[1]


提示：

链表中节点的数目在范围 [0, 100] 内
0 <= Node.val <= 100
 */
public class _中等_24_两两交换链表中的节点 {
    public static void main(String[] args) {
        Integer[] input = {1,2,3,4};
        ListNode head = ListNodeOperation.createListNode(input);
        new _中等_24_两两交换链表中的节点().swapPairs(head);
        ListNodeOperation.print(head);
    }

    /**
     * 递归解法
     * @param head
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    36.3 MB, 在所有 Java 提交中击败了82.45%的用户
     */
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }

    /**
     * 官方题解二：迭代
     * @param head
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    36.6 MB, 在所有 Java 提交中击败了33.95%的用户
     */
    public ListNode swapPairs2(ListNode head){
        ListNode dummyHead = new ListNode();
        ListNode temp, node1, node2;
        temp = dummyHead;
        temp.next = head;
        while(temp.next != null && temp.next.next != null){
            node1 = temp.next;
            node2 = node1.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return dummyHead.next;
    }
}

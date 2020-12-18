package com.ljp.leecode_cn.offer;

import com.ljp.leecode_cn.other.data_structure.ListNode;

/**
 * @author ljp
 * @date 2020/12/16 22:43
剑指 Offer 25. 合并两个排序的链表
输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。

示例1：

输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
限制：

0 <= 链表长度 <= 1000

注意：本题与主站 21 题相同：https://leetcode-cn.com/problems/merge-two-sorted-lists/
 */
public class _简单_25_合并两个排序的链表 {
    /**
     *
     * @param l1
     * @param l2
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了98.63%的用户
    内存消耗：
    38.7 MB, 在所有 Java 提交中击败了64.18%的用户
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode p = dummy;
        while(l1 != null && l2 != null) {
            if(l1.val <= l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else{
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        p.next = (l1 != null ? l1 : l2);
        return dummy.next;
    }
}

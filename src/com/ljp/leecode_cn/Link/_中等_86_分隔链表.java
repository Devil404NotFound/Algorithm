package com.ljp.leecode_cn.Link;

import com.ljp.leecode_cn.other.data_structure.ListNode;

/** 2021.01.03
 * @author ljp
 * @date 2021/1/3 14:03
86. 分隔链表
给你一个链表和一个特定值 x ，请你对链表进行分隔，使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。

你应当保留两个分区中每个节点的初始相对位置。



示例：

输入：head = 1->4->3->2->5->2, x = 3
输出：1->2->2->4->3->5
 */
public class _中等_86_分隔链表 {
    /**
     *  双指针
     * @param head
     * @param x
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    37.7 MB, 在所有 Java 提交中击败了71.13%的用户
     */
    public ListNode partition(ListNode head, int x) {
        //新建两个虚拟头指针，一个放小于x，一个存放大于等于x
        ListNode dummy1 = new ListNode();
        ListNode dummy2 = new ListNode();
        ListNode p1, p2, cur;
        p1 = dummy1;
        p2 = dummy2;
        cur = head;
        while(cur != null) {
            if(cur.val < x) {
                p1.next = cur;
                p1 = p1.next;
            }else{
                p2.next = cur;
                p2 = p2.next;
            }
            cur = cur.next;
        }
        p2.next = null; //末尾置空，避免形成环
        p1.next = dummy2.next;
        return dummy1.next;
    }
}

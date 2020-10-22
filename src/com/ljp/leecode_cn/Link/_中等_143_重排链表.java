package com.ljp.leecode_cn.Link;

import com.ljp.leecode_cn.other.data_structure.ListNode;
import com.ljp.leecode_cn.other.data_structure_operation.ListNodeOperation;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/** 每日一题 2020.10.20
 143. 重排链表
 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…

 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

 示例 1:

 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 示例 2:

 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 * @author ljp
 * @date 2020/10/20 0:16
 */
public class _中等_143_重排链表 {
    public static void main(String[] args) {
        Integer[] input = {1,2,3,4,5};
        ListNode head = ListNodeOperation.createListNode(input);
        new _中等_143_重排链表().reorderList2(head);
    }

    /**
     *
     * @param head
    执行用时：
    4 ms, 在所有 Java 提交中击败了28.22%的用户
    内存消耗：
    41.8 MB, 在所有 Java 提交中击败了37.45%的用户
     */
    public void reorderList(ListNode head) {
        Deque<ListNode> deque = new LinkedList<>();
        ListNode p = head, pre = null;
        while(p != null){
            deque.offer(p);
            pre = p;
            p = p.next;
            pre.next = null;
        }
        ListNode left, right;
        right = new ListNode();
        while(deque.size() > 1){
            left = deque.removeFirst();
            right.next = left;
            right = deque.removeLast();
            left.next = right;
        }
        if(!deque.isEmpty()){
            left = deque.removeFirst();
            right.next = left;
        }
    }

    /**
     * 官方题解
     * @param head
    执行用时：
    4 ms, 在所有 Java 提交中击败了28.22%的用户
    内存消耗：
    41.4 MB, 在所有 Java 提交中击败了37.45%的用户
     */
    public void reorderList2(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> list = new ArrayList<ListNode>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }
}

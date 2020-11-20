package com.ljp.leecode_cn.Link;

import com.ljp.leecode_cn.other.data_structure.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/** 每日一题2020.11.20

 147. 对链表进行插入排序
 对链表进行插入排序。


 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。



 插入排序算法：

 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 重复直到所有输入数据插入完为止。


 示例 1：

 输入: 4->2->1->3
 输出: 1->2->3->4
 示例 2：

 输入: -1->5->3->4->0
 输出: -1->0->3->4->5

 * @author ljp
 * @date 2020/11/20 0:53
 */
public class _中等_147_对链表进行插入排序 {
    /**
     * 每次都从头开始比较
     * @param head
     * @return
    执行用时：
    28 ms, 在所有 Java 提交中击败了24.28%的用户
    内存消耗：
    38.3 MB, 在所有 Java 提交中击败了64.00%的用户
     */
    public ListNode insertionSortList(ListNode head) {
        ListNode p, pre, next, newHead;
        newHead = new ListNode();
        pre = newHead;
        p = head;
        while(p != null){
            pre = newHead;
            while(pre.next != null && pre.next.val <= p.val){
                pre = pre.next;
            }
            next = p.next;
            p.next = pre.next;
            pre.next = p;
            p = next;
        }
        return newHead.next;
    }

    /**
     * 使用自定义优先级队列
     * @param head
     * @return
    执行用时：
    5 ms, 在所有 Java 提交中击败了52.43%的用户
    内存消耗：
    38.2 MB, 在所有 Java 提交中击败了71.90%的用户
     */
    public ListNode insertionSortList2(ListNode head) {
        ListNode p, pre, newHead;
        p = head;
        PriorityQueue<ListNode> priority = new PriorityQueue<>(new Comparator<ListNode>(){
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        while(p != null){
            priority.offer(p);
            p = p.next;
        }
        newHead = new ListNode();
        pre = newHead;
        while(!priority.isEmpty()){
            p = priority.remove();
            pre.next = p;
            pre = p;
        }
        pre.next = null;
        return newHead.next;
    }

    /** 官方题解：从钱往后找插入点
     *
     * @param head
     * @return
    执行用时：
    3 ms, 在所有 Java 提交中击败了98.81%的用户
    内存消耗：
    38.4 MB, 在所有 Java 提交中击败了44.76%的用户
     */
    public ListNode insertionSortList3(ListNode head) {
        if(head == null){
            return head;
        }
        ListNode prev, curr, lastSorted, dummyHead;
        dummyHead = new ListNode(0);
        dummyHead.next = head;
        lastSorted = head;
        curr = head.next;
        while(curr != null){
            if(lastSorted.val <= curr.val){
                lastSorted = curr;
            }else{
                prev = dummyHead;
                while(prev.next.val <= curr.val){
                    prev = prev.next;
                }
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            curr = lastSorted.next;
        }
        return dummyHead.next;
    }
}

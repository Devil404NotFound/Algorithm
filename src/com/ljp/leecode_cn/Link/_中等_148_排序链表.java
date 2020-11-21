package com.ljp.leecode_cn.Link;

import com.ljp.leecode_cn.other.data_structure.ListNode;
import com.ljp.leecode_cn.other.data_structure_operation.ListNodeOperation;

import java.util.Comparator;
import java.util.PriorityQueue;

/** 每日一题 2020.11.21
 *
 148. 排序链表
 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。

 进阶：

 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？


 示例 1：


 输入：head = [4,2,1,3]
 输出：[1,2,3,4]
 示例 2：


 输入：head = [-1,5,3,4,0]
 输出：[-1,0,3,4,5]
 示例 3：

 输入：head = []
 输出：[]


 提示：

 链表中节点的数目在范围 [0, 5 * 104] 内
 -105 <= Node.val <= 105
 * @author ljp
 * @date 2020/11/21 1:04
 */
public class _中等_148_排序链表 {
    public static void main(String[] args) {
        _中等_148_排序链表 test = new _中等_148_排序链表();
        Integer[] input = {4,2,1,3};
        ListNode head = ListNodeOperation.createListNode(input);
        ListNode ans = test.sortList3(head);
        ans.print();
    }
    /**
     * 使用优先队列排序
     * @param head
     * @return
    执行用时：
    22 ms, 在所有 Java 提交中击败了11.61%的用户
    内存消耗：
    47.8 MB, 在所有 Java 提交中击败了5.07%的用户
     */
    public ListNode sortList(ListNode head) {
        if(head == null){
            return head;
        }
        PriorityQueue<ListNode> priority = new PriorityQueue<>(new Comparator<ListNode>(){
            @Override
            public int compare(ListNode o1, ListNode o2){
                return o1.val - o2.val;
            }
        });
        ListNode pre, p, newHead;
        newHead = new ListNode();
        pre = newHead;
        p = head;
        while(p != null){
            priority.offer(p);
            p = p.next;
        }
        while(!priority.isEmpty()){
            p = priority.remove();
            pre.next = p;
            pre = p;
        }
        pre.next = null;
        return newHead.next;
    }

    /**
     * 官方题解一：自顶向下归并排序（小改动）
     * @param head
     * @return
    执行用时：
    7 ms, 在所有 Java 提交中击败了40.32%的用户
    内存消耗：
    46.7 MB, 在所有 Java 提交中击败了14.82%的用户
     */
    public ListNode sortList2(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        //
        ListNode fast = head, slow = head, slowPre = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slowPre = slow;
            slow = slow.next;
        }
        slowPre.next = null;
        ListNode list1 = sortList2(head);
        ListNode list2 = sortList2(slow);
        return merge(list1, list2);
    }
    //实现归并排序
    private ListNode merge(ListNode list1, ListNode list2){
        ListNode dummyHead = new ListNode(0);
        ListNode p = dummyHead;
        while(list1 != null && list2 != null){
            if(list1.val <= list2.val){
                p.next = list1;
                list1 = list1.next;
            }else{
                p.next = list2;
                list2 = list2.next;
            }
            p = p.next;
        }
        p.next = list1 == null ? list2 : list1;
        return dummyHead.next;
    }

    /**
     * 官方题解二：自底向上归并排序
     * @param head
     * @return
    执行用时：
    10 ms, 在所有 Java 提交中击败了22.57%的用户
    内存消耗：
    46.6 MB, 在所有 Java 提交中击败了18.32%的用户
     */
    public ListNode sortList3(ListNode head) {
        if(head == null){
            return head;
        }
        ListNode dummyHead = new ListNode(0, head);
        //计算链表长度
        int length = 0;
        ListNode p = head;
        while(p != null){
            ++length;
            p = p.next;
        }
        for (int subLength = 1; subLength < length; subLength <<= 1) {
            ListNode prev = dummyHead, curr = dummyHead.next;
            while(curr != null){
                ListNode head1, head2, merged, next = null;
                head1 = curr;
                for (int i = 1; i < subLength && curr.next != null; i++) {//注意这里i从1开始遍历
                    curr = curr.next;
                }
                //设置第二个子链表
                head2 = curr.next;
                curr.next = null;//切断第一个子链表与第二个子链表的连接
                curr = head2;//当前指针指向head2
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }
                //如果后面还有链表，就切断head2与后面的链表的连接，并用next记录后面的链表
                if(curr != null){
                    next = curr.next;
                    curr.next = null;
                }
                //排序两个子链表
                merged = merge(head1, head2);
                //前节点连上排序好的链表
                prev.next = merged;
                //前节点只到末尾
                while(prev.next != null){
                    prev = prev.next;
                }
                curr = next;//继续处理后面的链表
            }
        }
        return dummyHead.next;
    }
}

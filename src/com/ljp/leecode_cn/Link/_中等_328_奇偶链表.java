package com.ljp.leecode_cn.Link;

import com.ljp.leecode_cn.other.data_structure.ListNode;
import com.ljp.leecode_cn.other.data_structure_operation.ListNodeOperation;

/** 每日一题 2020.11.13
 328. 奇偶链表
 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。

 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。

 示例 1:

 输入: 1->2->3->4->5->NULL
 输出: 1->3->5->2->4->NULL
 示例 2:

 输入: 2->1->3->5->6->4->7->NULL
 输出: 2->3->6->7->1->5->4->NULL
 说明:

 应当保持奇数节点和偶数节点的相对顺序。
 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 * @author ljp
 * @date 2020/11/13 10:15
 */
public class _中等_328_奇偶链表 {
    public static void main(String[] args) {
        _中等_328_奇偶链表 test = new _中等_328_奇偶链表();
        Integer[] input = {1,2,3,4};
        ListNode head = ListNodeOperation.createListNode(input);
        ListNode ans = test.oddEvenList(head);
    }

    /**
     *
     * @param head
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    38.3 MB, 在所有 Java 提交中击败了76.77%的用户
     */
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode odd = head;
        ListNode even = head.next;
        ListNode p = even.next, p1 = odd, p2 = even;
        int i = 1;
        while(p != null){
            if((i & 1) == 1){
                p1.next = p;
                p1 = p1.next;
            }else{
                p2.next = p;
                p2 = p2.next;
            }
            i++;
            p = p.next;
        }
        p2.next = null;
        p1.next = even;
        return head;
    }

    /**
     * 官方题解
     * @param head
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    38.3 MB, 在所有 Java 提交中击败了76.04%的用户
     */
    public ListNode oddEvenList2(ListNode head) {
        if(head == null){
            return head;
        }
        ListNode evenHead = head.next;
        ListNode odd = head;
        ListNode even = evenHead;
        while(even != null && even.next != null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}

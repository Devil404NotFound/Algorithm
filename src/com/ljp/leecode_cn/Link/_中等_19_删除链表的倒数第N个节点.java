package com.ljp.leecode_cn.Link;

import com.ljp.leecode_cn.other.data_structure.ListNode;

/**每日一题 2020.10.18
 19. 删除链表的倒数第N个节点
 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

 示例：

 给定一个链表: 1->2->3->4->5, 和 n = 2.

 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 说明：

 给定的 n 保证是有效的。

 进阶：

 你能尝试使用一趟扫描实现吗？
 * @author ljp
 * @date 2020/10/18 23:53
 */
public class _中等_19_删除链表的倒数第N个节点 {
    /**
     *
     * @param head
     * @param n
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    36.1 MB, 在所有 Java 提交中击败了99.77%的用户
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p, pre;
        p = head;
        int len = 0;
        while(p != null){
            len++;
            p = p.next;
        }
        //需要删除第一个的情况
        if(len == n){
            return head.next;
        }
        pre = head;
        for(int i = 1; i < len - n; i++){
            pre = pre.next;
        }
        pre.next = pre.next.next;
        return head;
    }

    /**
     * 官方题解-方法三：只扫描一次
     * @param head
     * @param n
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    36.3 MB, 在所有 Java 提交中击败了78.67%的用户
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        ListNode p = head;
        for (int i = 0; i < n; i++) {
            p = p.next;
        }
        while(p != null){
            pre = pre.next;
            p = p.next;
        }
        pre.next = pre.next.next;
        return dummy.next;
    }
}

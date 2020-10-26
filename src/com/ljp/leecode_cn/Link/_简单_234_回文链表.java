package com.ljp.leecode_cn.Link;

import com.ljp.leecode_cn.other.data_structure.ListNode;

import java.util.Deque;
import java.util.LinkedList;

/** 每日一题 2020.10.23
 234. 回文链表
 请判断一个链表是否为回文链表。

 示例 1:

 输入: 1->2
 输出: false
 示例 2:

 输入: 1->2->2->1
 输出: true
 进阶：
 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * @author ljp
 * @date 2020/10/23 0:13
 * 领悟：能用栈的都能用递归（参见官方题解二）
 */
public class _简单_234_回文链表 {
    /**
     * 栈
     * @param head
     * @return
    执行用时：
    3 ms, 在所有 Java 提交中击败了33.92%的用户
    内存消耗：
    42.1 MB, 在所有 Java 提交中击败了42.81%的用户
     */
    public boolean isPalindrome(ListNode head) {
        Deque<ListNode> deque = new LinkedList<>();
        ListNode p = head;
        while(p != null){
            deque.push(p);
            p = p.next;
        }
        p = head;
        while(p != null){
            if(p.val != deque.poll().val){
                return false;
            }
            p = p.next;
        }
        return true;
    }
}

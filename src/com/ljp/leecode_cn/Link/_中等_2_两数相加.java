package com.ljp.leecode_cn.Link;

import com.ljp.leecode_cn.other.data_structure.ListNode;

/** 每日一题 2020.10.04
 2. 两数相加
 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

 示例：

 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 输出：7 -> 0 -> 8
 原因：342 + 465 = 807
 */
public class _中等_2_两数相加 {
    /**
     *
     * @param l1
     * @param l2
     * @return
    执行用时：
    2 ms, 在所有 Java 提交中击败了99.91%的用户
    内存消耗：
    38.8 MB, 在所有 Java 提交中击败了73.37%的用户
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int digit = 0, num = 0;
        ListNode ans = new ListNode();
        ListNode p, current;
        p = ans;
        while(l1 != null || l2 != null){
            num = digit;
            if(l1 != null){
                num += l1.val;
            }
            if(l2 != null){
                num += l2.val;
            }
            digit = num / 10;
            current = new ListNode(num % 10);
            p.next = current;
            p = current;
            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }
        }
        if(digit != 0){
            p.next = new ListNode(digit);
        }
        return ans.next;
    }
}

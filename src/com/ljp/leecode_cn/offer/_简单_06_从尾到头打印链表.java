package com.ljp.leecode_cn.offer;

import com.ljp.leecode_cn.other.data_structure.ListNode;

/**
 * 剑指 Offer 06. 从尾到头打印链表
 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。



 示例 1：

 输入：head = [1,3,2]
 输出：[2,3,1]


 限制：

 0 <= 链表长度 <= 10000
 */
public class _简单_06_从尾到头打印链表 {
    /**
     *
      * @param head
     * @return
     * 执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    40.3 MB, 在所有 Java 提交中击败了77.88%的用户
     */
    public int[] reversePrint(ListNode head) {
        if(head == null){
            return new int[]{};
        }
        int size = 0;
        ListNode node = head;
        while(node != null){
            size++;
            node = node.next;
        }
        node = head;
        int[] res = new int[size];
        for(int i = size - 1; i >= 0; i--){
            res[i] = node.val;
            node = node.next;
        }
        return res;
    }
}

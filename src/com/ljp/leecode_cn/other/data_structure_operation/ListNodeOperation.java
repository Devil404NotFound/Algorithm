package com.ljp.leecode_cn.other.data_structure_operation;

import com.ljp.leecode_cn.other.data_structure.ListNode;

/**
 * 对LeetCode的数据结构ListNode的一个操作类
 * @author ljp
 * @date 2020/10/13 15:59
 */
public class ListNodeOperation {
    /**
     * 作为工具类，关闭该类的新建渠道
     */
    private ListNodeOperation(){};

    /**
     * 通过整型数组创建单链表
     * @param input
     * @return
     */
    public static ListNode createListNode(Integer[] input){
        ListNode head = new ListNode();
        ListNode p = head;
        for (int i = 0; i < input.length; i++) {
            p.next = new ListNode(input[i]);
            p = p.next;
        }
        return head.next;
    }

    /**
     * 输出单链表
     * @param head
     */
    public static void print(ListNode head){
        StringBuilder sb = new StringBuilder();
        while(head != null){
            sb.append(head.val);
            sb.append("->");
            head = head.next;
        }
        sb.setLength(sb.length() - 2);
        System.out.println(sb.toString());
    }
}

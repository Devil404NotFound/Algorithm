package com.ljp.newcoder.common.bean;

/**
 * @author lijunpeng
 * @date 2022/4/10 20:14
 * @description 链表类
 **/

public class ListNode {
    public int val;
    public ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }
    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
    /**
    * @Author lijunpeng
    * @Date 2022/4/10 20:22
    * @Description 重写输出方法
    */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode cur = this;
        while(cur.next != null) {
            sb.append(cur.val).append("->");
            cur = cur.next;
        }
        sb.append(cur.val);
        return sb.toString();
    }
}

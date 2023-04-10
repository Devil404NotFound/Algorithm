package com.ljp.leecode_cn.other.data_structure;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(){}
    public ListNode(int x) {
        val = x;
    }
    public ListNode(int x, ListNode next){
        this.val = x;
        this.next = next;
    }
    public static ListNode createListNode(int[] arr) {
        if(arr == null || arr.length == 0) {
            return null;
        }
        ListNode node = new ListNode(arr[0]);
        ListNode dummyNode = new ListNode(0, node);
        for (int i = 1; i < arr.length; i++) {
            ListNode p = new ListNode(arr[i]);
            node.next = p;
            node = p;
        }
        return dummyNode.next;
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }else if(l2 == null){
            return l1;
        }
        ListNode p1,p2,p3,newList;
        p1 = l1;
        p2 = l2;
        if(p1.val < p2.val){
            p3 = p1;
            p1 = p1.next;
        }else{
            p3 = p2;
            p2 = p2.next;
        }
        newList = p3;
        while(p1 != null && p2 != null){
            if(p1.val < p2.val){
                p3.next = p1;
                p1 = p1.next;
            }else{
                p3.next = p2;
                p2 = p2.next;
            }
            p3 = p3.next;
        }
        p3.next = (p1 == null? p2 : p1);
        return newList;
    }
    public void print(){
        ListNode node = this;
        while(node != null){
            System.out.print(node.val + "→");
            node = node.next;
        }
        System.out.println("NULL");
    }
    @Override
    public String toString() {
        return val + "->" + next;
    }
}

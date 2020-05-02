package com.ljp.leecode_cn.other.data_structure;

import java.util.Scanner;

public class ListNodeDemo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        ListNode l1 = new ListNode(arr[0]);
        l1.createListNode(arr);
        n = sc.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        ListNode l2 = new ListNode(arr[0]);
        l2.createListNode(arr);
        ListNode newList = l1.mergeTwoLists(l1, l2);
        System.out.println(l1.toString());
        System.out.println(l2.toString());
        System.out.println(newList.toString());
        sc.close();
    }

}

package com.ljp.newcoder.common.util;

import com.ljp.newcoder.common.bean.ListNode;

/**
 * @author lijunpeng
 * @date 2022/4/10 20:17
 * @description
 **/

public class ListNodeUtil {
    public static ListNode createListNode(int[] arr) {
        ListNode cur, next = null;
        for(int i = arr.length - 1; i >= 0; --i) {
            cur = new ListNode(arr[i], next);
            next = cur;
        }
        return next;
    }


}

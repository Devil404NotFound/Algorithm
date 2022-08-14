package com.ljp.newcoder.link;

import com.ljp.newcoder.common.bean.ListNode;
import com.ljp.newcoder.common.util.ListNodeUtil;

/**
 * @author lijunpeng
 * @date 2022/4/10 19:51
 * @description
描述
将给出的链表中的节点每 k 个一组翻转，返回翻转后的链表
如果链表中的节点数不是 k 的倍数，将最后剩下的节点保持原样
你不能更改节点中的值，只能更改节点本身。

数据范围： \ 0 \le n \le 2000 0≤n≤2000 ， 1 \le k \le 20001≤k≤2000 ，链表中每个元素都满足 0 \le val \le 10000≤val≤1000
要求空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
例如：
给定的链表是 1\to2\to3\to4\to51→2→3→4→5
对于 k = 2k=2 , 你应该返回 2\to 1\to 4\to 3\to 52→1→4→3→5
对于 k = 3k=3 , 你应该返回 3\to2 \to1 \to 4\to 53→2→1→4→5

示例1
输入：
{1,2,3,4,5},2
复制
返回值：
{2,1,4,3,5}
复制
示例2
输入：
{},1
复制
返回值：
{}
 **/
public class BM3_中等_链表中的节点每k个一组翻转 {

    public ListNode reverseKGroup (ListNode head, int k) {
        // write code here
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = null, cur, next, last;
        cur = head;
        last = dummyNode;
        while(isReverse(cur, k)) {
            ListNode right = cur;
            for(int i = 0; i < k; ++i) {
                pre = cur;
                next = cur.next;
                cur.next = next.next;
                next.next = cur;
                cur = next;
            }
            last.next = pre;
            last = right;
        }
        return dummyNode.next;
    }
    private boolean isReverse(ListNode node, int k) {
        while(k > 0 && node != null) {
            node = node.next;
            --k;
        }
        return k == 0;
    }

    public static void main(String[] args) {
        BM3_中等_链表中的节点每k个一组翻转 test = new BM3_中等_链表中的节点每k个一组翻转();
        int[] arr = new int[]{1,2,3,4,5};
        int k = 2;
        ListNode head = ListNodeUtil.createListNode(arr);
        ListNode result = test.reverseKGroup(head, k);
        System.out.println(result);
    }
}

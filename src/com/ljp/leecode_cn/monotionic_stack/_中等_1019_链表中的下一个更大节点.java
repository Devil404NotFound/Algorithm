package com.ljp.leecode_cn.monotionic_stack;

import com.ljp.leecode_cn.other.data_structure.ListNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author lijunpeng
 * @date 2023-04-10 23:18
 * @Description
1019. 链表中的下一个更大节点
给定一个长度为 n 的链表 head
对于列表中的每个节点，查找下一个 更大节点 的值。也就是说，对于每个节点，找到它旁边的第一个节点的值，这个节点的值 严格大于 它的值。
返回一个整数数组 answer ，其中 answer[i] 是第 i 个节点( 从1开始 )的下一个更大的节点的值。如果第 i 个节点没有下一个更大的节点，设置 answer[i] = 0 。

示例 1：
输入：head = [2,1,5]
输出：[5,5,0]

示例 2：
输入：head = [2,7,4,3,5]
输出：[7,0,5,5,0]

提示：
链表中节点数为 n
1 <= n <= 104
1 <= Node.val <= 109 */
public class _中等_1019_链表中的下一个更大节点 {
    public static void main(String[] args) {
        int[] arr = new int[]{9,7,6,7,6,9};
        int[] ints = new _中等_1019_链表中的下一个更大节点().nextLargerNodes2(ListNode.createListNode(arr));
        System.out.println(ints);
    }

    /**
     * 官方题解一：单调栈
     执行用时：
     13 ms, 在所有 Java 提交中击败了80.49%的用户
     内存消耗：
     44.9 MB, 在所有 Java 提交中击败了44.56%的用户
     */
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> ans = new ArrayList<>();
        Deque<int[]> stack = new ArrayDeque<>();

        ListNode cur = head;
        int idx = -1;
        while (cur != null) {
            ++idx;
            ans.add(0);
            while (!stack.isEmpty() && stack.peek()[0] < cur.val) {
                ans.set(stack.pop()[1], cur.val);
            }
            stack.push(new int[]{cur.val, idx});
            cur = cur.next;
        }

        int size = ans.size();
        int[] arr = new int[size];
        for (int i = 0; i < size; ++i) {
            arr[i] = ans.get(i);
        }
        return arr;
    }

    /**
     * 官方题解评论1：直接使用ans数组记录下标
     执行用时：
     15 ms, 在所有 Java 提交中击败了75.36%的用户
     内存消耗：45.1 MB, 在所有 Java 提交中击败了36.35%的用户
     */
    public int[] nextLargerNodes2(ListNode head) {
        List<Integer> ans = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        ListNode cur = head;
        int idx = -1;
        while (cur != null) {
            ans.add(cur.val);
            while (!stack.isEmpty() && ans.get(stack.peek()) < cur.val) {
                ans.set(stack.pop(), cur.val);
            }
            stack.push(++idx);
            cur = cur.next;
        }
        while(!stack.isEmpty()) {
            ans.set(stack.pop(), 0);
        }
        int size = ans.size();
        int[] arr = new int[size];
        for (int i = 0; i < size; ++i) {
            arr[i] = ans.get(i);
        }
        return arr;
    }
}

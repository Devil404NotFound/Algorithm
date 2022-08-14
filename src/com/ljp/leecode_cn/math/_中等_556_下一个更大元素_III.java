package com.ljp.leecode_cn.math;

import java.util.PriorityQueue;

/** 每日一题 2022.07.03
 * @author lijunpeng
 * @date 2022/7/3 22:05
 * @description
556. 下一个更大元素 III
给你一个正整数 n ，请你找出符合条件的最小整数，其由重新排列 n 中存在的每位数字组成，并且其值大于 n 。如果不存在这样的正整数，则返回 -1 。

注意 ，返回的整数应当是一个 32 位整数 ，如果存在满足题意的答案，但不是 32 位整数 ，同样返回 -1 。

示例 1：

输入：n = 12
输出：21
示例 2：

输入：n = 21
输出：-1

提示：
1 <= n <= 231 - 1
 **/

public class _中等_556_下一个更大元素_III {
    public int nextGreaterElement(int n) {
        if(n < 12) {
            return -1;
        }
        String nStr = String.valueOf(n);
        int start = nStr.length() - 2;
        PriorityQueue<Character> queue = new PriorityQueue<>();
        // 找到最后递增数字
        while(start >= 0 && nStr.charAt(start) >= nStr.charAt(start + 1)) {
            queue.add(nStr.charAt(start + 1));
            start--;
        }
        // 如果这个整数非递增，直接返回
        if(start < 0) {
            return -1;
        }
        // 添加[0, start)的字母
        StringBuilder sb = new StringBuilder(nStr.substring(0, start));
        // 将最后一个递增数加入到优先队列中
        queue.add(nStr.charAt(start + 1));
        // 拿比nStr.charAt(start)大的第一个数
        StringBuilder end = new StringBuilder();
        while(nStr.charAt(start) >= queue.peek()) {
            end.append(queue.poll());
        }
        sb.append(queue.poll());
        sb.append(end);
        queue.add(nStr.charAt(start));
        while(!queue.isEmpty()) {
            sb.append(queue.poll());
        }
        long ans = Long.parseLong(sb.toString());
        return ans > Integer.MAX_VALUE ? -1 : (int)ans;
    }
}

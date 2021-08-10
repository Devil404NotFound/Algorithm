package com.ljp.leecode_cn.sliding_window;

/** 每日一题 2021.02.23
 * @author lijunpeng
 * @date 2021/2/23 22:43
1052. 爱生气的书店老板
今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。

在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。

书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。

请你返回这一天营业下来，最多有多少客户能够感到满意的数量。


示例：

输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
输出：16
解释：
书店老板在最后 3 分钟保持冷静。
感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.


提示：

1 <= X <= customers.length == grumpy.length <= 20000
0 <= customers[i] <= 1000
0 <= grumpy[i] <= 1
 */
public class _简单_1052_爱生气的书店老板 {
    /**
     *
     * @param customers
     * @param grumpy
     * @param X
     * @return
    执行用时：
    4 ms, 在所有 Java 提交中击败了39.80%的用户
    内存消耗：
    40.5 MB, 在所有 Java 提交中击败了91.76%的用户
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int n = grumpy.length;
        int count = 0; //动态统计窗口内生气人数
        int maxCount = Integer.MIN_VALUE;//区间X内最大生气人数
        int sum = 0; //没有控制情绪时的总满意客户
        int left = 0, right = 0;
        while(right < n) {
            if(grumpy[right] == 1) {
                count += customers[right];
            }else{
                sum += customers[right];
            }
            if(right - left + 1 > X) {
                if(grumpy[left] == 1) {
                    count -= customers[left];
                }
                left++;
            }
            right++;
            maxCount = Math.max(maxCount, count);
        }
        //返回没有控制情绪的客户总数+区间X内最大生气人数
        return sum + maxCount;
    }
}

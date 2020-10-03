package com.ljp.leecode_cn.math;

/**
 1523. 在区间范围内统计奇数数目
 给你两个非负整数 low 和 high 。请你返回 low 和 high 之间（包括二者）奇数的数目。



 示例 1：

 输入：low = 3, high = 7
 输出：3
 解释：3 到 7 之间奇数数字为 [3,5,7] 。
 示例 2：

 输入：low = 8, high = 10
 输出：1
 解释：8 到 10 之间奇数数字为 [9] 。


 提示：

 0 <= low <= high <= 10^9
 */
public class _简单_1523_在区间范围内统计奇数数目 {
    /**
     * 暴力解法：
     * @param low
     * @param high
     * @return
    执行用时：
    2095 ms, 在所有 Java 提交中击败了5.06%的用户
    内存消耗：
    35.8 MB, 在所有 Java 提交中击败了7.43%的用户
     */
    public int countOdds(int low, int high) {
        int count = 0;
        for(int i = low; i <= high; i++){
            if((i & 1) == 1){
                count++;
            }
        }
        return count;
    }

    /**
     * 数学方法
     * @param low
     * @param high
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    35.6 MB, 在所有 Java 提交中击败了44.71%的用户
     */
    public int countOdds2(int low, int high) {
        int count = (high - low) >> 1;
        if(((low & 1) == 1) || (high & 1) == 1) {// 有一个是奇数就加1（我也不知道为啥）
            count++;
        }
        return count;
    }
}

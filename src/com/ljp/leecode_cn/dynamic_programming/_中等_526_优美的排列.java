package com.ljp.leecode_cn.dynamic_programming;

import java.util.ArrayList;
import java.util.List;

/** 每日一题 2021.08.16
 * @author lijunpeng
 * @date 2021/8/16 22:34
 * @Description
526. 优美的排列
假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，我们就称这个数组为一个优美的排列。条件：

第 i 位的数字能被 i 整除
i 能被第 i 位上的数字整除
现在给定一个整数 N，请问可以构造多少个优美的排列？

示例1:

输入: 2
输出: 2
解释:

第 1 个优美的排列是 [1, 2]:
第 1 个位置（i=1）上的数字是1，1能被 i（i=1）整除
第 2 个位置（i=2）上的数字是2，2能被 i（i=2）整除

第 2 个优美的排列是 [2, 1]:
第 1 个位置（i=1）上的数字是2，2能被 i（i=1）整除
第 2 个位置（i=2）上的数字是1，i（i=2）能被 1 整除
说明:

N 是一个正整数，并且不会超过15。
 */
public class _中等_526_优美的排列 {

    /** 官方题解一：回溯
    * @Author lijunpeng
    * @Date 2021/8/16 22:50
    * @Description
    执行用时：
    40 ms, 在所有 Java 提交中击败了89.97%的用户
    内存消耗：
    38.1 MB, 在所有 Java 提交中击败了5.43%的用户
    **/
    List<Integer>[] match; //记录是否满足条件
    boolean[] vis; //记录该数字是否已经使用
    int num = 0;
    public int countArrangement(int n) {
        vis = new boolean[n + 1];
        match = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            match[i] = new ArrayList<>();
        }
        //预处理
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i % j == 0 || j % i == 0) {
                    match[i].add(j);
                }
            }
        }
        backtrack(1, n);
        return num;
    }
    private void backtrack(int index, int n) {
        if(index == n + 1) {
            ++num;
            return;
        }
        //遍历满足条件的数字
        for (Integer x : match[index]) {
            if(!vis[x]) {
                vis[x] = true;
                backtrack(index + 1, n);
                vis[x] = false;
            }
        }
    }
    /** 官方题解二：状态压缩+动态规划
    * @Author lijunpeng
    * @Date 2021/8/16 23:17
    * @Description
    执行用时：6 ms, 在所有 Java 提交中击败了96.05%的用户
    内存消耗：
    35.5 MB, 在所有 Java 提交中击败了13.65%的用户
    **/
    public int countArrangement2(int n) {
        int[] f = new int[1 << n]; //f[i]表示i的二进制位数为1的位置已经选中
        f[0] = 1;
        for(int mask = 1; mask < (1 << n); ++mask) {
            int num = Integer.bitCount(mask); //统计选中了几位
            for (int i = 0; i < n; i++) {
                if((mask & (1 + i)) != 0 && (num % (1 + i) == 0 || (1 << i) % num == 0)) {
                    f[mask] += f[mask ^ (1 << i)];
                }
            }
        }
        return f[(1 << n) - 1];
    }
}

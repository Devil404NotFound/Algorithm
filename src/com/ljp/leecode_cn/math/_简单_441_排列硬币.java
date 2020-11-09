package com.ljp.leecode_cn.math;

/**
 441. 排列硬币
 你总共有 n 枚硬币，你需要将它们摆成一个阶梯形状，第 k 行就必须正好有 k 枚硬币。

 给定一个数字 n，找出可形成完整阶梯行的总行数。

 n 是一个非负整数，并且在32位有符号整型的范围内。

 示例 1:

 n = 5

 硬币可排列成以下几行:
 ¤
 ¤ ¤
 ¤ ¤

 因为第三行不完整，所以返回2.
 示例 2:

 n = 8

 硬币可排列成以下几行:
 ¤
 ¤ ¤
 ¤ ¤ ¤
 ¤ ¤

 因为第四行不完整，所以返回3.

 * @author ljp
 * @date 2020/11/8 14:15
 */
public class _简单_441_排列硬币 {
    /**
     *暴力算法
     * @param n
     * @return
    执行用时：
    9 ms, 在所有 Java 提交中击败了33.65%的用户
    内存消耗：
    35.6 MB, 在所有 Java 提交中击败了86.12%的用户
     */
    public int arrangeCoins(int n) {
        int i = 0;
        while(n >= 0){
            i++;
            n -= i;
        }
        return i - 1;
    }

    /**
     * 数学方法 等差数列前n项和Sn = (a1 + an) / 2 * n 得 d*n^2 + n - 2*Sn = 0
     * @param n
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    35.8 MB, 在所有 Java 提交中击败了79.26%的用户
     */
    public int arrangeConis2(int n){
//        return  (int)(Math.sqrt(0.25 + 2 * n) - 0.25);
        return (int)(Math.sqrt(1 + 8 * (long)n) - 1) >> 1;
    }

    /**
     * 题解大佬：二分查找@seerJJJ
     * @param n
     * @return
    执行用时：
    2 ms, 在所有 Java 提交中击败了86.98%的用户
    内存消耗：
    35.9 MB, 在所有 Java 提交中击败了78.68%的用户
     */
    public int arrageCoins3(int n){
        long sum, mid, low, high;
        low = 0;
        high = n;
        while(low <= high){
            mid = low + (high - low) / 2;
            sum = (1 + mid) * mid / 2;
            if(sum < n){
                low = 1 + mid;
            }else if(sum > n){
                high = mid - 1;
            }else{
                return (int)mid;
            }
        }
        return (int)high;
    }
}

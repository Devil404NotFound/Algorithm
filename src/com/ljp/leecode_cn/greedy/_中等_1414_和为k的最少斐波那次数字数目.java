package com.ljp.leecode_cn.greedy;

/**
 * 1414. 和为 K 的最少斐波那契数字数目
 给你数字 k ，请你返回和为 k 的斐波那契数字的最少数目，其中，每个斐波那契数字都可以被使用多次。

 斐波那契数字定义为：

 F1 = 1
 F2 = 1
 Fn = Fn-1 + Fn-2 ， 其中 n > 2 。
 数据保证对于给定的 k ，一定能找到可行解。



 示例 1：

 输入：k = 7
 输出：2
 解释：斐波那契数字为：1，1，2，3，5，8，13，……
 对于 k = 7 ，我们可以得到 2 + 5 = 7 。
 示例 2：

 输入：k = 10
 输出：2
 解释：对于 k = 10 ，我们可以得到 2 + 8 = 10 。
 示例 3：

 输入：k = 19
 输出：3
 解释：对于 k = 19 ，我们可以得到 1 + 5 + 13 = 19 。


 提示：

 1 <= k <= 10^9
 */
public class _中等_1414_和为k的最少斐波那次数字数目 {
    public static void main(String[] args) {
        int k = 991244035;
        System.out.println(findMinFibonacciNumbers(k));
    }

    /**
     * 执行用时 :
     1 ms, 在所有 Java 提交中击败了99.32%的用户
     内存消耗 :
     36.8 MB, 在所有 Java 提交中击败了100.00%的用户
     * @param k
     * @return
     */
    public static int findMinFibonacciNumbers(int k) {
        int[] arr = new int[44];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        int count = 0;
        int i = arr.length - 1;
        while (k > 0 && i >= 0) {
            if (k - arr[i] >= 0) {
                int num = k / arr[i];
                k -= (arr[i] * num);
                count += num;
            }
            i--;
        }
        return count;
    }
}

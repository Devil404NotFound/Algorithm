package com.ljp.leecode_cn.math;

/**
 1175. 质数排列
 请你帮忙给从 1 到 n 的数设计排列方案，使得所有的「质数」都应该被放在「质数索引」（索引从 1 开始）上；你需要返回可能的方案总数。

 让我们一起来回顾一下「质数」：质数一定是大于 1 的，并且不能用两个小于它的正整数的乘积来表示。

 由于答案可能会很大，所以请你返回答案 模 mod 10^9 + 7 之后的结果即可。



 示例 1：

 输入：n = 5
 输出：12
 解释：举个例子，[1,2,5,4,3] 是一个有效的排列，但 [5,2,3,4,1] 不是，因为在第二种情况里质数 5 被错误地放在索引为 1 的位置上。
 示例 2：

 输入：n = 100
 输出：682289015
 * @author ljp
 * @date 2020/11/4 21:46
 */
public class _简单_1175_质数排列 {
    public static void main(String[] args) {
        int ans = new _简单_1175_质数排列().numPrimeArrangements(6);
        System.out.println(ans);
    }
    /**
     * 质数的全排列 * 非质数的全排列
     * @param n
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    35 MB, 在所有 Java 提交中击败了94.74%的用户
     */
    public int numPrimeArrangements(int n) {
        long ans = 1;
        int count = 0;
        for(int i = 2; i <= n; i++){
            if(isPrime(i)){
                count++;
            }
        }
        for(int i = 2; i <= count; i++){
            ans *= i;
            ans %= 1000000007;
        }
        for(int i = 2; i <= n - count; i++){
            ans *= i;
            ans %= 1000000007;
        }
        return (int)ans;
    }
    private boolean isPrime(int n){
        int sqrt = (int)Math.sqrt(n);
        for(int i = 2; i <= sqrt; i++){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }
}

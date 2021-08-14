package com.ljp.leecode_cn.dynamic_programming;

/** 每日一题 2021.08.13
 * @author lijunpeng
 * @date 2021/8/13 22:19
 * @Description
 */
public class _困难_233_数字1的个数 {
    /** 官方题解：枚举每一数位上 11 的个数
     O(log n)
     O(1)
    * @Author lijunpeng
    * @Date 2021/8/13 23:08
    * @Description
    执行用时：
    0 ms, 在所有 Java 提交中击败了10
    3-0.00%的用户
    内存消耗：
    35.1 MB, 在所有 Java 提交中击败了64.78%的用户
    **/
    public int countDigitOne(int n) {
        // mulk 表示 10^k
        // 在下面的代码中，可以发现 k 并没有被直接使用到（都是使用 10^k）
        // 但为了让代码看起来更加直观，这里保留了 k
        long mulk = 1;
        int ans = 0;
        for (int k = 0; n >= mulk; ++k) {
            ans += (n / (mulk * 10)) * mulk + Math.min(Math.max(n % (mulk * 10) - mulk + 1, 0), mulk);
            mulk *= 10;
        }
        return ans;
    }
}

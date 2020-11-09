package com.ljp.leecode_cn.math;

/**

 * @author ljp
 * @date 2020/11/5 23:03
 */
public class _简单_面试题16_05_阶乘尾数 {
    /**
     * 评论区大佬：
     0 是由 *10 得到的，而 10 是由 2 * 5 得到的
     因此我们求 n！ 过程中存在多少个 2 * 5
     因为 2 的个数必定比 5 的个数多，因此我们只求 5 的个数
     * @param n
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了98.47%的用户
    内存消耗：
    35.7 MB, 在所有 Java 提交中击败了63.83%的用户
     */
    public int trailingZeroes(int n) {
        int ans = 0;
        while(n != 0){
            //ans += n / 5;
            n /= 5;
            ans += n;
        }
        return ans;
    }
}

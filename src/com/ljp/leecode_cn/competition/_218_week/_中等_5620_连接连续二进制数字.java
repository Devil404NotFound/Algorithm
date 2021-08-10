package com.ljp.leecode_cn.competition._218_week;

/**
 * @author ljp
 * @date 2020/12/6 11:32
5620. 连接连续二进制数字 显示英文描述
通过的用户数1169
尝试过的用户数1709
用户总通过次数1178
用户总提交次数3106
题目难度Medium
给你一个整数 n ，请你将 1 到 n 的二进制表示连接起来，并返回连接结果对应的 十进制 数字对 109 + 7 取余的结果。



示例 1：

输入：n = 1
输出：1
解释：二进制的 "1" 对应着十进制的 1 。
示例 2：

输入：n = 3
输出：27
解释：二进制下，1，2 和 3 分别对应 "1" ，"10" 和 "11" 。
将它们依次连接，我们得到 "11011" ，对应着十进制的 27 。
示例 3：

输入：n = 12
输出：505379714
解释：连接结果为 "1101110010111011110001001101010111100" 。
对应的十进制数字为 118505380540 。
对 109 + 7 取余后，结果为 505379714 。


提示：

1 <= n <= 105
 */
public class _中等_5620_连接连续二进制数字 {
    public static void main(String[] args) {
        int n = 4;
        _中等_5620_连接连续二进制数字 test = new _中等_5620_连接连续二进制数字();
        System.out.println(test.concatenatedBinary(n));
    }
    //通过

    /**
     *
     * @param n
     * @return
    执行用时：
    86 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    35.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int concatenatedBinary(int n) {
        final int MOD = 1000000000 + 7;
        long ans = 0; //long避免溢出
        int bit = 0;//需要移动的位数
        for(int i = 1; i <= n; ++i) {
            //当i是2的n次方时，i & (i - 1) == 0 如： i = 4, 二进制i为100, i - 1 = 011， i & (i - 1) == 0
            if((i & (i - 1)) == 0){
                ++bit;
            }
            ans = ((ans << bit) + i) % MOD;
        }
        return (int)ans;
    }
    public int concatenatedBinary2(int n) {
        long mult = 1;
        long ans = 0;
        final int MOD = 1000000000 + 7;
        for(int i = 1; i <= n; ++i) {
            //当i是2的n次方时，mult=2^(n + 1);
            if((i & (i - 1)) == 0){
                mult = i << 1;
            }
            //mult是2^(n + 1) 次方，因此ans*mult的本质是ans左移n+1位
            ans = (ans * mult + i) % MOD;
        }
        return (int)ans;
    }
}

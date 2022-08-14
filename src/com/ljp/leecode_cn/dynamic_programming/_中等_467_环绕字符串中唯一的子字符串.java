package com.ljp.leecode_cn.dynamic_programming;

import java.util.Arrays;

/** 每日一题 2022.05.25
 * @author lijunpeng
 * @date 2022/5/25 21:23
 * @description
467. 环绕字符串中唯一的子字符串
把字符串 s 看作是 “abcdefghijklmnopqrstuvwxyz” 的无限环绕字符串，所以 s 看起来是这样的：

"...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd...." .
现在给定另一个字符串 p 。返回 s 中 唯一 的 p 的 非空子串 的数量 。



示例 1:

输入: p = "a"
输出: 1
解释: 字符串 s 中只有一个"a"子字符。
示例 2:

输入: p = "cac"
输出: 2
解释: 字符串 s 中的字符串“cac”只有两个子串“a”、“c”。.
示例 3:

输入: p = "zab"
输出: 6
解释: 在字符串 s 中有六个子串“z”、“a”、“b”、“za”、“ab”、“zab”。


提示:

1 <= p.length <= 105
p 由小写英文字母构成
 **/

public class _中等_467_环绕字符串中唯一的子字符串 {
    /**
    * @Author lijunpeng
    * @Date 2022/5/25 23:05
    * @Description
    执行用时：9 ms, 在所有 Java 提交中击败了22.68%的用户
    内存消耗：41.3 MB, 在所有 Java 提交中击败了41.49%的用户
    */
    public int findSubstringInWraproundString(String p) {
        int len = p.length();
        int[] dp = new int[26];
        int k = 0;
        for(int i = 0; i < len; ++i) {
            if(i > 0 && (p.charAt(i) - p.charAt(i - 1) + 26) % 26 == 1) { // 字符之差为 1 或 -25
                k++;
            }else{
                k = 1;
            }
            dp[p.charAt(i) - 'a'] = Math.max(dp[p.charAt(i) - 'a'], k);
        }
        return Arrays.stream(dp).sum();
    }
}

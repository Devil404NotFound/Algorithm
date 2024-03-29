package com.ljp.leecode_cn.string;

import java.util.Arrays;

/**每日一题2020.05.20
 * 1371. 每个元音包含偶数次的最长子字符串
 给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度：每个元音字母，即 'a'，'e'，'i'，'o'，'u' ，在子字符串中都恰好出现了偶数次。



 示例 1：
 输入：s = "eleetminicoworoep"
 输出：13
 解释：最长子字符串是 "leetminicowor" ，它包含 e，i，o 各 2 个，以及 0 个 a，u 。
 示例 2：

 输入：s = "leetcodeisgreat"
 输出：5
 解释：最长子字符串是 "leetc" ，其中包含 2 个 e 。
 示例 3：

 输入：s = "bcbcbc"
 输出：6
 解释：这个示例中，字符串 "bcbcbc" 本身就是最长的，因为所有的元音 a，e，i，o，u 都出现了 0 次。


 提示：

 1 <= s.length <= 5 x 10^5
 s 只包含小写英文字母。
 */
public class _中等_1371_每个元音包含偶数次的最长字符串 {
    /**
     * 官方题解：前缀和+状态压缩
     * @param s
     * @return
     * 执行用时 :
    14 ms, 在所有 Java 提交中击败了87.11%的用户
    内存消耗 :
    43.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int findTheLongestSubstring(String s) {
        int n = s.length();
        int[] pos = new int[1 << 5];
        Arrays.fill(pos, -1);
        int ans = 0, status = 0;
        pos[0] = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == 'a') {
                status ^= (1 << 0);
            } else if (ch == 'e') {
                status ^= (1 << 1);
            } else if (ch == 'i') {
                status ^= (1 << 2);
            } else if (ch == 'o') {
                status ^= (1 << 3);
            } else if (ch == 'u') {
                status ^= (1 << 4);
            }
            if (pos[status] >= 0) {
                ans = Math.max(ans, i + 1 - pos[status]);
            } else {
                pos[status] = i + 1;
            }
        }
        return ans;
    }

    /**
     *
     * @param s
     * @return
     * 执行用时 :
    24 ms, 在所有 Java 提交中击败了63.66%的用户
    内存消耗 :
    44.5 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int findTheLongestSubstring2(String s) {
        String aeiou = "aeiou";
        char[] ch = s.toCharArray();
        int[] arr = new int[1 << 5];
        Arrays.fill(arr, -1);
        //用mask记录aeiou的奇偶性
        int status = 0;
        int ans = 0;
        for (int i = 0; i < ch.length; i++) {
            int j = aeiou.indexOf(ch[i]);
            if(j != -1){
                status ^= 1 << j;
            }
            if(arr[status] == -1){
                arr[status] = i + 1;
            }else{
                ans = Math.max(ans, i + 1 - arr[status]);
            }
        }
        return ans;
    }
    /*public int findTheLongestSubstring3(String s) {
        String aeiou = "aeiou";
        char[] ch = s.toCharArray();
        //建立一个映射数组，a,e,i,o,u,分别为1,2,3,4,5，其他都为-1
        int[] arr = new int[ch.length];
        //用mask记录aeiou的奇偶性
        int mask = 0;
        for (int i = 0; i < ch.length; i++) {
            int j = aeiou.indexOf(ch[i]);
            if(j == -1){
                arr[i] = j;
            }else {
                arr[i] = 1 << j;
                mask ^= arr[i];
            }
        }
        int low = 0, high = ch.length - 1;

    }*/

}

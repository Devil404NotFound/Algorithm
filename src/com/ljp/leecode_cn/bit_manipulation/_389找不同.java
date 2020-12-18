package com.ljp.leecode_cn.bit_manipulation;

/** 2020.12.18
 * 389. 找不同
 给定两个字符串 s 和 t，它们只包含小写字母。

 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。

 请找出在 t 中被添加的字母。



 示例:

 输入：
 s = "abcd"
 t = "abcde"

 输出：
 e

 解释：
 'e' 是那个被添加的字母。
 *
 * 执行结果：
 通过
 执行用时 :
 2 ms, 在所有 Java 提交中击败了79.41%的用户
 内存消耗 :
 37.9 MB, 在所有 Java 提交中击败了25.00%的用户
 */
public class _389找不同 {
    public static void main(String[] args) {
        String s = "abcdee";
        String t = "abcde";
        System.out.println(findTheDifference(s,t));
    }
    public static char findTheDifference(String s, String t) {
        int res = 0;
        int len1 = s.length();
        int len2 = t.length();
        for(int i = 0; i < len1; ++i){
            res ^= (int)(s.charAt(i) - 'a');
        }
        for(int i = 0; i < len2; ++i){
            res ^= (int)(t.charAt(i) - 'a');
        }
        return (char)(res + 'a');
    }

    /**
     * 位运算 (同官方题解三）
     * @param s
     * @param t
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    36.8 MB, 在所有 Java 提交中击败了71.83%的用户
     */
    public char findTheDifference2(String s, String t) {
        int len = s.length();
        int hash = 0;
        for(int i = 0; i < len; i++) {
            hash ^= s.charAt(i);
            hash ^= t.charAt(i);
        }
        hash ^= t.charAt(t.length() - 1);
        return (char)(hash);
    }

    /**
     * 官方题解一：计数
     * @param s
     * @param t
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    36.7 MB, 在所有 Java 提交中击败了87.11%的用户
     */
    public char findTheDifference3(String s, String t) {
        int[] cnt = new int[26];
        for(char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        for(char c : t.toCharArray()) {
            --cnt[c - 'a'];
            if(cnt[c - 'a'] < 0) {
                return c;
            }
        }
        return ' ';
    }

    /**
     * 官方题解二：求和
     * @param s
     * @param t
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    36.7 MB, 在所有 Java 提交中击败了86.84%的用户
     */
    public char findTheDifference4(String s, String t) {
        int as = 0, at = 0;
        for(char c : s.toCharArray()) {
            as += c;
        }
        for(char c : t.toCharArray()) {
            at += c;
        }
        return (char)(at - as);
    }
}

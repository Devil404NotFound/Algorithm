package com.ljp.leecode_cn.string;

/**
 * 5. 最长回文子串
 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

 示例 1：

 输入: "babad"
 输出: "bab"
 注意: "aba" 也是一个有效答案。
 示例 2：

 输入: "cbbd"
 输出: "bb"
 */
public class _中等_5_最长回文子串 {
    /**
     * 答案错误
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if(s.length() < 2){
            return s;
        }
        String ans = "";
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length - 1; i++) {
            int l = i;
            int r = i + 1;
            if(ch[l] == ch[r]){
                l--;
                r++;
            }else{
                l--;
            }
            while(l >=0 && r < ch.length && ch[l] == ch[r]){
                l--;
                r++;
            }
            if(r - l + 1 > ans.length()){
                ans = s.substring(l, r + 1);
            }
        }
        return ans;
    }

    /**
     *
     * @param s
     * @return
     * 执行用时 :
    12 ms, 在所有 Java 提交中击败了92.34%的用户
    内存消耗 :
    39.8 MB, 在所有 Java 提交中击败了15.18%的用户
     */
    public String longestPalindrome2(String s) {
        if(s.length() < 2){
            return s;
        }
        String ans = s.substring(0, 1);
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length - 1; i++) {
            int l = i;
            int r = i + 1;
            while(l >=0 && r < ch.length && ch[l] == ch[r]){
                l--;
                r++;
            }
            l++;
            r--;
            if(r - l + 1 > ans.length()){
                ans = s.substring(l, r + 1);
            }

            l = i;
            r = i + 2;
            if(r < ch.length){
                while(l >=0 && r < ch.length && ch[l] == ch[r]){
                    l--;
                    r++;
                }
                l++;
                r--;
                if(r - l + 1 > ans.length()){
                    ans = s.substring(l, r + 1);
                }
            }
        }
        return ans;
    }
}

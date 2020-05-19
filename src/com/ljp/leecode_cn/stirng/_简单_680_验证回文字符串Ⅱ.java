package com.ljp.leecode_cn.stirng;

/**
 * 680. 验证回文字符串 Ⅱ
 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。

 示例 1:

 输入: "aba"
 输出: True
 示例 2:

 输入: "abca"
 输出: True
 解释: 你可以删除c字符。
 注意:

 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
 */
public class _简单_680_验证回文字符串Ⅱ {
    public static void main(String[] args) {
        String s = "ebcbbececabbacecbbcbe";
        System.out.println(validPalindrome(s));
    }

    /**
     *
     * @param s
     * @return
     * 执行用时 :
    10 ms, 在所有 Java 提交中击败了50.20%的用户
    内存消耗 :
    40 MB, 在所有 Java 提交中击败了6.67%的用户
     */
    public static boolean validPalindrome(String s) {
        if(s.length() < 3){
            return true;
        }
        char[] ch = s.toCharArray();
        int right = ch.length - 1;
        int left = 0;
        int can = 1;
        int lastLeft = -1;
        int lastRight = -1;
        while(left < right){
            if(ch[left] == ch[right]){
                left++;
                right--;
            }else if(can == 0){
                if(lastLeft != -1) {
                    left = lastLeft + 2;
                    right = lastRight - 1;
                    lastLeft = -1;
                }else{
                    return false;
                }
            }else{
                can--;
                if(ch[left] == ch[right - 1]){
                    if(ch[left + 1] == ch[right]){
                        lastLeft = left;
                        lastRight = right;
                    }
                    left++;
                    right -= 2;
                }else if(ch[left + 1] == ch[right]){
                    left += 2;
                    right--;
                }else{
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 评论区大佬思路：分2个方法
     * @param s
     * @return
     * 执行用时 :
    8 ms, 在所有 Java 提交中击败了89.55%的用户
    内存消耗 :
    40.3 MB, 在所有 Java 提交中击败了6.67%的用户
     */
    public boolean validPalindrome2(String s) {
        char[] ch = s.toCharArray();
        int i = 0, j = ch.length - 1;
        while(i < j){
            if(ch[i] != ch[j]){
                return validPalindromeCore(ch, i + 1, j) || validPalindromeCore(ch, i, j - 1);
            }
            i++;
            j--;
        }
        return true;
    }
    private boolean validPalindromeCore(char[] ch, int i, int j){
        while(i < j){
            if(ch[i] != ch[j]){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}

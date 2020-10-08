package com.ljp.leecode_cn.stirng;

/**

 */
public class _中等_344_反转字符串 {
    /**
     * 两端替换反转
     * @param s
    执行用时：
    1 ms, 在所有 Java 提交中击败了99.98%的用户
    内存消耗：
    45.4 MB, 在所有 Java 提交中击败了64.72%的用户
     */
    public void reverseString(char[] s) {
        char temp = 0;
        int mid = s.length / 2;
        int len = s.length;
        for(int i = 0; i < mid; i++){
            temp = s[i];
            s[i] = s[len - i - 1];
            s[len - i - 1] = temp;
        }
    }

    /**
     * 对方法一的写法优化
     * @param s
    执行用时：
    1 ms, 在所有 Java 提交中击败了99.98%的用户
    内存消耗：
    45.6 MB, 在所有 Java 提交中击败了35.58%的用户
     */
    public void reverseString2(char[] s) {
        char temp = 0;
        int i = 0, j = s.length - 1;
        while(i < j){
            temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }

}

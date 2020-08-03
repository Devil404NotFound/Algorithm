package com.ljp.leecode_cn.stirng;

/**
 * 415. 字符串相加
 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。

 注意：

 num1 和num2 的长度都小于 5100.
 num1 和num2 都只包含数字 0-9.
 num1 和num2 都不包含任何前导零。
 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 */
public class _简单_415_字符串相加 {
    public static void main(String[] args) {
        String num1 = "98";
        String num2 = "8";
        System.out.println(addStrings(num1, num2));
    }

    /**
     *
     * @param num1
     * @param num2
     * @return
     * 执行用时：
    7 ms, 在所有 Java 提交中击败了13.96%的用户
    内存消耗：
    39.6 MB, 在所有 Java 提交中击败了83.82%的用户
     */
    public static String addStrings(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int temp, digit = 0;
        StringBuilder sb = new StringBuilder();
        while(i >= 0 && j >= 0){
            temp = num1.charAt(i) - '0' + num2.charAt(j) - '0';
            temp += digit;
            digit = temp / 10;
            sb.append(temp % 10);
            i--;
            j--;
        }
        String srcPre = "", pre = "";
        int k = 0;
        if(i >= 0){
           srcPre = num1.substring(0, i + 1);
           k = i;
        }else{
            srcPre = num2.substring(0, j + 1);
            k = j;
        }
        while(k >= 0 && digit != 0){
            temp = srcPre.charAt(k) - '0';
            temp += digit;
            digit = temp / 10;
            sb.append(temp % 10);
            k--;
        }
        if(k >= 0){
            pre = srcPre.substring(0, k + 1);
        }else if(digit != 0){
            pre = "" + digit;
        }
        return pre + sb.reverse().toString();
    }
}

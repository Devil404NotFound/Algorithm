package org.lanqiao.algorithm.chapter08;

/**
 * 回文字符串
 */
public class _10判断是否回文 {
    public static void main(String[] args) {
        String str = "";
        System.out.println(isPalindrome(str));
    }
    public static Boolean isPalindrome(String str){
        return str.equals(new StringBuilder(str).reverse().toString());
    }
}

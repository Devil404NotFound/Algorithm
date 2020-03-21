package org.lanqiao.algorithm.chapter08;

/**
 * 请实现一个算法,翻转一个给定的字符串
 * 测试样例:This is nowcoder返回:" redocwon si sihT
 */
public class _02翻转字符串 {
    public static void main(String[] args) {
        String str = "This is nowcoder";
        String result = reverseString2(str);
        System.out.println(result);
    }

    /**
     * 方法一，字符数组处理
     * @param str
     * @return
     */
    public static String reverseString1(String str){
        char[] chars = new char[str.length()];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = str.charAt(chars.length - 1 - i);
        }
//        return String.valueOf(chars);
        return new String(chars);
    }
    /**
     * 方法二，使用StringBuilder类函数处理
     * @param str
     * @return
     */
    public static String reverseString2(String str){
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return sb.reverse().toString();
    }
}

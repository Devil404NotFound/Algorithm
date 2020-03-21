package org.lanqiao.algorithm.chapter08;

/**
 * 给定两个字符串s1和s2,要求判定s2是否能够被通过s1作循环移位( rotate)得到的字符串包含。
 * 例如,给定s1= AABCD和2=CDAA,返回true;给定s1=ABCD和s2=ACBD,返回 false。
 */
public class _07旋转词 {
    public static void main(String[] args) {
        String s1 = "aabcd";
        String s2 = "cdaa";
        Boolean result = isRotate(s1, s2);
        System.out.println(result);
    }
    public static boolean isRotate(String s1, String s2){
        String help = s1 + s1;
        return help.contains(s2);
    }
}

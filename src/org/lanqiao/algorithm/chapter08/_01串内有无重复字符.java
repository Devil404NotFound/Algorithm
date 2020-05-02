package org.lanqiao.algorithm.chapter08;

import java.util.HashSet;
import java.util.Set;

/**
 * 串内有无重复字符
 * 实现一个算法,确定一个字符串的所有字符是否全都不同。假使不允许使用额外的数据结构,又该如何外理?
 */

public class _01串内有无重复字符 {
    public static void main(String[] args) {
        String str = "abcdefg";
        System.out.println(noRepeString1(str));
    }

    /**
     * 方法一，借助数据Set的唯一性，添加每一个字符，如果遇到已经添加过的，则返回false
     * @param str
     * @return
     */
    public static boolean noRepeString1(String str){
        Set<Character> set = new HashSet();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(!set.add(chars[i])){
                return false;
            }
        }
        return true;
    }
    /**
     * 不能使用其他数据结构，如果是ASCII码
     * @param str
     * @return
     */
    public static boolean noRepeString2(String str){
        int[] helpArr = new int[128];
        int length = str.length();
        for (int i = 0; i < length; i++) {
            int c = str.charAt(i);
            if(helpArr[c] == 0){
                helpArr[c]++;
            }else{
                return false;
            }
        }
        return true;
    }
}

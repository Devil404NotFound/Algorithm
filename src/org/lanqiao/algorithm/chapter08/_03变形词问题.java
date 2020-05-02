package org.lanqiao.algorithm.chapter08;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个字符串,请编写程序,确定其中一个字符串的字符重新排列后,能否变成另一个字符串。
 * 这里规定大小写为不同字符,且考虑字符串中的空格。给定一个 string stringA和一个 string stringB,
 * 请返回一个bool,代表两串是否重新排列后可相同。保证两串的长度都小于等于5000
 * 测试样例Here you are, "Are you here返回: falset
 */
public class _03变形词问题 {
    public static void main(String[] args) {
        String str1 = "hello";
        String str2 = "hello";
        Boolean result = isSameString2(str1,str2);
        System.out.println(result);
    }


    /**
     * 如果是ASCII码
     * @param str1
     * @param str2
     * @return
     */
    private static Boolean isSameString1(String str1, String str2) {
        char[] chArr1 = str1.toCharArray();
        char[] chArr2 = str2.toCharArray();
        if(chArr1.length != chArr2.length){
            return false;
        }
        int[] helper = new int[128];
        for (int i = 0; i < chArr1.length; i++) {
            helper[chArr1[i]]++;
        }
        for (int i = 0; i < chArr2.length; i++) {
            if((--helper[chArr2[i]]) < 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 方法二，通用方法，利用HashMap
     * @param str1
     * @param str2
     * @return
     */
    private static Boolean isSameString2(String str1, String str2) {
        char[] chArr1 = str1.toCharArray();
        char[] chArr2 = str2.toCharArray();
        if(chArr1.length != chArr2.length){
            return false;
        }
        Integer num = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chArr1.length; i++) {
            num = map.get(chArr1[i]);
            if(num == null){
                num = 0;
            }
            map.put(chArr1[i],++num);
        }
        for (int i = 0; i < chArr2.length; i++) {
            num = map.get(chArr2[i]);
            if(num == null || (--num) < 0){
                return false;
            }
        }
        return true;
    }
}

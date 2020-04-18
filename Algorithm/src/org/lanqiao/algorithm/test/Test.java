package org.lanqiao.algorithm.test;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.lanqiao.Utils.Util;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        /*int i = 100;
        System.out.println(Integer.toBinaryString(i));
        System.out.println(i>>2);
        System.out.println(i<<1);
        System.out.println(1 << 1);*/
        /*int a = -15, b = -16;
        System.out.println(((long)(a - b) >> 63) & 1);*/
//        int[] arr = Util.getRandomArr(10, 4, 5);
//        Util.print(arr);
//        System.out.println(6 & 1);
        //求一个数的第几位
//        System.out.println(Util.getDigit(123,3));
        /*String str = "你好，世界";
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            System.out.println(ch);
            System.out.print(chars[i]+ " ");
            System.out.print((int)chars[i]);
        }*/
        /*String s1 = "heoello";
        String s2 = "el";
//        Boolean result = s1.contains(s2);
        String result = s1.substring(0,8);
        System.out.println(result);*/
        /*char ch = 'A';
        int a = ch;
        System.out.println(a);*/
        //看看计算机的正数负数的二进制
        /*System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(Integer.toBinaryString(-Integer.MIN_VALUE));
        System.out.println(-Integer.MIN_VALUE);
        int n = 6;
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(-n));
        System.out.println(n&-n);*/
        //字符串
//        System.out.println("abc".indexOf("b"));
        /*int n = 5;
        n -= 3 + 1;
        System.out.println(n);*/
        double data = 3.141592;
        System.out.println(String.format("%.2f", data));
    }
}

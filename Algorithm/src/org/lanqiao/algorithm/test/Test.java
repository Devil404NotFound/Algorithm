package org.lanqiao.algorithm.test;

import org.lanqiao.Utils.Util;

public class Test {
    public static void main(String[] args) {
//        int i = 100;
//        System.out.println(Integer.toBinaryString(i));
//        System.out.println(i>>2);
//        System.out.println(i<<1);
//        int[] arr = Util.getRandomArr(10, 4, 5);
//        Util.print(arr);
//        System.out.println(6 & 1);
        //求一个数的第几位
//        System.out.println(Util.getDigit(123,3));
        String str = "你好，世界";
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            System.out.println(ch);
            System.out.print(chars[i]+ " ");
            System.out.print((int)chars[i]);
        }

    }
}

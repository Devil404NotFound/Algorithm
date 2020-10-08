package org.lanqiao.algorithm.test;

//import com.sun.org.apache.xpath.internal.operations.Bool;
import org.lanqiao.Utils.Util;

import java.util.*;

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
        /*double data = 3.141592;
        System.out.println(String.format("%.2f", data));*/
     /*   System.out.println(Integer.toBinaryString(-3));
        System.out.println(Integer.toBinaryString(2));*/
//        int i = 3;
//        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
//        i = i - ((i >>> 1) & 0x55555555);
//        System.out.println(i);
//        System.out.println(0b11111111111111111111111111);//[a-z]映射位图最大整数
//        System.out.println(1 << 0);

       /* Set<Obstacle> set = new HashSet<>();
        Obstacle o1 = new Obstacle(1,2);
        Obstacle o2 = new Obstacle(2,1);
        System.out.println(o1.equals(o2));
        System.out.println(set.add(o1));
        System.out.println(set.add(o2));*/
        /*System.out.println(test.a);//5
        test t1 = new test();
        test t2 = new test();
        System.out.println(t1.a);//5
        t1.a = 1;
        System.out.println(t1.a);//1
        System.out.println(test.a);//1
        System.out.println(t2.a);//1
        t2.a = 10;
        System.out.println(t2.a);//10
        System.out.println(t1.a);//10
*/
        /*System.out.println(Long.MAX_VALUE);
        System.out.println((long)(Integer.MAX_VALUE / 2) * (Integer.MAX_VALUE / 2));*/
        String[] str = {"hello", "world", "!"};
        List<String> list = Arrays.asList(str);
        str[0] = "HHHHH";
        list.add("can I add it ?");
    }
/*    static class test{
        static int a = 5;
    }*/
    //重写hashCode()和equals()
    /*static class Obstacle{
        int x;
        int y;

        public Obstacle(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(x) + Integer.hashCode(y);
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj){
                return true;
            }
            if(!(obj instanceof Obstacle)){
                return false;
            }
            Obstacle o2 = (Obstacle)obj;
            return this.x == o2.x && this.y == o2.y;
        }
    }*/
}

package com.ljp.leecode_cn;

/**
 * @author lijunpeng
 * @date 2022/6/2 22:22
 * @description
 **/

public class Test2 {
    public static void main(String[] args) {
        /*for(long i = 41; i <= 8888888888L; ++i) {
            long result1 = fun1(i);
            long result2 = fun2(i);
            if(result1 != result2) {
                System.out.println("n=" + i + ":" + result1 + "==" + result2);
            }
        }*/
        int n = 8888;
        //System.out.println(func(n));
        System.out.println(1e2);
        Object subClass = new SubClass();
        Test2 test2 = (Test2) subClass;
        System.out.println(new SubClass() == new Test2());

    }

    private static long func(long n) {
        return n - fun2(n);
    }
    private static long fun2(long n) {
        if(n == 0) {
            return 0;
        }
        long count = 0;
        int num = 10;
        while(num <= n) {
            //1-10^n公式
            count = count * 9 + num / 10;
            num *= 10;
        }
        long first = n / (num / 10) % 10; //第一位数
        long cur4 = 0;
        if(first > 4) { //大于4的情况
            cur4 = (num / 10) - count;
        }else if(first == 4){ //第一位等于4的情况，直接把剩下的相加
            cur4 = n % (num / 10) + 1;
            return count * first + cur4;
        }
        count = count * first + cur4;
        long nextN = n % (num / 10);
        return count + fun2(nextN);
    }
    private static int fun1(long n) {
        int count = 0;
        for(long i = 1; i <= n; ++i) {
            if(String.valueOf(i).contains("4")) {
                count++;
            }
        }
        return count;
    }
}
class SubClass extends Test2{}

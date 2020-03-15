package org.lanqiao.algorithm.chapter01;

import org.lanqiao.Utils.Util;

import java.util.HashSet;
import java.util.Random;

/**
 * 【如何找到数组中唯一成对的那个数？】
 * 1-1000这个1000个数放在含有1001个元素的数组中，
 * 只有唯一的一个元素值重复，其它均只出现一次。
 * 每个数组元素只能访问一次，
 * 设计一个算法，将它找出来；不用辅助空间。
 */
public class _01FindDoubleNum {
    public static void main(String[] args) {
        //首先生成一个1-N的数组，有一个数重复
        int N = 11;
        int[] num = new int[N];
        for(int i = 0; i < num.length - 1; i++){
            num[i] = i + 1;
        }
        num[num.length - 1] = new Random().nextInt(N-1) + 1;
        int index = new Random().nextInt(N);
        //交换连个数
        Util.swap(num, index, num.length - 1);
        Util.print(num);
        int result = 0;
        // 异或运算符 a^a=0, a^0=a
        for(int i = 1; i < num.length; i++) {
            result = i^result;
        }
        for(int i = 0; i < num.length; i++) {
            result = result^num[i];
        }
        System.out.println(result);
        //方法二
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < num.length; i++){
            if(set.add(num[i]) != true) {
                System.out.println(num[i]);
                return;
            }

        }

    }
}

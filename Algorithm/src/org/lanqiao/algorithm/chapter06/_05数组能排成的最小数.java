package org.lanqiao.algorithm.chapter06;


import java.util.Arrays;
import java.util.Comparator;

/**
 * 数组能排成的最小数
 * 描述：
 *      输入一个正整数数组，把数组里所有整数拼接起来排成一个数，打印出能拼接出的所有数字中的最小的一个
 * 例如：
 *      输入数组{3,32,321}，则打印出321323
 */
public class _05数组能排成的最小数 {
    public static void main(String[] args) {
        Integer[] arr = {3,32,321};
       int result = sort(arr);
        System.out.println(result);
    }

    private static int sort(Integer[] arr) {
        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String str1 = o1 + "" + o2;
                String str2 = o2 + "" + o1;
                return str1.compareTo(str2);
            }
        });
        int result = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
        }
        result = Integer.parseInt(sb.toString());
        return result;
    }


}

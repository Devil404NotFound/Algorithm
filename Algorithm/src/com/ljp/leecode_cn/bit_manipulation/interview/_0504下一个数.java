package com.ljp.leecode_cn.bit_manipulation.interview;

import org.lanqiao.Utils.Util;

/**
 * 面试题 05.04. 下一个数
 下一个数。给定一个正整数，找出与其二进制表达式中1的个数相同且大小最接近的那两个数（一个略大，一个略小）。

 示例1:

 输入：num = 2（或者0b10）
 输出：[4, 1] 或者（[0b100, 0b1]）
 示例2:

 输入：num = 1
 输出：[2, -1]
 提示:

 num的范围在[1, 2147483647]之间；
 如果找不到前一个或者后一个满足条件的正数，那么输出 -1。
 */
public class _0504下一个数 {
    public static void main(String[] args) {
        int num = Integer.MAX_VALUE;
        for (int i = num; i > 0; i--) {
            int[] arr1 = findClosedNumbers(i);
            int[] arr2 = findClosedNumbers2(i);
//            Util.print(arr2);
            if(!isEquals(arr1
                    , arr2)){
                System.out.println("====================================");
                Util.print(arr1);
                Util.print(arr2);
            }
        }
    }
    public static boolean isEquals(int[] arr1, int[] arr2){
        return arr1[0] == arr2[0] && arr1[1] == arr2[1];
    }

    /**
     * 暴力解法
     * @param num
     * @return
     * 执行用时 :
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗 :
    36.9 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public static int[] findClosedNumbers(int num) {
        int max = -1, min = -1;
        int count = countBit(num);
        int x = num;
        while(x < Integer.MAX_VALUE){
            ++x;
            if(countBit(x) == count){
                max = x;
                break;
            }
        }
        x = num;
        //判断num是否全是1，全是1直接返回-1
        if((num & (num + 1)) != 0){
            while(x > 1){
                --x;
                if(countBit(x) == count){
                    min = x;
                    break;
                }
            }
        }
        return new int[]{max, min};
    }
    private static int countBit(int num){
        int count = 0;
        while(num != 0){
            num &= (num - 1);//这个计数有意思
            count++;
        }
        return count;
    }

    public static int[] findClosedNumbers2(int num){
        int max = -1, min = -1;
        int count = -1;
        //找max==1之后的第一0与前面的1互换
        for (int i = 0; i < 31; i++) {
            if((num & (1 << i)) != 0){
                count++;
            }else{
                if(count != -1){
                    max = num & ~((1 << i) - 1) | ((1 << i) + ((1 << count) - 1));
                    break;
                }
            }
        }
        count = -1;
        boolean flag = false;
        for (int i = 0; i < 31; i++) {
            if((num & (1 << i)) == 0){
                flag = true;
            }else{
                count++;
                if(flag){
                    min = (num & ~((1 << count) - 1) | ((1 << count) - 1) << (i - count - 1)) - (1 << (i - 1));
                    break;
                }
            }
        }
        return new int[]{max, min};
    }
}

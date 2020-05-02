package com.ljp.leecode_cn.bit_manipulation.interview;

import java.util.Scanner;

/**
 * 面试题 17.04. 消失的数字
 数组nums包含从0到n的所有整数，但其中缺了一个。请编写代码找出那个缺失的整数。你有办法在O(n)时间内完成吗？

 注意：本题相对书上原题稍作改动

 示例 1：

 输入：[3,0,1]
 输出：2


 示例 2：

 输入：[9,6,4,2,3,5,7,0,1]
 输出：8

 执行结果：
 通过

 执行用时 :
 0 ms, 在所有 Java 提交中击败了100.00%的用户
 内存消耗 :
 40.5 MB, 在所有 Java 提交中击败了100.00%的用户
 */
public class _1704消失的数字 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int res = missingNumber(arr);
        System.out.println(res);
        res = missingNumber2(arr);
        System.out.println(res);
        sc.close();
    }
    public static int missingNumber(int[] nums) {
        int res = nums.length;
        for(int i = 0; i < nums.length; ++i){
            res = res ^ i ^ nums[i];
        }
        return res;
    }
    public static int missingNumber2(int[] arr){
        int res = arr.length;
        for (int i = 0; i < arr.length; i++) {
            res += i - arr[i];
        }
        return res;
    }
}
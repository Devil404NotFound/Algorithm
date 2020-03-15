package org.lanqiao.Utils;

import sun.security.util.Length;

import java.util.Random;

public class Util {
    /**
     * 交换一个数组的两个元素
     * @param arr 传入一个数组
     * @param i 第一个元素下标
     * @param j 第二个元素下标
     */
    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 新建一个随机数组
     * @param length 数组长度
     * @param min 数组元素最小值
     * @param max 数组元素最大值
     * @return 返回新建的随机数组
     */
    public static int[] getRandomArr(int length, int min, int max){
        Random random = new Random();
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(max - min + 1) + min;
        }
        return arr;
    }

    /**
     * 打印一个数组的每一个元素
     * @param arr
     */
    public static void print(int[] arr){
        for(int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.println(arr[arr.length - 1]);
    }

    public static void print(int[][] matrix){
        for(int[] arr : matrix){
            for (int i : arr){
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    /**
     * 求一个数的digit位上的数字
     * @param num 传入一个数
     * @param digit 位数
     * @return 返回该位上的数字
     */
    public static int getDigit(int num, int digit){
        while(digit > 1){
            digit--;
            num /= 10;
        }
        return num % 10;
    }
}

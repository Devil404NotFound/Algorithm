package org.lanqiao._2020_03_14_校内模拟赛;

import java.util.Scanner;


/**
 * 问题描述
 　　在数列 a[1], a[2], ..., a[n] 中，如果对于下标 i, j, k 满足 0<i<j<k<n+1 且 a[i]<a[j]<a[k]，则称 a[i], a[j], a[k] 为一组递增三元组，a[j]为递增三元组的中心。
 　　给定一个数列，请问数列中有多少个元素可能是递增三元组的中心。
 输入格式
 　　输入的第一行包含一个整数 n。
 　　第二行包含 n 个整数 a[1], a[2], ..., a[n]，相邻的整数间用空格分隔，表示给定的数列。
 输出格式
 　　输出一行包含一个整数，表示答案。
 样例输入
 5
 1 2 5 3 5
 样例输出
 2

 思路：
 1.建立最小顶堆
 */

public class _05三元组 {
    public static void main(String[] args) {
        //输入
        Scanner sc = new Scanner(System.in);
        int num = Integer.valueOf(sc.nextLine());
        int[] arr = new int[num];
        for(int i = 0; i < num; i++){
            arr[i] = sc.nextInt();
        }
        int count = 0;
        for (int i = arr.length - 2; i > 0; i--){
            if(arr[i] < arr[i + 1]){
                for(int k = 0; k < i; k++){
                    if(arr[k] < arr[i]){
                        count++;
                        break;
                    }
                }
            }
            maxHeap(arr,i, arr.length);
        }
        System.out.println(count);

        sc.close();
    }
    public static void maxHeap(int[] arr, int i, int length) {
        int j = 2*i + 1;
        int temp = arr[i];
        while(j < length){
            if( j + 1 < length && arr[j+1] > arr[j]){//选择左右孩子中大的那个
                j++;
            }
            if(arr[j] > temp){//如果大的孩子比自己大
                arr[i] = arr[j];
                i = j;
                j = 2 * i + 1;
            }else{
                break;
            }
        }
        arr[i] = temp;
    }


}

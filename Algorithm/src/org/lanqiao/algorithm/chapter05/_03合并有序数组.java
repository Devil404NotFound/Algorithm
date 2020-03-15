package org.lanqiao.algorithm.chapter05;

import org.lanqiao.Utils.Util;

/**
 * 合并有序数组
 * 给定练个功排序后的数组A和B，其中A的末端有足够的缓冲空间容纳B。编写一个方法，将B合并入A并排序
 */
public class _03合并有序数组 {
    public static void main(String[] args) {
        int[] A = {3,4,6,8,12,15,18,0,0,0};
        int[] B = {0,5,9};
        Util.print(A);
        Util.print(B);
        merge(A, B);
        Util.print(A);

    }
    public static void merge(int[] A, int[] B){
        int a = A.length - 1;
        int b = B.length - 1;
        while(a > 0){
            if(A[a] != 0){
                break;
            }
            a--;
        }
        int index = a + b + 1;
        while(index >= 0){
            if(A[a] > B[b]){
                A[index] = A[a--];
            }else{
                A[index] = B[b--];
                if(b < 0){
                    return;
                }
            }
            index--;
            if(a < 0){
                while(b >= 0){
                    A[index--] = B[b--];
                }
            }
        }

    }
}

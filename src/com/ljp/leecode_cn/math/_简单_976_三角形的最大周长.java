package com.ljp.leecode_cn.math;

import java.util.Arrays;

/**
 976. 三角形的最大周长
 给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。

 如果不能形成任何面积不为零的三角形，返回 0。



 示例 1：

 输入：[2,1,2]
 输出：5
 示例 2：

 输入：[1,2,1]
 输出：0
 示例 3：

 输入：[3,2,3,4]
 输出：10
 示例 4：

 输入：[3,6,2,3]
 输出：8


 提示：

 3 <= A.length <= 10000
 1 <= A[i] <= 10^6
 * @author ljp
 * @date 2020/10/30 17:41
 */
public class _简单_976_三角形的最大周长 {
    /**
     * 直接排序再选择
     * @param A
     * @return
    执行用时：
    8 ms, 在所有 Java 提交中击败了97.90%的用户
    内存消耗：
    39.2 MB, 在所有 Java 提交中击败了85.29%的用户
     */
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for(int i = A.length - 1; i > 1; i--){
            if(A[i] < A[i - 1] + A[i - 2]){
                return A[i] + A[i - 1] + A[i - 2];
            }
        }
        return 0;
    }

    /**
     * 使用最大顶堆
     * @param A
     * @return
    执行用时：
    4 ms, 在所有 Java 提交中击败了98.71%的用户
    内存消耗：
    38.9 MB, 在所有 Java 提交中击败了92.96%的用户
     */
    public int largestPerimeter2(int[] A){
        for (int i = A.length / 2 - 1; i >= 0; i++) {
            maxHeap(A, i, A.length - 1);
        }
        for (int i = A.length - 1; i > 0; i++) {
            swap(A, 0, i);
            maxHeap(A, 0, i);
            if(i < A.length - 2 && A[i + 2] < A[i + 1] + A[i]){
                return A[i + 2] + A[i + 1] + A[i];
            }
        }
        return 0;
    }

    /**
     * 大顶堆
     * @param arr
     * @param i
     * @param length
     */
    private void maxHeap(int[] arr, int i, int length){
        int j = 2 * i + 1;
        int temp = arr[i];
        while(j < length){
            if(j + 1 < length && arr[j + 1] > arr[j]){
                j++;
            }
            if(arr[j] > temp) {
                arr[i] = arr[j];
                i = j;
                j = 2 * i + 1;
            }else{
                break;
            }
        }
        arr[i] = temp;
    }
    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

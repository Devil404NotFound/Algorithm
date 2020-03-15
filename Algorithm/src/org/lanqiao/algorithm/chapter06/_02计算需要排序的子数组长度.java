package org.lanqiao.algorithm.chapter06;

/**
 * 计算需要排序的子数组长度
 * 给定一个无序数组arr求出需要排序的最短
 * 要求：如arr={2,3,7,5,4,6},返回4，因为只有{7,5,4,6}需要排序
 * （该代码返回需要排序的左端点（res[0]）和右端点（res[1]）
 */

public class _02计算需要排序的子数组长度 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6};
        int[] res = findSegment(arr);
        System.out.println(res[0] + " " + res[1]);
        System.out.println(res[1] - res[0] + 1);

    }
    public static int[] findSegment(int[] arr){
        int[] res = new int[2];
        int start = -1;
        int end = 0;
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if( i < arr.length - 1 && arr[i] > arr[i + 1] && start == -1) {
                start = i;
            }
            //更新最大值
            if(max < arr[i]){
                max = arr[i];
            }
            //只要低于历史最大值，就更新右端点
            if(max > arr[i]){
                end = i;
            }
        }
        if(start == -1){
            res[0] = 0;
            res[1] = 0;
        }else{
            res[0] = start;
            res[1] = end;
        }
        return res;
    }
}

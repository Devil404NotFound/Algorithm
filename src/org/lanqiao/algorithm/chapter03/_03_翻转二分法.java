package org.lanqiao.algorithm.chapter03;

/**
 * 求旋转数组的最小数字
 * 把一个数组最开始的若干元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，素输出旋转数组的最小元素
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，改数组的最小值为1
 */
public class _03_翻转二分法 {
    public static void main(String[] args) {
        int[] arr = {3,4,5,1,2};
        int result = min(arr);
        System.out.println(result);
    }

    private static int min(int[] arr) {
        int begin = 0;
        int end = arr.length - 1;
        if(arr[begin] < arr[end]){
            return arr[begin];
        }
        int mid;
        while(begin + 1 < end){
            mid = begin + ((end - begin) >> 1);
            if(arr[mid] < arr[end]){
                end = mid;
            }else {
                begin = mid;
            }
        }
        return arr[end];
    }}

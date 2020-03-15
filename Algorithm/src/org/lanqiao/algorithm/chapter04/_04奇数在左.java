package org.lanqiao.algorithm.chapter04;

import org.lanqiao.Utils.Util;
/**
 * 调整数组顺序使奇数位于偶数前面
 * 输入一个整型数组，调整数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。要求时间复杂度为O(n)
 */

public class _04奇数在左 {
    public static void main(String[] args) {
        int[] arr = {8,1,3,6,2,4,7,9,0,10,11};
        leftAndRight(arr);
        Util.print(arr);
        Util.print(arr);
    }
    public static void leftAndRight(int[] arr){
        int left = 0;
        int right = arr.length - 1;
        while(left < right){
            while(left < right && (arr[left]&1) == 1){
                left++;
            }
            while(right > left && (arr[right] & 1) == 0){
                right--;
            }
            Util.swap(arr, left, right);
        }
    }
}

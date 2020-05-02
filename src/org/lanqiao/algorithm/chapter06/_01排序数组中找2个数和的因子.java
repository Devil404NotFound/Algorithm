package org.lanqiao.algorithm.chapter06;

/**
 * 给定已排序数组arr和数字k，不重复打印arr中所有相加和为k的不降序二元组
 * 如输入arr={-8,-4,-3,0,2,4,5,8,9,10}, k=10
 * 输出(0,10) (2,8)
 */
public class _01排序数组中找2个数和的因子 {
    public static void main(String[] args) {
        int[] arr = {-8,-4,-3,0,2,4,5,8,9,10};
        findAdd(arr, 10);

    }
    public static void findAdd(int[] arr, int k){
        int left = 0;
        int right = arr.length - 1;
        while(left < right){
            if(arr[left] + arr[right] < k){
                left++;
            }else if(arr[left] + arr[right] > k){
                right--;
            }else{
                System.out.println("(" + arr[left] + "," + arr[right] + ")");
                left++;
                right--;
            }
        }
    }
}

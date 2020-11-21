package com.ljp.leecode_cn.array;

import org.lanqiao.Utils.Util;

/** 每日一题 2020.11.10
 31. 下一个排列
 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

 必须原地修改，只允许使用额外常数空间。

 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 1,2,3 → 1,3,2
 3,2,1 → 1,2,3
 * @author ljp
 * @date 2020/11/10 16:03
 */
public class _中等_31_下一个排列 {
    public static void main(String[] args) {
        int[] nums = {1,4,3,2};
        new _中等_31_下一个排列().nextPermutation2(nums);
        Util.print(nums);
    }

    /**
     *快排，不用随机数，其实每次都是最糟糕的情况
     * @param nums
    执行用时：
    1 ms, 在所有 Java 提交中击败了98.72%的用户
    内存消耗：
    38.4 MB, 在所有 Java 提交中击败了94.66%的用户
     */
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        //找到倒数第一个递增下标
        while(i >= 0 && nums[i] >= nums[i + 1]){
            i--;
        }
        if(i >= 0){
            //往后遍历找到比nums[i] 大并且尽可能小的数
            int min = nums[i + 1];
            int index = i + 1;
            for (int j = i + 2; j < nums.length; j++) {
                if(min > nums[j] && nums[j] > nums[i]){//nums[i]后为下降序列，因此不必判断 min> nums[j]
                    min = nums[j];
                    index = j;
                }
            }
            swap(nums, i, index);
        }
        //利用快排让nums[i]后的数最小 (根据官方题解，这里[i + 1, length)必然为降序序列，因此只需要翻转这一段
        quickSort(nums, i + 1, nums.length - 1);
    }
    //快排
    private void quickSort(int[] arr, int low, int high){
        if(low < high){
            int temp = arr[low];
            int i = low, j = high;
            while(i < j){
                while(i < j && temp <= arr[j]){
                    j--;
                }
                arr[i] = arr[j];
                while(i < j && arr[i] <= temp){
                    i++;
                }
                arr[j] = arr[i];
            }
            arr[i] = temp;
            quickSort(arr, low, i - 1);
            quickSort(arr, i + 1, high);
        }
    }
    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /********************华丽的分割线*****************************/
    /**
     * 官方题解：两次扫描
     下一个排列总是比当前排列要大，除非已经是最大排列。我们希望找到一种方法，能够找到一个比当前序列大的新序列，而且变化幅度尽可能小。
     因此：
     1. 我们需要将左边的[较小数}与右边的[较大数]交换，以能够让当前序列变大。
     2. 同时我们要让这个[较小数]尽可能靠右，而[较大数]尽可能小。
     3. 当交换完成后[较小数]原来的位置后面从小到大排序（因为[i+1, n)是递减的，因此只需要翻转[i+1, n)
     * @param nums
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    38.5 MB, 在所有 Java 提交中击败了92.15%的用户
     */
    public void nextPermutation2(int[] nums) {
        int i = nums.length - 2;
        //找到倒数第一个递增的数nums[i]
        while(i >= 0 && nums[i] >= nums[i + 1]){
            i--;
        }
        //找到倒数第一个比nums[i]大的数
        if(i >= 0){
            int j = nums.length - 1;
            while(nums[i] >= nums[j]){
                j--;
            }
            //交换i与j
            swap(nums, i, j);
        }
        //翻转[i+1, n)
        ++i;
        int j = nums.length - 1;
        while(i < j){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }
}

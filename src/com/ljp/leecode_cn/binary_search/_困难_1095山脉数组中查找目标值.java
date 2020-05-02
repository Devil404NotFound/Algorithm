package com.ljp.leecode_cn.binary_search;

/**
 * 1095. 山脉数组中查找目标值【困难】
 （这是一个 交互式问题 ）

 给你一个 山脉数组 mountainArr，请你返回能够使得 mountainArr.get(index) 等于 target 最小 的下标 index 值。

 如果不存在这样的下标 index，就请返回 -1。



 何为山脉数组？如果数组 A 是一个山脉数组的话，那它满足如下条件：

 首先，A.length >= 3

 其次，在 0 < i < A.length - 1 条件下，存在 i 使得：

 A[0] < A[1] < ... A[i-1] < A[i]
 A[i] > A[i+1] > ... > A[A.length - 1]


 你将 不能直接访问该山脉数组，必须通过 MountainArray 接口来获取数据：

 MountainArray.get(k) - 会返回数组中索引为k 的元素（下标从 0 开始）
 MountainArray.length() - 会返回该数组的长度


 注意：

 对 MountainArray.get 发起超过 100 次调用的提交将被视为错误答案。此外，任何试图规避判题系统的解决方案都将会导致比赛资格被取消。

 为了帮助大家更好地理解交互式问题，我们准备了一个样例 “答案”：https://leetcode-cn.com/playground/RKhe3ave，请注意这 不是一个正确答案。



 示例 1：

 输入：array = [1,2,3,4,5,3,1], target = 3
 输出：2
 解释：3 在数组中出现了两次，下标分别为 2 和 5，我们返回最小的下标 2。
 示例 2：

 输入：array = [0,1,2,4,2,1], target = 3
 输出：-1
 解释：3 在数组中没有出现，返回 -1。


 提示：

 3 <= mountain_arr.length() <= 10000
 0 <= target <= 10^9
 0 <= mountain_arr.get(index) <= 10^9
 */

 // This is MountainArray's API interface.
 // You should not implement it, or speculate about its implementation
 interface MountainArray {
     public int get(int index);
     public int length();
     public int getCount();
 }
 class MountainArrayImpl implements MountainArray{
    int[] arr;
    int count = 0;
    MountainArrayImpl(int[] arr){
        this.arr = arr;
     }
     @Override
     public int get(int index) {
        count++;
         return arr[index];
     }

     @Override
     public int length() {
         return arr.length;
     }
     public int getCount(){
        return count;
     }
 }
public class _困难_1095山脉数组中查找目标值 {
    public static void main(String[] args) {
        int n = 10000;
        int[] arr = new int[n];
        for (int i = 0; i <= n >> 1; i++) {
            arr[i] = i * 2;
            arr[n - 1 - i] = i * 2 + 1;
        }
//        int[] arr = {1,5,2};
        MountainArray mountainArray = new MountainArrayImpl(arr);
        System.out.println(findInMountainArray(50, mountainArray));
        System.out.println(mountainArray.getCount());
    }

    /**
     *
     * @param target
     * @param mountainArr
     * @return
     * 执行用时 :
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗 :
    39.5 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public static int findInMountainArray(int target, MountainArray mountainArr) {
        int top = findInMountainArrayTop(mountainArr);
        int res = findInMountainArrayLeft(target, mountainArr, top);
        if(res == -1){
            res = findInMountainArrayRight(target, mountainArr, top);
        }
        return res;
    }
    private static int findInMountainArrayTop(MountainArray mountainArray){
        int left = 0;
        int right = mountainArray.length() - 1;
        int mid = right >> 1;
        while(left < right - 1){
            mid = left + ((right - left) >> 1);
            int m= mountainArray.get(mid);
            if(mountainArray.get(mid + 1) < m){
                right = mid;
            }else{
                left = mid;
            }
        }
        return right;
    }
    private static int findInMountainArrayLeft(int target, MountainArray mountainArray, int top){
        int left = 0;
        int right = top;
        while(left <= right){
            int mid = left + ((right - left) >> 1);
            int m = mountainArray.get(mid);
            if(target == m){
                return mid;
            }else if(target < m){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return -1;
    }
    private static int findInMountainArrayRight(int target, MountainArray mountainArray, int top){
        int left = top;
        int right = mountainArray.length() - 1;
        while(left <= right){
            int mid = left + ((right - left) >> 1);
            int m = mountainArray.get(mid);
            if(target == m){
                return mid;
            }else if(target > m){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return -1;
    }
}

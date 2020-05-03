package com.ljp.leecode_cn.greedy;

import java.util.Arrays;

/**
 * 1338. 数组大小减半
 给你一个整数数组 arr。你可以从中选出一个整数集合，并删除这些整数在数组中的每次出现。

 返回 至少 能删除数组中的一半整数的整数集合的最小大小。



 示例 1：

 输入：arr = [3,3,3,3,5,5,5,2,2,7]
 输出：2
 解释：选择 {3,7} 使得结果数组为 [5,5,5,2,2]、长度为 5（原数组长度的一半）。
 大小为 2 的可行集合有 {3,5},{3,2},{5,2}。
 选择 {2,7} 是不可行的，它的结果数组为 [3,3,3,3,5,5,5]，新数组长度大于原数组的二分之一。
 示例 2：

 输入：arr = [7,7,7,7,7,7]
 输出：1
 解释：我们只能选择集合 {7}，结果数组为空。
 示例 3：

 输入：arr = [1,9]
 输出：1
 示例 4：

 输入：arr = [1000,1000,3,7]
 输出：1
 示例 5：

 输入：arr = [1,2,3,4,5,6,7,8,9,10]
 输出：5


 提示：

 1 <= arr.length <= 10^5
 arr.length 为偶数
 1 <= arr[i] <= 10^5
 */
public class _中等_1338_数组大小减半 {
    /**
     *1. 找到最大值
     * 2. 创建一个长度为最大值+1的数组
     * 3. 统计arr数组的每个元素出现个数help[arr[i]]
     * 4. 给help数组排序。
     * 5. 反向遍历help，sum = arr.length，一个一个减，逐个减去，直到sum 小于等于arr.length的一般
     * @param arr
     * @return
     * 执行用时 :
    9 ms, 在所有 Java 提交中击败了98.98%的用户
    内存消耗 :
    49.6 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int minSetSize(int[] arr) {
        if(arr.length == 0){
            return 0;
        }
        //找到最大值
        int max= arr[0];
        for(int i = 0; i < arr.length; i++){
            if(max < arr[i]){
                max = arr[i];
            }
        }
        int[] help = new int[max + 1];
        for(int i = 0; i < arr.length; i++){
            help[arr[i]]++;
        }
        Arrays.sort(help);
        int count = 0;
        int sum = arr.length;
        for(int i = help.length - 1; i >= 0; i--){
            sum -= help[i];
            count++;
            if(sum * 2 <= arr.length){
                break;
            }
        }
        return count;
    }
}

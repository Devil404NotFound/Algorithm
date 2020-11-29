package com.ljp.leecode_cn.offer;

/**
 * @author ljp
 * @date 2020/11/29 23:44
 *
剑指 Offer 11. 旋转数组的最小数字
把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。

示例 1：

输入：[3,4,5,1,2]
输出：1
示例 2：

输入：[2,2,2,0,1]
输出：0
 */
public class _简单_11_旋转数组的最小数字 {
    /**
     * 二分法
     * @param numbers
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    38.2 MB, 在所有 Java 提交中击败了84.86%的用户
     */
    public int minArray(int[] numbers) {
        int left = 0, right = numbers.length - 1;
        while(left < right){
            if(numbers[left] < numbers[right]){
                return numbers[left];
            }
            int mid = (left + right) >> 1;
            if(numbers[left] < numbers[mid]){
                left = mid ;
            }else if(numbers[right] > numbers[mid]){
                right = mid;
            }else{//相等的情况
                ++left;
            }
        }
        return numbers[right];
    }
}

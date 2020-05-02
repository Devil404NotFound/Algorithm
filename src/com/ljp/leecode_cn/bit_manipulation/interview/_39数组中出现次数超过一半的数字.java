package com.ljp.leecode_cn.bit_manipulation.interview;

/**
 * 面试题39. 数组中出现次数超过一半的数字【简单】
 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。



 你可以假设数组是非空的，并且给定的数组总是存在多数元素。



 示例 1:

 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
 输出: 2


 限制：

 1 <= 数组长度 <= 50000
 */
public class _39数组中出现次数超过一半的数字 {
    public static void main(String[] args) {
        int[] arr = {8,8,7,7,7};
        System.out.println(majorityElement(arr));
    }

    /**
     * 摩尔投票法
     * @param nums
     * @return
     * 执行用时 :
    1 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗 :
    43.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public static int majorityElement(int[] nums) {
        int count = 1;
        int major = nums[0];
        for(int i = 1; i < nums.length; i++){
            if (major == nums[i]){
                count++;
            }else{
                count--;
                if(count == 0){
                    major = nums[i];
                    count++;
                }
            }
        }
        return major;
    }
}

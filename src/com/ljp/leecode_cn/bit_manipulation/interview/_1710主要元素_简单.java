package com.ljp.leecode_cn.bit_manipulation.interview;

/**
 *面试题 17.10. 主要元素【简单】
 如果数组中多一半的数都是同一个，则称之为主要元素。给定一个整数数组，找到它的主要元素。若没有，返回-1。

 示例 1：

 输入：[1,2,5,9,5,9,5,5,5]
 输出：5


 示例 2：

 输入：[3,2]
 输出：-1


 示例 3：

 输入：[2,2,1,1,1,2,2]
 输出：2


 说明：
 你有办法在时间复杂度为 O(N)，空间复杂度为 O(1) 内完成吗？
 */
public class _1710主要元素_简单 {
    public static void main(String[] args) {
        int[] arr = {1,3,3,4};
        System.out.println(majorityElement2(arr));
    }
    /**
     * 执行用时 :
     8 ms, 在所有 Java 提交中击败了30.21%的用户
     内存消耗 :
     42.6 MB, 在所有 Java 提交中击败了100.00%的用户
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int mid = nums.length >> 1;
        int count = 0;
        int res = 0;
        //先判断主要元素是不是0
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0){
                count++;
                if(count > mid){
                    return 0;
                }
            }
        }

        //不适用主要元素为0的情况
        for(int i = 0; i < 32; i++){
            count = 0;
            for(int j = 0; j < nums.length; j++){
                if(((nums[j] >> i) & 1) == 1){
                    count++;
                    if(count > mid){
                        res |= 1 << i;
                    }
                }
            }
        }
        if(res == 0){
            return -1;
        }
        return res;
    }

    /**
     * 提交运行最快（有缺陷，没有众数但是最后两个相同，答案错误)
     * 做了一些改动（注释处）
     * @param nums
     * @return
     * 执行用时 :
    2 ms, 在所有 Java 提交中击败了75.27%的用户
    内存消耗 :
    43.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public static int majorityElement2(int[] nums) {
        int co = 1, maj = nums[0];
        for(int i = 1;i < nums.length;i++){
            if(maj == nums[i])
                co++;
            else{
                co--;
                if(co == 0){
//                    maj = i == nums.length-1 ? -1 : nums[i+1];
                    maj = nums[i];
                    co++;
                }
            }
        }
        //再来一个循环，确认一下
        co = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == maj){
                co++;
                if(co > nums.length / 2){
                    return maj;
                }
            }
        }
        return -1;
    }
}

package com.ljp.leecode_cn.greedy;

/** 每日一题 2020.12.12
 * 376. 摆动序列
 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。

 例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。

 给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。

 示例 1:

 输入: [1,7,4,9,2,5]
 输出: 6
 解释: 整个序列均为摆动序列。
 示例 2:

 输入: [1,17,5,10,13,15,10,5,16,8]
 输出: 7
 解释: 这个序列包含几个长度为 7 摆动序列，其中一个可为[1,17,10,13,10,16,8]。
 示例 3:

 输入: [1,2,3,4,5,6,7,8,9]
 输出: 2
 进阶:
 你能否用 O(n) 时间复杂度完成此题?
 */
public class _中等_376_摆动序列 {
    public static void main(String[] args) {
        int[] nums = {1,7,4,9,2,5};
        System.out.println(wiggleMaxLength(nums));
    }

    /**
     * 错误方法
     * @param nums
     * @return
     */
    /*public static int wiggleMaxLength(int[] nums) {
        if(nums.length < 1){
            return 0;
        }
        int max1 = 1, max2 = 1;
        int last1 = nums[0];
        int last2 = nums[0];
        int plus = 0;
        int i = 1;
        while(i < nums.length){
            //正负情况
            if(nums[i] > 0){
                if(last1 < 0){
                    max1++;
                    last1 = nums[i];
                }else if(last1 < nums[i]){
                    last1 = nums[i];
                }
            }else if(nums[i] < 0){
                if(last1 > 0){
                    max1++;
                    last1 = nums[i];
                }else if(last1 > nums[i]){
                    last1 = nums[i];
                }
            }

            //差值是正负的情况
            if((plus > 0 && nums[i] - last2 < 0) || (plus < 0 && nums[i] - last2 > 0)){
                max2++;
                last2 = nums[i];
                plus = -plus;
            }else if(plus == 0 && nums[i] != last2){
                max2++;
                plus = nums[i] - last2;
                last2 = nums[i];
            }
            i++;
        }
        return Math.max(max1, max2);
    }*/

    /**
     * 官方题解三：贪心算法
     通过统计有多少个峰值和谷值
     * @param nums
     * @return
     */
    public static int wiggleMaxLength(int[] nums) {
        if(nums.length < 2){
            return nums.length;
        }
        int up = 1, down = 1;
        //交替更新顶峰数和低谷数
        for(int i = 1; i < nums.length; i++){
            if(nums[i] > nums[i - 1]){
                up = down + 1;
            }else if(nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }
}

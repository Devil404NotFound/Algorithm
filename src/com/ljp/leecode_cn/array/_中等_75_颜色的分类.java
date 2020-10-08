package com.ljp.leecode_cn.array;

/** 每日一题 2020.10.07
 75. 颜色分类
 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

 注意:
 不能使用代码库中的排序函数来解决这道题。

 示例:

 输入: [2,0,2,1,1,0]
 输出: [0,0,1,1,2,2]
 进阶：

 一个直观的解决方案是使用计数排序的两趟扫描算法。
 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 你能想出一个仅使用常数空间的一趟扫描算法吗？
 */
public class _中等_75_颜色的分类 {
    /**
     *
     * @param nums
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    37.2 MB, 在所有 Java 提交中击败了73.59%的用户
     */
    public void sortColors(int[] nums) {
        int zone = 0, one = 0;
        for(int i = 0; i < nums.length; i++){
            switch(nums[i]){
                case 0:
                    zone++;
                case 1:
                    one++;
            }
        }
        for(int i = 0; i < nums.length; i++){
            if(zone > i){
                nums[i] = 0;
            }else if(one > i){
                nums[i] = 1;
            }else{
                nums[i] = 2;
            }
        }
    }
}

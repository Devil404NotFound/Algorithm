package com.ljp.leecode_cn.bit_manipulation;

/**
 * 169. 多数元素
 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。

 你可以假设数组是非空的，并且给定的数组总是存在多数元素。



 示例 1:

 输入: [3,2,3]
 输出: 3
 示例 2:

 输入: [2,2,1,1,1,2,2]
 输出: 2

方法一
 执行用时 :
 8 ms, 在所有 Java 提交中击败了37.97%的用户
 内存消耗 :
 42.9 MB, 在所有 Java 提交中击败了5.71%的用户

 方法二：
 执行用时 :
 1 ms, 在所有 Java 提交中击败了99.97%的用户
 内存消耗 :
 43 MB, 在所有 Java 提交中击败了5.71%的用户
 */
public class _169多数元素 {

    /**
     * 二进制统计方法
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int count = 0;
        int res = 0;
        int k = nums.length >> 1;
        for(int i =  0; i < 32; ++i){
            count = 0;
            for(int j = 0; j < nums.length; ++j){
                count += (nums[j] >> i) & 1;
                if(count > k){
                    res += 1 << i;
                    break;
                }
            }
        }
        return res;
    }

    public int majorityElement2(int[] nums){
        int count = 1;
        int cand_num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(cand_num == nums[i]){
                count++;
            }else{
                count--;
                if(count == 0){
                    count++;
                    cand_num = nums[i];
                }
            }
        }
        return cand_num;
    }
}

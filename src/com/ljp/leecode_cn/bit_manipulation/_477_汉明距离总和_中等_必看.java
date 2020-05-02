package com.ljp.leecode_cn.bit_manipulation;

import java.util.HashMap;
import java.util.Map;

/**
 * 477. 汉明距离总和【中等】
 两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。

 计算一个数组中，任意两个数之间汉明距离的总和。

 示例:

 输入: 4, 14, 2

 输出: 6

 解释: 在二进制表示中，4表示为0100，14表示为1110，2表示为0010。（这样表示是为了体现后四位之间关系）
 所以答案为：
 HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
 注意:

 数组中元素的范围为从 0到 10^9。
 数组的长度不超过 10^4。
 */
public class _477_汉明距离总和_中等_必看 {
    /**
     * 我的暴力解法
     * @param nums
     * @return
     */
    public int totalHammingDistance(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();//统计汉明距离（key为汉明距离的整数表示，value为距离）
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int hanming = nums[i] ^ nums[j];
                if(map.containsKey(hanming)){
                    count += map.get(hanming);
                }else{
                    int number = countBit(hanming);
                    count += number;
                    map.put(hanming,number);
                }
            }
        }
        return count;
    }
    private int countBit(int n){
        int count = 0;
        while(n != 0){
            count++;
            n &= (n - 1);
        }
        return count;
    }

    /**
     * 大神解法
     * @param nums
     * @return
     * 执行用时 :
    19 ms, 在所有 Java 提交中击败了44.89%的用户
    内存消耗 :
    40.8 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int totalHammingDistance2(int[] nums) {
        int count = 0;
        for (int i = 0; i < 30; i++) {
            int bit = 0;
            for (int j = 0; j < nums.length; j++) {
                bit += ((1 << i) & nums[j]) == 0 ? 0 : 1;
            }
            count += bit * (nums.length - bit);
        }
        return count;
    }
}

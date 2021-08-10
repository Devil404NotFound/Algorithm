package com.ljp.leecode_cn.greedy;

/** 每日一题 2020.12.24
 * @author ljp
 * @date 2020/12/24 1:23
135. 分发糖果
老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。

你需要按照以下要求，帮助老师给这些孩子分发糖果：

每个孩子至少分配到 1 个糖果。
相邻的孩子中，评分高的孩子必须获得更多的糖果。
那么这样下来，老师至少需要准备多少颗糖果呢？

示例 1:

输入: [1,0,2]
输出: 5
解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
示例 2:

输入: [1,2,2]
输出: 4
解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 */
public class _困难_135_分发糖果 {
    /**
     * 二次遍历（根据题解做出来的）
     * @param ratings
     * @return
    执行用时：
    3 ms, 在所有 Java 提交中击败了65.99%的用户
    内存消耗：
    39.8 MB, 在所有 Java 提交中击败了28.46%的用户
     */
    public int candy(int[] ratings) {
        int count = 0;
        int n = ratings.length;
        if(n == 0) {
            return 0;
        }
        int[] left = new int[n];
        int[] right = new int[n];
        left[n - 1] = right[0] = 1;
        for(int i = n - 2; i >= 0; --i) {
            if(ratings[i] > ratings[i + 1]){
                left[i] = left[i + 1] + 1;
            }else{
                left[i] = 1;
            }
        }
        for(int i = 1; i < n; ++i) {
            if(ratings[i] > ratings[i - 1]){
                right[i] = right[i - 1] + 1;
            }else{
                right[i] = 1;
            }
            count += Math.max(left[i], right[i]);
        }
        count += Math.max(left[0], right[0]);//开头不要漏了
        return count;
    }
}

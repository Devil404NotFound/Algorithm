package com.ljp.leecode_cn.greedy;

/**
 * 1053. 交换一次的先前排列
 给你一个正整数的数组 A（其中的元素不一定完全不同），请你返回可在 一次交换（交换两数字 A[i] 和 A[j] 的位置）后得到的、按字典序排列小于 A 的最大可能排列。

 如果无法这么操作，就请返回原数组。



 示例 1：

 输入：[3,2,1]
 输出：[3,1,2]
 解释：
 交换 2 和 1


 示例 2：

 输入：[1,1,5]
 输出：[1,1,5]
 解释：
 这已经是最小排列


 示例 3：

 输入：[1,9,4,6,7]
 输出：[1,7,4,6,9]
 解释：
 交换 9 和 7


 示例 4：

 输入：[3,1,1,3]
 输出：[1,3,1,3]
 解释：
 交换 1 和 3


 提示：

 1 <= A.length <= 10000
 1 <= A[i] <= 10000
 */
public class _中等_1053_交换一次先前的排列 {
    /**
     * 相通了很简单，想不明白头都炸了，看了题解才懂
     * @param A
     * @return
     * 执行用时 :
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗 :
    40.4 MB, 在所有 Java 提交中击败了33.33%的用户
     */
    public int[] prevPermOpt1(int[] A) {
        if(A.length == 0){
            return A;
        }
        for(int i = A.length - 2; i >= 0; i--){
            if(A[i] > A[i + 1]){
                int max = A[i + 1];
                int index = i + 1;
                for(int j = i + 1; j < A.length; j++){
                    if(A[j] > max && A[j] < A[i]){
                        max = A[j];
                        index = j;
                    }
                }
                A[index] = A[i];
                A[i] = max;
                return A;
            }
        }
        return A;
    }
}

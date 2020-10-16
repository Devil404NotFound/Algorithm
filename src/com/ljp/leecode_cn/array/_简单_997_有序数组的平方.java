package com.ljp.leecode_cn.array;

import java.util.Arrays;

/**
 * @author ljp
 * @date 2020/10/16 12:35
 */
public class _简单_997_有序数组的平方 {
    /**
     * 直接平方、排序
     * @param A
     * @return
    执行用时：
    2 ms, 在所有 Java 提交中击败了66.81%的用户
    内存消耗：
    40.2 MB, 在所有 Java 提交中击败了95.81%的用户
     */
    public int[] sortedSquares(int[] A) {
        for(int i = 0; i < A.length; i++){
            A[i] = A[i] * A[i];
        }
        Arrays.sort(A);
        return A;
    }

    /**
     * 双指针
     * @param A
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    40.5 MB, 在所有 Java 提交中击败了62.26%的用户
     */
    public int[] sortedSquares2(int[] A){
        int[] ans = new int[A.length];
        int left = 0, right = A.length - 1;
        for(int i = right; i >= 0; i--){
            if(Math.abs(A[left]) >= Math.abs(A[right])){//这里可以用A[left]取负来和A[right]比较
                ans[i] = A[left] * A[left];
                ++left;
            }else{
                ans[i] = A[right] * A[right];
                --right;
            }
        }
        return ans;
    }
}

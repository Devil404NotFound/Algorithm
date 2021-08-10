package com.ljp.leecode_cn.array;

/** 每日一题 2021.02.28
 * @author lijunpeng
 * @date 2021/2/28 23:02
 */
public class _简单_896_单调数列 {
    /**
     * 贪心算法
     * @param A
     * @return
    执行用时：
    2 ms, 在所有 Java 提交中击败了40.70%的用户
    内存消耗：
    46.7 MB, 在所有 Java 提交中击败了54.15%的用户
     */
    public boolean isMonotonic(int[] A) {
        if(A.length == 1) {
            return true;
        }
        int last = A[1] - A[0]; //记录前面2个数的差是正是负（正递增，负递减，注意考虑为0的情况）
        for(int i = 2; i < A.length; ++i) {
            int curr = A[i] - A[i - 1];
            if(curr != 0) {//如果等于0就可以直接跳过
                //last为0的情况意味着前面相邻的数相减都时间0
                //curr 和last如果一个为正，一个为负，异或的结果为负
                if(last != 0 && (curr ^ last) < 0) {
                    return false;
                }
                last = curr;
            }
        }
        return true;
    }

    /**
     * 官方题解：
     * @param A
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    46.8 MB, 在所有 Java 提交中击败了37.06%的用户
     */
    public boolean isMonotonic2(int[] A) {
        return isSorted(A, true) || isSorted(A, false);
    }
    private boolean isSorted(int[] A, boolean increasing) {
        if(increasing) {
            for (int i = 1; i < A.length; i++) {
                if(A[i] > A[i - 1]) {
                    return false;
                }
            }
        }else{
            for (int i = 1; i < A.length; i++) {
                if(A[i] < A[i - 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}

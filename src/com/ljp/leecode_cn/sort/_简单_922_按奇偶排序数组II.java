package com.ljp.leecode_cn.sort;

/** 每日一题 2020.11.12
 922. 按奇偶排序数组 II
 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。

 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。

 你可以返回任何满足上述条件的数组作为答案。



 示例：

 输入：[4,2,5,7]
 输出：[4,5,2,7]
 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。


 提示：

 2 <= A.length <= 20000
 A.length % 2 == 0
 0 <= A[i] <= 1000

 * @author ljp
 * @date 2020/11/12 11:11
 */
public class _简单_922_按奇偶排序数组II {
    /**
     *笨方法
     * @param A
     * @return
    执行用时：
    3 ms, 在所有 Java 提交中击败了78.68%的用户
    内存消耗：
    39.5 MB, 在所有 Java 提交中击败了94.02%的用户
     */
    public int[] sortArrayByParityII(int[] A) {
        int i = 0, j = 1;
        while(true){
            while(i < A.length && (A[i] & 1) == 0){
                i += 2;
            }
            while(j < A.length && (A[j] & 1) == 1){
                j += 2;
            }
            if(i < A.length && j < A.length){
                swap(A, i, j);
                i += 2;
                j += 2;
            }else{
                break;
            }
        }
        return A;
    }
    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     *双指针：对方法一优化
     * @param A
     * @return
    执行用时：
    2 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    41.4 MB, 在所有 Java 提交中击败了35.54%的用户
     */
    public int[] sortArrayByParityII2(int[] A) {
        int[] ans =new int[A.length];
        int even = 0, odd = 1;
        for (int i = 0; i < A.length; i++) {
            if((A[i] & 1) == 0){
                ans[even] = A[i];
                even += 2;
            }else{
                ans[odd] = A[i];
                odd += 2;
            }
        }
        return ans;
    }

    /**
     * 官方题解二：双指针
     * @param A
     * @return
    执行用时：
    3 ms, 在所有 Java 提交中击败了78.68%的用户
    内存消耗：
    39.8 MB, 在所有 Java 提交中击败了84.48%的用户
     */
    public int[] sortArrayByParityII3(int[] A) {
        int j = 1;
        for (int i = 0; i < A.length; i += 2) {
            if(A[i] % 2 == 1){
                while(A[j] % 2 == 1){
                    j += 2;
                }
                swap(A, i, j);
            }
        }
        return A;
    }
}

package com.ljp.leecode_cn.bit_manipulation;

/**
 * 1131. 绝对值表达式的最大值
 给你两个长度相等的整数数组，返回下面表达式的最大值：

 |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|

 其中下标 i，j 满足 0 <= i, j < arr1.length。



 示例 1：

 输入：arr1 = [1,2,3,4], arr2 = [-1,4,5,6]
 输出：13
 示例 2：

 输入：arr1 = [1,-2,-5,0,10], arr2 = [0,-2,-1,-7,-4]
 输出：20


 提示：

 2 <= arr1.length == arr2.length <= 40000
 -10^6 <= arr1[i], arr2[i] <= 10^6

 执行用时 :
 3 ms, 在所有 Java 提交中击败了100.00%的用户
 内存消耗 :
 45.9 MB, 在所有 Java 提交中击败了100.00%的用户

 */
public class _1131绝对值表达式的最大值 {
    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int[][] maxArr = new int[4][arr1.length];
        for(int i = 0; i < arr1.length; i++){
            maxArr[0][i] = arr1[i] + arr2[i] + i;
            maxArr[1][i] = arr1[i] + arr2[i] - i;
            maxArr[2][i] = arr1[i] - arr2[i] + i;
            maxArr[3][i] = arr1[i] - arr2[i] - i;
        }
        int ans = Integer.MIN_VALUE;
        int max, min;
        for(int i = 0; i < 4; i++){
            max = maxArr[i][0];
            min = maxArr[i][0];
            for(int j = 0; j < arr1.length; j++){
                if(max < maxArr[i][j]){
                    max = maxArr[i][j];
                }else if(min > maxArr[i][j]){
                    min = maxArr[i][j];
                }
            }
            if(ans < max - min){
                ans = max - min;
            }
        }
        return ans;
    }

    /**
     * 大神案例
     * @param arr1
     * @param arr2
     * @return
     */
    public int maxAbsValExpr2(int[] arr1, int[] arr2) {
        int[][] arrs = {{1,1},{1,-1},{-1,1},{-1,-1}};
        int res = 0;
        for(int[] arr: arrs){
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for(int i =0; i<arr1.length; i++){
                int result = arr1[i] * arr[0] + arr2[i] * arr[1] + i;
                if(max < result){
                    max = result;
                }
                if(min > result){
                    min = result;
                }
            }
            if(res < max - min){
                res = max - min;
            }
        }
        return res;
    }
}

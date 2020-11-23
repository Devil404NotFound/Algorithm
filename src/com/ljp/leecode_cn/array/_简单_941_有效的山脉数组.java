package com.ljp.leecode_cn.array;

/** 每日一题2020.11.03
 941. 有效的山脉数组
 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。

 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：

 A.length >= 3
 在 0 < i < A.length - 1 条件下，存在 i 使得：
 A[0] < A[1] < ... A[i-1] < A[i]
 A[i] > A[i+1] > ... > A[A.length - 1]






 示例 1：

 输入：[2,1]
 输出：false
 示例 2：

 输入：[3,5,5]
 输出：false
 示例 3：

 输入：[0,3,2,1]
 输出：true


 提示：

 0 <= A.length <= 10000
 0 <= A[i] <= 10000

 * @author ljp
 * @date 2020/11/3 11:25
 */
public class _简单_941_有效的山脉数组 {
    /**
     * 这玩意我自己理解都费劲（官方题解更简单）
     * @param A
     * @return
    执行用时：
    2 ms, 在所有 Java 提交中击败了41.65%的用户
    内存消耗：
    39.4 MB, 在所有 Java 提交中击败了84.91%的用户
     */
    public boolean validMountainArray(int[] A) {
        boolean up = true;
        for(int i = 1; i < A.length; i++){
            if(up && A[i - 1] < A[i]){//上升
                continue;
            }else if(!up && A[i - 1] > A[i]){//下降
                continue;
            }else if(i > 1 && up && A[i - 1] > A[i]){//顶峰
                up = false;
                continue;
            }
            return false;
        }
        return A.length >= 3 && !up;
    }

    /**
     * 官方题解
     * @param A
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    39.5 MB, 在所有 Java 提交中击败了71.82%的用户
     */
    public boolean validMountainArray2(int[] A){
        int i = 1;
        while(i < A.length && A[i - 1] < A[i]){
            i++;
        }
        if(i == 1 || i == A.length){
            return false;
        }
        while(i < A.length && A[i - 1] > A[i]){
            i++;
        }
        return i == A.length;
    }
}

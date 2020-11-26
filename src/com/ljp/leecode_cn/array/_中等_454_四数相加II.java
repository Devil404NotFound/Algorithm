package com.ljp.leecode_cn.array;

import java.util.HashMap;
import java.util.Map;

/** 每日一题 2020.11.27
 * @author ljp
 * @date 2020/11/27 0:42
 *
454. 四数相加 II
给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。

为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。

例如:

输入:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

输出:
2

解释:
两个元组如下:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */
public class _中等_454_四数相加II {
    /**
     *
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
    执行用时：
    73 ms, 在所有 Java 提交中击败了78.76%的用户
    内存消耗：
    62.5 MB, 在所有 Java 提交中击败了12.04%的用户
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int n = A.length;
        int[] ab = new int[n * n];
        int[] cd = new int[n * n];
        //A与B相加
        int index = 0;
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < n; ++j){
                ab[index++] = A[i] + B[j];//////在这里就可以直接存哈希
            }
        }
        //C与D相加
        index = 0;
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j){
                cd[index++] = C[i] + D[j];//////在这里就可以直接读哈希
            }
        }
        //利用哈希表统计每个数出现次数
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < ab.length; ++i){
            int num = map.getOrDefault(ab[i], 0);
            map.put(ab[i], num + 1);
        }
        int count = 0;
        //把map中cd的负数的个数累加就是答案
        for(int i = 0; i < cd.length; ++i){
            int num = map.getOrDefault(-cd[i], 0);
            count += num;
        }
        return count;
    }

    /**
     * 官方题解（思路一样，代码好太多）
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
    执行用时：
    66 ms, 在所有 Java 提交中击败了93.89%的用户
    内存消耗：
    57.3 MB, 在所有 Java 提交中击败了72.25%的用户
     */
    public int fourSumCount2(int[] A, int[] B, int[] C, int[] D) {
        int n = A.length;
        //利用哈希表统计每个数出现次数
        Map<Integer, Integer> map = new HashMap<>();
        //A与B相加
        for(int a : A) {
            for(int b : B) {
                int num = a + b;
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }
        //把map中cd的负数的个数累加就是答案
        int count = 0;
        for(int c : C) {
            for(int d : D) {
                int num = c + d;
                count += map.getOrDefault(-num, 0);
            }
        }
        return count;
    }
}

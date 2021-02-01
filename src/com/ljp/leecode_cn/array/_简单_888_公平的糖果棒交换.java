package com.ljp.leecode_cn.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** 每日一题 2021.02.01
 * @author lijunpeng
 * @date 2021/2/1 22:53
888. 公平的糖果棒交换
爱丽丝和鲍勃有不同大小的糖果棒：A[i] 是爱丽丝拥有的第 i 根糖果棒的大小，B[j] 是鲍勃拥有的第 j 根糖果棒的大小。

因为他们是朋友，所以他们想交换一根糖果棒，这样交换后，他们都有相同的糖果总量。（一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）

返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，ans[1] 是 Bob 必须交换的糖果棒的大小。

如果有多个答案，你可以返回其中任何一个。保证答案存在。



示例 1：

输入：A = [1,1], B = [2,2]
输出：[1,2]
示例 2：

输入：A = [1,2], B = [2,3]
输出：[1,2]
示例 3：

输入：A = [2], B = [1,3]
输出：[2,3]
示例 4：

输入：A = [1,2,5], B = [2,4]
输出：[5,4]


提示：

1 <= A.length <= 10000
1 <= B.length <= 10000
1 <= A[i] <= 100000
1 <= B[i] <= 100000
保证爱丽丝与鲍勃的糖果总量不同。
答案肯定存在。
 */
public class _简单_888_公平的糖果棒交换 {
    /**
     * 哈希
     * @param A
     * @param B
     * @return
    执行用时：
    35 ms, 在所有 Java 提交中击败了27.76%的用户
    内存消耗：
    40.9 MB, 在所有 Java 提交中击败了5.01%的用户
     */
    public int[] fairCandySwap(int[] A, int[] B) {
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();
        int sumA = 0, sumB = 0;
        for(int i = 0; i < A.length; ++i) {
            setA.add(A[i]);
            sumA += A[i];
        }
        for(int i = 0; i < B.length; ++i) {
            setB.add(B[i]);
            sumB += B[i];
        }
        int dis = (sumA - sumB) >> 1;
        int num = 0;
        while(true) {
            if(setA.contains(num + dis) && setB.contains(num)) {
                break;
            }
            num++;
        }
        return new int[]{num + dis, num};
    }

    /**
     * 官方题解：哈希表
     * @param A
     * @param B
     * @return
    执行用时：
    18 ms, 在所有 Java 提交中击败了34.14%的用户
    内存消耗：
    40 MB, 在所有 Java 提交中击败了67.19%的用户
     */
    public int[] fairCandySwap2(int[] A, int[] B) {
        //获取数组A、B的累加值
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        //数组A与数组B元素互换的差值
        int delta = (sumA - sumB) / 2;
        //记录数组A的值
        Set<Integer> rec = new HashSet<>();
        for (int num : A) {
            rec.add(num);
        }
        int[] ans = new int[2];
        for (int y : B) {
            int x = y + delta;
            if(rec.contains(x)) {
                ans[0] = x;
                ans[1] = y;
                break;
            }
        }
        return ans;
    }
}

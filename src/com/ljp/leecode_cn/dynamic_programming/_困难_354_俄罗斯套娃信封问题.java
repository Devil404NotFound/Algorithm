package com.ljp.leecode_cn.dynamic_programming;

import java.util.Arrays;
import java.util.Comparator;

/** 每日一题 2021.03.04
 * @author lijunpeng
 * @date 2021/3/4 23:55
 */
public class _困难_354_俄罗斯套娃信封问题 {
    /**
     * 官方题解一：动态规划
     * @param envelopes
     * @return
    执行用时：
    252 ms, 在所有 Java 提交中击败了38.40%的用户
    内存消耗：
    39.2 MB, 在所有 Java 提交中击败了87.26%的用户
     */
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }

        int n = envelopes.length;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] e1, int[] e2) {
                if (e1[0] != e2[0]) {
                    return e1[0] - e2[0];
                } else {
                    return e2[1] - e1[1];
                }
            }
        });

        int[] f = new int[n];
        Arrays.fill(f, 1);
        int ans = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (envelopes[j][1] < envelopes[i][1]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}

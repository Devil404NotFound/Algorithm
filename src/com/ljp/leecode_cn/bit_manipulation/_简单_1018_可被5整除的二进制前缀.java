package com.ljp.leecode_cn.bit_manipulation;

import java.util.ArrayList;
import java.util.List;

/**每日一题 2021.01.14
 * @author lijunpeng
 * @date 2021/1/15 0:04
 */
public class _简单_1018_可被5整除的二进制前缀 {
    /**
     * 官方题解：模拟
     * @param A
     * @return
    执行用时：
    4 ms, 在所有 Java 提交中击败了92.76%的用户
    内存消耗：
    39.4 MB, 在所有 Java 提交中击败了18.07%的用户
     */
    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> list = new ArrayList<Boolean>();
        int prefix = 0;
        int length = A.length;
        for (int i = 0; i < length; i++) {
            prefix = ((prefix << 1) + A[i]) % 5;//关键代码
            list.add(prefix == 0);
        }
        return list;
    }
}

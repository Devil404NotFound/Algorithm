package com.ljp.leecode_cn.bit_manipulation;

import java.util.ArrayList;
import java.util.List;

/**
 * 89. 格雷编码
 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。

 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。即使有多个不同答案，你也只需要返回其中一种。

 格雷编码序列必须以 0 开头。



 示例 1:

 输入: 2
 输出: [0,1,3,2]
 解释:
 00 - 0
 01 - 1
 11 - 3
 10 - 2

 对于给定的 n，其格雷编码序列并不唯一。
 例如，[0,2,3,1] 也是一个有效的格雷编码序列。

 00 - 0
 10 - 2
 11 - 3
 01 - 1
 示例 2:

 输入: 0
 输出: [0]
 解释: 我们定义格雷编码序列必须以 0 开头。
 给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
 因此，当 n = 0 时，其格雷编码序列为 [0]。
 */
public class _中等_89_格雷编码 {
    public static void main(String[] args) {
        List<Integer> res = new _中等_89_格雷编码().grayCode(3);
    }

    /**
     *
     * @param n
     * @return
     * 执行用时：
    2 ms, 在所有 Java 提交中击败了13.36%的用户
    内存消耗：
    37.4 MB, 在所有 Java 提交中击败了84.35%的用户
     */
    public List<Integer> grayCode(int n) {
        List<Integer> list = new ArrayList<>();
        int len = 1 << n;
        for(int i = 0; i < len; i++){
            int num = i;
            int gray = 0;
            for(int j = 0; j < 30; j++){
                int i1 = (num >> j) & 1;
                int i2 = (num >> j + 1) & 1;
                gray |= (i1 ^ i2) << j;
            }
            list.add(gray);
        }
        return list;
    }

    /**
     * 镜像反射法
     * @param n
     * @return
     * 执行用时：
    1 ms, 在所有 Java 提交中击败了80.45%的用户
    内存消耗：
    37.6 MB, 在所有 Java 提交中击败了57.83%的用户
     */
    public List<Integer> grayCode2(int n) {
        List<Integer> list = new ArrayList<>();
        int head = 1;
        list.add(0);
        for (int i = 0; i < n; i++) {
            for(int j = list.size() - 1; i >= 0; --i){
                list.add(head + list.get(j));
            }
            head <<= 1;
        }
        return list;
    }
}

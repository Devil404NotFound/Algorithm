package com.ljp.leecode_cn.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** 每日一题 2021.02.12（春节）
 * @author lijunpeng
 * @date 2021/2/12 19:39
119. 杨辉三角 II
给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。



在杨辉三角中，每个数是它左上方和右上方的数的和。

示例:

输入: 3
输出: [1,3,3,1]
进阶：

你可以优化你的算法到 O(k) 空间复杂度吗？
 */
public class _简单_119_杨辉三角II {
    public static void main(String[] args) {
        _简单_119_杨辉三角II test = new _简单_119_杨辉三角II();
        List<Integer> list = test.getRow(3);
        list.toString();
    }

    /**
     *
     * @param rowIndex
     * @return
    执行用时：
    2 ms, 在所有 Java 提交中击败了35.01%的用户
    内存消耗：
    36.1 MB, 在所有 Java 提交中击败了71.96%的用户
     */
    public List<Integer> getRow(int rowIndex) {
        int[] nums = new int[rowIndex + 1];
        nums[0] = 1;
        for(int i = 1; i <= rowIndex; ++i) {
            int last = 1;//记录上一个元素
            for(int j = 1; j <= i; ++j) {
                int temp = last;
                last = nums[j];
                nums[j] = temp + nums[j];
            }
        }
        return Arrays.stream(nums).boxed().collect(Collectors.toList());
    }

    /**
     * 官方题解一（优化）：递推（从后往前加
     * @param rowIndex
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了83.95%的用户
    内存消耗：
    36.1 MB, 在所有 Java 提交中击败了80.57%的用户
     */
    public List<Integer> getRow2(int rowIndex) {
        List<Integer> row = new ArrayList<Integer>();
        row.add(1);
        for (int i = 1; i <= rowIndex; ++i) {
            row.add(0);
            for (int j = i; j > 0; --j) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
        }
        return row;
    }

    /**
     * 线性复杂度
     通过组合公式可以得出
     * @param rowIndex
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    36.3 MB, 在所有 Java 提交中击败了37.93%的用户
     */
    public List<Integer> getRow3(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        row.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            row.add((int)((long)row.get(i - 1) * (rowIndex - i + 1) / i));
        }
        return row;
    }
}

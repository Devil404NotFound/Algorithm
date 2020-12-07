package com.ljp.leecode_cn.array;

import java.util.ArrayList;
import java.util.List;

/**每日一题 2020.12.06
 * @author ljp
 * @date 2020/12/6 22:26
118. 杨辉三角
给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。



在杨辉三角中，每个数是它左上方和右上方的数的和。

示例:

输入: 5
输出:
[
[1],
[1,1],
[1,2,1],
[1,3,3,1],
[1,4,6,4,1]
]
 */
public class _简单_118_杨辉三角 {
    /**
     *
     * @param numRows
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了59.18%的用户
    内存消耗：
    36.1 MB, 在所有 Java 提交中击败了93.50%的用户
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        if(numRows < 1) {
            return ans;
        }
        List<Integer> preList = new ArrayList<>();
        preList.add(1);
        ans.add(preList);
        for(int i = 1; i < numRows; ++i) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            for(int j = 0; j < i - 1; ++j) {
                list.add(preList.get(j) + preList.get(j + 1));
            }
            list.add(1);
            ans.add(list);
            preList = list;
        }
        return ans;
    }

    /**
     * 官方题解：数学方法（代码优美）
     * @param numRows
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    36.6 MB, 在所有 Java 提交中击败了22.66%的用户
     */
    public List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if(j == 0 || j == i) {
                    row.add(1);
                }else{
                    row.add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
                }
            }
            ans.add(row);
        }
        return ans;
    }
}

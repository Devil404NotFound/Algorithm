package com.ljp.leecode_cn.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 216. 组合总和 III
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 *
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 *
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 *
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class _中等_216_组合总和III {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        solve(new ArrayList<Integer>(), 1, k, n);
        return ans;
    }
    private void solve(List<Integer> list, int i, int k, int n){
        if(n < 0 || k < 0){
            return;
        }
        if(n == 0 && k == 0){
            ans.add(new ArrayList<>(list));
            return;
        }
        if(i <= 9){
            //添加元素i
            list.add(i);
            solve(list, i + 1, k - 1, n - i);
            //不添加元素i
            list.remove(list.size() - 1);
            solve(list, i + 1, k, n);
        }
    }
}

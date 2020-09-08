package com.ljp.leecode_cn.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 *
 * 输入:n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 */
public class _中等_77_组合 {
    /**
     * 执行用时：
     * 23 ms, 在所有 Java 提交中击败了53.96%的用户
     * 内存消耗：
     * 40.9 MB, 在所有 Java 提交中击败了89.38%*的用户
     */
    List<List<Integer>> ans;
    public List<List<Integer>> combine(int n, int k) {
        ans = new ArrayList<>();
        combineCore(new ArrayList<>(), 1, n, k);
        return ans;
    }
    private void combineCore(List<Integer> list, int i, int n, int k){
        if(k == 0){
            List<Integer> newList = new ArrayList<>(list);
            ans.add(newList);
            return;
        }else if(i > n){
            return;
        }
        list.add(i);
        combineCore(list, i + 1, n, k - 1);
        list.remove(list.size() - 1);
        combineCore(list, i + 1, n, k);
    }
}

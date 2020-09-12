package com.ljp.leecode_cn.DFS;

import java.util.*;

/**
 * 40. 组合总和 II
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 */
public class _中等_40_组合总合II {
    public static void main(String[] args) {
        int[] candidates = {10,1,2,7,6,1,5};
        int target = 8;
        new _中等_40_组合总合II().combinationSum2(candidates, target);
    }

    /**
     * 执行用时：
     * 34 ms, 在所有 Java 提交中击败了6.45%的用户
     * 内存消耗：
     * 40.2 MB, 在所有 Java 提交中击败了24.12%*的用户
     */
    class MyListSet extends ArrayList<Integer>{
    public MyListSet() {

    }

    public MyListSet(MyListSet list) {
        super(list);
    }

    @Override
    public int hashCode() {
        int size = this.size();
        int mask = 31;
        int hash = 0;
        for(int i = 0; i < size; i++){
            hash += this.get(i) * mask;
        }
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }
        if(o == null){
            return false;
        }
        if(this.getClass() != o.getClass()){
            return false;
        }
        List<Integer> list = (List<Integer>) o;
        if(this.size() != list.size()){
            return false;
        }
        int size = this.size();
        for(int i = 0; i < size; i++){
            if(this.get(i) != list.get(i)){
                return false;
            }
        }
        return true;
    }
}
    List<List<Integer>> ans = new ArrayList<>();
    Set<MyListSet> hashSet = new HashSet<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        solve(candidates, new MyListSet(), 0, target);
        return ans;
    }
    private void solve(int[] candidates, MyListSet list, int i, int target){
        if(target == 0){
            if(hashSet.add(new MyListSet(list))){
                ans.add(new ArrayList<>(list));
            }
            return;
        }
        if(target < 0 || i >= candidates.length){
            return;
        }
        //选择
        list.add(candidates[i]);
        solve(candidates, list, i + 1, target - candidates[i]);
        //不选择
        list.remove(list.size() - 1);
        solve(candidates, list, i + 1, target);
    }
}

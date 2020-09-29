package com.ljp.leecode_cn.DFS;

import com.ljp.leecode_cn.other.data_structure.TreeNode;
import com.ljp.leecode_cn.other.data_structure_operation.TreeNodeOperation;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 113. 路径总和 II
 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。

 说明: 叶子节点是指没有子节点的节点。

 示例:
 给定如下二叉树，以及目标和 sum = 22，

 5
 / \
 4   8
 /   / \
 11  13  4
 /  \    / \
 7    2  5   1
 返回:

 [
 [5,4,11,2],
 [5,8,4,5]
 ]
 */
public class _中等_113_路径总和II {
    public static void main(String[] args) {
        Integer[] input = {5,4,8,11,null,13,4,7,2,null,null,5,1};
        TreeNode root = TreeNodeOperation.createTreeNode(input);
        List<List<Integer>> result = new _中等_113_路径总和II().pathSum2(root, 22);
    }

    /**
     * 官方题解
     */
    List<List<Integer>> ret = new LinkedList<List<Integer>>();
    Deque<Integer> path = new LinkedList<Integer>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        dfs(root, sum);
        return ret;
    }

    public void dfs(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        path.offerLast(root.val);
        sum -= root.val;
        if (root.left == null && root.right == null && sum == 0) {
            ret.add(new LinkedList<Integer>(path));
        }
        dfs(root.left, sum);
        dfs(root.right, sum);
        path.pollLast();
    }

    /**
     *
     * @param root
     * @param sum
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    39.4 MB, 在所有 Java 提交中击败了27.12%的用户
     */
    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(ans, new ArrayList<Integer>(), root, sum);
        return ans;
    }
    private void dfs(List<List<Integer>> ans, List<Integer> list, TreeNode node ,int sum){
        if(node == null){
            return;
        }
        sum -= node.val;
        list.add(node.val);
        if(node.left == null && node.right == null && sum == 0){
            ans.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            return;
        }
        dfs(ans, list, node.left, sum);
        dfs(ans, list, node.right, sum);
        list.remove(list.size() - 1);
    }
}

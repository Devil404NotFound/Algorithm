package com.ljp.leecode_cn.DFS;

import com.ljp.leecode_cn.other.data_structure.TreeNode;

/**
 404. 左叶子之和
 计算给定二叉树的所有左叶子之和。

 示例：

 3
 / \
 9  20
 /  \
 15   7

 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 */
public class _简单_404_左叶子之和 {
    public int sumOfLeftLeaves(TreeNode root) {
        return solve(root, false);
    }
    private int solve(TreeNode root, boolean flag){
        //空
        if(root == null){
            return 0;
        }
        //是左叶子
        if(root.left == null && root.right == null && flag){
            return root.val;
        }
        //递归调用
        return solve(root.left, true) + solve(root.right, false);
    }
}

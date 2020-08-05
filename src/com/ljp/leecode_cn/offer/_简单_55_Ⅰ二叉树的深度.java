package com.ljp.leecode_cn.offer;

import com.ljp.leecode_cn.other.data_structure.TreeNode;

/**
 * 剑指 Offer 55 - I. 二叉树的深度
 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。

 例如：

 给定二叉树 [3,9,20,null,null,15,7]，

 3
 / \
 9  20
 /  \
 15   7
 返回它的最大深度 3 。



 提示：

 节点总数 <= 10000
 */
public class _简单_55_Ⅰ二叉树的深度 {
    /**
     *
     * @param root
     * @return
     * 执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    40 MB, 在所有 Java 提交中击败了5.12%的用户
     */
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}

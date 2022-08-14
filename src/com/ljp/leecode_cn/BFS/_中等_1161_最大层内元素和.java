package com.ljp.leecode_cn.BFS;

import com.ljp.leecode_cn.other.data_structure.TreeNode;

/**
 * @author lijunpeng
 * @date 2022/7/31 10:21
 * @description

1161. 最大层内元素和
给你一个二叉树的根节点 root。设根节点位于二叉树的第 1 层，而根节点的子节点位于第 2 层，依此类推。

请返回层内元素之和 最大 的那几层（可能只有一层）的层号，并返回其中 最小 的那个。



示例 1：



输入：root = [1,7,0,7,-8,null,null]
输出：2
解释：
第 1 层各元素之和为 1，
第 2 层各元素之和为 7 + 0 = 7，
第 3 层各元素之和为 7 + -8 = -1，
所以我们返回第 2 层的层号，它的层内元素之和最大。
示例 2：

输入：root = [989,null,10250,98693,-89388,null,null,null,-32127]
输出：2


提示：

树中的节点数在 [1, 104]范围内
-105 <= Node.val <= 105
 **/

public class _中等_1161_最大层内元素和 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(0);
        TreeNode right = new TreeNode(0);
        TreeNode leftleft = new TreeNode(7);
        TreeNode leftright = new TreeNode(8);
        root.left = left;
        root.right = right;
        left.left = leftleft;
        left.right = leftright;
        int result = new _中等_1161_最大层内元素和().maxLevelSum(root);
        System.out.println(result);
    }
    public int maxLevelSum(TreeNode root) {
        int layer = 0;
        int[] list = new int[(int)1e4 + 1];
        int maxDepptLayer = dfs(root, layer, list) - 1;
        int maxLayer = 1;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i <= maxDepptLayer; ++i) {
            if(list[i] > max) {
                max = list[i];
                maxLayer = i + 1;
            }
        }
        return maxLayer;
    }
    private int dfs(TreeNode root, int layer, int[] list) {
        if(root == null) {
            return layer;
        }
        list[layer] += root.val;
        return Math.max(dfs(root.left, layer + 1, list), dfs(root.right, layer + 1, list));
    }

}

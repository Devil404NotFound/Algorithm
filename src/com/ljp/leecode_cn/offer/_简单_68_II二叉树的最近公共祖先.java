package com.ljp.leecode_cn.offer;

import com.ljp.leecode_cn.other.data_structure.TreeNode;

/**
 * @author ljp
 * @date 2020/12/18 16:37
剑指 Offer 68 - II. 二叉树的最近公共祖先
给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]





示例 1:

输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出: 3
解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
示例 2:

输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
输出: 5
解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。


说明:

所有节点的值都是唯一的。
p、q 为不同节点且均存在于给定的二叉树中。
注意：本题与主站 236 题相同：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class _简单_68_II二叉树的最近公共祖先 {
    /**
     * 暴力法
     * @param root
     * @param p
     * @param q
     * @return
    执行用时：
    965 ms, 在所有 Java 提交中击败了5.05%的用户
    内存消耗：
    39.9 MB, 在所有 Java 提交中击败了78.30%的用户
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q) {
            return root;
        }
        int left = countTreeNode(root.left, p, q);
        if (left == 1) {
            return root;
        } else if (left == 2) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return lowestCommonAncestor(root.right, p, q);
        }
    }
    private int countTreeNode(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return 0;
        }
        int count = 0;
        if(root == p || root == q) {
            ++count;
        }
        return count + countTreeNode(root.left, p, q) + countTreeNode(root.right, p, q);
    }

    /**
     * 方法二：边统计边更新最近
     执行用时：
     7 ms, 在所有 Java 提交中击败了99.94%的用户
     内存消耗：
     39.7 MB, 在所有 Java 提交中击败了86.76%的用户
     */
    TreeNode ans = null;
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        countTreeNode2(root, p, q);
        return ans;
    }
    private int countTreeNode2(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return 0;
        }
        int count = 0;
        if(root == p || root == q) {
            ++count;
        }
        int left = countTreeNode2(root.left, p, q);
        int right = countTreeNode2(root.right, p, q);
        //当root为p或者q并且左子树或者右子树有另外一个节点时，root为最近公共祖先
        if((left == 1 || right == 1) && (root == p || root == q)) {
            ans = root;
        }
        //当左子树和右子树各有一个节点时，root为最近公共祖先
        if(left == 1 && right == 1) {
            ans = root;
        }
        return count + left + right;
    }
}

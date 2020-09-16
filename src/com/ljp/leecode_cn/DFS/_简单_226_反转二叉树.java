package com.ljp.leecode_cn.DFS;

import com.ljp.leecode_cn.other.data_structure.TreeNode;

/**
 226. 翻转二叉树
 翻转一棵二叉树。

 示例：

 输入：

 4
 /   \
 2     7
 / \   / \
 1   3 6   9
 输出：

 4
 /   \
 7     2
 / \   / \
 9   6 3   1
 备注:
 这个问题是受到 Max Howell 的 原问题 启发的 ：
 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
 */
public class _简单_226_反转二叉树 {
    /**
     *
     * @param root
     * @return
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：36.4 MB, 在所有 Java 提交中击败了65.32%的用户
     */
    public TreeNode invertTree(TreeNode root) {
        if(root != null){
            TreeNode left = root.left;
            TreeNode right = root.right;
            invertTree(root.left);
            invertTree(root.right);
            root.left = right;
            root.right = left;
        }
        return root;
    }
}

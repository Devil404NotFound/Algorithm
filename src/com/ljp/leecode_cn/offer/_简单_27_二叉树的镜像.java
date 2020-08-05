package com.ljp.leecode_cn.offer;

import com.ljp.leecode_cn.other.data_structure.TreeNode;

/**
 * 剑指 Offer 27. 二叉树的镜像
 请完成一个函数，输入一个二叉树，该函数输出它的镜像。

 例如输入：

 4
 /   \
 2     7
 / \   / \
 1   3 6   9
 镜像输出：

 4
 /   \
 7     2
 / \   / \
 9   6 3   1



 示例 1：

 输入：root = [4,2,7,1,3,6,9]
 输出：[4,7,2,9,6,3,1]
 */
public class _简单_27_二叉树的镜像 {
    /**
     *
     * @param root
     * @return
     * 执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    37.5 MB, 在所有 Java 提交中击败了12.95%的用户
     */
    public TreeNode mirrorTree(TreeNode root) {
        if(root == null){
            return null;
        }
        //交换
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
        //递归调用
        mirrorTree(root.right);
        mirrorTree(root.left);
        return root;
    }

}

package com.ljp.leecode_cn.DFS;

import com.ljp.leecode_cn.other.data_structure.TreeNode;

/**
 * 110. 平衡二叉树
 给定一个二叉树，判断它是否是高度平衡的二叉树。

 本题中，一棵高度平衡二叉树定义为：

 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。

 示例 1:

 给定二叉树 [3,9,20,null,null,15,7]

        3
      / \
    9  20
   /  \
 15   7
 返回 true 。

 示例 2:

 给定二叉树 [1,2,2,3,3,null,null,4,4]

           1
         / \
        2   2
      / \
     3   3
   / \
 4   4
 返回 false 。


 */
public class _简单_110_平衡二叉树 {
    /**
     *
     * @param root
     * @return
     * 执行用时：
    1 ms, 在所有 Java 提交中击败了99.76%的用户
    内存消耗：
    39.7 MB, 在所有 Java 提交中击败了81.92%的用户
     */
    public boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }
        return isBalancedCore(root) != -1;
    }
    private int isBalancedCore(TreeNode node){
        if(node == null){
            return 0;
        }
        int left = isBalancedCore(node.left);
        int right = isBalancedCore(node.right);
        if(left == -1 || right == -1 || Math.abs(left - right) > 1){
            return -1;
        }else{
            return Math.max(left, right) + 1;//+1写在这里才不会出错（不能写在left、right后面）
        }
    }
}

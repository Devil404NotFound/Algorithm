package com.ljp.leecode_cn.DFS;

import com.ljp.leecode_cn.other.data_structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 111. 二叉树的最小深度
 给定一个二叉树，找出其最小深度。

 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

 说明: 叶子节点是指没有子节点的节点。

 示例:

 给定二叉树 [3,9,20,null,null,15,7],

 3
 / \
 9  20
 /  \
 15   7
 返回它的最小深度  2.
 */
public class _简单_111_二叉树的最小深度 {
    /**
     * 需要注意，是到叶节点的最小距离（叶节点是没有子树了）
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        //左子树没有就不统计
        if(root.left == null && root.right != null){
            return 1 + minDepth(root.right);
        }
        if(root.left != null && root.right == null){
            return 1 + minDepth(root.left);
        }
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }

    /**
     * 广度优先遍历
     * @param root
     * @return
     * 执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    39.8 MB, 在所有 Java 提交中击败了44.55%的用户
     */
    public int minDepth2(TreeNode root) {
        if(root == null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int size = queue.size();
        int depth = 0;
        TreeNode node;
        while(!queue.isEmpty()){
            depth++;
            for(int i = 0 ; i< size; i++){
                node = queue.poll();
                if(node.left == null && node.right == null){
                    return depth;
                }
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            size = queue.size();
        }
        return depth;
    }
}

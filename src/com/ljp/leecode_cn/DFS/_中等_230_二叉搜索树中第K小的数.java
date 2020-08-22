package com.ljp.leecode_cn.DFS;

import com.ljp.leecode_cn.other.data_structure.TreeNode;

/**
 * 230. 二叉搜索树中第K小的元素
 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。

 说明：
 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。

 示例 1:

 输入: root = [3,1,4,null,2], k = 1
 3
 / \
 1   4
 \
 2
 输出: 1
 示例 2:

 输入: root = [5,3,6,2,4,null,null,1], k = 3
 5
 / \
 3   6
 / \
 2   4
 /
 1
 输出: 3
 进阶：
 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
 */
public class _中等_230_二叉搜索树中第K小的数 {
    /**
     * 执行用时：
     0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：
     39.7 MB, 在所有 Java 提交中击败了60.57%的用户
     */
    int res = 0;
    public int kthSmallest(TreeNode root, int k) {
        count(root, k);
        return res;
    }
    //统计节点个数
    private int count(TreeNode node, int k){
        if(node == null){
            return 0;
        }
        //计算当前节点是第几小的数
        int left = count(node.left, k) + 1;
        if(left == k){
            res = node.val;
        }
        //计算右节点
        int right = count(node.right,k - left);
        return left + right;
    }
}

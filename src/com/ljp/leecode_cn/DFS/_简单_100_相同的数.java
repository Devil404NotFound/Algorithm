package com.ljp.leecode_cn.DFS;

import com.ljp.leecode_cn.other.data_structure.TreeNode;

/**
 * 100. 相同的树
 给定两个二叉树，编写一个函数来检验它们是否相同。

 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。

 示例 1:

 输入:
   1         1
 / \       / \
 2   3     2   3

 [1,2,3],   [1,2,3]

 输出: true
 示例 2:

 输入:
  1          1
 /           \
 2             2

 [1,2],     [1,null,2]

 输出: false
 示例 3:

 输入:
   1         1
 / \       / \
 2   1     1   2

 [1,2,1],   [1,1,2]

 输出: false
 */
public class _简单_100_相同的数 {
    /**
     * 执行用时：
     0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：
     36.9 MB, 在所有 Java 提交中击败了90.48%的用户
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null || q == null){
            return p == q;
        }
        if(p.val != q.val){
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}

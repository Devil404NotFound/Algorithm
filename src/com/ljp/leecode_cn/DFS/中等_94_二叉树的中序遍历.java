package com.ljp.leecode_cn.DFS;

import com.ljp.leecode_cn.other.data_structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 94. 二叉树的中序遍历
 给定一个二叉树，返回它的中序 遍历。

 示例:

 输入: [1,null,2,3]
 1
 \
 2
 /
 3

 输出: [1,3,2]
 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class 中等_94_二叉树的中序遍历 {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderTraversalCore(list, root);
        return list;
    }
    private void inorderTraversalCore(List<Integer> list, TreeNode root){
        if(root != null){
            inorderTraversalCore(list, root.left);
            list.add(root.val);
            inorderTraversalCore(list, root.right);
        }
    }

    /**
     执行用时：
     0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：
     38.1 MB, 在所有 Java 提交中击败了31.16%的用户
     */
    List<Integer> list = new ArrayList<>();
    public List<Integer> inorderTraversal2(TreeNode root) {
        if(root != null){
            inorderTraversal(root.left);
            list.add(root.val);
            inorderTraversal(root.right);
        }
        return list;
    }
}

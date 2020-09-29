package com.ljp.leecode_cn.DFS;

import com.ljp.leecode_cn.other.data_structure.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 每日一题2020.09.29
 145. 二叉树的后序遍历
 给定一个二叉树，返回它的 后序 遍历。

 示例:

 输入: [1,null,2,3]
 1
 \
 2
 /
 3

 输出: [3,2,1]
 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class _中等_145_二叉树的后序遍历 {

    /**
     * 递归方法
     * @param root
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    37.1 MB, 在所有 Java 提交中击败了43.79%的用户
     */
    List<Integer> ans = new ArrayList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        if(root == null){
            return ans;
        }
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        ans.add(root.val);
        return ans;
    }

    /**
     * 官方迭代方法
     * @param root
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了58.06%的用户
    内存消耗：
    37.2 MB, 在所有 Java 提交中击败了17.89%的用户
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();

        TreeNode pre = null;
        while(root != null || !stack.isEmpty()){
            while(root!= null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(root.right == null || root.right == pre){
                res.add(root.val);
                pre = root;
                root = null;
            }else{
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }
}

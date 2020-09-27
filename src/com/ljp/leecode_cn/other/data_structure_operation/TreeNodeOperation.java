package com.ljp.leecode_cn.other.data_structure_operation;

import com.ljp.leecode_cn.other.data_structure.TreeNode;
import sun.reflect.generics.tree.Tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 对LeetCode中TreeNode数据结构的操作
 */
public class TreeNodeOperation {
    public static TreeNode createTreeNode(Integer[] input){
        if(input.length == 0){
            return null;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        TreeNode root = new TreeNode(input[0]);
        TreeNode node, left, right;
        int i = 1;
        deque.offer(root);
        while(!deque.isEmpty() && i < input.length){
            node = deque.poll();
            if(input[i] != null){
                left = new TreeNode(input[i]);
                node.left = left;
                deque.offer(left);
            }
            i++;
            if(i < input.length && input[i] != null){
                right = new TreeNode(input[i]);
                node.right = right;
                deque.offer(right);
            }
            i++;
        }
        return root;
    }
}

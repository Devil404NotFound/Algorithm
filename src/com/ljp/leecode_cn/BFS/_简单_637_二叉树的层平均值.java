package com.ljp.leecode_cn.BFS;

import com.ljp.leecode_cn.other.data_structure.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 637. 二叉树的层平均值
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
 *
 *
 *
 * 示例 1：
 *
 * 输入：
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 输出：[3, 14.5, 11]
 * 解释：
 * 第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
 *
 *
 * 提示：
 *
 * 节点值的范围在32位有符号整数范围内。
 */
public class _简单_637_二叉树的层平均值 {
    public List<Double> averageOfLevels(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        List<Double> ans = new ArrayList<>();
        if(root != null){
            deque.offer(root);
        }
        int size;
        double average;
        TreeNode node;
        while(!deque.isEmpty()){
            size = deque.size();
            average = 0;
            for(int i = 0; i < size; i++){
                node = deque.poll();
                average += node.val;
                if(node.left != null){
                    deque.offer(node.left);
                }
                if(node.right != null){
                    deque.offer(node.right);
                }
            }
            average /= size;
            ans.add(average);
        }
        return ans;
    }
}

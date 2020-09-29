package com.ljp.leecode_cn.other.data_structure_operation;


import com.ljp.leecode_cn.other.data_structure.Node;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 对LeetCode中Node数据结构的操作
 */
public class NodeOperation {
    public static Node createTreeNode(Integer[] input){
        if(input.length == 0){
            return null;
        }
        Deque<Node> deque = new LinkedList<>();
        Node root = new Node(input[0]);
        Node node, left, right;
        int i = 1;
        deque.offer(root);
        while(!deque.isEmpty() && i < input.length){
            node = deque.poll();
            if(input[i] != null){
                left = new Node(input[i]);
                node.left = left;
                deque.offer(left);
            }
            i++;
            if(i < input.length && input[i] != null){
                right = new Node(input[i]);
                node.right = right;
                deque.offer(right);
            }
            i++;
        }
        return root;
    }
}

package com.ljp.leecode_cn.tree;

import com.ljp.leecode_cn.other.data_structure.TreeNode;
import com.ljp.leecode_cn.other.data_structure_operation.TreeNodeOperation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/** 每日一题 2022.05.12
 * @author lijunpeng
 * @date 2022/5/12 23:05
 * @description
 **/

public class _中等_449_序列化和反序列化二叉搜索数 {
    public static void main(String[] args) {
        Codec1 codec = new Codec1();
        Integer[] input = {3,2,4,1};
        TreeNode root = TreeNodeOperation.createTreeNode(input);
        String str = codec.serialize(root);
        TreeNode node = codec.deserialize(str);
        System.out.println(str);
        System.out.println(node.toString());
    }
}
/**
* @Author lijunpeng
* @Date 2022/5/12 23:34
* @Description 官方题解：后序遍历
*/
class Codec {
    public String serialize(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        postOrder(root, list);
        String str = list.toString();
        return str.substring(1, str.length() - 1);
    }

    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] arr = data.split(", ");
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            stack.push(Integer.parseInt(arr[i]));
        }
        return construct(Integer.MIN_VALUE, Integer.MAX_VALUE, stack);
    }

    private void postOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postOrder(root.left, list);
        postOrder(root.right, list);
        list.add(root.val);
    }

    private TreeNode construct(int lower, int upper, Deque<Integer> stack) {
        if (stack.isEmpty() || stack.peek() < lower || stack.peek() > upper) {
            return null;
        }
        int val = stack.pop();
        TreeNode root = new TreeNode(val);
        root.right = construct(val, upper, stack);
        root.left = construct(lower, val, stack);
        return root;
    }
}
/**
* @Author lijunpeng
* @Date 2022/5/13 0:07
* @Description 报错
*/
class Codec1 {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder preSb = new StringBuilder();
        StringBuilder midSb = new StringBuilder();
        preOrder(preSb, root);
        midOrder(midSb, root);
        return preSb.append("#").append(midSb).toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strs = data.split("#");
        return buildTree(strs[0], strs[1]);
    }
    private void preOrder(StringBuilder sb, TreeNode node) {
        if(node != null) {
            sb.append(node.val);
            preOrder(sb, node.left);
            preOrder(sb, node.right);
        }
    }
    private void midOrder(StringBuilder sb, TreeNode node) {
        if(node != null) {
            midOrder(sb, node.left);
            sb.append(node.val);
            midOrder(sb, node.right);
        }
    }
    private TreeNode buildTree(String preStr, String midStr) {
        if(preStr == null || midStr == null || preStr.isEmpty() || midStr.isEmpty()) {
            return null;
        }
        char cur = preStr.charAt(0);
        TreeNode root = new TreeNode(cur - 'a');
        int index = getMidIndex(midStr, cur);
        String leftPreStr = preStr.substring(1, index + 1);
        String leftMidStr = midStr.substring(0, index);
        root.left = buildTree(leftPreStr, leftMidStr);
        String rightPreStr = preStr.substring(index + 1);
        String rightMidStr = midStr.substring(index + 1);
        root.right = buildTree(rightPreStr, rightMidStr);
        return root;
    }
    private int getMidIndex(String str, char val) {
        int count = 0;
        for(char ch : str.toCharArray()) {
            if(ch == val) {
                return count;
            }
            count++;
        }
        return count;
    }
}
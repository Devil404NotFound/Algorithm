package com.ljp.leecode_cn.util;

import com.ljp.leecode_cn.other.data_structure.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author lijunpeng
 * @date 2022/5/23 22:04
 * @description
 **/

public class CommonUtil {
    public static List<Integer> ArrayToList(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int num : array) {
            list.add(num);
        }
        return list;
    }
    public static String printArray(Object[] objects) {
        StringBuilder sb = new StringBuilder("[");
        for (Object object : objects) {
            sb.append(object);
            sb.append(",");
        }
        sb.setLength(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    public static TreeNode array2TreeNode(Integer[] arr) {
        if(arr == null || arr.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(arr[0]);
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        for (int i = 1; i < arr.length; i += 2) {
            TreeNode node = deque.poll();
            if(arr[i] != null) {
                node.left = new TreeNode(arr[i]);
                deque.offer(node.left);
            }
            if(i + 1 < arr.length && arr[i + 1] != null) {
                node.right = new TreeNode(arr[i + 1]);
                deque.offer(node.right);
            }
        }
        return root;
    }
}

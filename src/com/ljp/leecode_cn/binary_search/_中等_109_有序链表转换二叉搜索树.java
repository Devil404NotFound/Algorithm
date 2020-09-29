package com.ljp.leecode_cn.binary_search;

import com.ljp.leecode_cn.other.data_structure.ListNode;
import com.ljp.leecode_cn.other.data_structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 109. 有序链表转换二叉搜索树
 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。

 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

 示例:

 给定的有序链表： [-10, -3, 0, 5, 9],

 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：

 0
 / \
 -3   9
 /   /
 -10  5
 */
public class _中等_109_有序链表转换二叉搜索树 {
    public static void main(String[] args) {
        int[] arr = {-10,-3,0,5,9};
        ListNode head = new ListNode(arr[0]).createListNode(arr);
        TreeNode res = new _中等_109_有序链表转换二叉搜索树().sortedListToBST(head);
        System.out.println(res);
    }

    /**
     *
     * @param head
     * @return
     * 执行用时：
    2 ms, 在所有 Java 提交中击败了21.77%的用户
    内存消耗：
    41 MB, 在所有 Java 提交中击败了57.39%的用户
     */
    public TreeNode sortedListToBST(ListNode head) {
        List<TreeNode> list = new ArrayList<>();
        ListNode node = head;
        while(node != null){
            list.add(new TreeNode(node.val));
            node = node.next;
        }
        return sortedListToBSTCore(list, 0, list.size() - 1);
    }
    private TreeNode sortedListToBSTCore(List<TreeNode> list, int left, int right){
        if(left > right){
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode node = list.get(mid);
        node.left = sortedListToBSTCore(list, left, mid - 1);
        node.right = sortedListToBSTCore(list, mid + 1, right);
        return node;
    }
}

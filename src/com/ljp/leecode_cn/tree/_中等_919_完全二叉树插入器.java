package com.ljp.leecode_cn.tree;

import com.ljp.leecode_cn.other.data_structure.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/** 2022.07.25每日一题
 * @author lijunpeng
 * @date 2022/7/25 23:13
 * @description
919. 完全二叉树插入器
完全二叉树 是每一层（除最后一层外）都是完全填充（即，节点数达到最大）的，并且所有的节点都尽可能地集中在左侧。

设计一种算法，将一个新节点插入到一个完整的二叉树中，并在插入后保持其完整。

实现 CBTInserter 类:

CBTInserter(TreeNode root) 使用头节点为 root 的给定树初始化该数据结构；
CBTInserter.insert(int v)  向树中插入一个值为 Node.val == val的新节点 TreeNode。使树保持完全二叉树的状态，并返回插入节点 TreeNode 的父节点的值；
CBTInserter.get_root() 将返回树的头节点。

示例 1：
输入
["CBTInserter", "insert", "insert", "get_root"]
[[[1, 2]], [3], [4], []]
输出
[null, 1, 2, [1, 2, 3, 4]]

解释
CBTInserter cBTInserter = new CBTInserter([1, 2]);
cBTInserter.insert(3);  // 返回 1
cBTInserter.insert(4);  // 返回 2
cBTInserter.get_root(); // 返回 [1, 2, 3, 4]

提示：
树中节点数量范围为 [1, 1000]
0 <= Node.val <= 5000
root 是完全二叉树
0 <= val <= 5000
每个测试用例最多调用 insert 和 get_root 操作 104 次
 **/

public class _中等_919_完全二叉树插入器 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        CBTInserter cbtInserter = new CBTInserter(root);
        System.out.println(cbtInserter.insert(4));
        System.out.println(cbtInserter.insert(5));
        System.out.println(cbtInserter.insert(6));
        System.out.println(cbtInserter.get_root());
    }

}
class CBTInserter {
    TreeNode root;
    Deque<TreeNode> deque;

    public CBTInserter(TreeNode root) {
        this.root = root;
        this.deque = new ArrayDeque<>();
        TreeNode node = root;
        deque.offer(root);
        while(node.left != null && node.right != null) {
            deque.poll();
            deque.offer(node.left);
            deque.offer(node.right);
            node = deque.peek();
        }
        if(node.left != null) {
            deque.offer(node.left);
        }
    }

    public int insert(int val) {
        TreeNode node = this.deque.peek();
        TreeNode nextNode = new TreeNode(val);
        if(node.left == null) {
            node.left = nextNode;
        }else if(node.right == null){
            node.right = nextNode;
            this.deque.poll();
        }
        this.deque.offer(nextNode);
        return node.val;
    }

    public TreeNode get_root() {
        return this.root;
    }
}
package com.ljp.leecode_cn.BFS;

import com.ljp.leecode_cn.other.data_structure.Node;
import com.ljp.leecode_cn.other.data_structure_operation.NodeOperation;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 每日一题（2020.09.28）
 * 117. 填充每个节点的下一个右侧节点指针 II
 * 给定一个二叉树
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 *
 *
 * 进阶：
 *
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *
 *
 * 示例：
 *
 *
 *
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 *
 *
 * 提示：
 *
 * 树中的节点数小于 6000
 * -100 <= node.val <= 100
 */
public class _中等_117_填充每个节点的下一个右侧节点指针II {
    public static void main(String[] args) {
        Integer[] input = {1,2,3,4,5,null,6,7,null,null,null,null,8};
        Node root = NodeOperation.createTreeNode(input);
        Node res = new _中等_117_填充每个节点的下一个右侧节点指针II().connect3(root);
    }
    /**
     *方法一：借助额外空间——队列，层遍历
     * @param root
     * @return
    执行用时：
    2 ms, 在所有 Java 提交中击败了45.73%的用户
    内存消耗：
    38.6 MB, 在所有 Java 提交中击败了45.50%的用户
     */
    public Node connect(Node root) {
        if(root == null){
            return root;
        }
        Deque<Node> deque = new LinkedList<>();
        deque.offer(root);
        while(!deque.isEmpty()){
            int size = deque.size();
            Node pre = null, node = null;
            for(int i = size - 1; i >= 0; i--){
                node = deque.poll();
                if(node.right != null){
                    deque.offer(node.right);
                }
                if(node.left != null){
                    deque.offer(node.left);
                }
                node.next = pre;
                pre = node;
            }
        }
        return root;
    }

    /**
     * 递归，常数空间复杂度【错误】
     * @param root
     * @return
    解答错误：
    输入：[1,2,3,4,5,null,6,7,null,null,null,null,8]
     输出：[1,#,2,3,#,4,5,6,#,7,#]
     */
    public Node connect2(Node root) {
        dfs(root, null);
        return root;
    }
    private void dfs(Node node, Node next){
        if(node == null){
            return;
        }
        //next为空
        if(next == null){
            dfs(node.left, node.right);//左节点的下一个节点是右节点
            dfs(node.right, null);//右节点的下一个节点为null
        }else{//next不为空
            node.next = next;//该节点的next指向next
            if(node.right != null){//右节点不为空
                dfs(node.left, node.right);
                if(next.left != null){
                    dfs(node.right, next.left);
                }else{
                    dfs(node.right, next.right);
                }
            }else{
                if(next.left != null){
                    dfs(node.left, next.left);
                }else{
                    dfs(node.left, next.right);
                }
            }
        }
    }

    /**
     * 官方题解二，利用已经建立好的next来建立下一层的next
     * @param root
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    38.5 MB, 在所有 Java 提交中击败了57.60%的用户
     */
    Node pre, nextStart;
    public Node connect3(Node root) {
        if(root == null){
            return null;
        }
        Node start;//当前层的开始节点
        start = root;
        while(start != null){
            nextStart = null;//下一层的开始节点
            pre = null;
            for(Node p = start; p != null; p = p.next){//循环遍历这一层
                if(p.left != null){
                    handle(p.left);
                }
                if(p.right != null){
                    handle(p.right);
                }
            }
            start = nextStart;
        }
        return root;
    }
    /*
    处理next节点（前一个节点的next指向p，如果还没有开始节点， 就将开始节点nextStart指向p
     */
    private void handle(Node p){
        if(pre != null){
            pre.next = p;
        }
        if(nextStart == null){
            nextStart = p;
        }
        pre = p;
    }
}

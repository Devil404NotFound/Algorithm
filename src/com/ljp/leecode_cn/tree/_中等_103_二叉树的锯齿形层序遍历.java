package com.ljp.leecode_cn.tree;

import com.ljp.leecode_cn.other.data_structure.TreeNode;

import java.util.*;

/** 每日一题 2020.12.22
 * @author ljp
 * @date 2020/12/22 19:05
103. 二叉树的锯齿形层序遍历
给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

例如：
给定二叉树 [3,9,20,null,null,15,7],

3
/ \
9  20
/  \
15   7
返回锯齿形层序遍历如下：

[
[3],
[20,9],
[15,7]
]
 */
public class _中等_103_二叉树的锯齿形层序遍历 {
    /**
     *
     * @param root
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了98.42%的用户
    内存消耗：
    38.6 MB, 在所有 Java 提交中击败了56.30%的用户
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>(); //返回结果集
        if(root == null) {
            return ans;
        }
        boolean flag = true; //判断从左往右(true)还是从右往左(false)
        Deque<TreeNode> deque = new LinkedList<>();//实现层序遍历的队列
        deque.push(root);
        Deque<Integer> link = new LinkedList<>(); //临时存储遍历结果的队列/栈
        while(!deque.isEmpty()) {
            int size = deque.size();
            for(int i = 0; i < size; ++i) {
                TreeNode p = deque.poll();
                if(p.left != null) {
                    deque.offer(p.left);
                }
                if(p.right != null) {
                    deque.offer(p.right);
                }
                link.offer(p.val);
            }
            List<Integer> list = new ArrayList<>(size);
            if(flag) {//从左往右的情况，直接顺序遍历
                for(int i = 0; i < size; ++i) {
                    list.add(link.poll());
                }
            }else{//右往左的情况，反序遍历
                for(int i = 0; i < size; ++i) {
                    list.add(link.pollLast());
                }
            }
            ans.add(list);
            flag = !flag;
        }
        return ans;
    }

    /**
     * 官方题解一：广度优先遍历（比我的思路优美）
     * @param root
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了98.42%的用户
    内存消耗：
    38.6 MB, 在所有 Java 提交中击败了51.63%的用户
     */
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
       List<List<Integer>> ans = new LinkedList<>();
       if(root == null) {
           return ans;
       }
       Queue<TreeNode> nodeDeque = new LinkedList<>(); //广度遍历的队列
       nodeDeque.offer(root);
       boolean isOrderLeft = true; //判断从左往右（true）还是从右往左（false）
       while(!nodeDeque.isEmpty()) {
           Deque<Integer> levelList = new LinkedList<>();//如果这里用LinkedList，下面添加时就不用新建一个list
           int size = nodeDeque.size();
           for (int i = 0; i < size; i++) {
               TreeNode curNode = nodeDeque.poll();
                if(isOrderLeft) {//如果是左往右，就添加在deque的尾部
                    levelList.offerLast(curNode.val);
                }else{//如果是从右往左，就添加在deque的头部
                    levelList.offerFirst(curNode.val);
                }
                //添加左右子树
                if(curNode.left != null) {
                    nodeDeque.offer(curNode.left);
                }
                if(curNode.right != null) {
                    nodeDeque.offer(curNode.right);
                }
           }
           //根据levelList新建一个list添加到返回结果集中
           ans.add(new LinkedList<Integer>(levelList));
           isOrderLeft = !isOrderLeft;
       }
       return ans;
    }
}

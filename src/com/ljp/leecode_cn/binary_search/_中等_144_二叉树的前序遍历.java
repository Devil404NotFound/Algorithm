package com.ljp.leecode_cn.binary_search;

import com.ljp.leecode_cn.other.data_structure.TreeNode;
import com.ljp.leecode_cn.other.data_structure_operation.TreeNodeOperation;

import java.util.*;

/** 每日一题 2020.10.27
 144. 二叉树的前序遍历
 给定一个二叉树，返回它的 前序 遍历。

 示例:

 输入: [1,null,2,3]
 1
 \
 2
 /
 3

 输出: [1,2,3]
 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * @author ljp
 * @date 2020/10/27 0:12
 */
public class _中等_144_二叉树的前序遍历 {
    public static void main(String[] args) {
        Integer[] input = {1,2,3,4,5,6,7};
        TreeNode root = TreeNodeOperation.createTreeNode(input);
        List<Integer> ans = new _中等_144_二叉树的前序遍历().preorderTraversal4(root);
        System.out.println(ans.toString());
    }

    /** 递归
     执行用时：
     0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：
     36.8 MB, 在所有 Java 提交中击败了86.66%的用户
     */
    List<Integer> ans = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root != null){
            ans.add(root.val);
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
        return ans;
    }

    /**【可优化】
     * 迭代算法   利用栈和hashSet
     * @param root
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了46.26%的用户
    内存消耗：
    36.8 MB, 在所有 Java 提交中击败了87.71%的用户
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        Set<TreeNode> set = new HashSet<>();
        List<Integer> ans = new ArrayList<>();
        if(root == null){
            return ans;
        }
        stack.push(root);
        set.add(root);
        ans.add(root.val);
        TreeNode p;
        while(!stack.isEmpty()){
            p = stack.peek();
            while(p.left != null && !set.contains(p.left)){
                stack.push(p.left);
                set.add(p.left);
                ans.add(p.left.val);
                p = p.left;
            }
            p = stack.peek();
            if(p.right != null && !set.contains(p.right)){
                stack.push(p.right);
                set.add(p.right);
                ans.add(p.right.val);
            }else{
                stack.poll();
            }
        }
        return ans;
    }

    /**
     * 官方题解二：迭代
     * @param root
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    36.3 MB, 在所有 Java 提交中击败了99.24%的用户
     */
    public List<Integer> preorderTraversal3(TreeNode root){
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while(node != null || !stack.isEmpty()){
            while(node != null){
                stack.push(node);
                ans.add(node.val);
                node = node.left;
            }
            node = stack.poll().right;
        }
        return ans;
    }

    /**
     * Morris算法====用巧妙的方法在线性时间内只占用常数空间来实现前序遍历
     * Morris的核心思想是利用树的大量空闲指针，来实现实现空间开销的极限缩减
     * 前序遍历的规则如下
     * 1. 新建节点p指向该根节点
     * 2. 如果节点p的左子树为空，就将当前节点加入答案，并遍历右子树
     * 3. 如果节点p的左子树不为空，就需要找到中序遍历中节点p的前驱节点（即为左子树的最右子树节点）：
     *      * 如果前驱节点的右节点为空，就将前驱节点设置为当前节点（节点p）
     *      * 如果前驱节点的右节点不为空（意味着右节点等于节点p），就将右节点置为空（恢复树结构），再遍历节点p的右节点
     * @param root
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    36.4 MB, 在所有 Java 提交中击败了98.67%的用户
     */
    public List<Integer> preorderTraversal4(TreeNode root){
        List<Integer> ans = new ArrayList<>();
        TreeNode p1, p2;
        p1 = root;
        while(p1 != null){
            p2 = p1.left;
            if(p2 != null){
                while(p2.right != null && p2.right != p1){
                    p2 = p2.right;
                }
                if(p2.right == null){
                    ans.add(p1.val);
                    p2.right = p1;
                    p1 = p1.left;
                    continue;
                }else{
                    p2.right = null;
                }
            }else{
                ans.add(p1.val);
            }
            p1 = p1.right;
        }
        return ans;
    }

}

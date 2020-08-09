package com.ljp.leecode_cn.DFS;

import com.ljp.leecode_cn.other.data_structure.ListNode;
import com.ljp.leecode_cn.other.data_structure.TreeNode;

import java.util.*;

/*8
99. 恢复二叉搜索树
二叉搜索树中的两个节点被错误地交换。

请在不改变其结构的情况下，恢复这棵树。

示例 1:

输入: [1,3,null,null,2]

   1
  /
 3
  \
   2

输出: [3,1,null,null,2]

   3
  /
 1
  \
   2
示例 2:

输入: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

输出: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3
进阶:

使用 O(n) 空间复杂度的解法很容易实现。
你能想出一个只使用常数空间的解决方案吗？
 */
public class _困难_99_恢复二叉树 {

    /**
     *
     * @param root
     * 执行用时：
    5 ms, 在所有 Java 提交中击败了10.85%的用户
    内存消耗：
    40.1 MB, 在所有 Java 提交中击败了57.82%的用户
     */
    public void recoverTree(TreeNode root){
        if(root == null){
            return;
        }
        //队列存树节点值（按照val的大小排序）
        PriorityQueue<Integer> priority = new PriorityQueue<>();
        //添加节点值到优先队列
        dfs(root, priority);
        //中序优先遍历，改成收搜索二叉树
        inorder(root, priority);
    }
    //添加节点值到队列
    private void dfs(TreeNode node, PriorityQueue<Integer> priority){
        if(node == null){
            return;
        }
        priority.offer(node.val);
        dfs(node.left, priority);
        dfs(node.right, priority);
    }
    //中序优先遍历
    private void inorder(TreeNode node, PriorityQueue<Integer> priority){
        if(node == null){
            return;
        }
        inorder(node.left, priority);
        node.val = priority.poll();
        inorder(node.right, priority);
    }


    /**
     * 题解方法一：显式中序遍历
     * @param root
     * 执行用时：
    3 ms, 在所有 Java 提交中击败了53.50%的用户
    内存消耗：
    40.3 MB, 在所有 Java 提交中击败了12.68%的用户
     */
    public void recoverTree1(TreeNode root){
        List<Integer> nums = new ArrayList<>();
        inorder1(root, nums);
        int[] swapped = findTwoSwapped(nums);
        recover(root, 2, swapped[0], swapped[1]);
    }
    //中序遍历，添加到nums中
    private void inorder1(TreeNode node, List<Integer> nums){
        if(node == null){
            return;
        }
        inorder1(node.left, nums);
        nums.add(node.val);
        inorder1(node.right, nums);
    }
    //找到错误的两个节点值
    private int[] findTwoSwapped(List<Integer> nums){
        int x = -1, y = -1;
        int size = nums.size();
        for (int i = 0; i < size - 1; i++) {
            if(nums.get(i) > nums.get(i + 1)){
                y = nums.get(i + 1);
                if(x == -1){
                    x = nums.get(i);
                }
            }
        }
        return new int[]{x, y};
    }
    //交换错误的两个节点值
    private void recover(TreeNode node, int count, int x, int y){
        if(node != null){
            if(node.val == x || node.val == y){
                node.val = (node.val == x) ? y : x;
                if(--count == 0){
                    return;
                }
            }
            recover(node.left, count, x, y);
            recover(node.right, count, x, y);
        }
    }

    /**
     * 官方题解二：隐式中序遍历（使用栈）
     * @param root
     * 执行用时：
    3 ms, 在所有 Java 提交中击败了53.50%的用户
    内存消耗：
    40.2 MB, 在所有 Java 提交中击败了23.60%的用户
     */
    public void recoverTree2(TreeNode root){
        if(root == null){
            return;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode pre = null;
        TreeNode x = null, y = null;
        while(!stack.isEmpty() || root != null){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(pre != null && pre.val > root.val){
                y = root;
                if(x == null){
                    x = pre;
                }else{
                    break;
                }
            }
            pre = root;
            root = root.right;
        }
        swap(x, y);
    }
    private void swap(TreeNode x, TreeNode y){
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }

    /**
     * 官方题解，方法三：Morris中序遍历
     * @param root
     * 执行用时：
    2 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    40.1 MB, 在所有 Java 提交中击败了43.36%的用户
     */
    public void recoverTree3(TreeNode root){
        if(root == null){
            return;
        }
        TreeNode x = null, y = null, pre = null, predecessor = null;
        while(root != null){
            if(root.left != null){
                predecessor = root.left;
                while(predecessor.right != null && predecessor.right != root){
                    predecessor = predecessor.right;
                }
                if(predecessor.right == null){//当找到左子树的最右子树（root的前驱节点）
                    predecessor.right = root;
                    root = root.left;
                }else{//左子树遍历完成，断开root的前置节点与root的链接
                    if(pre != null && pre.val > root.val){
                        y = root;
                        if(x == null){
                            x = pre;
                        }
                    }
                    pre = root;
                    predecessor.right = null;
                    root = root.right;
                }
            }else{//开始遍历右子树
                if(pre != null && pre.val > root.val){
                    y = root;
                    if(x == null){
                        x = pre;
                    }/*else{//这里不能break，否则原来的前驱节点没断开，会出现循环
                        break;
                    }*/
                }
                pre = root;
                root = root.right;
            }
        }
        swap(x, y);
    }
}

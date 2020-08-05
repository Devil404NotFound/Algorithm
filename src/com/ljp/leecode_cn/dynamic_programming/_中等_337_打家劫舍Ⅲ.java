package com.ljp.leecode_cn.dynamic_programming;


import com.ljp.leecode_cn.other.data_structure.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 337. 打家劫舍 III
 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。

 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。

 示例 1:

 输入: [3,2,3,null,3,null,1]

 3
 / \
 2   3
 \   \
 3   1

 输出: 7
 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 示例 2:

 输入: [3,4,5,1,3,null,1]

 3
 / \
 4   5
 / \   \
 1   3   1

 输出: 9
 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 */
public class _中等_337_打家劫舍Ⅲ {
    /**
     * 递归
     * @param root
     * @return
     *  执行用时：
    2026 ms, 在所有 Java 提交中击败了5.03%的用户
    内存消耗：
    39.6 MB, 在所有 Java 提交中击败了45.33%的用户
     */
    public int rob(TreeNode root) {
        if(root == null){
            return 0;
        }
        return Math.max(robCore(root, true), robCore(root,false));
    }
    private int robCore(TreeNode root, boolean stealable){
        if(root == null){
            return 0;
        }
        if(stealable){
            return root.val + robCore(root.left, false) + robCore(root.right, false);
        }else{
            int leftTrue = robCore(root.left, true);
            int leftFalse = robCore(root.left, false);
            int rightTrue = robCore(root.right, true);
            int rightFalse = robCore(root.right, false);
//            return Math.max(Math.max(leftTrue + rightTrue, leftTrue + rightFalse),Math.max(leftFalse + rightTrue, leftFalse + rightFalse));
            return Math.max(leftTrue, leftFalse) + Math.max(rightTrue, rightFalse);
        }
    }

    /*********方法二：动态规划（深度优先搜索+Map）******************************************************************/
    /**
     *
     * @param root
     * @return
     * 执行用时：
    4 ms, 在所有 Java 提交中击败了38.67%的用户
    内存消耗：
    39.5 MB, 在所有 Java 提交中击败了65.43%的用户
     */
    public int rob2(TreeNode root) {
        Map<TreeNode, Integer> f = new HashMap<>();
        Map<TreeNode, Integer> g = new HashMap<>();
        dfs(root, f, g);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }
    private void dfs(TreeNode node, Map<TreeNode, Integer> f, Map<TreeNode, Integer> g){
        if(node == null){
            return;
        }
        dfs(node.left,f,g);
        dfs(node.right, f,g);
        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0))+ Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }

    /*********方法三：对方法二进行优化（将Map换为数组）******************************************************************/
    /**
     *
     * @param root
     * @return
     * 执行用时：
    1 ms, 在所有 Java 提交中击败了74.25%的用户
    内存消耗：
    39.6 MB, 在所有 Java 提交中击败了40.92%的用户
     */
    public int rob3(TreeNode root){
        if(root == null){
            return 0;
        }
        int[] res= dfs2(root);
        return Math.max(res[0], res[1]);
    }
    private int[] dfs2(TreeNode node){
        if(node == null){
            return new int[]{0, 0};
        }
        int[] left = dfs2(node.left);
        int[] right = dfs2(node.right);
        int selected = node.val + left[1] + right[1];
        int noSelected = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[]{selected, noSelected};
    }
}

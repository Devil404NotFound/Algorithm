package com.ljp.leecode_cn.binary_search;

import com.ljp.leecode_cn.other.data_structure.TreeNode;

/** 每日一题 2020.10.12
 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。

  

 示例：

 输入：

 1
 \
 3
 /
 2

 输出：
 1

 解释：
 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。

 */
public class _简单_530_二叉搜索树的最小绝对差 {
    /**
     执行用时：
     0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：
     38 MB, 在所有 Java 提交中击败了99.69%的用户
     */
    int lastNum = Integer.MAX_VALUE;
    int min = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        if(root != null){
            getMinimumDifference(root.left);
            min = Math.min(min, Math.abs(lastNum - root.val));
            lastNum = root.val;
            getMinimumDifference(root.right);
        }
        return min;
    }
}

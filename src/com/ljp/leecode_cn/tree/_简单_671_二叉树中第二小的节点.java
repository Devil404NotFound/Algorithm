package com.ljp.leecode_cn.tree;

import com.ljp.leecode_cn.other.data_structure.TreeNode;

/** 每日一题 2021.07.27
 * @author lijunpeng
 * @date 2021/7/27 23:05
 * @Description
671. 二叉树中第二小的节点
给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。

更正式地说，root.val = min(root.left.val, root.right.val) 总成立。

给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。



示例 1：


输入：root = [2,2,5,null,null,5,7]
输出：5
解释：最小的值是 2 ，第二小的值是 5 。
示例 2：


输入：root = [2,2,2]
输出：-1
解释：最小的值是 2, 但是不存在第二小的值。


提示：

树中节点数目在范围 [1, 25] 内
1 <= Node.val <= 231 - 1
对于树中每个节点 root.val == min(root.left.val, root.right.val)
 */
public class _简单_671_二叉树中第二小的节点 {
    private int ans = -1;
    private int rootValue;
    public int findSecondMinimumValue(TreeNode root) {
        rootValue = root.val;
        dfs(root);
        return ans;
    }
    private void dfs(TreeNode root) {
        if(root == null) {
            return;
        }
        if(ans != -1 && root.val >= ans) {
            return;
        }
        if(root.val > rootValue) {
            ans = root.val;
        }
        dfs(root.left);
        dfs(root.right);
    }
}
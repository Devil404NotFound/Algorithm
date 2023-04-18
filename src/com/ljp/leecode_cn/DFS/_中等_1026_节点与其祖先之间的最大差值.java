package com.ljp.leecode_cn.DFS;

import com.ljp.leecode_cn.other.data_structure.TreeNode;
import com.ljp.leecode_cn.util.CommonUtil;

/** 每日一题 2023.04.18
 * @author lijunpeng
 * @date 2023-04-18 21:50
 * @Description
1026. 节点与其祖先之间的最大差值
给定二叉树的根节点 root，找出存在于 不同 节点 A 和 B 之间的最大值 V，其中 V = |A.val - B.val|，且 A 是 B 的祖先。

（如果 A 的任何子节点之一为 B，或者 A 的任何子节点是 B 的祖先，那么我们认为 A 是 B 的祖先）

示例 1：
输入：root = [8,3,10,1,6,null,14,null,null,4,7,13]
输出：7
解释：
我们有大量的节点与其祖先的差值，其中一些如下：
|8 - 3| = 5
|3 - 7| = 4
|8 - 1| = 7
|10 - 13| = 3
在所有可能的差值中，最大值 7 由 |8 - 1| = 7 得出。
示例 2：
输入：root = [1,null,2,null,0,3]
输出：3
提示：
树中的节点数在 2 到 5000 之间。
0 <= Node.val <= 105
 */
public class _中等_1026_节点与其祖先之间的最大差值 {

    public static void main(String[] args) {
        Integer[] arr = {8,3,10,1,6,null,14,null,null,4,7,13};
        TreeNode root = CommonUtil.array2TreeNode(arr);
        int diff = new _中等_1026_节点与其祖先之间的最大差值().maxAncestorDiff(root);
        System.out.println(diff);

    }

    /** 深度优先遍历
     执行用时：
     0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：
     41.4 MB, 在所有 Java 提交中击败了36.88%的用户
     */
    int ans = 0;
    public int maxAncestorDiff(TreeNode root) {
        maxAncestorDiffCore(root, root.val, root.val);
        return ans;
    }
    private void maxAncestorDiffCore(TreeNode root, int min, int max) {
        if(root == null) {
            return;
        }
        int curMax = Math.max(Math.abs(root.val - min), Math.abs(root.val - max));
        ans = Math.max(curMax, ans);
        int nextMin = Math.min(min, root.val);
        int nextMax = Math.max(max, root.val);
        maxAncestorDiffCore(root.left, nextMin, nextMax);
        maxAncestorDiffCore(root.right,nextMin, nextMax);
    }
}

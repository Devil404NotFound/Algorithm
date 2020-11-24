package com.ljp.leecode_cn.tree;

import com.ljp.leecode_cn.other.data_structure.TreeNode;

/** 每日一题 2020.11.24
 * @author ljp
 * @date 2020/11/24 11:50
 *
222. 完全二叉树的节点个数
给出一个完全二叉树，求出该树的节点个数。

说明：

完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。

示例:

输入:
1
/ \
2   3
/ \  /
4  5 6

输出: 6
 */
public class _中等_222_完全二叉树的节点个数 {
    /**
     * 不讲武德 直接DFS
     * @param root
     * @return
    执行结果：
    通过
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    40.7 MB, 在所有 Java 提交中击败了92.95%的用户
     */
    public int countNodes(TreeNode root) {
        if(root == null){
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    /** 官方题解：二分查找+位运算
     作者：LeetCode-Solution
     链接：https://leetcode-cn.com/problems/count-complete-tree-nodes/solution/wan-quan-er-cha-shu-de-jie-dian-ge-shu-by-leetco-2/
     来源：力扣（LeetCode）
     * @param root
     * @return
     */
    public int countNodes2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int level = 0;
        TreeNode node = root;
        while (node.left != null) {
            level++;
            node = node.left;
        }
        int low = 1 << level, high = (1 << (level + 1)) - 1;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (exists(root, level, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public boolean exists(TreeNode root, int level, int k) {
        int bits = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && bits > 0) {
            if ((bits & k) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }


}

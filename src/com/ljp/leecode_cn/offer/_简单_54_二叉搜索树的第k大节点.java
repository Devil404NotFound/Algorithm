package com.ljp.leecode_cn.offer;

import com.ljp.leecode_cn.other.data_structure.TreeNode;

/** 类似230-二叉搜索树中第k小的数
 * @author ljp
 * @date 2020/12/5 0:05
剑指 Offer 54. 二叉搜索树的第k大节点
给定一棵二叉搜索树，请找出其中第k大的节点。



示例 1:

输入: root = [3,1,4,null,2], k = 1
3
/ \
1   4
\
2
输出: 4
示例 2:

输入: root = [5,3,6,2,4,null,null,1], k = 3
5
/ \
3   6
/ \
2   4
/
1
输出: 4


限制：

1 ≤ k ≤ 二叉搜索树元素个数
 */
public class _简单_54_二叉搜索树的第k大节点 {
    /**
     * 统计右边再递归调用
     * @param root
     * @param k
     * @return
     */
    public int kthLargest(TreeNode root, int k) {
        int right = count(root.right);
        if(right == k - 1){
            return root.val;
        }else if(right < k - 1){
            return kthLargest(root.left, k - 1 - right);
        }else{
            return kthLargest(root.right, k);
        }
    }
    private int count(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return count(root.left) + count(root.right) + 1;
    }
}

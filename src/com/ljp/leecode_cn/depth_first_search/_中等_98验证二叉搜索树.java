package com.ljp.leecode_cn.depth_first_search;

/**
 * 98. 验证二叉搜索树
 给定一个二叉树，判断其是否是一个有效的二叉搜索树。

 假设一个二叉搜索树具有如下特征：

 节点的左子树只包含小于当前节点的数。
 节点的右子树只包含大于当前节点的数。
 所有左子树和右子树自身必须也是二叉搜索树。
 示例 1:

 输入:
   2
  / \
 1   3
 输出: true
 示例 2:

 输入:
      5
   /   \
 1     4
     /  \
    3     6
 输出: false
 解释: 输入为: [5,1,4,null,null,3,6]。
 根节点的值为 5 ，但是其右子节点值为 4 。
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class _中等_98验证二叉搜索树 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 方法一，给定子树一个范围
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if(root != null){
            /*这段可以不要
            if(root.left != null && root.val <= root.left.val){
                return false;
            }
            if(root.right != null && root.val >= root.right.val){
                return false;
            }*/
            //左子树范围[-∞, root.val)，右子树范围(root.val, +∞]
            return isValidBSTCore(root.left, Long.MIN_VALUE, root.val) && isValidBSTCore(root.right, root.val, Long.MAX_VALUE);
        }
        return true;
    }
    //自定义方法，限定子分支的范围，超出该范围就返回false
    private boolean isValidBSTCore(TreeNode root, long min, long max){
        if(root == null){
            return true;
        }
        //判断是否超出范围
        if(root.val >= max || root.val <= min){
            //要处理最大值或者最小值的情况
            return max == Integer.MAX_VALUE || min == Integer.MIN_VALUE;
        }
   /*    这段可以不用，在范围比较那里也比较了
         //左子树大于等于根节点，false
        if(root.left != null && root.left.val >= root.val){
            return false;
        }
        //右子树小于等于根节点，false
        if(root.right != null && root.right.val <= root.val){
            return false;
        }*/
        //递归判断左右子树
        return isValidBSTCore(root.left, min, root.val) && isValidBSTCore(root.right, root.val, max);
    }

    /**
     * 方法二，中序遍历
     * @param root
     * @return
     * 执行用时 :
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗 :
    40 MB, 在所有 Java 提交中击败了5.80%的用户
     */
    long val = Long.MIN_VALUE;
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST2(root.left)) {
            return false;
        }
        if (root.val <= val) {
            return false;
        }
        val = root.val;
        return isValidBST2(root.right);
    }
}

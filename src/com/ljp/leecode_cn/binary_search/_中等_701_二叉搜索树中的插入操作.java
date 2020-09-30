package com.ljp.leecode_cn.binary_search;

import com.ljp.leecode_cn.other.data_structure.TreeNode;

/**
 701. 二叉搜索树中的插入操作
 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据保证，新值和原始二叉搜索树中的任意节点值都不同。

 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。



 例如,

 给定二叉搜索树:

 4
 / \
 2   7
 / \
 1   3

 和 插入的值: 5
 你可以返回这个二叉搜索树:

 4
 /   \
 2     7
 / \   /
 1   3 5
 或者这个树也是有效的:

 5
 /   \
 2     7
 / \
 1   3
 \
 4


 提示：

 给定的树上的节点数介于 0 和 10^4 之间
 每个节点都有一个唯一整数值，取值范围从 0 到 10^8
 -10^8 <= val <= 10^8
 新值和原始二叉搜索树中的任意节点值都不同
 通过次数39,595提交次数55,092
 */
public class _中等_701_二叉搜索树中的插入操作 {
    /**
     *递归方法
     * @param root
     * @param val
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    39.6 MB, 在所有 Java 提交中击败了31.45%的用户
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null){
            return new TreeNode(val);// 注意这里，如果是空，需要新建一个节点
        }
        if(root.val > val){// 值小于该节点，如果左子树为空，就新建一个左子树，否则，递归调用左子树
            if(root.left == null){
                root.left = new TreeNode(val);
            }else{
                insertIntoBST(root.left, val);
            }
        }else{
            if(root.right == null){// 值大于该节点，如果右子树为空，就新建一个右子树，否则，递归调用右子树
                root.right = new TreeNode(val);
            }else{
                insertIntoBST(root.right, val);
            }
        }
        return root;
    }


    /**
     * 官方题解：迭代方法
     * @param root
     * @param val
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    39.3 MB, 在所有 Java 提交中击败了86.32%的用户
     */
    public TreeNode insertIntoBST2(TreeNode root, int val){
        if(root == null){
            return new TreeNode(val);
        }
        TreeNode node = root;
        while(node != null){
            if(val < node.val){
                if(node.left == null){
                    node.left = new TreeNode(val);
                    break;
                }else{
                    node = node.left;
                }
            }else{
                if(node.right == null){
                    node.right = new TreeNode(val);
                    break;
                }else{
                    node = node.right;
                }
            }
        }
        return root;
    }
}

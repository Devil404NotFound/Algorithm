package com.ljp.leecode_cn.binary_search;

import com.ljp.leecode_cn.other.data_structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 235. 二叉搜索树的最近公共祖先
 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]





 示例 1:

 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 输出: 6
 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 示例 2:

 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 输出: 2
 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。


 说明:

 所有节点的值都是唯一的。
 p、q 为不同节点且均存在于给定的二叉搜索树中。
 */
public class _简单_235_二叉搜索树的最近公共祖先 {
    /**
     *官方题解一：二次遍历
     * @param root
     * @param p
     * @param q
     * @return
    执行用时：
    8 ms, 在所有 Java 提交中击败了15.55%的用户
    内存消耗：
    39.9 MB, 在所有 Java 提交中击败了10.22%的用户
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> listP = dfs(root, p);
        List<TreeNode> listQ = dfs(root, q);
        TreeNode ans = null;
        int size = Math.min(listP.size(), listQ.size());
        for(int i = 0; i < size; i++){
            if(listP.get(i) == listQ.get(i)){
                ans = listP.get(i);
            }else{
                break;
            }
        }
        return ans;
    }
    private List<TreeNode> dfs(TreeNode node, TreeNode p){
        List<TreeNode> list = new ArrayList<>();
        while(node != null){
            list.add(node);
            if(node.val > p.val){
                node = node.left;
            }else if(node.val < p.val){
                node = node.right;
            }else{
                break;
            }
        }
        return list;
    }

    /**
     * 官方题解&题解评论：一次遍历
     * @param root
     * @param p
     * @param q
     * @return
    执行用时：
    6 ms, 在所有 Java 提交中击败了99.70%的用户
    内存消耗：
    39.6 MB, 在所有 Java 提交中击败了49.75%的用户
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val > p.val && root.val > q.val){
            return lowestCommonAncestor2(root.left, p, q);
        }else if(root.val < p.val && root.val < q.val){
            return lowestCommonAncestor2(root.right, p, q);
        }else{
            return root;
        }
    }
}

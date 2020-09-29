package com.ljp.leecode_cn.binary_search;

import com.ljp.leecode_cn.other.data_structure.TreeNode;

import java.util.*;

/**
 501. 二叉搜索树中的众数
 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。

 假定 BST 有如下定义：

 结点左子树中所含结点的值小于等于当前结点的值
 结点右子树中所含结点的值大于等于当前结点的值
 左子树和右子树都是二叉搜索树
 例如：
 给定 BST [1,null,2,2],

 1
 \
 2
 /
 2
 返回[2].

 提示：如果众数超过1个，不需考虑输出顺序

 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 */
public class _简单_501_二叉搜索树的众数 {
    /**
     *常规方法
     * @param root
     * @return
    执行用时：
    4 ms, 在所有 Java 提交中击败了34.20%的用户
    内存消耗：
    40.5 MB, 在所有 Java 提交中击败了20.59%的用户
     */
    int max = Integer.MIN_VALUE;
    public int[] findMode(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        dfs(map, root);
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        List<Integer> list = new ArrayList<>();
        while(iterator.hasNext()){
            Map.Entry<Integer, Integer> entry = iterator.next();
            if(entry.getValue() == max){
                list.add(entry.getKey());
            }
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
    private void dfs(Map<Integer, Integer> map, TreeNode root){
        if(root != null){
            int num = map.getOrDefault(root.val, 0) + 1;
            map.put(root.val, num);
            max = Math.max(max, num);
            dfs(map, root.left);
            dfs(map, root.right);
        }
    }
}

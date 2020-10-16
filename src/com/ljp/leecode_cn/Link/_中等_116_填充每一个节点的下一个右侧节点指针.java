package com.ljp.leecode_cn.Link;

import com.ljp.leecode_cn.other.data_structure.Node;

/**
 * @author ljp
 * @date 2020/10/15 23:13
 */
public class _中等_116_填充每一个节点的下一个右侧节点指针 {
    /**
     *利用已经建立的next来建立下一层的next
     执行用时：
     1 ms, 在所有 Java 提交中击败了59.84%的用户
     内存消耗：
     38.8 MB, 在所有 Java 提交中击败了95.92%的用户
     */
    Node nextStart, pre;
    public Node connect(Node root) {
        Node start = root;
        while(start != null){
            nextStart = null;
            pre = null;
            for(Node p = start; p != null; p = p.next){
                if(p.left != null){
                    handle(p.left);
                }
                if(p.right != null){
                    handle(p.right);
                }
            }
            start = nextStart;
        }
        return root;
    }
    private void handle(Node p){
        if(pre != null){
            pre.next = p;
        }
        if(nextStart == null){
            nextStart = p;
        }
        pre = p;
    }

    /**
     * 评论区大佬方法：递归调用
     * @param root
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    38.7 MB, 在所有 Java 提交中击败了96.73%的用户
     */
    public Node connect2(Node root){
        if(root == null || root.left == null){
            return root;
        }
        root.left.next = root.right;
        if(root.next != null){
            //因为是完美二叉树，不存在有右子树而没有左子树的情况（因此不用考虑root.next.right）
            root.right.next = root.next.left;
        }
        connect(root.left);
        connect(root.right);
        return root;
    }
}

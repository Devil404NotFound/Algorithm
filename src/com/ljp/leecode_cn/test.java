package com.ljp.leecode_cn;

import java.util.HashSet;
import java.util.Set;

/**
 * 一个日常测试类
 * @author ljp
 * @date 2020/10/14 10:38
 */
public class test {
    public static void main(String[] args) {
        System.out.println(1e5);
        /*int[] nums = new int[]{1,2,3,5,2,4,3};
        System.out.println(getNum(new int[]{}));*/
        String result = "{\"code\":200,\"msg\":\"success\",\"newslist\":[{\"content\":\"没有人不会走，也没有人会一直在。\",\"source\":\"佚名\"}]}1";
        System.out.println(result.substring(0, result.length() - 1));
    }

    public static int getNum(int[] nums) {
        if(nums == null || nums.length == 0) {
            return -1;
        }
        Set<Integer> set = new HashSet<>();
        for(int i : nums) {
            if(set.contains(i)) {
                return i;
            }
            set.add(i);
        }
        return -1;
    }
    public static int getNum2(int[] nums) {
        if(nums == null || nums.length == 0) {
            return -1;
        }
        return -1;
    }
    public void print(Tree root) {
        if(root == null) {
            return;
        }
        System.out.println(root.val);
        print(root.leftNode);
        print(root.rightNode);
    }
}

class Tree{
    int val;
    Tree leftNode;
    Tree rightNode;
}
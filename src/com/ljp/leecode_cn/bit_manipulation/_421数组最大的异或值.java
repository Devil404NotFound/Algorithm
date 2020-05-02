package com.ljp.leecode_cn.bit_manipulation;

/**
 * 421. 数组中两个数的最大异或值
 给定一个非空数组，数组中元素为 a0, a1, a2, … , an-1，其中 0 ≤ ai < 231 。

 找到 ai 和aj 最大的异或 (XOR) 运算结果，其中0 ≤ i,  j < n 。

 你能在O(n)的时间解决这个问题吗？

 示例:

 输入: [3, 10, 5, 25, 2, 8]

 输出: 28

 解释: 最大的结果是 5 ^ 25 = 28.
 */
public class _421数组最大的异或值 {
    public static void main(String[] args) {
        int[] arr = {10,2, 8};
        System.out.println(findMaximumXOR2(arr));
        System.out.println(findMaximumXOR3(arr));
    }

    /**
     *
     * @param nums
     * @return
     * 执行用时 :
    380 ms, 在所有 Java 提交中击败了5.01%的用户
    内存消耗 :
    39.5 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public static int findMaximumXOR(int[] nums) {
        int max = 0;
        for(int i = 0; i < nums.length; i++){
            for (int j = 0; j < nums.length; j++) {
                max = Math.max(max, nums[i] ^ nums[j]);
            }
        }
        return max;
    }

    /**
     * 小优化
     * @param nums
     * @return
     * 执行结果：
    通过
    显示详情
    执行用时 :
    356 ms, 在所有 Java 提交中击败了5.01%的用户
    内存消耗 :
    39.6 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public static int findMaximumXOR2(int[] nums) {
        int max = 0;
        int digit = 1;
        for(int i = 0; i < nums.length; i++){
            if(digit < nums[i]){
                for (int j = 0; j < nums.length; j++) {
                    max = Math.max(max, nums[i] ^ nums[j]);
                }
                if((digit << 1) < nums[i]){
                    digit <<= 1;
                }
            }
        }
        return max;
    }

    /**
     * 字典树结构
     * @param nums
     * @return
     * 执行用时 :
    27 ms, 在所有 Java 提交中击败了89.70%的用户
    内存消耗 :
    47.9 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public static int findMaximumXOR3(int[] nums) {
        //找到最高位1的位置
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if(max < nums[i]){
                max = nums[i];
            }
        }
        if(max == 0){
            return 0;
        }
        int digit = 0;
        while(max != 0){
            digit++;
            max >>= 1;
        }
        //构造字典树
        TireNode root = new TireNode(0);
        TireNode node;
        for (int i = 0; i < nums.length; i++) {
            node = root;
            for (int j = digit - 1; j > -1; j--) {
                if(((nums[i] >> j) & 1) == 0){
                    if(node.left == null){
                        node.left = new TireNode(0);
                    }
                    node = node.left;
                }else{
                    if(node.right == null){
                        node.right = new TireNode(1);
                    }
                    node = node.right;
                }
            }
        }
        //查找最大值
        max = 0;
        for (int i = 0; i < nums.length; i++) {
            int n = (~nums[i]);
            int nn = 0;
            node = root;
            int j = digit - 1;
            while(node != null){
                if(((n >>> j) & 1) == 0){
                    if(node.left != null){
                        node = node.left;
                    }else{
                        nn |= (1 << j);
                        node = node.right;
                    }
                }else{
                    if(node.right != null){
                        node = node.right;
                        nn |= (1 << j);
                    }else{
                        node = node.left;
                    }
                }
                j--;
            }
            nn ^= nums[i];
            if(max < nn){
                max = nn;
            }
        }
        return max;

    }
    static class TireNode{
        int val;
        TireNode left;
        TireNode right;

        public TireNode(int val) {
            this.val = val;
        }
    }
}

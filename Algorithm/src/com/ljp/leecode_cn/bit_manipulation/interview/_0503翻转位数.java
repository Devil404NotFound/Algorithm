package com.ljp.leecode_cn.bit_manipulation.interview;

/**
 * 面试题 05.03. 翻转数位【简单】
 给定一个32位整数 num，你可以将一个数位从0变为1。请编写一个程序，找出你能够获得的最长的一串1的长度。

 示例 1：

 输入: num = 1775(110111011112)
 输出: 8
 示例 2：

 输入: num = 7(01112)
 输出: 4
 */
public class _0503翻转位数 {
    public static void main(String[] args) {
        int num = 0b11110100111;
        System.out.println(Integer.toBinaryString(num));
        System.out.println(reverseBits(num));
    }

    /**
     *
     * @param num
     * @return
     * 执行用时 :
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗 :
    36.5 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public static int reverseBits(int num) {
        if(num == 0){
            return 1;
        }
        int preSum = 0;
        while((num & 1) == 0){
            num >>>= 1;
        }
        while((num & 1) == 1){
            preSum++;
            num >>>= 1;
        }
        num >>>= 1;
        int max = preSum;
        int sum = 0;
        while(num != 0){
            if((num & 1) == 1){
                sum++;
            }else{
                if(max < sum + preSum){
                    max = sum + preSum;
                }
                preSum = sum;
                sum = 0;
            }
            num >>>= 1;
        }
        if(max < sum + preSum){
            max = sum + preSum;
        }
        return max + 1;
    }

    /**
     * 小优化
     * @param num
     * @return
     */
    public static int reverseBits2(int num){
        int preLen = 0, curLen = 0, maxLen = 0;
        for (int i = 0; i < 32; i++) {
            if((num & 1) == 0){
                curLen -= preLen;
                preLen = curLen + 1;
            }
            curLen++;
            if(maxLen < curLen){
                maxLen = curLen;
            }
            num >>>= 1;
        }
        return maxLen;
    }
}

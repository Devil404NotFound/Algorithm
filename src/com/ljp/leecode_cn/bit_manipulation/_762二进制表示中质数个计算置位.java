package com.ljp.leecode_cn.bit_manipulation;

/**
 * \762. 二进制表示中质数个计算置位【简单】
 给定两个整数 L 和 R ，找到闭区间 [L, R] 范围内，计算置位位数为质数的整数个数。

 （注意，计算置位代表二进制表示中1的个数。例如 21 的二进制表示 10101 有 3 个计算置位。还有，1 不是质数。）

 示例 1:

 输入: L = 6, R = 10
 输出: 4
 解释:
 6 -> 110 (2 个计算置位，2 是质数)
 7 -> 111 (3 个计算置位，3 是质数)
 9 -> 1001 (2 个计算置位，2 是质数)
 10-> 1010 (2 个计算置位，2 是质数)
 示例 2:

 输入: L = 10, R = 15
 输出: 5
 解释:
 10 -> 1010 (2 个计算置位, 2 是质数)
 11 -> 1011 (3 个计算置位, 3 是质数)
 12 -> 1100 (2 个计算置位, 2 是质数)
 13 -> 1101 (3 个计算置位, 3 是质数)
 14 -> 1110 (3 个计算置位, 3 是质数)
 15 -> 1111 (4 个计算置位, 4 不是质数)
 注意:

 L, R 是 L <= R 且在 [1, 10^6] 中的整数。
 R - L 的最大值为 10000。
 */
public class _762二进制表示中质数个计算置位 {
    public static void main(String[] args) {
        int L = -6;
        int R = 100;
        System.out.println(countPrimeSetBits(L, R));
    }

    /**
     *
     * @param L
     * @param R
     * @return
     * 执行用时 :
    31 ms, 在所有 Java 提交中击败了26.51%的用户
    内存消耗 :
    36.9 MB, 在所有 Java 提交中击败了25.00%的用户
     */
    public static int countPrimeSetBits(int L, int R) {
        boolean[] flag = new boolean[20];
        int sum = 0;
        for(int i = 2; i < 20; i++){
            flag[i] = true;
        }
        //初始化质数
        for (int i = 2; i < 20; i++) {
            for (int j = 2; j < i; j++) {
                if(i % j == 0){
                    flag[i] = false;
                    break;
                }
            }
        }
        for (int i = L; i <= R; i++) {
            int count = 0;
            int num = i;
            while(num != 0){
                if((num & 1) == 1){
                    count++;
                }
                num >>>= 1;
            }
            if(flag[count]){
                sum++;
            }
        }
        return sum;
    }
}

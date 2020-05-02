package org.lanqiao.algorithm.chapter11;

/**
 * 一共有n堆石子，编号1...n，第i堆中有a[i]个石子。
 * 每一次操作Alice和Bob可以从任意一堆石子中取出任意数量的石子，
 * 至少取1颗，至多取出这一堆剩下的所有石子。
 * 两个人轮流行动，取光所有石子的一方获胜。Alice为先手
 * 给定a，假设两人都采用最优策略，谁会获胜？
 * 答：
 * 转换为二进制，全部异或，看结果是否为0，如果为0，先手输，不为0，就赢
 */
public class _02Nim游戏 {
    public static void main(String[] args) {
        int[] a = {3,4,5};
        boolean result = solve(a);
        System.out.println(result);
    }

    private static boolean solve(int[] a) {
        int result = 0;
        for (int i = 0; i < a.length; i++) {
            result ^= a[i];
        }
        return result != 0;
    }
}

package com.ljp.leecode_cn.binary_search;

/**
 * 每日一题 2021.06.14
 *
 * @author lijunpeng
 * @date 2021/6/14 22:39
 * @Description 374. 猜数字大小
 * 猜数字游戏的规则如下：
 * <p>
 * 每轮游戏，我都会从 1 到 n 随机选择一个数字。 请你猜选出的是哪个数字。
 * 如果你猜错了，我会告诉你，你猜测的数字比我选出的数字是大了还是小了。
 * 你可以通过调用一个预先定义好的接口 int guess(int num) 来获取猜测结果，返回值一共有 3 种可能的情况（-1，1 或 0）：
 * <p>
 * -1：我选出的数字比你猜的数字小 pick < num
 * 1：我选出的数字比你猜的数字大 pick > num
 * 0：我选出的数字和你猜的数字一样。恭喜！你猜对了！pick == num
 * 返回我选出的数字。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 10, pick = 6
 * 输出：6
 * 示例 2：
 * <p>
 * 输入：n = 1, pick = 1
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：n = 2, pick = 1
 * 输出：1
 * 示例 4：
 * <p>
 * 输入：n = 2, pick = 2
 * 输出：2
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 231 - 1
 * 1 <= pick <= n
 */
public class _简单_374_猜数字大小 {
    /**
     * @Author lijunpeng
     * @Date 2021/6/14 22:39
     * @Description 执行用时：
     * 0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：
     * 35 MB, 在所有 Java 提交中击败了77.47%的用户
     **/
    public int guessNumber(int n) {
        int left = 1, right = n;
        int num = 1;
        int result = 1;
        while (result != 0) {
            num = right - ((right - left) >> 1);
            result = guess(num);
            if (result > 0) {
                left = num + 1;
            } else if (result < 0) {
                right = num - 1;
            }
        }
        return num;
    }

    /**
     * Forward declaration of guess API.
     *
     * @param num your guess
     * @return -1 if num is lower than the guess number
     * 1 if num is higher than the guess number
     * otherwise return 0
     * int guess(int num);
     */
    /**
     * 随便写个空壳
     * @param num
     * @return
     */
    private int guess(int num) {
        return 0;
    }
}

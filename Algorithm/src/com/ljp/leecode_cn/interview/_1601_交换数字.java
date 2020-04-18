package com.ljp.leecode_cn.interview;

/**
 * 面试题 16.01. 交换数字
 编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值。

 示例：

 输入: numbers = [1,2]
 输出: [2,1]
 提示：

 numbers.length == 2

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/swap-numbers-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 执行结果：
 通过
 执行用时 :
 0 ms, 在所有 Java 提交中击败了100.00%的用户
 内存消耗 :
 37.5 MB, 在所有 Java 提交中击败了100.00%的用户
 */
public class _1601_交换数字 {
    class Solution {
        public int[] swapNumbers(int[] numbers) {
            numbers[0] = numbers[0] ^ numbers[1];
            numbers[1] = numbers[0] ^ numbers[1];
            numbers[0] = numbers[0] ^ numbers[1];
            return numbers;
        }
    }
}

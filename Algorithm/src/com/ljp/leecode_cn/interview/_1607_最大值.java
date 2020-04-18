package com.ljp.leecode_cn.interview;

/**
 * 面试题 16.07. 最大数值
编写一个方法，找出两个数字a和b中最大的那一个。不得使用if-else或其他比较运算符。

 示例：

 输入： a = 1, b = 2
 输出： 2
 执行结果：
 通过
 执行用时 : 0 ms, 在所有 Java 提交中击败了100.00% 的用户
 内存消耗 :
 36.4 MB在所有 Java 提交中击败了 100.00% 的用户

 题解：（参考评论）
 1. 通过key判断a-b大于0还是小于0，再右移63位（long有64位，最后一位是正负标识）,转换为long防止溢出
 2. 结果为0或者-1，
 3. 再与1相与，
 4. key结果为0或者1
 5. key与1异或
 ==========
 当key为0时（即a>=b），因此key^1 = 1，所以(key ^ 1) * a + key * b = a
 当key为1时（即a<b)，因此key^1 = 0，所以(key ^ 1) * a + key * b  = b
 */
public class _1607_最大值 {
    class Solution {
        public int maximum(int a, int b) {
            int key = (int)(((long)a - (long)b)>>63 & 1);
            return (key ^ 1) * a + key * b;
        }
    }
}

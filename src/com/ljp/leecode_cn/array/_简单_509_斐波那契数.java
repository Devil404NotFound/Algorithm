package com.ljp.leecode_cn.array;

import java.util.HashMap;
import java.util.Map;

/** 每日一题 2021.01.04
 * @author ljp
 * @date 2021/1/4 21:01
509. 波那契数
斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：

F(0) = 0，F(1) = 1
F(n) = F(n - 1) + F(n - 2)，其中 n > 1
给你 n ，请计算 F(n) 。



示例 1：

输入：2
输出：1
解释：F(2) = F(1) + F(0) = 1 + 0 = 1
示例 2：

输入：3
输出：2
解释：F(3) = F(2) + F(1) = 1 + 1 = 2
示例 3：

输入：4
输出：3
解释：F(4) = F(3) + F(2) = 2 + 1 = 3


提示：

0 <= n <= 30
 */
public class    _简单_509_斐波那契数 {
    /**
     *
     * @param n
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    35 MB, 在所有 Java 提交中击败了84.88%的用户
     */
    public int fib(int n) {
        if(n == 0) {
            return 0;
        }
        int ret = 1;
        int preLast = 0;
        int last = 1;
        for(int i = 2; i <= n; ++i) {
            ret = preLast + last;
            preLast = last;
            last = ret;
        }
        return ret;
    }

    /**
     * 递归
     */
    Map<Integer,Integer> map = new HashMap<>();
    public int fib2(int n) {
        if(map.containsKey(n)){
            return map.get(n);
        }
        int result;
        if(n < 2){
            result = n;
        }else{
            result = fib2(n - 1) + fib2(n - 2);
        }
        map.put(n, result);
        return result;
    }

    /**
     * 官方题解：n < 2时，结果就是n
     * @param n
     * @return
     */
    public int fib3(int n) {
        if (n < 2) {
            return n;
        }
        int p = 0, q = 0, r = 1;
        for (int i = 2; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
}

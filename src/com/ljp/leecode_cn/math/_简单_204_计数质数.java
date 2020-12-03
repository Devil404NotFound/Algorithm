package com.ljp.leecode_cn.math;

import java.util.ArrayList;
import java.util.List;

/**每日一题 2020.12.03
 * @author ljp
 * @date 2020/12/3 19:41
 *
204. 计数质数
统计所有小于非负整数 n 的质数的数量。



示例 1：

输入：n = 10
输出：4
解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
示例 2：

输入：n = 0
输出：0
示例 3：

输入：n = 1
输出：0


提示：

0 <= n <= 5 * 106
 */
public class _简单_204_计数质数 {
    /**
     * 官方题解一: 枚举（超时）
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            ans += isPrime(i) ? 1 : 0;
        }
        return ans;
    }
    public boolean isPrime(int x) {
        for (int i = 2; i * i <= x; ++i) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
    /**********end************/

    /**
     * 官方题解二：埃氏筛选法
     * @param n
     * @return
    执行用时：
    13 ms, 在所有 Java 提交中击败了87.56%的用户
    内存消耗：
    37.3 MB, 在所有 Java 提交中击败了41.29%的用户
     */
    public int countPrimes2(int n) {
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for(int i = 2; i < n; ++i) {
            if(!notPrime[i]){
                ++count;
                if(((long)i * i) < n) {
                    for(int j = i * i; j < n; j += i) {
                        notPrime[j] = true;
                    }
                }
            }
        }
        return count;
    }

    /**
     * 官方题解三：线性筛
     * @param n
     * @return
    执行用时：
    49 ms, 在所有 Java 提交中击败了21.10%的用户
    内存消耗：
    42.7 MB, 在所有 Java 提交中击败了10.46%的用户
     */
    public int countPrimes3(int n) {
        List<Integer> list = new ArrayList<>();
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if(!notPrime[i]) {
                ++count;
                list.add(i);//添加到质数队列
            }
            //让每个质数都与当前这个数相乘
            int size = list.size();
            for (int j = 0; j < size && (long) i * list.get(j) < n; j++) {
                notPrime[i * list.get(j)] = true;
                if((i % list.get(j)) == 0) {
                    break;
                }
            }
        }
        return count;
    }

    /**
     * 除了2，质数都是奇数，因此可以对埃氏筛选法优化
     * @param n
     * @return
    执行用时：
    9 ms, 在所有 Java 提交中击败了93.10%的用户
    内存消耗：
    37.1 MB, 在所有 Java 提交中击败了62.86%的用户
     */
    public int countPrime4(int n) {
        if(n < 3) {
            return 1;
        }
        boolean[] notPrime = new boolean[n];
        int count = 1;
        for (int i = 3; i < n; i += 2) {
            if(!notPrime[i]) {
                ++count;
                long t;
                for(int j = i; (t = (long)i * j) < n; j += 2) {
                    notPrime[(int)t] = true;
                }
            }
        }
        return count;
    }
}

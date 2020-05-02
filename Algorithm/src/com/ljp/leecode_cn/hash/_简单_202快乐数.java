package com.ljp.leecode_cn.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * 202. 快乐数
 编写一个算法来判断一个数 n 是不是快乐数。

 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。

 如果 n 是快乐数就返回 True ；不是，则返回 False 。



 示例：

 输入：19
 输出：true
 解释：
 12 + 92 = 82
 82 + 22 = 68
 62 + 82 = 100
 12 + 02 + 02 = 1

 不是快乐数的数称为不快乐数（unhappy number），
 所有不快乐数的数位平方和计算，最後都会进入 4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4 的循环中。
 */
public class _简单_202快乐数 {
    public static void main(String[] args) {
        int n = 10000001;
        System.out.println(isHappy(n));
    }

    /**
     *
     * @param n
     * @return
     * 执行用时 :
    1 ms, 在所有 Java 提交中击败了99.89%的用户
    内存消耗 :
    37.1 MB, 在所有 Java 提交中击败了8.33%的用户
     */
    public static boolean isHappy(int n) {
        int res = 0;
        Set<Integer> set = new HashSet<>();
        while(n != 1){
            res = 0;
            while(n > 0){
                res += (n % 10) * (n % 10);
                n /= 10;
            }
            n = res;
            if(!set.add(n)){
                return false;
            }
        }
        return true;
    }
}

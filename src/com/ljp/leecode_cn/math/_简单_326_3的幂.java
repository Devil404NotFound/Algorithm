package com.ljp.leecode_cn.math;

/**
 326. 3的幂
 给定一个整数，写一个函数来判断它是否是 3 的幂次方。

 示例 1:

 输入: 27
 输出: true
 示例 2:

 输入: 0
 输出: false
 示例 3:

 输入: 9
 输出: true
 示例 4:

 输入: 45
 输出: false
 进阶：
 你能不使用循环或者递归来完成本题吗？
 * @author ljp
 * @date 2020/11/4 21:58
 */
public class _简单_326_3的幂 {
    /**
     *递归解法
     * @param n
     * @return
    执行用时：
    16 ms, 在所有 Java 提交中击败了75.04%的用户
    内存消耗：
    38.3 MB, 在所有 Java 提交中击败了87.98%的用户
     */
    public boolean isPowerOfThree(int n) {
        if(n == 1){
            return true;
        }
        if(n == 0){
            return false;
        }
        if(n % 3 == 0){
            return isPowerOfThree(n / 3);
        }
        return false;
    }

    /**
     *
     * @param n
     * @return
    执行用时：
    15 ms, 在所有 Java 提交中击败了98.47%的用户
    内存消耗：
    38.4 MB, 在所有 Java 提交中击败了80.32%的用户
     */
    public boolean isPowerOfThree2(int n) {
        if(n == 0){
            return false;
        }
        while(n != 1){
            if(n % 3 == 0){
                n /= 3;
            }else{
                return false;
            }
        }
        return true;
    }

    /**
     * 官方题解一：迭代
     * @param n
     * @return
    执行用时：
    15 ms, 在所有 Java 提交中击败了98.47%的用户
    内存消耗：
    38.6 MB, 在所有 Java 提交中击败了50.38%的用户
     */
    public boolean isPowerOfThree3(int n) {
        if(n < 1){
            return false;
        }
        while(n % 3 == 0){
            n /= 3;
        }
        return n == 1;
    }

    /**
     * 官方题解二：基准转换 3的n次幂可以转换为三进制就是1000...
     * @param n
     * @return
    执行用时：
    39 ms, 在所有 Java 提交中击败了5.30%的用户
    内存消耗：
    39 MB, 在所有 Java 提交中击败了5.35%的用户
     */
    public boolean isPowerOfThree4(int n) {
        return Integer.toString(n, 3).matches("^10*$");
    }

    /**
     * 官方题解三：运算法
     * @param n
     * @return
     */
    public boolean isPowerOfThree5(int n) {
        ///1.log10不用担心精度
//        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
        ///2. log()存在精度问题
        int epsilon = 10^6;
        return ((Math.log(n) / Math.log(3) + epsilon) % 1) <= 2 * epsilon;
    }

    /**
     * 官方题解四：整数限制 int的最大值是2^31-1 ====> log3(2^31-1) = 19.56，因此3的最大次幂为3^19 == 1162261467
     * @param n
     * @return
     */
    public boolean isPowerOfThree6(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}

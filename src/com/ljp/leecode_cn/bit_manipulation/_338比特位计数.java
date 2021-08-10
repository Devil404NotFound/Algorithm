package com.ljp.leecode_cn.bit_manipulation;

/** 每日一题 2021.03.03
 * 338. 比特位计数
 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。

 示例 1:

 输入: 2
 输出: [0,1,1]
 示例 2:

 输入: 5
 输出: [0,1,1,2,1,2]
 进阶:

 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 要求算法的空间复杂度为O(n)。
 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。

 执行用时 :
 2 ms, 在所有 Java 提交中击败了81.24%的用户
 内存消耗 :
 43.7 MB, 在所有 Java 提交中击败了5.88%的用户
 */
public class _338比特位计数 {
    /**
     * 官方题解三：动态规划——最低有效位
     * @param num
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了99.95%的用户
    内存消耗：
    42.4 MB, 在所有 Java 提交中击败了81.59%的用户
     */
    public int[] countBits(int num) {
        int[] res = new int[num+1];
        for(int i = 1; i <= num; ++i){
            /*res[i] = res[i>>1];
            if((i & 1) == 1){
                res[i]++;
            }*/
            //优化
            res[i] = res[i>>1] + (i & 1);
        }
        return res;
    }

    /**
     * 每日一题 2021.03.03
     通过Integer内置方法实现
     * @param num
     * @return
    执行用时：
    2 ms, 在所有 Java 提交中击败了60.00%的用户
    内存消耗：
    42.5 MB, 在所有 Java 提交中击败了77.87%的用户
     */
    public int[] countBits2(int num) {
        int[] ans = new int[num + 1];
        for(int i = 0; i <= num; ++i) {
            ans[i] = Integer.bitCount(i);
        }
        return ans;
    }

    /**
     * 官方题解二：动态规划——最高有效位
     * @param num
     * @return
     */
    public int[] countBits3(int num) {
        int[] bits = new int[num + 1];
        int highBit = 0;
        for (int i = 1; i <= num; i++) {
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            bits[i] = bits[i - highBit] + 1;
        }
        return bits;
    }

    /**
     * 官方题解四：动态规划——最低设置位
     * @param num
     * @return
     */
    public int[] countBits4(int num) {
        int[] bits = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            bits[i] = bits[i >> 1] + (i & 1);
        }
        return bits;
    }
}

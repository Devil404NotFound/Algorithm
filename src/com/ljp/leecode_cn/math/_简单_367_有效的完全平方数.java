package com.ljp.leecode_cn.math;

/**
 *
 367. 有效的完全平方数
 给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。

 说明：不要使用任何内置的库函数，如  sqrt。

 示例 1：

 输入：16
 输出：True
 示例 2：

 输入：14
 输出：False
 * @author ljp
 * @date 2020/11/6 15:35
 */
public class _简单_367_有效的完全平方数 {
    /**
     * 超时
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        int i = 1;
        while(i * i < num){
            i++;
        }
        return i * i == num;
    }

    /**二分法：
     *评论区大佬@小小伯纳乌
     * @param num
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    35.5 MB, 在所有 Java 提交中击败了54.52%的用户
     */
    public boolean isPerfectSquare2(int num) {
            int low = 1;
            int high = num;
            while(low <= high){
                int mid = low + (high - low) / 2;
                int t = num / mid;
                if(t < mid){//意味着mid大了，需要减小high
                    high = mid - 1;
                }else if(t > mid){//mid小了，因此需要增大low
                    low = mid + 1;
                }else{//这里t == mid        t <= num / mid
                    if(num % mid == 0){
                        return true;
                    }
                    low = mid + 1;
                }
            }
            return false;
    }

    /**
     * 评论区大佬@@算法练习生：牛顿迭代法
     * @param num
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    35.2 MB, 在所有 Java 提交中击败了88.83%的用户
     */
    public boolean isPerfectSquare3(int num) {
        long r = num;
        while(r * r  > num){
            r = (r + num / r) / 2;
        }
        return r * r == num;
    }
}

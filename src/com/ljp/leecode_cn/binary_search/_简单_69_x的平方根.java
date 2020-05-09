package com.ljp.leecode_cn.binary_search;

/**
 * 69. x 的平方根
 实现 int sqrt(int x) 函数。

 计算并返回 x 的平方根，其中 x 是非负整数。

 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

 示例 1:

 输入: 4
 输出: 2
 示例 2:

 输入: 8
 输出: 2
 说明: 8 的平方根是 2.82842...,
 由于返回类型是整数，小数部分将被舍去。
 */
public class _简单_69_x的平方根 {
    /**
     * 二分法
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        int min = 0;
        int max = x;
        while(max - min > 1){
            int i = (max + min) / 2;
            if(x / i < i){
                max = i;
            }else{
                min = i;
            }
        }
        return min;
    }

    /**
     * 牛顿法
     * y = x^2 - C
     * 求斜率
     * y = 2x
     * 点（xi,xi - C)做一条斜率为2xi的直线，直线方程为
     * y = 2xi(x - xi) + xi^2 - C
     * y = 2xix - (2xi^2 + C)
     *与x轴交点(令y = 0)，得x = xi + C/2xi
     * @param x
     * @return
     */
    public int mySqrt2(int x) {
        if (x == 0) {
            return 0;
        }

        double C = x, x0 = x;
        while (true) {
            double xi = 0.5 * (x0 + C / x0);
            if (Math.abs(x0 - xi) < 1e-7) {
                break;
            }
            x0 = xi;
        }
        return (int)x0;
    }
}

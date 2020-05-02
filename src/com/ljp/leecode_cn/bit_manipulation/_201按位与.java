package com.ljp.leecode_cn.bit_manipulation;

/**
 *201. 数字范围按位与
 给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。

 示例 1:

 输入: [5,7]
 输出: 4
 示例 2:

 输入: [0,1]
 输出: 0
 */
public class _201按位与 {
    public static void main(String[] args) {
        int m = 6;
        int n = 7;
        System.out.println(rangeBitwiseAnd(m,n));
        System.out.println(rangeBitwiseAnd2(m,n));
        System.out.println(rangeBitwiseAnd3(m,n));
    }
    //超时
    public static int rangeBitwiseAnd(int m, int n) {
        int res = m;
        while(m < n){
            res = res & ++m;
        }
        return res;
    }
    //有点乱
    public static int rangeBitwiseAnd2(int m, int n) {
        if(m == 0){
            return 0;
        }
        int res = Integer.MAX_VALUE;
        while(res >= n){
            res >>= 1;
        }
        if(res > m){
            return 0;
        }else{
            res = m;
            while(m < n){
                res &= ++m;
            }
            return res;
        }
    }
    public static int rangeBitwiseAnd3(int m, int n) {
        int zero = 0;
        while(m != n){
            m >>=1;
            n >>=1;
            ++zero;
        }
        return m << zero;
    }
}

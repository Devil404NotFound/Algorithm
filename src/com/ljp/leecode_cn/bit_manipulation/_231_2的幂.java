package com.ljp.leecode_cn.bit_manipulation;

/**
 * 231. 2的幂
 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。

 示例 1:

 输入: 1
 输出: true
 解释: 20 = 1
 示例 2:

 输入: 16
 输出: true
 解释: 24 = 16
 示例 3:

 输入: 218
 输出: false
 */
public class _231_2的幂 {

    //我的方法
    public boolean isPowerOfTwo(int n) {
        if(n <= 0){
            return false;
        }
        int count = 0;
        while(n != 0){
            if((n & 1) == 1){
                ++count;
                if(count > 1){
                    return false;
                }
            }
            n >>= 1;
        }
        return true;
    }
    public boolean isPowerOfTwo2(int n) {
        if(n <= 0){
            return false;
        }
        if((n & (n - 1)) == 0){
            return true;
        }else{
            return false;
        }
    }
}

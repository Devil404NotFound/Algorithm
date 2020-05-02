package com.ljp.leecode_cn.bit_manipulation;

/**
 * 397. 整数替换
 给定一个正整数 n，你可以做如下操作：

 1. 如果 n 是偶数，则用 n / 2替换 n。
 2. 如果 n 是奇数，则可以用 n + 1或n - 1替换 n。
 n 变为 1 所需的最小替换次数是多少？

 示例 1:

 输入:
 8

 输出:
 3

 解释:
 8 -> 4 -> 2 -> 1
 示例 2:

 输入:
 7

 输出:
 4

 解释:
 7 -> 8 -> 4 -> 2 -> 1
 或
 7 -> 6 -> 3 -> 2 -> 1


 思路：
 1.偶数时，直接除2
 2.奇数时，需要尽可能将二进制的1变少，因此当二进制最后2位为01时，n--；最后两位为11时，n++
 3.特殊情况，当n=3时，n--比n++快一步
 */
public class _397整数替换 {
    //第一种，递归调用，需要将n转换成long防止溢出
    //大神二进制解法
    public int integerReplacement(int n) {
        int count = 0;
        while(n != 1){
            //偶数
            if((n & 1) == 0){
                n >>>= 1;
            }else{
                if((n & 2) == 0){
                    --n;
                }else{
                    if(n == 3){
                        count += 2;
                        break;
                    }
                    ++n;
                }
            }
            ++count;
        }
        return count;
    }
}

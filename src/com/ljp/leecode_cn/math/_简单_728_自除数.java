package com.ljp.leecode_cn.math;

import java.util.ArrayList;
import java.util.List;

/**
 * 728. 自除数
 自除数 是指可以被它包含的每一位数除尽的数。

 例如，128 是一个自除数，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。

 还有，自除数不允许包含 0 。

 给定上边界和下边界数字，输出一个列表，列表的元素是边界（含边界）内所有的自除数。

 示例 1：

 输入：
 上边界left = 1, 下边界right = 22
 输出： [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
 注意：

 每个输入参数的边界满足 1 <= left <= right <= 10000。
 通过次数23,263提交次数31,395
 */
public class _简单_728_自除数 {
    /**
     *
     * @param left
     * @param right
     * @return
     * 执行用时：
    3 ms, 在所有 Java 提交中击败了87.01%的用户
    内存消耗：
    37 MB, 在所有 Java 提交中击败了89.82%的用户
     */
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();
        int mod;
        for(int i = left; i <= right; ++i){
            int num = i;
            while(num != 0){
                mod = num % 10;
                if(mod == 0 || i % mod != 0){
                    break;
                }
                num /= 10;
            }
            if(num == 0){
                res.add(i);
            }
        }
        return res;
    }
}

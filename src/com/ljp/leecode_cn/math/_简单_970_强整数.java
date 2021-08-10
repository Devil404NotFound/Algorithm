package com.ljp.leecode_cn.math;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 970. 强整数
 给定两个正整数 x 和 y，如果某一整数等于 x^i + y^j，其中整数 i >= 0 且 j >= 0，那么我们认为该整数是一个强整数。

 返回值小于或等于 bound 的所有强整数组成的列表。

 你可以按任何顺序返回答案。在你的回答中，每个值最多出现一次。



 示例 1：

 输入：x = 2, y = 3, bound = 10
 输出：[2,3,4,5,7,9,10]
 解释：
 2 = 2^0 + 3^0
 3 = 2^1 + 3^0
 4 = 2^0 + 3^1
 5 = 2^1 + 3^1
 7 = 2^2 + 3^1
 9 = 2^3 + 3^0
 10 = 2^0 + 3^2
 示例 2：

 输入：x = 3, y = 5, bound = 15
 输出：[2,4,6,8,10,14]


 提示：

 1 <= x <= 100
 1 <= y <= 100
 0 <= bound <= 10^6
 * @author ljp
 * @date 2020/11/15 17:29
 */
public class _简单_970_强整数 {
    /**
     *
     * @param x
     * @param y
     * @param bound
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    36.3 MB, 在所有 Java 提交中击败了69.95%的用户
     */
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        List<Integer> listX = new ArrayList<>();
        List<Integer> listY = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        int temp = 1;
        if(x != 1){
            while(temp <= bound){
                listX.add(temp);
                temp *= x;
            }
        }else{
            listX.add(1);
        }
        if(y != 1){
            temp = 1;
            while(temp <= bound){
                listY.add(temp);
                temp *= y;
            }
        }else{
            listY.add(1);
        }
        int lenX = listX.size();
        int lenY = listY.size();
        for(int i = 0; i < lenX; i++){
            for(int j = 0; j < lenY; j++){
                int num = listX.get(i) + listY.get(j);
                if(num > bound){
                    break;
                }
                if(set.contains(num)){
                    continue;
                }
                set.add(num);
                ans.add(num);
            }
        }
        return ans;
    }
}

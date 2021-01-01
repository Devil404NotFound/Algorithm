package com.ljp.leecode_cn.greedy;

/** 每日一题 2021.01。01
 * @author ljp
 * @date 2021/1/1 17:01
605. 种花问题
假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。

给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。

示例 1:

输入: flowerbed = [1,0,0,0,1], n = 1
输出: True
示例 2:

输入: flowerbed = [1,0,0,0,1], n = 2
输出: False
注意:

数组内已种好的花不会违反种植规则。
输入的数组长度范围为 [1, 20000]。
n 是非负整数，且不会超过输入数组的大小。
 */
public class _简单_605_种花问题 {
    /**
     * 贪心算法，需要处理头尾是否为0
     * @param flowerbed
     * @param n
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    39.8 MB, 在所有 Java 提交中击败了72.63%的用户
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int lastCount = -1 + (1 ^ flowerbed[0]);//如果第一个元素是0,那就有可能可以种花
        for(int i = 0; i < flowerbed.length; ++i) {
            if(flowerbed[i] == 0) {
                ++lastCount;
            }else{
                int count = lastCount / 2;
                if(count > 0) {
                    n -= count;
                    if(n <= 0) {
                        return true;
                    }
                }
                lastCount = -1;
            }
        }
        //lastCount大于0意味着最后一个数是0，那么这个数也有可能可以种花，因此结果lastCount从0开始统计
        if(lastCount > 0) {
            n -= (lastCount + 1) / 2;
        }
        return n <= 0;
    }
}

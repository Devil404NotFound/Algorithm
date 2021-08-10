package com.ljp.leecode_cn.array;

import java.util.HashMap;
import java.util.Map;

/**每日一题 2021.01.26
 * @author lijunpeng
 * @date 2021/1/26 0:25
1128. 等价多米诺骨牌对的数量
给你一个由一些多米诺骨牌组成的列表 dominoes。

如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。

形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。

在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。



示例：

输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
输出：1


提示：

1 <= dominoes.length <= 40000
1 <= dominoes[i][j] <= 9
 */
public class _简单_1128_等价多米诺骨牌对的数量 {
    
    /**
     *
     * @param dominoes
     * @return
    执行用时：
    19 ms, 在所有 Java 提交中击败了17.94%的用户
    内存消耗：
    48 MB, 在所有 Java 提交中击败了9.75%的用户
     */
    public int numEquivDominoPairs(int[][] dominoes) {
        Map<Integer, Integer> map = new HashMap<>();//建立一个a与b生成的index和该组合出现次数的映射
        int count = 0;
        for(int i = 0; i < dominoes.length; ++i) {
            Integer a = dominoes[i][0], b = dominoes[i][1];
            if(a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            int index = a * 10 + b;
            if(map.containsKey(index)) {
                int num = map.get(index);
                count += num;
                map.put(index, num + 1);
            }else{
                map.put(index, 1);
            }
        }
        return count;
    }

    /**
     * 对方法一优化：利用a*b == b*a 错了，天真
     * @param dominoes
     * @return
     */
    public int numEquivDominoPairs2(int[][] dominoes) {
        Map<Integer, Integer> map = new HashMap<>();//建立一个a与b生成的index和该组合出现次数的映射
        int count = 0;
        for(int i = 0; i < dominoes.length; ++i) {
            Integer a = dominoes[i][0], b = dominoes[i][1];
            int index = a *  b;
            if(map.containsKey(index)) {
                int num = map.get(index);
                count += num;
                map.put(index, num + 1);
            }else{
                map.put(index, 1);
            }
        }
        return count;
    }

    /**
     * 官方题解：二元组表示+计数
     * @param dominoes
     * @return
    执行用时：
    3 ms, 在所有 Java 提交中击败了85.48%的用户
    内存消耗：
    47.6 MB, 在所有 Java 提交中击败了50.81%的用户
     */
    public int numEquivDominoPairs3(int[][] dominoes) {
        int[] num = new int[100];
        int count = 0;
        for(int[] domino : dominoes) {
            int val = domino[0] > domino[1] ? domino[0] * 10 + domino[1] : domino[1] * 10 + domino[0];
            count += num[val];
            num[val]++;
        }
        return count;
    }
}

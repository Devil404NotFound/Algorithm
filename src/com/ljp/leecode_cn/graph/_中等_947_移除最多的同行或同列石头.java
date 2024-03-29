package com.ljp.leecode_cn.graph;

import java.util.HashMap;
import java.util.Map;

/** 每日一题 2021.01.15
 * @author lijunpeng
 * @date 2021/1/15 0:51
947. 移除最多的同行或同列石头
n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。

如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。

给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。



示例 1：

输入：stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
输出：5
解释：一种移除 5 块石头的方法如下所示：
1. 移除石头 [2,2] ，因为它和 [2,1] 同行。
2. 移除石头 [2,1] ，因为它和 [0,1] 同列。
3. 移除石头 [1,2] ，因为它和 [1,0] 同行。
4. 移除石头 [1,0] ，因为它和 [0,0] 同列。
5. 移除石头 [0,1] ，因为它和 [0,0] 同行。
石头 [0,0] 不能移除，因为它没有与另一块石头同行/列。
示例 2：

输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
输出：3
解释：一种移除 3 块石头的方法如下所示：
1. 移除石头 [2,2] ，因为它和 [2,0] 同行。
2. 移除石头 [2,0] ，因为它和 [0,0] 同列。
3. 移除石头 [0,2] ，因为它和 [0,0] 同行。
石头 [0,0] 和 [1,1] 不能移除，因为它们没有与另一块石头同行/列。
示例 3：

输入：stones = [[0,0]]
输出：0
解释：[0,0] 是平面上唯一一块石头，所以不可以移除它。


提示：

1 <= stones.length <= 1000
0 <= xi, yi <= 104
不会有两块石头放在同一个坐标点上
 */
public class _中等_947_移除最多的同行或同列石头 {
    /**
     * 官方题解：并查集
     * @param stones
     * @return
    执行用时：
    9 ms, 在所有 Java 提交中击败了69.49%的用户
    内存消耗：
    39.1 MB, 在所有 Java 提交中击败了19.08%的用户
     */
    public int removeStones(int[][] stones) {
        int n = stones.length;
        UnionFind unionFind = new UnionFind();
        for(int[] stone : stones) {
            int x = stone[0] + 10000;
            int y = stone[1];
            unionFind.union(x, y);
        }
        return n - unionFind.getCount();
    }
    //连通图类（使用Map而不用int数组的原因是1.并不知道stones中行最大值， 2.坐标为离散型，并不连续）
    private class UnionFind {
        private Map<Integer, Integer> parent = new HashMap<>();
        private int count = 0;

        public int getCount() {
            return this.count;
        }
        public int find(int x) {
            if(!parent.containsKey(x)) {
                parent.put(x, x);
                count++;
            } else if(parent.get(x) != x) {
                parent.put(x, find(parent.get(x)));
            }
            return parent.get(x);
        }
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if(rootX != rootY) {
                --count;
                parent.put(rootX, rootY);
            }
        }
    }
}

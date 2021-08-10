package com.ljp.leecode_cn.graph;

import java.util.*;

/** 每日一题 2021.07.25
 * @author lijunpeng
 * @date 2021/7/25 10:18
 * @Description
1743. 从相邻元素对还原数组
存在一个由 n 个不同元素组成的整数数组 nums ，但你已经记不清具体内容。好在你还记得 nums 中的每一对相邻元素。

给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，其中每个 adjacentPairs[i] = [ui, vi] 表示元素 ui 和 vi 在 nums 中相邻。

题目数据保证所有由元素 nums[i] 和 nums[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，存在形式可能是 [nums[i], nums[i+1]] ，也可能是 [nums[i+1], nums[i]] 。这些相邻元素对可以 按任意顺序 出现。

返回 原始数组 nums 。如果存在多种解答，返回 其中任意一个 即可。



示例 1：

输入：adjacentPairs = [[2,1],[3,4],[3,2]]
输出：[1,2,3,4]
解释：数组的所有相邻元素对都在 adjacentPairs 中。
特别要注意的是，adjacentPairs[i] 只表示两个元素相邻，并不保证其 左-右 顺序。
示例 2：

输入：adjacentPairs = [[4,-2],[1,4],[-3,1]]
输出：[-2,4,1,-3]
解释：数组中可能存在负数。
另一种解答是 [-3,1,4,-2] ，也会被视作正确答案。
示例 3：

输入：adjacentPairs = [[100000,-100000]]
输出：[100000,-100000]


提示：

nums.length == n
adjacentPairs.length == n - 1
adjacentPairs[i].length == 2
2 <= n <= 105
-105 <= nums[i], ui, vi <= 105
题目数据保证存在一些以 adjacentPairs 作为元素对的数组 nums
 */
public class _中等_1743_从相邻元素还原数组 {
    /**
     *
     * @param adjacentPairs
     * @return

    执行用时：
    135 ms, 在所有 Java 提交中击败了64.00%的用户
    内存消耗：
    81 MB, 在所有 Java 提交中击败了68.80%的用户*/
    public int[] restoreArray(int[][] adjacentPairs) {
        int n = adjacentPairs.length  + 1;
        int[] ans = new int[n];
        Set<Integer> set = new HashSet<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < adjacentPairs.length; ++i) {
            int x = adjacentPairs[i][0];
            int y = adjacentPairs[i][1];
            //记录边界
            if(!set.add(x)) {
                set.remove(x);
            }
            if(!set.add(y)) {
                set.remove(y);
            }
            if(map.get(x) == null) {
                map.put(x, new ArrayList<>());
            }
            map.get(x).add(y);
            if(map.get(y) == null) {
                map.put(y, new ArrayList<>());
            }
            map.get(y).add(x);
        }
        //找到边界
        Iterator<Integer> it = set.iterator();
        while(it.hasNext()) {
            ans[0] = it.next();
            break;
        }
        set.clear();
        set.add(ans[0]);
        for(int i = 1; i < n; ++i) {
            List<Integer> list = map.get(ans[i - 1]);
            int x = list.get(0);
            int y = list.size() < 2 ? x : list.get(1);
            ans[i] = set.contains(x) ? y : x;
            set.add(ans[i]);
        }
        return ans;
    }

    /**
     * 官方题解：哈希表
     * @param adjacentPairs
     * @return
    执行用时：
    95 ms, 在所有 Java 提交中击败了91.20%的用户
    内存消耗：
    86.8 MB, 在所有 Java 提交中击败了53.60%的用户
     */
    public int[] restoreArray2(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for (int[] adjacentPair : adjacentPairs) {
            map.putIfAbsent(adjacentPair[0], new ArrayList<Integer>());
            map.putIfAbsent(adjacentPair[1], new ArrayList<Integer>());
            map.get(adjacentPair[0]).add(adjacentPair[1]);
            map.get(adjacentPair[1]).add(adjacentPair[0]);
        }

        int n = adjacentPairs.length + 1;
        int[] ret = new int[n];
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int e = entry.getKey();
            List<Integer> adj = entry.getValue();
            if (adj.size() == 1) {
                ret[0] = e;
                break;
            }
        }

        ret[1] = map.get(ret[0]).get(0);
        for (int i = 2; i < n; i++) {
            List<Integer> adj = map.get(ret[i - 1]);
            ret[i] = ret[i - 2] == adj.get(0) ? adj.get(1) : adj.get(0);
        }
        return ret;
    }
}

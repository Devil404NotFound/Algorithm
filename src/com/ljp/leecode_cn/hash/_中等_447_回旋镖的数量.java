package com.ljp.leecode_cn.hash;

import java.util.HashMap;
import java.util.Map;

/**每日一题 2021.09.13
 * @author lijunpeng
 * @date 2021/9/13 23:02
 * @Description
447. 回旋镖的数量
给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。

返回平面上所有回旋镖的数量。


示例 1：

输入：points = [[0,0],[1,0],[2,0]]
输出：2
解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
示例 2：

输入：points = [[1,1],[2,2],[3,3]]
输出：2
示例 3：

输入：points = [[1,1]]
输出：0


提示：

n == points.length
1 <= n <= 500
points[i].length == 2
-104 <= xi, yi <= 104
所有点都 互不相同
 */
public class _中等_447_回旋镖的数量 {
    /** 枚举+哈希
    * @Author lijunpeng
    * @Date 2021/9/13 23:40
    * @Description
    执行用时：
    148 ms, 在所有 Java 提交中击败了52.40%的用户
    内存消耗：
    45.5 MB, 在所有 Java 提交中击败了10.12%的用户
    **/
    public int numberOfBoomerangs(int[][] points) {
        int n = points.length;
        int[][] lens = new int[n][n];
        int ans = 0;
        for (int i = 0; i < points.length; ++i) {
            Map<Integer, Integer> hash = new HashMap<>();
            int x = points[i][0], y = points[i][1];
            for (int j = 0; j < points.length; j++) {
                if(i == j) {
                    continue;
                }
                if(lens[i][j] == 0) {
                    int x1 = points[j][0], y1 = points[j][1];
                    int length = (x - x1) * (x - x1) + (y - y1) * (y - y1);
                    lens[i][j] = lens[j][i] = length;
                }
                hash.put(lens[i][j], hash.getOrDefault(lens[i][j], 0) + 1);
            }
            for(Integer value : hash.values()) {
                ans += value * (value - 1);
            }
        }
        return ans;
    }
    /** 官方题解：枚举+哈希表
    * @Author lijunpeng
    * @Date 2021/9/13 23:41
    * @Description
    **/
    public int numberOfBoomerangs2(int[][] points) {
        int ans = 0;
        for (int[] p : points) {
            Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
            for (int[] q : points) {
                int dis = (p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1]);
                cnt.put(dis, cnt.getOrDefault(dis, 0) + 1);
            }
            for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
                int m = entry.getValue();
                ans += m * (m - 1);
            }
        }
        return ans;
    }
}

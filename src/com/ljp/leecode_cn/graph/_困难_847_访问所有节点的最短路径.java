package com.ljp.leecode_cn.graph;

import java.util.LinkedList;
import java.util.Queue;

/** 每日一题 2021.08.06
 * @author lijunpeng
 * @date 2021/8/6 22:23
 * @Description
847. 访问所有节点的最短路径
存在一个由 n 个节点组成的无向连通图，图中的节点按从 0 到 n - 1 编号。

给你一个数组 graph 表示这个图。其中，graph[i] 是一个列表，由所有与节点 i 直接相连的节点组成。

返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。



示例 1：


输入：graph = [[1,2,3],[0],[0],[0]]
输出：4
解释：一种可能的路径为 [1,0,2,0,3]
示例 2：



输入：graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
输出：4
解释：一种可能的路径为 [0,1,4,2,3]


提示：

n == graph.length
1 <= n <= 12
0 <= graph[i].length < n
graph[i] 不包含 i
如果 graph[a] 包含 b ，那么 graph[b] 也包含 a
输入的图总是连通图
 */
public class _困难_847_访问所有节点的最短路径 {
    /**
     * 官方题解一：广度优先遍历 + 状态压缩
     * @param graph
     * @return
    添加备注

    执行用时：
    9 ms, 在所有 Java 提交中击败了78.31%的用户
    内存消耗：
    38.5 MB, 在所有 Java 提交中击败了52.41%的用户
     */
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] seen = new boolean[n][1 << n]; //记录走过的路径
        //初始化每个节点
        for (int i = 0; i < n; i++) {
            queue.offer(new int[]{i, 1 << i, 0});
            seen[i][1 << i] = true;
        }
        int ans = 0;
        //广度优先遍历
        while(!queue.isEmpty()) {
            int[] tuple = queue.poll();
            //u：节点编号，mask：位对应的节点是否已经经过，dist：到该节点经过的距离
            int u = tuple[0], mask = tuple[1], dist = tuple[2];
            if(mask == (1 << n) - 1) {
                ans = dist;
                break;
            }
            //每个节点的路径都走一遍
            for(int v : graph[u]) {
                int maskV = mask | (1 << v);
                if(!seen[v][maskV]) {
                    queue.offer(new int[]{v, maskV, dist + 1});
                    seen[v][maskV] = true;
                }
            }
        }
        return ans;
    }
}
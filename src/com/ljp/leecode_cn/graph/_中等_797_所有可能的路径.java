package com.ljp.leecode_cn.graph;

import java.util.*;

/** 每日一题 2021.08.25
 * @author lijunpeng
 * @date 2021/8/25 21:40
 * @Description
797. 所有可能的路径
给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序）

二维数组的第 i 个数组中的单元都表示有向图中 i 号节点所能到达的下一些节点，空就是没有下一个结点了。

译者注：有向图是有方向的，即规定了 a→b 你就不能从 b→a 。



示例 1：



输入：graph = [[1,2],[3],[3],[]]
输出：[[0,1,3],[0,2,3]]
解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3
示例 2：



输入：graph = [[4,3,1],[3,2,4],[3],[4],[]]
输出：[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
示例 3：

输入：graph = [[1],[]]
输出：[[0,1]]
示例 4：

输入：graph = [[1,2,3],[2],[3],[]]
输出：[[0,1,2,3],[0,2,3],[0,3]]
示例 5：

输入：graph = [[1,3],[2],[3],[]]
输出：[[0,1,2,3],[0,3]]


提示：

n == graph.length
2 <= n <= 15
0 <= graph[i][j] < n
graph[i][j] != i（即，不存在自环）
graph[i] 中的所有元素 互不相同
保证输入为 有向无环图（DAG）
 */
public class _中等_797_所有可能的路径 {
    /** 广度优先遍历
    * @Author lijunpeng
    * @Date 2021/8/25 22:08
    * @Description
    执行用时：
    10 ms, 在所有 Java 提交中击败了5.23%的用户
    内存消耗：
    40.5 MB, 在所有 Java 提交中击败了29.72%的用户
    **/
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int n = graph.length;
        List<List<Integer>> ansList = new ArrayList<>();
        int[] dp = new int[n];
        Deque<List<Integer>> queue = new LinkedList<>();
        queue.offer(new ArrayList<>());
        queue.peek().add(0);
        while(!queue.isEmpty()) {
            List<Integer> pre = queue.poll();
            int cur = pre.get(pre.size() - 1);
            for(int next : graph[cur]) {
                List<Integer> nextList = new ArrayList<>(pre);
                nextList.add(next);
                queue.offer(nextList);
                if(next == n - 1) {
                    ansList.add(nextList);
                }
            }
        }
        return ansList;
    }
    /** 官方题解：深度优先遍历
    * @Author lijunpeng
    * @Date 2021/8/25 22:28
    * @Description
    执行用时：
    2 ms, 在所有 Java 提交中击败了87.93%的用户
    内存消耗：
    40.2 MB, 在所有 Java 提交中击败了55.34%的用户
    **/
    List<List<Integer>> ans = new ArrayList<>();
    Deque<Integer> stack = new ArrayDeque<>();
    public List<List<Integer>> allPathsSourceTarget2(int[][] graph) {
        stack.offerLast(0);
        dfs(graph, 0, graph.length - 1);
        return ans;
    }
    private void dfs(int[][] graph, int x, int n) {
        if(x == n) {
            ans.add(new ArrayList<>(stack));
            return;
        }
        for(int y : graph[x]) {
            stack.offerLast(y);
            dfs(graph, y, n);
            stack.pollLast();
        }
    }
}

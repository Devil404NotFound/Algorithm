package com.ljp.leecode_cn.graph;

import java.util.Deque;
import java.util.LinkedList;

/**每日一题 2021.01.07
 * @author ljp
 * @date 2021/1/7 21:49
547. 省份数量
有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。

省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。

给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。

返回矩阵中 省份 的数量。



示例 1：


输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
输出：2
示例 2：


输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
输出：3


提示：

1 <= n <= 200
n == isConnected.length
n == isConnected[i].length
isConnected[i][j] 为 1 或 0
isConnected[i][i] == 1
isConnected[i][j] == isConnected[j][i]
 */
public class _中等_547_省份数量 {
    public static void main(String[] args) {
        _中等_547_省份数量 test = new _中等_547_省份数量();
        int[][] isConnected = new int[][]{{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}};
        int count = test.findCircleNum2(isConnected);
        System.out.println(count);
    }

    /**
     * 官方题解一: 深度优先遍历
     * @param isConnected
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了99.49%的用户
    内存消耗：
    39.3 MB, 在所有 Java 提交中击败了74.10%的用户
     */
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int provices = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if(!visited[i]){
                dfs(isConnected, visited, i);
                provices++;
            }
        }
        return provices;
    }
    public void dfs(int[][] isConnected, boolean[] visited, int i) {
        for (int j = 0; j < isConnected[i].length; j++) {
            if(isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(isConnected, visited, j);
            }
        }
    }

    /**
     * 官方题解二： 广度优先遍历
     * @param isConnected
     * @return
    执行用时：
    6 ms, 在所有 Java 提交中击败了20.20%的用户
    内存消耗：
    39.1 MB, 在所有 Java 提交中击败了92.06%的用户
     */
    public int findCircleNum2(int[][] isConnected) {
        int n = isConnected.length;
        Deque<Integer> deque = new LinkedList<>();
        boolean[] visited = new boolean[n];
        int circles = 0;
        for(int i = 0; i < n; ++i) {
            if(!visited[i]) {
                deque.offer(i);
                ++circles;
                while(!deque.isEmpty()) {
                    int j = deque.poll();
                    visited[j] = true;
                    for(int k = 0; k < n; ++k) {
                        if(!visited[k] && isConnected[j][k] == 1) {
                            deque.offer(k);
                        }
                    }
                }
            }
        }
        return circles;
    }

    /**
     * 官方题解三：并查集
     * @param isConnected
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了99.49%的用户
    内存消耗：
    39.5 MB, 在所有 Java 提交中击败了38.65%的用户
     */
    public int findCircleNum3(int[][] isConnected) {
        int circles = 0;
        int n = isConnected.length;
        int[] parent = new int[n];
        //初始化(每个节点都是图）
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        //联合
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if(isConnected[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if(parent[i] == i) {
                ++circles;
            }
        }
        return circles;
    }
    //关联两个节点
    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }
    //查找每个集的根节点
    public int find(int[] parent, int index) {
        if(parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }
}

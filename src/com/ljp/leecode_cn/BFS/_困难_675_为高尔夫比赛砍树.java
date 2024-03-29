package com.ljp.leecode_cn.BFS;

import com.ljp.leecode_cn.util.CommonUtil;

import java.util.*;

/** 2022-05-23每日一题
 * @author lijunpeng
 * @date 2022/5/23 21:40
 * @description
675. 为高尔夫比赛砍树
你被请来给一个要举办高尔夫比赛的树林砍树。树林由一个 m x n 的矩阵表示， 在这个矩阵中：

0 表示障碍，无法触碰
1 表示地面，可以行走
比 1 大的数 表示有树的单元格，可以行走，数值表示树的高度
每一步，你都可以向上、下、左、右四个方向之一移动一个单位，如果你站的地方有一棵树，那么你可以决定是否要砍倒它。

你需要按照树的高度从低向高砍掉所有的树，每砍过一颗树，该单元格的值变为 1（即变为地面）。

你将从 (0, 0) 点开始工作，返回你砍完所有树需要走的最小步数。 如果你无法砍完所有的树，返回 -1 。

可以保证的是，没有两棵树的高度是相同的，并且你至少需要砍倒一棵树。



示例 1：


输入：forest = [[1,2,3],[0,0,4],[7,6,5]]
输出：6
解释：沿着上面的路径，你可以用 6 步，按从最矮到最高的顺序砍掉这些树。
示例 2：


输入：forest = [[1,2,3],[0,0,0],[7,6,5]]
输出：-1
解释：由于中间一行被障碍阻塞，无法访问最下面一行中的树。
示例 3：

输入：forest = [[2,3,4],[0,0,5],[8,7,6]]
输出：6
解释：可以按与示例 1 相同的路径来砍掉所有的树。
(0,0) 位置的树，可以直接砍去，不用算步数。


提示：

m == forest.length
n == forest[i].length
1 <= m, n <= 50
0 <= forest[i][j] <= 109
 **/

public class _困难_675_为高尔夫比赛砍树 {
    /**
    * @Author lijunpeng
    * @Date 2022/5/23 22:36
    * @Description 不通过
    */
    public int cutOffTree(List<List<Integer>> forest) {
        //初始化节点值
        int[][][] node = new int[forest.size()][forest.get(0).size()][3];
        Queue<int[]> deque = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        for(int i = 0; i < forest.size(); ++i) {
            for (int j = 0; j < forest.get(i).size(); j++) {
                node[i][j][0] = i;
                node[i][j][1] = j;
                int value = forest.get(i).get(j);
                node[i][j][2] = value;
                if(value != 0) {
                    deque.offer(node[i][j]);
                }
            }
        }
        int count = deque.size() - 1;
        if(count == 0) {
            return -1;
        }
        int[] last = deque.poll();
        while(!deque.isEmpty()) {
            int[] cur = deque.poll();
            if(!isNext(last, cur)) {
                return -1;
            }
            last = cur;
        }
        return count;
    }
    private boolean isNext(int[] last, int[] cur) {
        int lastX = last[0], lastY = last[1];
        int curX = cur[0], curY = cur[1];
        if(lastX == curX && Math.abs(lastY - curY) == 1) {
            return true;
        }
        if(Math.abs(lastX - curX) == 1 && lastY == curY) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] array = new int[][]{{1,2,3},{0,0,4},{7,6,5}};
        List<List<Integer>> list = new ArrayList<>();
        for (int[] ints : array) {
            List<Integer> cur = CommonUtil.ArrayToList(ints);
            list.add(cur);
        }
        _困难_675_为高尔夫比赛砍树 test = new _困难_675_为高尔夫比赛砍树();
        int count = test.cutOffTree(list);
        System.out.println(count);
    }


}

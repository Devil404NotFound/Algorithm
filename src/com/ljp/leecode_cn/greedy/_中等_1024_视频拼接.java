package com.ljp.leecode_cn.greedy;

import java.util.Arrays;
import java.util.Comparator;

/** 每日一题 2020.10.24
 1024. 视频拼接
 你将会获得一系列视频片段，这些片段来自于一项持续时长为 T 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。

 视频片段 clips[i] 都用区间进行表示：开始于 clips[i][0] 并于 clips[i][1] 结束。我们甚至可以对这些片段自由地再剪辑，例如片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。

 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）。返回所需片段的最小数目，如果无法完成该任务，则返回 -1 。



 示例 1：

 输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
 输出：3
 解释：
 我们选中 [0,2], [8,10], [1,9] 这三个片段。
 然后，按下面的方案重制比赛片段：
 将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
 现在我们手上有 [0,2] + [2,8] + [8,10]，而这些涵盖了整场比赛 [0, 10]。
 示例 2：

 输入：clips = [[0,1],[1,2]], T = 5
 输出：-1
 解释：
 我们无法只用 [0,1] 和 [1,2] 覆盖 [0,5] 的整个过程。
 示例 3：

 输入：clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], T = 9
 输出：3
 解释：
 我们选取片段 [0,4], [4,7] 和 [6,9] 。
 示例 4：

 输入：clips = [[0,4],[2,8]], T = 5
 输出：2
 解释：
 注意，你可能录制超过比赛结束时间的视频。


 提示：

 1 <= clips.length <= 100
 0 <= clips[i][0] <= clips[i][1] <= 100
 0 <= T <= 100
 * @author ljp
 * @date 2020/10/24 10:09
 */
public class _中等_1024_视频拼接 {
    public static void main(String[] args) {
//        int[][] clips = {{0,1},{6,8},{0,2},{5,6},{0,4},{0,3},{6,7},{1,3},{4,7},{1,4},{2,5},{2,6},{3,4},{4,5},{5,7},{6,9}};
//        int[][] clips = {{0,2},{4,6},{8,10},{1,9},{1,5},{5,9},{9,10}};
        int[][] clips = {{0, 4}, {2, 8}};
        new _中等_1024_视频拼接().videoStitching(clips, 5);
    }

    /**
     * 贪心算法
     * @param clips
     * @param T
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了46.14%的用户
    内存消耗：
    35.7 MB, 在所有 Java 提交中击败了98.63%的用户
     */
    public int videoStitching(int[][] clips, int T) {
        //按照第一个元素升序排序
        Arrays.sort(clips, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]){
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });
        int start, end, count;
        end = 0;
        start = 0;
        count = 0;
        //需要考虑中途end >= T的情况，end >= T后就必继续遍历了
        for (int i = 0; i < clips.length && end < T; i++) {
            //满足起点的条件下尽可能覆盖多的范围
            if(clips[i][0] <= start){
                end = Math.max(end, clips[i][1]);
            }else if(clips[i][0] > end){//开始的位置都无法覆盖上一个结束点
                return -1;
            }else{
                start = end;
                end = clips[i][1];
                count++;
            }
        }
        if(end < T){
            return -1;
        }
        //最后一个没统计上
        return count+1;
    }

    /**
     * 官方题解一：动态规划
     * @param clips
     * @param T
     * @return
    执行用时：
    2 ms, 在所有 Java 提交中击败了22.95%的用户
    内存消耗：
    36 MB, 在所有 Java 提交中击败了93.17%的用户
     */
    public int videoStitching2(int[][] clips, int T) {
        int[] dp = new int[T + 1]; //表示0-T这一段需要最少的片段数
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        for (int i = 1; i <= T; i++) {
            for (int j = 0; j < clips.length; j++) {
                if(clips[j][0] < i && i <= clips[j][1]){
                    dp[i] = Math.max(dp[i], dp[clips[j][0]] + 1);
                }
            }
        }
        return dp[T];
    }

    /**
     * 官方题解二：贪心算法 O(N+T)
     * @param clips
     * @param T
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    35.5 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int videoStitching3(int[][] clips, int T) {
        int[] maxN = new int[T]; //记录每个起点的最长距离
        int last = 0, pre = 0, ret = 0;
        for (int i = 0; i < clips.length; i++) {
            if(clips[i][0] < T){
                maxN[clips[i][0]] = Math.max(maxN[clips[i][0]], clips[i][1]);
            }
        }
        for (int i = 0; i < T; i++) {
            last = Math.max(last, maxN[i]);
            //最远距离到i，i到i+1就没有了
            if(i == last){
                return -1;
            }
            if(i == pre){
                ret++;
                pre = last;
            }
        }
        return ret;
    }
}

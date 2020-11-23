package com.ljp.leecode_cn.array;

import java.util.ArrayList;
import java.util.List;

/** 每日一题 2020.11.04
 57. 插入区间
 给出一个无重叠的 ，按照区间起始端点排序的区间列表。

 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。



 示例 1：

 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 输出：[[1,5],[6,9]]
 示例 2：

 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 输出：[[1,2],[3,10],[12,16]]
 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。


 注意：输入类型已在 2019 年 4 月 15 日更改。请重置为默认代码定义以获取新的方法签名。
 * @author ljp
 * @date 2020/11/4 11:39
 */
public class _困难_57_插入区间 {
    /**
     *
     * @param intervals
     * @param newInterval
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了99.65%的用户
    内存消耗：
    40.8 MB, 在所有 Java 提交中击败了75.27%的用户
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int len = intervals.length;
        if(len == 0){
            return new int[][]{newInterval};
        }
        int[][] ans;
        int i;
        //插入左边
        if(intervals[0][0] > newInterval[1]){
            ans = new int[len + 1][2];
            ans[0] = newInterval;
            for (i = 0; i < len; i++) {
                ans[i + 1] = intervals[i];
            }
            return ans;
        }
        //插入右边
        if(intervals[len - 1][1] < newInterval[0]){
            ans = new int[len + 1][2];
            for (i = 0; i < len; i++) {
                ans[i] = intervals[i];
            }
            ans[len] = newInterval;
            return ans;
        }
        //插入中间
        List<int[]> list = new ArrayList<>();
        i = 0;
        //添加交叉前
        while(i < len && intervals[i][1] < newInterval[0]){
            list.add(intervals[i++]);
        }
        //选取交叉起点
        int left = Math.min(intervals[i][0], newInterval[0]);
        //跳过交叉区间
        while(i < len && intervals[i][0] <= newInterval[1]){
            i++;
        }
        //选取交叉终点
        int right = Math.max(intervals[i - 1][1], newInterval[1]);
        list.add(new int[]{left, right});
        //添加交叉后
        while(i < len){
            list.add(intervals[i]);
            i++;
        }
        return list.toArray(new int[list.size()][2]);
    }

    /**
     * 官方题解 模拟
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert2(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        List<int[]> ansList = new ArrayList<int[]>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                // 在插入区间的右侧且无交集
                if (!placed) {
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(interval);
            } else if (interval[1] < left) {
                // 在插入区间的左侧且无交集
                ansList.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); ++i) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }
}

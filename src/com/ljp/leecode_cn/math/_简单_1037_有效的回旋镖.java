package com.ljp.leecode_cn.math;

/**
 1037. 有效的回旋镖
 回旋镖定义为一组三个点，这些点各不相同且不在一条直线上。

 给出平面上三个点组成的列表，判断这些点是否可以构成回旋镖。



 示例 1：

 输入：[[1,1],[2,3],[3,2]]
 输出：true
 示例 2：

 输入：[[1,1],[2,2],[3,3]]
 输出：false


 提示：

 points.length == 3
 points[i].length == 2
 0 <= points[i][j] <= 100

 * @author ljp
 * @date 2020/11/6 14:47
 */
public class _简单_1037_有效的回旋镖 {
    /**
     *
     * @param points
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    35.7 MB, 在所有 Java 提交中击败了94.90%的用户
     */
    public boolean isBoomerang(int[][] points) {
        return (points[0][0] - points[1][0]) * (points[1][1] - points[2][1]) != (points[1][0] - points[2][0]) * (points[0][1] - points[1][1]);
    }
}

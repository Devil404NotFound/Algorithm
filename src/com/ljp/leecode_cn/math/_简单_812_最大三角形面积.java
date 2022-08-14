package com.ljp.leecode_cn.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 每日一题 2022.05.15
 * 812. 最大三角形面积
 给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。

 示例:
 输入: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
 输出: 2
 解释:
 这五个点如下图所示。组成的橙色三角形是最大的，面积为2。


 注意:

 3 <= points.length <= 50.
 不存在重复的点。
 -50 <= points[i][j] <= 50.
 结果误差值在 10^-6 以内都认为是正确答案。
 */
public class _简单_812_最大三角形面积 {
    /** 2020/08/22 18:27
     * 暴力+海伦公式
     * @param points
     * @return
     * 执行用时：
    10 ms, 在所有 Java 提交中击败了21.36%的用户
    内存消耗：
    37.6 MB, 在所有 Java 提交中击败了41.89%的用户
     */
    public double largestTriangleArea(int[][] points) {
        double maxArea = 0;
        for(int i = 0; i < points.length -2; ++i){
            for(int j = i + 1; j < points.length - 1; ++j){
                double a = getLength(points[i],points[j]);
                for(int k = j + 1; k < points.length; ++k){
                    double b = getLength(points[i], points[k]);
                    double c = getLength(points[j], points[k]);
                    if(a < b + c && a > Math.abs(b - c)){
                        maxArea = Math.max(maxArea, getArea(a, b, c));
                    }
                }
            }
        }
        return maxArea;
    }
    //计算两点的距离
    private double getLength(int[]a, int[] b){
        return Math.sqrt((double)((a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1])));
    }
    //计算三角形面积
    private double getArea(double a, double b, double c){
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    /**
     * 官方题解：鞋带公式
     * @param points
     * @return
     */
    public double largestTriangleArea2(int[][] points) {
        int N = points.length;
        double ans = 0;
        for (int i = 0; i < N; ++i)
            for (int j = i+1; j < N; ++j)
                for (int k = j+1; k < N; ++k)
                    ans = Math.max(ans, area(points[i], points[j], points[k]));
        return ans;
    }

    public double area(int[] P, int[] Q, int[] R) {
        return 0.5 * Math.abs(P[0]*Q[1] + Q[0]*R[1] + R[0]*P[1]
                -P[1]*Q[0] - Q[1]*R[0] - R[1]*P[0]);
    }

    /** 官方题解二：凸包
     * @Author lijunpeng
     * @Date 2022/5/15 11:52
     * @Description
     */
    public double largestTriangleArea3(int[][] points) {
        int[][] convexHull = getConvexHull(points);
        int n = convexHull.length;
        double ret = 0.0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1, k = i + 2; j + 1 < n; j++) {
                while (k + 1 < n) {
                    double curArea = triangleArea(convexHull[i][0], convexHull[i][1], convexHull[j][0], convexHull[j][1], convexHull[k][0], convexHull[k][1]);
                    double nextArea = triangleArea(convexHull[i][0], convexHull[i][1], convexHull[j][0], convexHull[j][1], convexHull[k + 1][0], convexHull[k + 1][1]);
                    if (curArea >= nextArea) {
                        break;
                    }
                    k++;
                }
                double area = triangleArea(convexHull[i][0], convexHull[i][1], convexHull[j][0], convexHull[j][1], convexHull[k][0], convexHull[k][1]);
                ret = Math.max(ret, area);
            }
        }
        return ret;
    }
    public int[][] getConvexHull(int[][] points) {
        int n = points.length;
        if (n < 4) {
            return points;
        }
        /* 按照 x 大小进行排序，如果 x 相同，则按照 y 的大小进行排序 */
        Arrays.sort(points, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        List<int[]> hull = new ArrayList<int[]>();
        /* 求出凸包的下半部分 */
        for (int i = 0; i < n; i++) {
            while (hull.size() > 1 && cross(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) <= 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(points[i]);
        }
        int m = hull.size();
        /* 求出凸包的上半部分 */
        for (int i = n - 2; i >= 0; i--) {
            while (hull.size() > m && cross(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) <= 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(points[i]);
        }
        /* hull[0] 同时参与凸包的上半部分检测，因此需去掉重复的 hull[0] */
        hull.remove(hull.size() - 1);
        m = hull.size();
        int[][] hullArr = new int[m][];
        for (int i = 0; i < m; i++) {
            hullArr[i] = hull.get(i);
        }
        return hullArr;
    }

    public double triangleArea(int x1, int y1, int x2, int y2, int x3, int y3) {
        return 0.5 * Math.abs(x1 * y2 + x2 * y3 + x3 * y1 - x1 * y3 - x2 * y1 - x3 * y2);
    }

    public int cross(int[] p, int[] q, int[] r) {
        return (q[0] - p[0]) * (r[1] - q[1]) - (q[1] - p[1]) * (r[0] - q[0]);
    }
}

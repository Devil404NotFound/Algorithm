package com.ljp.leecode_cn.math;

/**
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
    /**
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
            for(int j = i; j < points.length - 1; ++j){
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
}

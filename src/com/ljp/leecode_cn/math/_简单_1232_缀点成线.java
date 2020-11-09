package com.ljp.leecode_cn.math;

/**
 *
 1232. 缀点成线
 在一个 XY 坐标系中有一些点，我们用数组 coordinates 来分别记录它们的坐标，其中 coordinates[i] = [x, y] 表示横坐标为 x、纵坐标为 y 的点。

 请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 true，否则请返回 false。



 示例 1：



 输入：coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
 输出：true
 示例 2：



 输入：coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
 输出：false


 提示：

 2 <= coordinates.length <= 1000
 coordinates[i].length == 2
 -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
 coordinates 中不含重复的点
 * @author ljp
 * @date 2020/11/5 22:47
 */
public class _简单_1232_缀点成线 {
    public static void main(String[] args) {
        int[][] coordinates = new int[][]{{4, 2}, {2, 1}, {6, 3}};
        boolean ans = new _简单_1232_缀点成线().checkStraightLine(coordinates);
        System.out.println(ans);
    }

    /**
     * 利用公式 y = kx + c （要区分斜率k为0的情况）
     * @param coordinates
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    38.3 MB, 在所有 Java 提交中击败了83.17%的用户
     */
    public boolean checkStraightLine(int[][] coordinates) {
        double EPSILON = 1e-6;
        if(coordinates.length <= 2){
            return true;
        }
        int y1 = coordinates[0][1];
        int x1 = coordinates[0][0];
        int y2 = coordinates[1][1];
        int x2 = coordinates[1][0];
        if(x1 == x2){
            for(int i = 2; i< coordinates.length; i++){
                int x = coordinates[i][0];
                if(x != x1){
                    return false;
                }
            }
            return true;
        }
        double a = 1.0 * (y1 - y2) / (x1 - x2);//注意这里要先乘1.0转换为double类型，否则会丧失小数部分
        double c = coordinates[0][1] - a * coordinates[0][0];
        for(int i = 2; i < coordinates.length; i++){
            int x = coordinates[i][0];
            int y = coordinates[i][1];
            if(Math.abs(y - a * x - c) > EPSILON){
                return false;
            }
        }
        return true;
    }
}

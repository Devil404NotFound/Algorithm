package com.ljp.leecode_cn.greedy;

import java.util.HashSet;
import java.util.Set;

/**
 * 874. 模拟行走机器人
 机器人在一个无限大小的网格上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令：

 -2：向左转 90 度
 -1：向右转 90 度
 1 <= x <= 9：向前移动 x 个单位长度
 在网格上有一些格子被视为障碍物。

 第 i 个障碍物位于网格点  (obstacles[i][0], obstacles[i][1])

 机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，但仍然可以继续该路线的其余部分。

 返回从原点到机器人的最大欧式距离的平方。



 示例 1：

 输入: commands = [4,-1,3], obstacles = []
 输出: 25
 解释: 机器人将会到达 (3, 4)
 示例 2：

 输入: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
 输出: 65
 解释: 机器人在左转走到 (1, 8) 之前将被困在 (1, 4) 处


 提示：

 0 <= commands.length <= 10000
 0 <= obstacles.length <= 10000
 -30000 <= obstacle[i][0] <= 30000
 -30000 <= obstacle[i][1] <= 30000
 答案保证小于 2 ^ 31
 */
public class _简单_874_模拟行走机器人 {
    public static void main(String[] args) {
        int[] commands = {4,-1,4,-2,4};
        int[][] obstacles = new int[1][];
        obstacles[0] = new int[]{2,4};
        System.out.println(robotSim(commands, obstacles));
    }

    /**
     *
     * @param commands
     * @param obstacles
     * @return
     * 执行用时 :
    13 ms, 在所有 Java 提交中击败了98.76%的用户
    内存消耗 :
    45.5 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public static int robotSim(int[] commands, int[][] obstacles) {
        int max = 0;
        Set<Obstacle> set = new HashSet<>();
        for (int i = 0; i < obstacles.length; i++) {
            set.add(new Obstacle(obstacles[i][0], obstacles[i][1]));
        }
        int x = 0, y = 0;
        int dircetion = 0;//0,上；1，右，2，下，3，左
        for (int i = 0; i < commands.length; i++) {
            if(commands[i] == -1){//向右
                dircetion = (dircetion + 1) % 4;
            }else if(commands[i] == -2){//向右
                dircetion = (dircetion - 1);
                if(dircetion < 0){
                    dircetion += 4;
                }
            }else{
                switch (dircetion){
                    case 0://上
                        for (int j = 1; j <= commands[i]; j++) {
                            if(set.contains(new Obstacle(x, y + 1))){
                                break;
                            }else{
                                y++;
                            }
                        }
                        break;
                    case 1://右
                        for (int j = 1; j <= commands[i]; j++) {
                            if(set.contains(new Obstacle(x + 1, y))){
                                break;
                            }else{
                                x++;
                            }
                        }
                        break;
                    case 2://下
                        for (int j = 1; j <= commands[i]; j++) {
                            if(set.contains(new Obstacle(x, y - 1))){
                                break;
                            }else{
                                y--;
                            }
                        }
                        break;
                    case 3://左
                        for (int j = 1; j <= commands[i]; j++) {
                            if(set.contains(new Obstacle(x - 1, y))){
                                break;
                            }else{
                                x--;
                            }
                        }
                        break;
                }
                int num = x * x + y * y;
                if(max < num){
                    max = num;
                }
            }
        }
        return max;
    }
    static class Obstacle{
        int x;
        int y;

        public Obstacle(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(x) + Integer.hashCode(y);
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj){
                return true;
            }
            if(!(obj instanceof Obstacle)){
                return false;
            }
            Obstacle o2 = (Obstacle)obj;
            return this.x == o2.x && this.y == o2.y;
        }
    }
}

package com.ljp.leecode_cn.math;

/**每日一题 2023.04.11
 * @author lijunpeng
 * @date 2023-04-11 22:11
 * @Description
1041. 困于环中的机器人
在无限的平面上，机器人最初位于 (0, 0) 处，面朝北方。注意:

北方向 是y轴的正方向。
南方向 是y轴的负方向。
东方向 是x轴的正方向。
西方向 是x轴的负方向。
机器人可以接受下列三条指令之一：

"G"：直走 1 个单位
"L"：左转 90 度
"R"：右转 90 度
机器人按顺序执行指令 instructions，并一直重复它们。
只有在平面中存在环使得机器人永远无法离开时，返回 true。否则，返回 false。

示例 1：
输入：instructions = "GGLLGG"
输出：true
解释：机器人最初在(0,0)处，面向北方。
“G”:移动一步。位置:(0,1)方向:北。
“G”:移动一步。位置:(0,2).方向:北。
“L”:逆时针旋转90度。位置:(0,2).方向:西。
“L”:逆时针旋转90度。位置:(0,2)方向:南。
“G”:移动一步。位置:(0,1)方向:南。
“G”:移动一步。位置:(0,0)方向:南。
重复指令，机器人进入循环:(0,0)——>(0,1)——>(0,2)——>(0,1)——>(0,0)。
在此基础上，我们返回true。

示例 2：
输入：instructions = "GG"
输出：false
解释：机器人最初在(0,0)处，面向北方。
“G”:移动一步。位置:(0,1)方向:北。
“G”:移动一步。位置:(0,2).方向:北。
重复这些指示，继续朝北前进，不会进入循环。
在此基础上，返回false。
示例 3：

输入：instructions = "GL"
输出：true
解释：机器人最初在(0,0)处，面向北方。
“G”:移动一步。位置:(0,1)方向:北。
“L”:逆时针旋转90度。位置:(0,1).方向:西。
“G”:移动一步。位置:(- 1,1)方向:西。
“L”:逆时针旋转90度。位置:(- 1,1)方向:南。
“G”:移动一步。位置:(- 1,0)方向:南。
“L”:逆时针旋转90度。位置:(- 1,0)方向:东方。
“G”:移动一步。位置:(0,0)方向:东方。
“L”:逆时针旋转90度。位置:(0,0)方向:北。
重复指令，机器人进入循环:(0,0)——>(0,1)——>(- 1,1)——>(- 1,0)——>(0,0)。
在此基础上，我们返回true。

提示：
1 <= instructions.length <= 100
instructions[i] 仅包含 'G', 'L', 'R'
 */
public class _中等_1041_困于环中的机器人 {
    public static void main(String[] args) {
        _中等_1041_困于环中的机器人 test = new _中等_1041_困于环中的机器人();
        String instrcutions = "GGLLGG";
        boolean robotBounded = test.isRobotBounded(instrcutions);
        System.out.println(robotBounded);
    }

    /**、
     * 题解一，循环4次
     执行用时：
     0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：
     39.4 MB, 在所有 Java 提交中击败了60.63%的用户
     */
    public boolean isRobotBounded(String instructions) {
        int x = 0;
        int y = 0;
        int[][] dict = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
        int d = 0;
        for(int i = 0; i < 4; ++i) {
            for(char ch : instructions.toCharArray()) {
                switch (ch) {
                    case 'G':
                        x += dict[d][0];
                        y += dict[d][1];
                        break;
                    case 'L':
                        d = ++d % 4;
                        break;
                    case 'R':
                        d = (--d + 4) % 4;
                        break;
                }
            }
            if(x == 0 && y == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 官方题解：模拟
     如果最在原点，一定会重复
     如果不在原点，不管使东、南、西方向，循环几次后，都会不断重复
     机器人想要摆脱循环，在一串指令之后的状态，必须是不位于原点且方向朝北。
     */
    public boolean isRobotBounded2(String instructions) {
        int[][] direc = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int direcIndex = 0;
        int x = 0, y = 0;
        int n = instructions.length();
        for (int idx = 0; idx < n; idx++) {
            char instruction = instructions.charAt(idx);
            if (instruction == 'G') {
                x += direc[direcIndex][0];
                y += direc[direcIndex][1];
            } else if (instruction == 'L') {
                direcIndex += 3;
                direcIndex %= 4;
            } else {
                direcIndex++;
                direcIndex %= 4;
            }
        }
        return direcIndex != 0 || (x == 0 && y == 0);
    }
}

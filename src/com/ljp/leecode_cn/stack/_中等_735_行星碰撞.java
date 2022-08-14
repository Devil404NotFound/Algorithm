package com.ljp.leecode_cn.stack;

import org.lanqiao.Utils.Util;

import java.util.ArrayDeque;
import java.util.Deque;

/** 每日一题 2022.07.13
 * @author lijunpeng
 * @date 2022/7/13 20:34
 * @description
735. 行星碰撞
给定一个整数数组 asteroids，表示在同一行的行星。

对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。

找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。



示例 1：

输入：asteroids = [5,10,-5]
输出：[5,10]
解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。
示例 2：

输入：asteroids = [8,-8]
输出：[]
解释：8 和 -8 碰撞后，两者都发生爆炸。
示例 3：

输入：asteroids = [10,2,-5]
输出：[10]
解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。


提示：

2 <= asteroids.length <= 104
-1000 <= asteroids[i] <= 1000
asteroids[i] != 0

 **/

public class _中等_735_行星碰撞 {
    public static void main(String[] args) {
        _中等_735_行星碰撞 test = new _中等_735_行星碰撞();
        int[] asteroids = new int[]{-2,1,-1,-2};
        int[] ans = test.asteroidCollision(asteroids);
        Util.print(ans);
    }
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> deque = new ArrayDeque<>();
        for(int asteroid : asteroids) {
            if(asteroid > 0) {
                deque.push(asteroid);
            }else {
                int absAsteroid = -asteroid;
                //所有比asteroid小的行星都爆炸
                while(!deque.isEmpty() && deque.peek() > 0 && deque.peek() < absAsteroid) {
                    deque.pop();
                }
                //相等质量的同时爆炸
                if(!deque.isEmpty() && deque.peek() == absAsteroid) {
                    deque.pop();
                    continue;
                }
                //遇到比asteroid大的行星，自身爆炸
                if(!deque.isEmpty() && deque.peek() > absAsteroid) {
                    continue;
                }
                //前面没有行星，或者都是往左移动的
                deque.push(asteroid);
            }
        }
        int[] ans = new int[deque.size()];
        for(int i = ans.length - 1; i >= 0; --i) {
            ans[i] = deque.pop();
        }
        return ans;
    }
}

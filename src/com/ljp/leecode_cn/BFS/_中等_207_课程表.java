package com.ljp.leecode_cn.BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 207. 课程表
 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。

 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]

 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？



 示例 1:

 输入: 2, [[1,0]]
 输出: true
 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 示例 2:

 输入: 2, [[1,0],[0,1]]
 输出: false
 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。


 提示：

 输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
 你可以假定输入的先决条件中没有重复的边。
 1 <= numCourses <= 10^5

 */
public class _中等_207_课程表 {
    //广度优先搜索+拓扑排序
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] edges = new ArrayList[numCourses];//存储图
        int[] inedge = new int[numCourses];//存储入边
        Queue<Integer> queue = new LinkedList<>();
        //创建邻接表
        for (int i = 0; i < prerequisites.length; i++) {
            if(edges[prerequisites[i][1]] == null){
                edges[prerequisites[i][1]] = new ArrayList<>();
            }
            edges[prerequisites[i][1]].add(prerequisites[i][0]);
            inedge[prerequisites[i][0]]++;
        }
        //将没有前置条件的课程表入队列（入度为0）
        for (int i = 0; i < numCourses; i++) {
            if(inedge[i] == 0){
                queue.offer(i);
            }
        }
        //广度优先搜索
        int scheduleCourses = 0;
        while(!queue.isEmpty()){
            scheduleCourses++;
            int currentCourse = queue.poll();
            if(edges[currentCourse] != null){
                for(int postCourse : edges[currentCourse]){
                    inedge[postCourse]--;
                    if(inedge[postCourse] == 0){
                        queue.offer(postCourse);
                    }
                }
            }
        }
        return scheduleCourses == numCourses;
    }
}

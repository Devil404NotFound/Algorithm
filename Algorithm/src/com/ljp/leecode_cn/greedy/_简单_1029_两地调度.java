package com.ljp.leecode_cn.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1029. 两地调度
 公司计划面试 2N 人。第 i 人飞往 A 市的费用为 costs[i][0]，飞往 B 市的费用为 costs[i][1]。

 返回将每个人都飞到某座城市的最低费用，要求每个城市都有 N 人抵达。



 示例：

 输入：[[10,20],[30,200],[400,50],[30,20]]
 输出：110
 解释：
 第一个人去 A 市，费用为 10。
 第二个人去 A 市，费用为 30。
 第三个人去 B 市，费用为 50。
 第四个人去 B 市，费用为 20。

 最低总费用为 10 + 30 + 50 + 20 = 110，每个城市都有一半的人在面试。


 提示：

 1 <= costs.length <= 100
 costs.length 为偶数
 1 <= costs[i][0], costs[i][1] <= 1000
 */
public class _简单_1029_两地调度 {
    public static void main(String[] args) {
        int[][] costs = {{259,770},{448,54},{926,667},{184,139},{840,118},{577,469}};
        System.out.println(twoCitySchedCost2(costs));
    }

    /**
     *
     * @param costs
     * @return
     * 执行用时 :
    2 ms, 在所有 Java 提交中击败了67.07%的用户
    内存消耗 :
    37.9 MB, 在所有 Java 提交中击败了66.67%的用户
     */
    public static int twoCitySchedCost(int[][] costs) {
        int sum = 0;
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Math.abs(o2[0] - o2[1]) - Math.abs(o1[0] - o1[1]);
            }
        });
        int index1 = 0, index2 = 0;
        for (int i = 0; i < costs.length; i++) {
            if(costs[i][0] < costs[i][1]){
                sum += costs[i][0];
                index1++;
            }else{
                sum += costs[i][1];
                index2++;
            }
            if(index1 * 2 == costs.length){
                while(++i < costs.length){
                    sum += costs[i][1];
                }
                break;
            }
            if(index2 * 2 == costs.length){
                while(++i < costs.length){
                    sum += costs[i][0];
                }
            }
        }
        return sum;
    }

    /**
     * 优化
     * @param costs
     * @return
     * 执行用时 :
    2 ms, 在所有 Java 提交中击败了67.07%的用户
    内存消耗 :
    38 MB, 在所有 Java 提交中击败了66.67%
     */
    public static int twoCitySchedCost2(int[][] costs) {
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                //根据costs[i][0] - costs[i][1]大小排序
                return (o1[0] - o1[1]) - (o2[0] - o2[1]);
            }
        });
        int sum = 0;
        int i = 0;
        int mid = costs.length >> 1;
        //前一半去A
        while(i < mid){
            sum += costs[i][0];
            i++;
        }
        //后一半去B
        while(i < costs.length){
            sum += costs[i][1];
            i++;
        }
        return sum;
    }
}

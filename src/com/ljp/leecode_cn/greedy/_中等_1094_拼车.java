package com.ljp.leecode_cn.greedy;

public class _中等_1094_拼车 {
    public static void main(String[] args) {
        int[][] trips = {
                {9,3,4},
                {9,1,7},
                {4,2,4},
                {7,4,5}
        };
        int capacity = 23;
        System.out.println(carPooling(trips, capacity));
    }

    /**
     *
     * @param trips
     * @param capacity
     * @return
     * 执行用时 :
    1 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗 :
    39.5 MB, 在所有 Java 提交中击败了77.78%的用户
     */
    public static boolean carPooling(int[][] trips, int capacity) {
        if(trips.length == 0){
            return true;
        }
        //找出最大值（最后一个下车的地点）
        int max = 0;
        for(int i = 0; i < trips.length; i++){
            if(max < trips[i][2]){
                max = trips[i][2];
            }
        }
        int[] start = new int[max + 1];//记录哪个点有多少人上车
        int[] end = new int[max + 1];//记录哪个点有多少人下车
        for(int i = 0; i < trips.length; i++){
            //此处为累加！
            start[trips[i][1]] += trips[i][0];
            end[trips[i][2]] += trips[i][0];
        }
        //模拟上下车
        int count = 0;//车里的人数
        for(int i = 0; i < max; i++){
            count -= end[i];
            count += start[i];
            if(count > capacity){
                return false;
            }
        }
        return true;
    }
}

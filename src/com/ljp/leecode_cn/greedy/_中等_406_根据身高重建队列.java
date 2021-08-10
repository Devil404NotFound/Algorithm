package com.ljp.leecode_cn.greedy;

import org.lanqiao.Utils.Util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/** 每日一题 2020.11.16
 * 406. 根据身高重建队列
 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。

 注意：
 总人数少于1100人。

 示例

 输入:
 [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

 输出:
 [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
public class _中等_406_根据身高重建队列 {
    public static void main(String[] args) {
//        int[][] people = {{7,0}, {4,4}, {7,1}, {5,2}, {6,1}, {5,0}};
//        int[][] people = {{0,0},{6,0},{1,1},{5,1},{5,2},{4,3},{7,0},{6,2},{6,3},{5,5}};
        int[][] people = {{8,2},{4,1},{0,3},{3,2},{8,1},{4,0},{7,0},{6,2},{8,0},{4,7}};
        _中等_406_根据身高重建队列 test = new _中等_406_根据身高重建队列();
        int[][] ans = test.reconstructQueue5(people);
        Util.print(ans);
    }

    /**
     *
     * @param people
     * @return
    执行用时：
    17 ms, 在所有 Java 提交中击败了12.68%的用户
    内存消耗：
    39.6 MB, 在所有 Java 提交中击败了69.11%的用户
     */
    public static int[][] reconstructQueue(int[][] people) {
        //按照身高降序、人数升序排序
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o2[0] - o1[0];
            }
        });
        for (int i = 1; i < people.length; i++) {
            int num = people[i][1];
            int[] temp = people[i];
            int j = i;
            while(j > num){
                people[j] = people[j - 1];
                j--;
            }
            people[j] = temp;
        }

        return people;
    }

    /**
     * 官方题解一：从低到高考虑
     * 从低到高插空
     * @param people
     * @return
    执行用时：
    18 ms, 在所有 Java 提交中击败了12.03%的用户
    内存消耗：
    39 MB, 在所有 Java 提交中击败了99.09%的用户
     */
    public int[][] reconstructQueue2(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>(){
           @Override
           public int compare(int[] o1, int[] o2){
               if(o1[0] == o2[0]){//身高相同的情况
                   return o2[1] - o1[1];//按照k值降序排序
               }
               return o1[0] - o2[0];//按照身高升序排序
           }
        });
        int[][] ans = new int[people.length][];
        for(int[] person : people){
            int spaces = person[1] + 1;
            for (int i = 0; i < people.length; i++) {
                if(ans[i] == null){
                    --spaces;
                    if(spaces == 0){
                        ans[i] = person;
                        break;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 官方题解二：从高到低排序（我的题解优化版）
     * @param people
     * @return
    执行用时：
    8 ms, 在所有 Java 提交中击败了90.65%的用户
    内存消耗：
    39.6 MB, 在所有 Java 提交中击败了63.60%的用户
     */
    public int[][] reconstructQueue3(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[0] == o2[0]){//身高相同的情况
                    return o1[1] - o2[1];//按照k值升序排序
                }
                return o2[0] - o1[0];//按照身高降序排序
            }
        });
        List<int[]> ans = new LinkedList<>();//这里用LinkedList插入比ArrayList更快
        for(int[] person : people){
            ans.add(person[1], person);//将每个person插空到第k+1的位置
        }
        return ans.toArray(new int[ans.size()][]);
    }

    /**
     * 大佬题解@孙笑川
     * @param people
     * @return
    执行用时：
    11 ms, 在所有 Java 提交中击败了29.11%的用户
    内存消耗：
    39.5 MB, 在所有 Java 提交中击败了73.90%的用户
     */
    public int[][] reconstructQueue4(int[][] people) {
        Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        List<int[]> ans = new LinkedList<>();
        for(int[] person : people) {
            ans.add(person[1], person);
        }
        return ans.toArray(new int[ans.size()][]);
    }

    /**
     * 快排
     * @param people
     * @return
    执行用时：
    4 ms, 在所有 Java 提交中击败了99.97%的用户
    内存消耗：
    39.2 MB, 在所有 Java 提交中击败了93.56%的用户
     */
    public int[][] reconstructQueue5(int[][] people) {
        quickSort(people, 0, people.length - 1);
        List<int[]> ans = new LinkedList<>();
        for(int[] person : people){
            ans.add(person[1], person);
        }
        return ans.toArray(new int[ans.size()][]);
    }
    private void quickSort(int[][] arr, int left, int right){
        if(left < right){
            //直接取最右边为pivot
            int pivot[] = arr[right];
            int i = left, j = right;
            while(i < j){
                //找到身高比pivot低，或者身高相同k比pivot大的一组
                while(i < j && (arr[i][0] > pivot[0] || (arr[i][0] == pivot[0] && arr[i][1] <= pivot[1]))){
                    i++;
                }
                //交换到pivot右边
                arr[j] = arr[i];
                //找到身高比pivot高，或者身高相同但k值比pivot小的一组
                while(i < j && (arr[j][0] < pivot[0] || (arr[j][0] == pivot[0] && arr[j][1] >= pivot[1]))) {
                    j--;
                }
                //交换到pivot左边
                arr[i] = arr[j];
            }
            //中间为pivot，保证pivot左边身高都比pivot高，右边都比pivot低
            arr[i] = pivot;
            //给左边右边分别排序
            quickSort(arr, left, i - 1);
            quickSort(arr, i + 1, right);
        }
    }
}

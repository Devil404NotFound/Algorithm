package com.ljp.leecode_cn.sort;

import org.lanqiao.Utils.Util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/** 每日一题 2020.11.09
 973. 最接近原点的 K 个点
 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。

 （这里，平面上两点之间的距离是欧几里德距离。）

 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。



 示例 1：

 输入：points = [[1,3],[-2,2]], K = 1
 输出：[[-2,2]]
 解释：
 (1, 3) 和原点之间的距离为 sqrt(10)，
 (-2, 2) 和原点之间的距离为 sqrt(8)，
 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 示例 2：

 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
 输出：[[3,3],[-2,4]]
 （答案 [[-2,4],[3,3]] 也会被接受。）


 提示：

 1 <= K <= points.length <= 10000
 -10000 < points[i][0] < 10000
 -10000 < points[i][1] < 10000
 * @author ljp
 * @date 2020/11/9 16:43
 */
public class _中等_973_最接近原点的K个点 {
    public static void main(String[] args) {
         _中等_973_最接近原点的K个点 test = new _中等_973_最接近原点的K个点();
         int[][] point = {{6,10},{-3,3},{-2,5},{0,2}};
         int K = 3;
         int[][] ans = test.kClosest(point, K);
        Util.print(ans);
    }

    /**
     *
     * @param points
     * @param K
     * @return
    执行用时：
    13 ms, 在所有 Java 提交中击败了84.54%的用户
    内存消耗：
    46.8 MB, 在所有 Java 提交中击败了85.09%的用户
     */
    public int[][] kClosest(int[][] points, int K) {
        int len = points.length;
        int[] distance = new int[len];
        int[] helpMap = new int[len];
        for (int i = 0; i < len; i++) {
            distance[i] = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            helpMap[i] = i;
        }
        for (int i = len / 2 - 1; i >= 0; i--) {
            minHeap(helpMap, distance, i, len);
        }
        int[][] ans = new int[K][2];
        int index = 0;
        for (int i = len - 1; index < K ; i--, index++) {
            swap(distance, 0, i);
            swap(helpMap, 0, i);
            ans[index] = points[helpMap[i]];
            minHeap(helpMap, distance, 0, i);
        }
        return ans;
    }

    /**
     * 小顶堆, 并记录下标变化
     * @param arr
     * @param i
     * @param length
     */
    private void minHeap(int[] helpMap, int[] arr, int i, int length){
        int j = 2 * i + 1;
        int temp = arr[i];
        int tempHelp = helpMap[i];
        while( j < length){
            if(j + 1 < length && arr[j + 1] < arr[j]){
                j++;
            }
            if(arr[j] < temp){
                arr[i] = arr[j];
                helpMap[i] = helpMap[j];
                i = j;
                j = 2 * j + 1;
            }else{
                break;
            }
        }
        arr[i] = temp;
        helpMap[i] = tempHelp;
    }
    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    /***************************下面官方题解**********************/
    /**
     * 官方题解一：排序
     * @param points
     * @param K
     * @return
    执行用时：
    28 ms, 在所有 Java 提交中击败了76.70%的用户
    内存消耗：
    46.7 MB, 在所有 Java 提交中击败了88.33%的用户
     */
    public int[][] kClosest3(int[][] points, int K) {
        Arrays.sort(points, new Comparator<int[]> (){
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[0] * o1[0] + o1[1] * o1[1]) - (o2[0] * o2[0] + o2[1] * o2[1]);
            }
        });
        return Arrays.copyOf(points, K);
    }

    /**
     * 官方题解二：优先序列（大顶堆）
     *
     * @param points
     * @param K
     * @return
    执行用时：
    32 ms, 在所有 Java 提交中击败了51.55%的用户
    内存消耗：
    47.3 MB, 在所有 Java 提交中击败了29.44%的用户
     */
    public int[][] kClosest4(int[][] points, int K) {
        //记录平方和、数组points的下标
        PriorityQueue<int[]> priority = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });
        //初始化优先队列
        for (int i = 0; i < K; i++) {
            priority.offer(new int[]{points[i][0] * points[i][0] + points[i][1] * points[i][1], i});
        }
        for (int i = K; i < points.length; i++) {
            int dist = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            if(dist < priority.peek()[0]){//如果dist比优先序列最大的数小，就替换最大的数
                priority.poll();
                priority.offer(new int[]{dist, i});
            }
        }
        //根据下标找初始的坐标对
        int[][] ans = new int[K][2];
        for (int i = 0; i < K; i++) {
            ans[i] = points[priority.poll()[1]];
        }
        return ans;
    }

    /**
     * 官方题解三：快速选择（快排思想）(个人写法与官方不太一样----取标杆那里）
     * @param points
     * @param K
     * @return
    执行用时：
    3 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    47.3 MB, 在所有 Java 提交中击败了27.96%的用户
     */
    public int[][] kClosest5(int[][] points, int K) {
        quickSelect(points, 0, points.length - 1, K);
        return Arrays.copyOf(points, K);
    }
    private void quickSelect(int[][] points, int left, int right, int K) {
        if(left < right){
            //取right的值作为标杆
            int pivot = points[right][0] * points[right][0] + points[right][1] * points[right][1];
            int i = left - 1;
            for (int j = left; j < right; j++) {
                int dist = points[j][0] * points[j][0] + points[j][1] * points[j][1];
                if(dist <= pivot) {
                    ++i;
                    swap(points, i, j);
                }
            }
            ++i;
            swap(points, i, right);
            //[left, i - 1]都小于等于pivot， [i + 1, right] 都大于pivot
            if(K < i - left + 1) {//K在左边
                quickSelect(points, left, i - 1, K);
            }else if(K > i - left + 1) {//K在右边
                quickSelect(points, i + 1, right, K - (i - left + 1));
            }
        }
    }
    private void swap(int[][] arr, int i, int j){
        int[] temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    /***************************下面大佬方法**********************/
    /**
     *
     * @param points
     * @param K
     * @return
    执行用时：
    48 ms, 在所有 Java 提交中击败了16.48%的用户
    内存消耗：
    47.1 MB, 在所有 Java 提交中击败了61.75%的用户
     */
    public int[][] kClosest2(int[][] points, int K) {
        Arrays.sort(points, Comparator.comparing((array) -> array[0] * array[0] + array[1] * array[1]));
        return Arrays.copyOf(points, K);
    }

    /**
     *
     * @param points
     * @param K
     * @return
    执行用时：
    59 ms, 在所有 Java 提交中击败了11.70%的用户
    内存消耗：
    47.8 MB, 在所有 Java 提交中击败了15.97%的用户
     */
    public int[][] kColsest3(int[][] points, int K){
        return Arrays.stream(points)
                .sorted(Comparator.comparing((p) -> p[0] * p[0] + p[1] * p[1]))
                .limit(K)
                .toArray(int[][]::new);
    }
}

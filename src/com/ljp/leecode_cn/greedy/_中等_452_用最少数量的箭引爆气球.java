package com.ljp.leecode_cn.greedy;

import java.util.Arrays;
import java.util.Comparator;

/** 每日一题2020.11.23
 * 452. 用最少数量的箭引爆气球
 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以y坐标并不重要，因此只要知道开始和结束的x坐标就足够了。开始坐标总是小于结束坐标。平面内最多存在104个气球。

 一支弓箭可以沿着x轴从不同点完全垂直地射出。在坐标x处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。

 Example:

 输入:
 [[10,16], [2,8], [1,6], [7,12]]

 输出:
 2

 解释:
 对于该样例，我们可以在x = 6（射爆[2,8],[1,6]两个气球）和 x = 11（射爆另外两个气球）。
 */
public class _中等_452_用最少数量的箭引爆气球 {
    public static void main(String[] args) {
        _中等_452_用最少数量的箭引爆气球 test = new _中等_452_用最少数量的箭引爆气球();
        int[][] points = {{-2147483646,-2147483645},{2147483646,2147483647}};
        int ret = test.findMinArrowShots(points);
        System.out.println(ret);
    }
    //以前可以通过，现在报错
    public int findMinArrowShots(int[][] points) {
        if(points.length == 0){
            return 0;
        }
        //按照结束坐标排序
        Arrays.sort(points, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[1] - o2[1];//报错原因（-2147483645 - 2147483647溢出成正数）
            }
        });
        int count = 1;
        int start = points[0][0];
        int end = points[0][1];
        for(int i = 0; i < points.length; i++){
            if(points[i][0] > end){
                count++;
                end = points[i][1];
            }
        }
        return count;
    }

    /**
     * 测试用例不全，这个可以通过（实际是无法通过的）
     * @param points
     * @return
    执行用时：
    19 ms, 在所有 Java 提交中击败了96.58%的用户
    内存消耗：
    45.9 MB, 在所有 Java 提交中击败了86.62%的用户
     */
    public int findMinArrowShots2(int[][] points) {
        if(points.length == 0){
            return 0;
        }
        //按照结束坐标排序
        Arrays.sort(points, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if((o1[1] ^ o2[1]) < 0){//避免一正一负会溢出（有问题）
                    return o2[1] - o1[1];
//                    return o1[1];//这个没问题
                }
                return o1[1] - o2[1];
            }
        });
        int count = 1;
        int start = points[0][0];
        int end = points[0][1];
        for(int i = 0; i < points.length; i++){
            if(points[i][0] > end){
                count++;
                end = points[i][1];
            }
        }
        return count;
    }

    /**
     * 快排
     * @param points
     * @return
     使用arr[right]作为pivot（超级慢）
    执行用时：
    173 ms, 在所有 Java 提交中击败了5.14%的用户
    内存消耗：
    46.7 MB, 在所有 Java 提交中击败了5.47%的用户
     //使用随机pivot后
    执行用时：
    13 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    46.4 MB, 在所有 Java 提交中击败了26.52%的用户
     */
    public int findMinArrowShots3(int[][] points) {
        if(points.length == 0){
            return 0;
        }
        quickSort(points, 0, points.length - 1);
        int end = points[0][1];
        int ret = 0;
        for (int i = 0; i < points.length; i++) {
            if(end < points[i][0]) {
                ret++;
                end = points[i][1];
            }
        }
        return ret;
    }
    private void quickSort(int[][] arr, int left, int right) {
        if(left < right){
            int l = left, r = right;
            int pivotId = (int)(Math.random() * (right - left) + left);
            int[] pivot = arr[pivotId];
            swap(arr, pivotId, right);//记得要换到末尾
            while(l < r){
                while(l < r && arr[l][1] <= pivot[1]){
                    l++;
                }
                arr[r] = arr[l];
                while(l < r && arr[r][1] >= pivot[1]) {
                    r--;
                }
                arr[l] = arr[r];
            }
            arr[l] = pivot;
            quickSort(arr, left, l - 1);
            quickSort(arr, l + 1, right);
        }
    }
    private void swap(int[][] arr, int i, int j){
        int[] temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

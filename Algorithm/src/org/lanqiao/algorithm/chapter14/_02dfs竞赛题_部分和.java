package org.lanqiao.algorithm.chapter14;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定整数序列a1,a2,,an,判断是否可以从中选出若干数,使它们的和恰好为k
 * 1≤n≤20
 * -1O^8≤ai≤10^8
 * -10^8≤k≤10^8
 * 样例:
 * 输入：
 *      n = 4
 *      a = {1,2,4,7}
 *      k = 13
 * 输出：
 * Yes（13 = 2 + 4 + 7）
 * 方法一：这是求子集的变体，用二进制法求子集相加为k即为答案（效率最快）
 * 方法二：dfs深度搜索
 */
public class _02dfs竞赛题_部分和 {
    public static int kk;
    public static void main(String[] args) {
        int[] arr = {1,2,4,7};
        int k = 13;
        kk = k;
        partialSum(arr, k);
        partialSumDfs(arr, k, 0, new ArrayList<Integer>());
    }

    //方法一：利用求子集的方法（性能最高）
    public static boolean partialSum(int[] arr, int k){
        int res = 0;
        int n = (int) Math.pow(2, arr.length);
        for (int i = 1; i < n; i++) {
            List<Integer> items = new ArrayList<>();
            for (int j = 0; j < arr.length; j++) {
                if((i>>j & 1) == 1){
                    items.add(arr[j]);
                    res += arr[j];
                    if(res > k){
                        break;
                    }else if(res == k){
                        System.out.print("Yes(" + k +"=");
                        for (int l = 0; l < items.size() - 1; l++) {
                            System.out.print(items.get(l) + "+" );
                        }
                        System.out.println(items.get(items.size() - 1) + ")");
                        return true;
                    }
                }
            }
            res = 0;
        }
        return false;
    }
    public static void partialSumDfs(int[] arr, int k, int cur, List<Integer> list){
        //出口
        if(k == 0){
            System.out.print("Yes("+ kk+"=");
            for (int i = 0; i < list.size() - 1; i++) {
                System.out.print(list.get(i)+"+");
            }
            System.out.println(list.get(list.size() - 1) + ")");
            System.exit(0);
        }
        if(k < 0 ||  cur == arr.length){
            return;
        }

        //不选择当前的元素
        partialSumDfs(arr, k, cur+1, list);
        //选择当前的元素，
        list.add(arr[cur]);
        int index = list.size() - 1;
        partialSumDfs(arr, k- arr[cur], cur+1, list);
        list.remove(index);
    }

}

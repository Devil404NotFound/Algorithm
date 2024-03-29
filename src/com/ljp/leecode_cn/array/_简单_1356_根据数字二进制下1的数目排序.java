package com.ljp.leecode_cn.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/** 每日一题 2020.11.06
 1356. 根据数字二进制下 1 的数目排序
 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。

 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。

 请你返回排序后的数组。



 示例 1：

 输入：arr = [0,1,2,3,4,5,6,7,8]
 输出：[0,1,2,4,8,3,5,6,7]
 解释：[0] 是唯一一个有 0 个 1 的数。
 [1,2,4,8] 都有 1 个 1 。
 [3,5,6] 有 2 个 1 。
 [7] 有 3 个 1 。
 按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7]
 示例 2：

 输入：arr = [1024,512,256,128,64,32,16,8,4,2,1]
 输出：[1,2,4,8,16,32,64,128,256,512,1024]
 解释：数组中所有整数二进制下都只有 1 个 1 ，所以你需要按照数值大小将它们排序。
 示例 3：

 输入：arr = [10000,10000]
 输出：[10000,10000]
 示例 4：

 输入：arr = [2,3,5,7,11,13,17,19]
 输出：[2,3,5,17,7,11,13,19]
 示例 5：

 输入：arr = [10,100,1000,10000]
 输出：[10,100,10000,1000]


 提示：

 1 <= arr.length <= 500
 0 <= arr[i] <= 10^4

 * @author ljp
 * @date 2020/11/6 11:24
 */
public class _简单_1356_根据数字二进制下1的数目排序 {
    /**
     * 自定义排序类Comparator
     * @param arr
     * @return
    执行用时：
    10 ms, 在所有 Java 提交中击败了40.42%的用户
    内存消耗：
    39.2 MB, 在所有 Java 提交中击败了48.78%的用户
     */
    public int[] sortByBits(int[] arr) {
        Integer[] ans = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        Arrays.sort(ans, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                int count1 = 0, count2 = 0;
                int i = o1, j = o2;
                while(i > 0){
                    count1++;
                    i = i & (i - 1);
                }
                while(j > 0){
                    count2++;
                    j = j & (j - 1);
                }
                if(count1 == count2){
                    return o1 - o2;
                }
                return count1 - count2;
            }
        });
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ans[i];
        }
        return arr;
    }
    //以前的方法在com.ljp.leecode_cn.bit_manipulation._1356根据数字二进制下1的数目排序 类下

    /**
     * 官方题解一：暴力（这也比我的优化）
     * @param arr
     * @return
    执行用时：
    9 ms
    , 在所有 Java 提交中击败了47.58%的用户内存消耗：
    39 MB, 在所有 Java 提交中击败了66.30%的用户
     */
    public int[] sortByBits2(int[] arr) {
        int[] bit = new int[10001];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
            bit[arr[i]] = getBitCount(arr[i]);
        }
        list.sort(new Comparator<Integer>(){
           @Override
           public int compare(Integer o1, Integer o2){
               if(bit[o1] == bit[o2]){
                   return o1 - o2;
               }
               return bit[o1] - bit[o2];
           }
        });
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
    private int getBitCount(int num){
//      return Integer.bitCount(num);
        int i = num;
        int count = 0;
        while(i > 0){
            i = i & (i - 1);
            count++;
        }
        return count;
    }

    /**
     * 官方题解二：递推预处理 利用公式bit[i]=bit[i>>1]+(i&1)
     * @param arr
     * @return
    执行用时：
    9 ms, 在所有 Java 提交中击败了47.58%的用户
    内存消耗：
    39 MB, 在所有 Java 提交中击败了66.30%的用户
     */
    public int[] sortByBits3(int[] arr){
        List<Integer> list = new ArrayList<>();
        int[] bit = new int[10001];
        for (int i = 0; i < bit.length; i++) {
            bit[i] = bit[i >> 1] + (i & 1);
        }
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        list.sort(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                if(bit[o1] == bit[o2]){
                    return o1 - o2;
                }
                return bit[o1] - bit[o2];
            }
        });
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}

package com.ljp.leecode_cn.sort;

import java.util.*;

/** 每日一题 2020.11.14
 1122. 数组的相对排序
 给你两个数组，arr1 和 arr2，

 arr2 中的元素各不相同
 arr2 中的每个元素都出现在 arr1 中
 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。



 示例：

 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 输出：[2,2,2,1,4,3,3,9,6,7,19]


 提示：

 arr1.length, arr2.length <= 1000
 0 <= arr1[i], arr2[i] <= 1000
 arr2 中的元素 arr2[i] 各不相同
 arr2 中的每个元素 arr2[i] 都出现在 arr1 中
 *
 * @author ljp
 * @date 2020/11/14 23:59
 */
public class _简单_1122_数组的相对排序 {
    /**
     * 利用Map映射，排序
     * @param arr1
     * @param arr2
     * @return
    执行用时：
    6 ms, 在所有 Java 提交中击败了16.45%的用户
    内存消耗：
    38.5 MB, 在所有 Java 提交中击败了49.69%的用户
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < arr2.length; i++){
            map.put(arr2[i], i);
        }
        for(int i = 0; i < arr1.length; i++){
            if(!map.containsKey(arr1[i])){
                map.put(arr1[i], arr1[i] + 1000);
            }
        }
        List<Integer> list = new ArrayList<>(arr1.length);
        for (int i = 0; i < arr1.length; i++) {
            list.add(arr1[i]);
        }

        list.sort(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                return map.get(o1) - map.get(o2);
            }
        });
        return list.stream().mapToInt(i -> i).toArray();
    }
    /**
     * 官方题解二：计数排序
     * @param arr1
     * @param arr2
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    38.6 MB, 在所有 Java 提交中击败了43.91%的用户
     */
    public int[] relativeSortArray2(int[] arr1, int[] arr2) {
        int upper = 0;
        for (int x : arr1) {
            upper = Math.max(upper, x);
        }
        int[] frequency = new int[upper + 1];
        for (int x : arr1) {
            ++frequency[x];
        }
        int[] ans = new int[arr1.length];
        int index = 0;
        for (int x : arr2) {
            for (int i = 0; i < frequency[x]; ++i) {
                ans[index++] = x;
            }
            frequency[x] = 0;
        }
        for (int x = 0; x <= upper; ++x) {
            for (int i = 0; i < frequency[x]; ++i) {
                ans[index++] = x;
            }
        }
        return ans;
    }
}

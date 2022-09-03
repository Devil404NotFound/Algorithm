package com.ljp.leecode_cn.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 每日一题 2022.08.25
 *
 * @author lijunpeng
 * @date 2022/8/25 23:54
 * @description 658. 找到 K 个最接近的元素
 * 给定一个 排序好 的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
 * <p>
 * 整数 a 比整数 b 更接近 x 需要满足：
 * <p>
 * |a - x| < |b - x| 或者
 * |a - x| == |b - x| 且 a < b
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [1,2,3,4,5], k = 4, x = 3
 * 输出：[1,2,3,4]
 * 示例 2：
 * <p>
 * 输入：arr = [1,2,3,4,5], k = 4, x = -1
 * 输出：[1,2,3,4]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= arr.length
 * 1 <= arr.length <= 104
 * arr 按 升序 排列
 * -104 <= arr[i], x <= 104
 */

public class _中等_658_找到K个最接近的元素 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 4;
        int x = 3;
        List<Integer> list = new _中等_658_找到K个最接近的元素().findClosestElements(arr, k, x);
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        PriorityQueue<Integer> priority = new PriorityQueue<>((a, b) -> Math.abs(a - x) == Math.abs(b - x) ? a - b : Math.abs(a - x) - Math.abs(b - x));

        for (int num : arr) {
            priority.offer(num);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < k; ++i) {
            list.add(priority.poll());
        }
        Collections.sort(list);
        return list;
    }

    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        int right = 0;
        while (arr[right] < x && right < arr.length - 1) {
            ++right;
        }
        int left = right - 1;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < k; ++i) {
            if (left >= 0 && right < arr.length) {
                int sq = Math.abs(arr[left] - x) - Math.abs(arr[right] - x);
                if (sq <= 0) {
                    list.add(arr[left--]);
                } else {
                    list.add(arr[right++]);
                }
            } else {
                if (left >= 0) {
                    list.add(arr[left--]);
                } else {
                    list.add(arr[right++]);
                }
            }
        }
        Collections.sort(list);
        return list;
    }
}

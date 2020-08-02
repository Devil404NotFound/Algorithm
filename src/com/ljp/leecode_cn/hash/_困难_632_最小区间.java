package com.ljp.leecode_cn.hash;

import org.lanqiao.Utils.Util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class _困难_632_最小区间 {
    public static void main(String[] args) {
        List<List<Integer>> nums = new ArrayList<>();
        List<Integer> element = new ArrayList<>();
        element.add(4);
        element.add(10);
        element.add(15);
        element.add(24);
        element.add(26);
        nums.add(element);
        element = new ArrayList<>();
        element.add(0);
        element.add(9);
        element.add(12);
        element.add(20);
        nums.add(element);
        element = new ArrayList<>();
        element.add(5);
        element.add(18);
        element.add(22);
        element.add(30);
        nums.add(element);
        int[] res = smallestRange(nums);
        Util.print(res);
    }

    /**
     *堆（PriorityQueue+自定义Comparator)
     * @param nums
     * @return
     * 执行用时：
    71 ms, 在所有 Java 提交中击败了43.87%的用户
    内存消耗：
    44.9 MB, 在所有 Java 提交中击败了66.67%的用户
     */
    public static int[] smallestRange(List<List<Integer>> nums) {
        int leftIndex = 0;
        int rightIndex = Integer.MAX_VALUE;
        int minRange = rightIndex - leftIndex;
        int size = nums.size();
        int[] next = new int[size];
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare(Integer index1, Integer index2){
                return nums.get(index1).get(next[index1]) - nums.get(index2).get(next[index2]);
            }
        });
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < size; i++){
            heap.offer(i);
            max = Math.max(max, nums.get(i).get(next[i]));
        }
        int minIndex, currentRange;
        while(true){
            minIndex = heap.poll();
            currentRange = max - nums.get(minIndex).get(next[minIndex]);
            if(currentRange < minRange){
                minRange = currentRange;
                leftIndex = nums.get(minIndex).get(next[minIndex]);
                rightIndex = max;
            }
            next[minIndex]++;
            if(next[minIndex] >= nums.get(minIndex).size()){
                break;
            }
            heap.offer(minIndex);
            max = Math.max(max, nums.get(minIndex).get(next[minIndex]));
        }
        return new int[]{leftIndex, rightIndex};
    }
}

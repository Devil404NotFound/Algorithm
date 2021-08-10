package com.ljp.leecode_cn.greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**每日一题 2020.12.04
 * 659. 分割数组为连续子序列
 输入一个按升序排序的整数数组（可能包含重复数字），你需要将它们分割成几个子序列，其中每个子序列至少包含三个连续整数。返回你是否能做出这样的分割？



 示例 1：

 输入: [1,2,3,3,4,5]
 输出: True
 解释:
 你可以分割出这样两个连续子序列 :
 1, 2, 3
 3, 4, 5


 示例 2：

 输入: [1,2,3,3,4,4,5,5]
 输出: True
 解释:
 你可以分割出这样两个连续子序列 :
 1, 2, 3, 4, 5
 3, 4, 5


 示例 3：

 输入: [1,2,3,4,4,5]
 输出: False


 提示：

 输入的数组长度范围为 [1, 10000]
 */
public class _中等_659分割数组为连续子序列 {

    /**
     * 官方题解一：贪心+最小堆
     一个Map，key为子序列的最后一个数，value为子序列的长度的集合（使用优先队列）
     当key为x时，如果有个子序列的最后一个数为x-1,长度为prevLength，那么就将x添加到这个子序列中，
     即删除x-1的最小子序列，添加一个x，长度为prevLength + 1
     最后遍历Map，如果每个的最小子序列都不小于3，那么就返回true，否则返回false
     * @param nums
     * @return
    执行用时：
    91 ms, 在所有 Java 提交中击败了10.77%的用户
    内存消耗：
    39.9 MB, 在所有 Java 提交中击败了42.86%的用户
     */
    public boolean isPossible(int[] nums) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();//key为子序列的最后一个数，value为子序列的长度的集合
        for(int x : nums) {
            //不存在就新建一个优先队列
            if(!map.containsKey(x)) {
                map.put(x, new PriorityQueue<>());
            }
            //如果x-1存在，就将x添加到x-1的最小子序列中
            if(map.containsKey(x - 1)){
                int prevLength = map.get(x - 1).poll();
                if(map.get(x - 1).isEmpty()){
                    map.remove(x - 1);
                }
                map.get(x).offer(prevLength + 1);
            }else{//否则x自己作为一个子序列，长度为1
                map.get(x).offer(1);
            }
        }
        //遍历Map
        Set<Map.Entry<Integer, PriorityQueue<Integer>>> entrySet = map.entrySet();
        for(Map.Entry<Integer, PriorityQueue<Integer>> entry : entrySet) {
            if(entry.getValue().peek() < 3) {
                return false;
            }
        }
        return true;
    }

    /**
     * 旧官方题解-方法二-贪心算法
     * @param nums
     * @return
     * 执行用时 :
    30 ms, 在所有 Java 提交中击败了63.17%的用户
    内存消耗 :
    40.9 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean isPossible2(int[] nums) {
        Counter count = new Counter();
        Counter tails = new Counter();
        for (int i = 0; i < nums.length; i++) {
            count.add(nums[i], 1);
        }
        for (int i = 0; i < nums.length; i++) {
            if(count.get(nums[i]) == 0){
                continue;
            }else if(tails.get(nums[i]) > 0){
                tails.add(nums[i], -1);
                tails.add(nums[i] + 1, 1);
            }else if(count.get(nums[i] + 1) > 0 && count.get(nums[i] + 2) > 0){
                count.add(nums[i] + 1, -1);
                count.add(nums[i] + 2, -1);
                tails.add(nums[i] + 3, 1);
            }else{
                return false;
            }
            count.add(nums[i], -1);
        }
        return true;
    }
    class Counter extends HashMap<Integer, Integer> {
        public Integer get(Integer key) {
           return getOrDefault(key, 0);
        }
        public void add(Integer key, Integer val){
            put(key, get(key) + val);
        }
    }
}

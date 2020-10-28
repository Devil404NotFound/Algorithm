package com.ljp.leecode_cn.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ljp
 * @date 2020/10/28 10:50
 */
public class _简单_1207_独一无二的出现次数 {
    /**
     * 利用Map和Set
     * @param arr
     * @return
    执行用时：
    2 ms, 在所有 Java 提交中击败了91.43%的用户
    内存消耗：
    36.2 MB, 在所有 Java 提交中击败了94.85%的用户
     */
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0;  i< arr.length; i++){
            int num = map.getOrDefault(arr[i], 0) + 1;
            map.put(arr[i], num);
        }
        Set<Integer> set = new HashSet<>();
        for(Integer i : map.values()){
            if(!set.add(i)){
                return false;
            }
        }
        return true;
    }

    /**
     * 对一的小优化
     * @param arr
     * @return
    执行用时：
    2 ms, 在所有 Java 提交中击败了91.43%的用户
    内存消耗：
    36.5 MB, 在所有 Java 提交中击败了87.62%的用户
     */
    public boolean uniqueOccurrences2(int[] arr) {
        Map<Integer, Integer> occur = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            occur.put(arr[i], occur.getOrDefault(arr[i], 0) + 1);
        }
        Set<Integer> time = new HashSet<>(occur.values());
        return occur.size() == time.size();
    }
}

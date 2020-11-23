package com.ljp.leecode_cn.array;

import java.util.*;
import java.util.stream.Collectors;

/** 每日一题 2020.11.02
 349. 两个数组的交集
 给定两个数组，编写一个函数来计算它们的交集。



 示例 1：

 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 输出：[2]
 示例 2：

 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 输出：[9,4]


 说明：

 输出结果中的每个元素一定是唯一的。
 我们可以不考虑输出结果的顺序。
 * @author ljp
 * @date 2020/11/2 9:58
 */
public class _简单_349_两个数组的交集 {
    /**
     * 哈希表
     * @param nums1
     * @param nums2
     * @return
    执行用时：
    3 ms, 在所有 Java 提交中击败了95.82%的用户
    内存消耗：
    38.8 MB, 在所有 Java 提交中击败了77.33%的用户
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums1.length; i++){
            set.add(nums1[i]);
        }
        Set<Integer> ansSet = new HashSet<>();
        for(int i = 0; i < nums2.length; i++){
            if(set.contains(nums2[i])){
                ansSet.add(nums2[i]);
            }
        }
        int[] ans = new int[ansSet.size()];
        Iterator<Integer> it = ansSet.iterator();
        int index = 0;
        while(it.hasNext()){
            ans[index++] = it.next();
        }
        return ans;
    }

    /**
     * 调用集合方法
     * @param nums1
     * @param nums2
     * @return
    执行用时：
    8 ms, 在所有 Java 提交中击败了13.46%的用户
    内存消耗：
    38.6 MB, 在所有 Java 提交中击败了90.46%的用户
     */
    public int[] intersection2(int[] nums1, int[] nums2) {
        Set<Integer> set1, set2;
        set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        set2 = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
        set1.retainAll(set2);
        return set1.stream().mapToInt(i->i).toArray();
    }

    /**
     * Stream、distinct、filter的调用
     * @param nums1
     * @param nums2
     * @return
    执行用时：
    10 ms, 在所有 Java 提交中击败了9.09%的用户
    内存消耗：
    38.7 MB, 在所有 Java 提交中击败了83.60%的用户
     */
    public int[] intersection3(int[] nums1, int[] nums2){
        Set<Integer> set = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        return Arrays.stream(nums2).distinct().filter(set::contains).toArray();
    }
}

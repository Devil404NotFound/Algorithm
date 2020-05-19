package com.ljp.leecode_cn.greedy;

import java.util.*;

/**
 * 870. 优势洗牌
 给定两个大小相等的数组 A 和 B，A 相对于 B 的优势可以用满足 A[i] > B[i] 的索引 i 的数目来描述。

 返回 A 的任意排列，使其相对于 B 的优势最大化。



 示例 1：

 输入：A = [2,7,11,15], B = [1,10,4,11]
 输出：[2,11,7,15]
 示例 2：

 输入：A = [12,24,8,32], B = [13,25,32,11]
 输出：[24,32,8,12]


 提示：

 1 <= A.length = B.length <= 10000
 0 <= A[i] <= 10^9
 0 <= B[i] <= 10^9
 */
public class _中等_870_优势洗牌 {
    /**
     * 超时
     * @param A
     * @param B
     * @return
     */
    public int[] advantageCount(int[] A, int[] B) {
        LinkedList<Integer> link = new LinkedList<>();
        Arrays.sort(A);
        for (int i = 0; i < A.length; i++) {
            link.add(A[i]);
        }
        int[] res = new int[A.length];
        for (int i = 0; i < B.length; i++) {
            int j = 0;
            int n;
            if(link.get(link.size() - 1) < B[i]){
                n = link.removeFirst();
            }else{
                while(link.get(j) < B[i]){
                    j++;
                }
                n = link.remove(j);
            }
            res[i] = n;

        }
        return res;
    }

    /**
     * 参考官方题解的思路
     * @param A
     * @param B
     * @return
     * 执行用时 :
    38 ms, 在所有 Java 提交中击败了80.21%的用户
    内存消耗 :
    44.7 MB, 在所有 Java 提交中击败了11.11%的用户
     */
    public int[] advantageCount2(int[] A, int[] B) {
        int[] sortedA = A.clone();
        int[] sortedB = B.clone();
        Arrays.sort(sortedA);
        Arrays.sort(sortedB);
        //映射数组与下标的关系， key是B[i}， value是i （key可能存在多个，因此需要用list存value
        Map<Integer, LinkedList<Integer>> map = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            LinkedList<Integer> list;
            if(map.containsKey(B[i])){
                list = map.get(B[i]);
            }else{
                list = new LinkedList<>();
            }list.addFirst(i);
            map.put(B[i], list);
        }
        int[] res = new int[B.length];
        int low = 0;
        int high = B.length - 1;
        for (int i = 0; i < sortedA.length; i++) {
            int index;
            if (sortedA[i] > sortedB[low]){
                index = map.get(sortedB[low]).getFirst();
                map.get(sortedB[low]).removeFirst();
                low++;
            }else{
                index = map.get(sortedB[high]).getFirst();
                map.get(sortedB[high]).removeFirst();
                high--;
            }
            res[index] = sortedA[i];
        }
        return res;
    }
}

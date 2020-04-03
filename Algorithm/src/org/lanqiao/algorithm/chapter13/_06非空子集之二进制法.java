package org.lanqiao.algorithm.chapter13;

import java.util.*;

/**
 * S = {A, B, C}， 用1表示取，0表示不取。
 */
public class _06非空子集之二进制法 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        System.out.println(subSets(arr));;
        System.out.println(_05非空子集.subSets(arr));
    }
    public static List<List<Integer>> subSets(int[] arr){
        List<List<Integer>> res = new ArrayList<>();
        int n = 1;
        for (int i = 0; i < arr.length; i++) {
            n *= 2;
        }
        //子集个数等于2的n次方-1
        for (int i = 1; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < arr.length; j++) {
                if((i >> j & 1) == 1){
                    list.add(arr[j]);
                }
            }
            res.add(list);
        }
        return res;
    }
}

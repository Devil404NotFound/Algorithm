package org.lanqiao.algorithm.chapter13;

import java.util.HashSet;
import java.util.Set;

/**
 * 编写一个方法，返回该集合的所有非空子集
 * 这里用int数组为例
 */
public class _05非空子集 {
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        Character[] arr2 = {'A','B','C'};
        Set<Set<Integer>> res1 = subSets(arr);
        System.out.println(res1);
        Set<Set<Integer>> res2 = subSets2(arr);
        System.out.println(res2);
        Set<Set<Character>> res3 = subSets3(arr2);
        System.out.println(res3);
        System.out.println(res1.equals(res2));
        System.out.println(res1.equals(res3));

    }

    /**
     * 传统递归方法
     * @param n
     * @return
     */
    public static Set<Set<Integer>> subSets(int[] n){
        Set<Set<Integer>> res = subSetsCore(n,n.length - 1);
        //去掉空子集
        res.remove(new HashSet<Integer>());
        return res;
    }
    private static Set<Set<Integer>> subSetsCore(int[] n, int cur) {
        if(cur == 0){
            //只有一个元素时
            Set<Set<Integer>> oldSet = new HashSet<>();
            oldSet.add(new HashSet<>());//添加空集合（该分支的子集下不加该元素）
            Set<Integer> set = new HashSet<>();
            set.add(n[cur]);
            oldSet.add(set);//加上该元素
            return oldSet;
        }
        Set<Set<Integer>> oldSet = subSetsCore(n, cur - 1);
        Set<Set<Integer>> newSet = new HashSet<Set<Integer>>();;
        for (Set<Integer> set : oldSet){
            newSet.add(set);//不加
            Set<Integer> cloneSet = (Set<Integer>)((HashSet<Integer>)set).clone();
            cloneSet.add(n[cur]);//加
            newSet.add(cloneSet);
        }
        return newSet;
    }

    /**
     * 逐步生成迭代大法
     * @param arr
     * @return
     */
    public static Set<Set<Integer>> subSets2(int[] arr){
        if(arr == null || arr.length == 0){
            return null;
        }
        Set<Set<Integer>> oldSet = new HashSet<>();
        oldSet.add(new HashSet<>());
        Set<Integer> set0 = new HashSet<>();
        set0.add(arr[0]);
        oldSet.add(set0);
        if(arr.length == 1){
            return oldSet;
        }
        Set<Set<Integer>> res = null;
        for (int i = 1; i < arr.length; i++) {
            res = new HashSet<>();
            res.addAll(oldSet);
            for (Set<Integer> set : oldSet){
                //不必每次都添加，可以在上面使用addAll方法全部一次添加
//                res.add(set);
                Set<Integer> clone = (Set<Integer>) ((HashSet<Integer>)set).clone();
                clone.add(arr[i]);
                res.add(clone);
            }
            oldSet = res;
        }
        res.remove(new HashSet<>());
        return res;
    }

    /**
     * 用泛型Set实现迭代大法
     * @param <T>  数据类型
     * @param arr 传入一个数据类型的数组
     * @return 返回一个包含所有子集的Set集合
     */
    public static <T> Set<Set<T>> subSets3(T[] arr){
        if(arr == null || arr.length == 0){
            return new HashSet<>();
        }
        Set<Set<T>> res = null;
        Set<Set<T>> oldSet = new HashSet<>();
        oldSet.add(new HashSet<>());
        Set<T> set0 = new HashSet<>();
        set0.add(arr[0]);
        oldSet.add(set0);
        for (int i = 1; i < arr.length; i++) {
            res = new HashSet<>();
            res.addAll(oldSet);
            for (Set<T> set : oldSet) {
                Set<T> clone = (Set<T>) ((HashSet<T>) set).clone();
                clone.add(arr[i]);
                res.add(clone);
            }
            oldSet = res;
        }
        res.remove(new HashSet<T>());
        return res;
    }
}

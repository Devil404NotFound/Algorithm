package org.lanqiao.algorithm.chapter13;

import org.lanqiao.Utils.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 一个结集合的全部排列方式
 */
public class _07全排列 {
    public static void main(String[] args) {
        String str = "abc";
        List<String> list = permutation(str);
        System.out.println(list);
    }
    public static List<String> permutation(String str){
        List<String> res = new ArrayList<>();
        permutationCore(res,str.toCharArray(), 0);
        //对得到的List排序
//        Collections.sort(res);

        //自定义排序判断方法对list排序
        /*res.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });*/
        return res;
    }
    private static void permutationCore(List<String> list, char[] chars, int cur){
        if(cur == chars.length){
            list.add(new String(chars));
            return;
        }
        for (int i = cur; i < chars.length; i++) {
            Util.swap(chars, cur, i);
            permutationCore(list, chars, cur + 1);
            Util.swap(chars, i, cur);
        }
    }
}

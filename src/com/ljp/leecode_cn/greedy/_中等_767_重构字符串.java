package com.ljp.leecode_cn.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 767. 重构字符串
 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。

 若可行，输出任意可行的结果。若不可行，返回空字符串。

 示例 1:

 输入: S = "aab"
 输出: "aba"
 示例 2:

 输入: S = "aaab"
 输出: ""
 注意:

 S 只包含小写字母并且长度在[1, 500]区间内。
 */
public class _中等_767_重构字符串 {
    public static void main(String[] args) {
        String S = "bbbbbac";
        System.out.println(reorganizeString(S));
    }

    /**
     * 哈希映射+排序+贪心
     * @param S
     * @return
     * 执行用时 :
    2 ms, 在所有 Java 提交中击败了64.86%的用户
    内存消耗 :
    37.6 MB, 在所有 Java 提交中击败了33.33%的用户
     */
    public static String reorganizeString(String S) {
        if("".equals(S)){
            return "";
        }
        char[] ch = S.toCharArray();
        int[][] mask = new int[26][2];
        //字符串映射到二维数组mask[i][0]为字符串数量， mask[i][1]为字符串的ASCII值
        for(int i = 0; i < ch.length; i++){
            int c  = ch[i] - 'a';
            mask[c][0]++;
            mask[c][1] = ch[i];
        }
        //自定义一个排序器
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        };
        Arrays.sort(mask, comparator);
        //如果最多的一个字符出现次数大于数组+1的一半，就不可能满足要求
        if(mask[25][0] > (ch.length + 1) / 2){
            return "";
        }
        int len = (ch.length + 1) / mask[25][0];
        StringBuilder sb = new StringBuilder();
        while(mask[25][0] != 0){
            for(int i = 25; mask[i][0] != 0 && i + len > 25; i--){
                sb.append((char)mask[i][1]);
                mask[i][0]--;
            }
            Arrays.sort(mask,comparator);
        }
        return sb.toString();
    }
}

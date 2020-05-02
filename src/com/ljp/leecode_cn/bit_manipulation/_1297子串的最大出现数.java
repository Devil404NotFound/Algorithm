package com.ljp.leecode_cn.bit_manipulation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 1297. 子串的最大出现次数【中等】
 给你一个字符串 s ，请你返回满足以下条件且出现次数最大的 任意 子串的出现次数：

 子串中不同字母的数目必须小于等于 maxLetters 。
 子串的长度必须大于等于 minSize 且小于等于 maxSize 。


 示例 1：

 输入：s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
 输出：2
 解释：子串 "aab" 在原字符串中出现了 2 次。
 它满足所有的要求：2 个不同的字母，长度为 3 （在 minSize 和 maxSize 范围内）。
 示例 2：

 输入：s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
 输出：2
 解释：子串 "aaa" 在原字符串中出现了 2 次，且它们有重叠部分。
 示例 3：

 输入：s = "aabcabcab", maxLetters = 2, minSize = 2, maxSize = 3
 输出：3
 示例 4：

 输入：s = "abcde", maxLetters = 2, minSize = 3, maxSize = 3
 输出：0


 提示：

 1 <= s.length <= 10^5
 1 <= maxLetters <= 26
 1 <= minSize <= maxSize <= min(26, s.length)
 s 只包含小写英文字母。
 通过次数2,600提交次数6,083
 */
public class _1297子串的最大出现数 {
    public static void main(String[] args) {
        String s = "aababcaab";
        int maxLetters = 2;
        int minSize = 3;
        int maxSize = 4;
        System.out.println(maxFreq(s, maxLetters, minSize, maxSize));
    }

    /**
     *
     * @param s
     * @param maxLetters
     * @param minSize
     * @param maxSize
     * @return
     * 执行用时 :
        486 ms, 在所有 Java 提交中击败了14.56%的用户
        内存消耗 :
        134.5 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public static int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        Map<String, Integer> map = new HashMap<>();
        int length = s.length() - minSize;
        for (int i = 0; i <= length; i++) {
            int bit = 0;
            int count = 0;
            for (int j = i; j < s.length() && j - i < maxSize; j++) {
                //判断该子串的不同字符的数量（位运算）
                if((bit & (1 << (s.charAt(j) - 'a'))) == 0){
                    bit |= 1 << (s.charAt(j) - 'a');
                    count++;
                }
                if(count > maxLetters){
                    break;
                }
                if(j + 1 - i >= minSize){
                    if(map.containsKey(s.substring(i,j+1))){
                        int num = map.get(s.substring(i, j + 1)) + 1;
                        map.put(s.substring(i,j+1),num);
                    }else{
                        map.put(s.substring(i,j+1),1);
                    }
                }
            }
        }
        //遍历map，找到最大值
        int max = 0;
        Set<String> set = map.keySet();
        for(String str : set){
            max = Math.max(max, map.get(str));
        }
        return max;
    }

    /**
     * 优化，不用Set映射Map的Key，只需要考虑最短的，长的肯定会覆盖短的（如：abc,覆盖了a,ab,bc,,参考AC最快的，29ms
     * @param s
     * @param maxLetters
     * @param minSize
     * @param maxSize
     * @return
     * 执行用时 :
    33 ms, 在所有 Java 提交中击败了98.54%的用户
    内存消耗 :
    41.7 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public static int maxFreq2(String s, int maxLetters, int minSize, int maxSize) {
        Map<String, Integer> map = new HashMap<>();
        int mask, difLetters, shift;
        int maxFreq = 0;
        for (int i = 0; i < s.length() - minSize; i++) {
            mask = 0;
            difLetters = 0;
            for (int j = i; j < i + minSize; j++) {
                shift = 1 << (s.charAt(j) - 'a');
                if((mask & shift) == 0){
                    difLetters++;
                    if(difLetters > maxLetters){
                        break;
                    }
                }
            }
            if(difLetters <= maxLetters){
                String strSub = s.substring(i, i + minSize);
                int count = map.getOrDefault(strSub,0) + 1;
                map.put(strSub, count);
                if(maxFreq < count){
                    maxFreq = count;
                }
            }
        }
        return maxFreq;
    }
}

package com.ljp.leecode_cn.string;

/**
 * @author lijunpeng
 * @date 2022/10/14 22:39
 * @description
 **/

public class _简单_2423_删除字符使频率相同 {
    public static void main(String[] args) {
        String str = "cccd";
        boolean result = new _简单_2423_删除字符使频率相同().equalFrequency(str);
        System.out.println(result);
    }
    public boolean equalFrequency(String word) {
        int[] dict = new int[26];
        int max = 0;
        for(int i = 0; i < word.length(); ++i) {
            max = Math.max(max, ++dict[word.charAt(i) - 'a']);
        }
        int maxCount = 0;
        int secCount = 0;
        for(int i = 0; i < 26; ++i) {
            if(dict[i] == 0) {
                continue;
            }
            if(dict[i] == max) {
                maxCount++;
            }else if(dict[i] == max - 1) {
                secCount++;
            }else {
                return false;
            }
        }
        return maxCount == 1 || secCount == 1 || (secCount == 0 && max  == 1);
    }
}

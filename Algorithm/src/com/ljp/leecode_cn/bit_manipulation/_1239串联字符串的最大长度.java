package com.ljp.leecode_cn.bit_manipulation;

import java.util.ArrayList;
import java.util.List;

/**
 * 1239. 串联字符串的最大长度【中等】
 给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。

 请返回所有可行解 s 中最长长度。



 示例 1：

 输入：arr = ["un","iq","ue"]
 输出：4
 解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
 示例 2：

 输入：arr = ["cha","r","act","ers"]
 输出：6
 解释：可能的解答有 "chaers" 和 "acters"。
 示例 3：

 输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
 输出：26


 提示：

 1 <= arr.length <= 16
 1 <= arr[i].length <= 26
 arr[i] 中只含有小写英文字母
 */
public class _1239串联字符串的最大长度 {
    public static void main(String[] args) {
        List<String> arr = new ArrayList<>();
        arr.add("abcdefghijklmnopqrstuvwxyz");

        System.out.println(maxLength(arr));
        System.out.println(maxLength2(arr));
    }

    /**
     *
     * @param arr
     * @return
     * 执行用时 :
    75 ms, 在所有 Java 提交中击败了21.17%的用户
    内存消耗 :
    36.8 MB, 在所有 Java 提交中击败了50.00%的用户
     */
    public static int maxLength(List<String> arr) {
        int size = arr.size();
        int[] hash = new int[size];
        for (int i = 0; i < size; i++) {
            for (char ch : arr.get(i).toCharArray()) {
                int c = 1 << (ch - 'a');
                if((hash[i] & c) == 0){
                    hash[i] |= c;
                }else{
                    hash[i] = 0;
                    break;
                }
            }
            System.out.println(Integer.toBinaryString(hash[i]));
        }
        int res = 0;
        int n = (1 << size) - 1;
        for (int i = 1; i <= n; i++) {
            int bit = 0;
            for (int j = 0; j < hash.length; j++) {
                if(((i >> j) & 1) == 1 && (bit & hash[j]) == 0){
                    bit |= hash[j];
                }
            }
            int count = 0;
            while(bit != 0){
                if((bit & 1) == 1){
                    count++;
                }
                bit >>= 1;
            }
            if(count > res){
                res = count;
            }
        }
        return res;
    }

    /**
     * DFS=====参考大牛算法编写
     * @param arr
     * @return
     * 执行用时 :
    4 ms, 在所有 Java 提交中击败了84.29%的用户
    内存消耗 :
    39.2 MB, 在所有 Java 提交中击败了16.67%的用户
     */
    private static int max;
    public static int maxLength2(List<String> arr) {
        return maxLengthCore(arr, new StringBuilder(),  0, 0);
    }

    private static int maxLengthCore(List<String> arr, StringBuilder sb, int hash, int cur) {
        if(cur == arr.size()){
            return sb.length();
        }
        char[] s = arr.get(cur).toCharArray();
        int tempHash = hash;
        boolean flag = true;
        for (int i = 0; i < s.length; i++) {
            int bit = 1 << (s[i] - 'a');
            if((tempHash & bit) != 0){
                flag = false;
                break;
            }
            tempHash |= bit;
        }
        int max = maxLengthCore(arr, sb, hash, cur + 1);
        if(flag){
            sb.append(arr.get(cur));
            int max2 = maxLengthCore(arr, sb, tempHash, cur + 1);
            sb.setLength(sb.length() - s.length);
            if(max < max2){
                max = max2;
            }
        }
        return max;
    }

}

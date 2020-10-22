package com.ljp.leecode_cn.greedy;

import java.util.ArrayList;
import java.util.List;

/**每日一题2020.10.22
 * 763. 划分字母区间
 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。

 示例 1:

 输入: S = "ababcbacadefegdehijhklij"
 输出: [9,7,8]
 解释:
 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 每个字母最多出现在一个片段中。
 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 注意:

 S的长度在[1, 500]之间。
 S只包含小写字母'a'到'z'。
 */
public class _中等_763_划字母区间 {
    public static void main(String[] args) {
        String S = "ababcbacadefegdehijhklij";
        System.out.println(partitionLabels(S));
    }

    /**
     *哈希表，贪心算法
     * @param S
     * @return
     * 执行用时 :
    4 ms, 在所有 Java 提交中击败了87.79%的用户
    内存消耗 :
    38.2 MB, 在所有 Java 提交中击败了25.00%的用户
     */
    public static List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        if(S.length() == 1){
            res.add(1);
            return res;
        }
        int[] hash = new int[S.length()];
        hash[hash.length - 2] = 1 << (S.charAt(hash.length - 1) - 'a');//哈希映射前缀和
        for(int i = hash.length - 2; i > 0; --i){
            hash[i - 1] = hash[i] | (1 << (S.charAt(i) - 'a'));
        }
        int mask = 0;
        int count = 0;
        for(int i = 0; i < S.length(); ++i){
            mask |= 1 << (S.charAt(i) - 'a');
            count++;
            if((mask & hash[i]) == 0){
                res.add(count);
                mask = 0;
                count = 0;
            }
        }
        return res;
    }

    /**
     * 官方题解：贪心算法+字母最后出现的下标
     * @param S
     * @return
     */
    public List<Integer> partitionLabels2(String S) {
        int[] last = new int[26];
        for (int i = 0; i < S.length(); ++i){
            last[S.charAt(i) - 'a'] = i;
        }
        int j = 0, anchor = 0;
        List<Integer> ans = new ArrayList();
        for (int i = 0; i < S.length(); ++i) {
            j = Math.max(j, last[S.charAt(i) - 'a']);
            if (i == j) {
                ans.add(i - anchor + 1);
                anchor = i + 1;
            }
        }
        return ans;
    }
}

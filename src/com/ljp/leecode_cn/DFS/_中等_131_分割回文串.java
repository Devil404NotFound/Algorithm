package com.ljp.leecode_cn.DFS;

import java.util.ArrayList;
import java.util.List;

/** 每日一题 2021.03.07
 * @author lijunpeng
 * @date 2021/3/7 23:50
131. 分割回文串
给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。

回文串 是正着读和反着读都一样的字符串。



示例 1：

输入：s = "aab"
输出：[["a","a","b"],["aa","b"]]
示例 2：

输入：s = "a"
输出：[["a"]]


提示：

1 <= s.length <= 16
s 仅由小写英文字母组成
 */
public class _中等_131_分割回文串 {
    /**
     * 深度优先遍历
     * @param s
     * @return
    执行用时：
    18 ms, 在所有 Java 提交中击败了12.45%的用户
    内存消耗：
    51.4 MB, 在所有 Java 提交中击败了88.24%的用户
     */
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        List<String> list = new ArrayList<>();
        dfs(ans, list, s);
        return ans;
    }
    //深度优先搜索
    private void dfs(List<List<String>> ans, List<String> list, String s) {
        int len = s.length();
        if(len == 0) {
            List<String> newList = new ArrayList<>();
            for(String str : list) {
                newList.add(str);
            }
            ans.add(newList);
        }
        for(int i = 1; i <= len; ++i) {
            String sub = s.substring(0, i);
            if(isPalindrome(sub)){
                list.add(sub);
                dfs(ans, list,s.substring(i));
                list.remove(list.size() - 1);
            }
        }
    }
    //判断是否回文
    private boolean isPalindrome(String s) {
        int len = s.length();
        if(len < 2) {
            return true;
        }
        int left = 0;
        int right = len - 1;
        while(left < right) {
            if(s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

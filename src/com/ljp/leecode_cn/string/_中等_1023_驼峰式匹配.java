package com.ljp.leecode_cn.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijunpeng
 * @date 2023-04-14 20:55
 * @Description
1023. 驼峰式匹配
如果我们可以将小写字母插入模式串 pattern 得到待查询项 query，那么待查询项与给定模式串匹配。（我们可以在任何位置插入每个字符，也可以插入 0 个字符。）
给定待查询列表 queries，和模式串 pattern，返回由布尔值组成的答案列表 answer。只有在待查项 queries[i] 与模式串 pattern 匹配时， answer[i] 才为 true，否则为 false。

示例 1：
输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FB"
输出：[true,false,true,true,false]
示例：
"FooBar" 可以这样生成："F" + "oo" + "B" + "ar"。
"FootBall" 可以这样生成："F" + "oot" + "B" + "all".
"FrameBuffer" 可以这样生成："F" + "rame" + "B" + "uffer".
示例 2：

输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBa"
输出：[true,false,true,false,false]
解释：
"FooBar" 可以这样生成："Fo" + "o" + "Ba" + "r".
"FootBall" 可以这样生成："Fo" + "ot" + "Ba" + "ll".
示例 3：

输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBaT"
输出：[false,true,false,false,false]
解释：
"FooBarTest" 可以这样生成："Fo" + "o" + "Ba" + "r" + "T" + "est".


提示：

1 <= queries.length <= 100
1 <= queries[i].length <= 100
1 <= pattern.length <= 100
所有字符串都仅由大写和小写英文字母组成。
 */
public class _中等_1023_驼峰式匹配 {
    public static void main(String[] args) {
        String[] queries = {"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"};
        String pattern = "FoBaT";
        List<Boolean> booleanList = new _中等_1023_驼峰式匹配().camelMatch(queries, pattern);
        System.out.println(booleanList);
    }


    /**
     * 执行用时：字符串匹配
     * 0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：
     * 39.9 MB, 在所有 Java 提交中击败了22.42%的用户
     */
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> ansList = new ArrayList<>(queries.length);
        for (String query : queries) {
            if (isMatch(query, pattern)) {
                ansList.add(true);
            } else {
                ansList.add(false);
            }
        }
        return ansList;
    }

    private boolean isMatch(String query, String pattern) {
        int idx = 0;
        char p = pattern.charAt(idx++);
        for (char ch : query.toCharArray()) {
            if (Character.isUpperCase(ch) && p != ch) {
                return false;
            }
            if (p == ch) {
                if (idx >= pattern.length()) {
                    p = '0';
                } else {
                    p = pattern.charAt(idx);
                }
                ++idx;
            }
        }
        return idx > pattern.length();
    }

    /**
     执行用时：
     0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：
     39.6 MB, 在所有 Java 提交中击败了67.88%的用户
     */
    public List<Boolean> camelMatch2(String[] queries, String pattern) {
        int n = queries.length;
        List<Boolean> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            int p = 0;
            for (char c : queries[i].toCharArray()) {
                // 如果相同，则匹配成功
                if (p < pattern.length() && c == pattern.charAt(p)) {
                    p++;
                } else if (Character.isUpperCase(c)) {
                    // 如果不同，并且字母是大写，则匹配不成功
                    flag = false;
                }
            }
            // 判断是否完成匹配
            if (p < pattern.length()) {
                flag = false;
            }
            res.add(flag);
        }
        return res;
    }
}

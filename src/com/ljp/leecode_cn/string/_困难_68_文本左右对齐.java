package com.ljp.leecode_cn.string;

import java.util.ArrayList;
import java.util.List;

/** 每日一题 2021.09.09
 * @author lijunpeng
 * @date 2021/9/9 23:05
 * @Description
68. 文本左右对齐
给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。

你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。

要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。

文本的最后一行应为左对齐，且单词之间不插入额外的空格。

说明:

单词是指由非空格字符组成的字符序列。
每个单词的长度大于 0，小于等于 maxWidth。
输入单词数组 words 至少包含一个单词。
示例:

输入:
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
输出:
[
"This    is    an",
"example  of text",
"justification.  "
]
示例 2:

输入:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
输出:
[
"What   must   be",
"acknowledgment  ",
"shall be        "
]
解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
因为最后一行应为左对齐，而不是左右两端对齐。
第二行同样为左对齐，这是因为这行只包含一个单词。
示例 3:

输入:
words = ["Science","is","what","we","understand","well","enough","to","explain",
"to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
输出:
[
"Science  is  what we",
"understand      well",
"enough to explain to",
"a  computer.  Art is",
"everything  else  we",
"do                  "
]
 */
public class _困难_68_文本左右对齐 {

    /*public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> list = new ArrayList<>();
        int n = words.length;
        int i = 0;
        while(i < n) {
            StringBuilder sb = new StringBuilder();
            int j = i + 1;
            int count = words[i].length();
            while(j < n && count + words[j].length() < maxWidth) {
                count += words[j].length() + 1;
                ++j;
            }
            if(j == n) {
                for (int k = i; k < j - 1; k++) {
                    sb.append(words[k]).append(' ');
                }
                sb.append(words[j - 1]);
                sb.append(getSpace(maxWidth - sb.length()));
                list.add(sb.toString());
                break;
            }
            int spaceNumber = (maxWidth - count) / (j - i - 1) + 1;
            int firstSpaceNumber = (maxWidth - count) % (j - i - 1) + spaceNumber;
            sb.append(words[i]);
            sb.append(getSpace(firstSpaceNumber));
            for (int k = i + 1; k < j - 1; k++) {
                sb.append(words[k]);
                sb.append(getSpace(spaceNumber));
            }
            sb.append(words[j - 1]);
            list.add(sb.toString());
            i = j;
        }
        return list;
    }

    private String getSpace(int spaceNumber) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < spaceNumber; i++) {
            sb.append(' ');
        }
        return sb.toString();
    }*/
    /** 官方题解：模拟
    * @Author lijunpeng
    * @Date 2021/9/9 23:42
    * @Description
    作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/text-justification/solution/wen-ben-zuo-you-dui-qi-by-leetcode-solut-dyeg/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    执行用时：
    1 ms, 在所有 Java 提交中击败了51.71%的用户
    内存消耗：
    37.1 MB, 在所有 Java 提交中击败了15.24%的用户
    **/
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<String>();
        int right = 0, n = words.length;
        while (true) {
            int left = right; // 当前行的第一个单词在 words 的位置
            int sumLen = 0; // 统计这一行单词长度之和
            // 循环确定当前行可以放多少单词，注意单词之间应至少有一个空格
            while (right < n && sumLen + words[right].length() + right - left <= maxWidth) {
                sumLen += words[right++].length();
            }

            // 当前行是最后一行：单词左对齐，且单词之间应只有一个空格，在行末填充剩余空格
            if (right == n) {
                StringBuffer sb = join(words, left, n, " ");
                sb.append(blank(maxWidth - sb.length()));
                ans.add(sb.toString());
                return ans;
            }

            int numWords = right - left;
            int numSpaces = maxWidth - sumLen;

            // 当前行只有一个单词：该单词左对齐，在行末填充剩余空格
            if (numWords == 1) {
                StringBuffer sb = new StringBuffer(words[left]);
                sb.append(blank(numSpaces));
                ans.add(sb.toString());
                continue;
            }

            // 当前行不只一个单词
            int avgSpaces = numSpaces / (numWords - 1);
            int extraSpaces = numSpaces % (numWords - 1);
            StringBuffer sb = new StringBuffer();
            sb.append(join(words, left, left + extraSpaces + 1, blank(avgSpaces + 1))); // 拼接额外加一个空格的单词
            sb.append(blank(avgSpaces));
            sb.append(join(words, left + extraSpaces + 1, right, blank(avgSpaces))); // 拼接其余单词
            ans.add(sb.toString());
        }
    }

    // blank 返回长度为 n 的由空格组成的字符串
    public String blank(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; ++i) {
            sb.append(' ');
        }
        return sb.toString();
    }

    // join 返回用 sep 拼接 [left, right) 范围内的 words 组成的字符串
    public StringBuffer join(String[] words, int left, int right, String sep) {
        StringBuffer sb = new StringBuffer(words[left]);
        for (int i = left + 1; i < right; ++i) {
            sb.append(sep);
            sb.append(words[i]);
        }
        return sb;
    }
}

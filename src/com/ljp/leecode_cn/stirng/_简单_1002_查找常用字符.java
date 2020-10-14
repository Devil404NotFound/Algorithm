package com.ljp.leecode_cn.stirng;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ljp
 * @date 2020/10/14 10:44
 *
1002. 查找常用字符
给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。

你可以按任意顺序返回答案。



示例 1：

输入：["bella","label","roller"]
输出：["e","l","l"]
示例 2：

输入：["cool","lock","cook"]
输出：["c","o"]


提示：

1 <= A.length <= 100
1 <= A[i].length <= 100
A[i][j] 是小写字母
通过次数21,283提交次数29,787
 */
public class _简单_1002_查找常用字符 {
    /** 计数
     * @param A
     * @return
    执行用时：
    3 ms, 在所有 Java 提交中击败了98.36%的用户
    内存消耗：
    38.6 MB, 在所有 Java 提交中击败了98.54%的用户
     */
    public List<String> commonChars(String[] A) {
        //统计每个单词共有的字母个数
        int[] count = new int[26];
        for(int i = 0; i < count.length; i++){
            count[i] = Integer.MAX_VALUE;
        }
        for(int i = 0; i < A.length; i++){
            char[] chars = A[i].toCharArray();
            //统计该单词的每个字母个数
            int[] temp = new int[26];
            for(int j = 0; j < chars.length; j++){
                temp[chars[j] - 'a']++;
            }
            for(int j = 0; j < temp.length; j++){
                count[j] = Math.min(count[j], temp[j]);
            }
        }
        List<String> ans = new ArrayList<>();
        for(int i = 0; i < count.length; i++){
            for(int j = 0; j < count[i]; j++){
                ans.add(String.valueOf((char)('a' + i)));
            }
        }
        return ans;
    }
}

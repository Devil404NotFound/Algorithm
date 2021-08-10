package com.ljp.leecode_cn.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 2021.07.18每日一题
 * @author lijunpeng
 * @date 2021/7/19 0:01
 * @Description
编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。

注意：本题相对原题稍作修改

示例:

输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
输出:
[
["ate","eat","tea"],
["nat","tan"],
["bat"]
]
说明：

所有输入均为小写字母。
不考虑答案输出的顺序。
通过次数30,344提交次数41,090

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/group-anagrams-lcci
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _中等_面试10_变位词数组 {
    /**
     * 官方题解二：计数
     * @param strs
     * @return
    执行用时：
    11 ms, 在所有 Java 提交中击败了31.94%的用户
    内存消耗：
    42 MB, 在所有 Java 提交中击败了21.68%的用户
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }
}

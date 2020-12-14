package com.ljp.leecode_cn.hash;

import java.util.*;

/** 每日一题 2020.12.14
 * @author ljp
 * @date 2020/12/14 19:56
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * <p>
 * 示例:
 * <p>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 说明：
 * <p>
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/group-anagrams
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _中等_49_字母异位词分组 {
    /**
     * 利用Map映射，key为排好序的字符串
     *
     * @param strs
     * @return 执行用时：
     * 8 ms, 在所有 Java 提交中击败了74.94%的用户
     * 内存消耗：
     * 41 MB, 在所有 Java 提交中击败了97.36%的用户
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String sorted = sort(str);
            List<String> list = map.get(sorted);
            if (list == null) {
                list = new ArrayList<>();
                map.put(sorted, list);
            }
            list.add(str);
        }
        List<List<String>> ans = new ArrayList<>();
        Set<Map.Entry<String, List<String>>> entrySet = map.entrySet();
        for (Map.Entry<String, List<String>> entry : entrySet) {
            ans.add(entry.getValue());
        }
        return ans;
    }
    //给字符串排序
    private String sort(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }

    /**
     * 官方题解一：排序
     * @param strs
     * @return
    执行用时：
    7 ms, 在所有 Java 提交中击败了95.84%的用户
    内存消耗：
    41.8 MB, 在所有 Java 提交中击败了39.47%的用户炫耀一下:
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        //新建一个map集合，key为排好序的str，value为List<String>集合
        Map<String, List<String>> map = new HashMap<>();
        //遍历strs
        for(String str : strs) {
            //转字符数组再排序
            char[] array = str.toCharArray();
            Arrays.sort(array);
            //通过key获取list
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);//将str添加到list中
            map.put(key, list);
        }
        //返回个list集合
        return new ArrayList<List<String>>(map.values());
    }

    /**
     * 官方题解二：计数
     通过统计每个字符串的字符个数，再用StringBuilder拼接起来作为map的key
     * @param strs
     * @return
    执行用时：
    执行用时：
    11 ms, 在所有 Java 提交中击败了34.94%的用户
    内存消耗：
    41.9 MB, 在所有 Java 提交中击败了30.62%的用户
     */
    public List<List<String>> groupAnagrams3(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();//通过统计数组生成的重排序字符串
        for(String str : strs) {
            int[] counts = new int[26]; //统计数组
            //统计字符串字母
            for(char c : str.toCharArray()) {
                ++counts[c - 'a'];
            }
            //根据统计数租重排序字符串
            for (int i = 0; i < 26; i++) {
                if(counts[i] > 0){
                    sb.append((char)(i + 'a'));
                    sb.append(counts[i]);
                }
            }
            //生成key
            String key = sb.toString();
            //根据key获取list
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);//添加字符串到list
            map.put(key, list);//将list添加到map中
            sb.setLength(0);//重新设置StringBuilder的长度为0
        }
        //map.values()返回的是一个list集合
        return new ArrayList<List<String>>(map.values());
    }
}

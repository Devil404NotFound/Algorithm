package com.ljp.leecode_cn.string;

import java.util.*;

/**
 * 336. 回文对
 给定一组 互不相同 的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。



 示例 1：

 输入：["abcd","dcba","lls","s","sssll"]
 输出：[[0,1],[1,0],[3,2],[2,4]]
 解释：可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 示例 2：

 输入：["bat","tab","cat"]
 输出：[[0,1],[1,0]]
 解释：可拼接成的回文串为 ["battab","tabbat"]
 */
public class _困难_336_回文对 {
    public static void main(String[] args) {
        String[] words = {"abcd","dcba","lls","s","sssll"};
        new _困难_336_回文对().palindromePairs2(words);
    }
    /**
     * 超时，暴力解法
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> resList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < words.length; i++){
            for(int j = i + 1; j < words.length; j++){
                sb.append(words[i]).append(words[j]);
                if(sb.toString().equals(sb.reverse().toString())){
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    resList.add(list);
                }
                sb.setLength(0);

                sb.append(words[j]).append(words[i]);
                if(sb.toString().equals(sb.reverse().toString())){
                    List<Integer> list = new ArrayList<>();
                    list.add(j);
                    list.add(i);
                    resList.add(list);
                }
                sb.setLength(0);

            }
        }
        return resList;
    }

    /**
     * 解法一：前缀和后缀回文+字典树
     * 执行用时：
     61 ms, 在所有 Java 提交中击败了72.45%的用户
     内存消耗：
     47.8 MB, 在所有 Java 提交中击败了45.24%的用户
     */
    class Node {
        int[] ch = new int[26];
        int flag = -1;
    }
    List<Node> trieTree = new ArrayList<>();
    public List<List<Integer>> palindromePairs1(String[] words) {
        trieTree.add(new Node());
        List<List<Integer>> ret = new ArrayList<>();
        //根据words数组构建一个字典树
        for (int i = 0; i < words.length; i++) {
            insert(words[i].toCharArray(), i);
        }
        for (int i = 0; i < words.length; i++) {
            int len = words[i].length();
            char[] chars = words[i].toCharArray();
            for (int j = 0; j <= len; j++) {
                //前缀是否为回文
                if(isPalindrome(chars, j, len - 1)){
                    int rightId = findWord(chars, 0, j - 1);
                    if(rightId != -1 && rightId != i){
                        ret.add(Arrays.asList(i, rightId));
                    }
                }
                if(j != 0 && isPalindrome(chars, 0, j - 1)){
                    int leftId = findWord(chars, j, len - 1);
                    if(leftId != -1 && leftId != i){
                        ret.add(Arrays.asList(leftId, i));
                    }
                }
            }
        }
        return ret;
    }
    /**
     * 插入一个字符串到字典树
     * @param chs
     * @param id
     */
    private void insert(char[] chs, int id){
        int add = 0;
        for(int i = 0; i < chs.length; i++){
            int x = chs[i] - 'a';
            if(trieTree.get(add).ch[x] == 0){
                trieTree.add(new Node());
                trieTree.get(add).ch[x] = trieTree.size() - 1;
            }
            add = trieTree.get(add).ch[x];
        }
        trieTree.get(add).flag = id;
    }

    /**
     * 判断是否为回文
     * @param chars
     * @param left
     * @param right
     * @return
     */
    public boolean isPalindrome(char[] chars, int left, int right){
        int len = right - left + 1;
        for (int i = 0; i < len; i++) {
            if(chars[left + i] != chars[right - i]){
                return false;
            }
        }
        return true;
    }
    public int findWord(char[] chars, int left, int right){
        int add = 0;
        for (int i = right; i >= left; i--) {
            int x = chars[i] - 'a';
            int nextAdd = trieTree.get(add).ch[x];
            if(nextAdd == 0){
                return -1;
            }
            add = nextAdd;
        }
        return trieTree.get(add).flag;
    }
    /**
     * 解法二：前缀和后缀回文+哈希表
     * 执行用时：
     133 ms, 在所有 Java 提交中击败了24.12%的用户
     内存消耗：
     42.6 MB, 在所有 Java 提交中击败了53.85%的用户
     */
    Map<String, Integer> indices = new HashMap<>();//记录word的反向字符串为key，下标位置为value
    public List<List<Integer>> palindromePairs2(String[] words) {
        List<List<Integer>> ret = new ArrayList<>();
        //初始化indices
        for(int i = 0; i < words.length; i++){
            indices.put(new StringBuilder(words[i]).reverse().toString(), i);
        }
        for (int i = 0; i < words.length; i++) {
            int len = words[i].length();
            if(len == 0){
                continue;
            }
            for (int j = 0; j <= len; j++) {
                if(isPalindrome(words[i].toCharArray(), j, len - 1)){
                    int rightId = findWord(words[i], 0, j - 1);
                    if(rightId != -1 && rightId != i){
                        ret.add(Arrays.asList(i, rightId));
                    }
                }
                if(j != 0 && isPalindrome(words[i].toCharArray(), 0, j - 1)){
                    int leftId = findWord(words[i], j, len - 1);
                    if(leftId != -1 && leftId != i){
                        ret.add(Arrays.asList(leftId, i));
                    }
                }
            }
        }
        return ret;
    }
    private int findWord(String s, int left, int right){
        return indices.getOrDefault(s.substring(left, right + 1), -1);
    }
}

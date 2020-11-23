package com.ljp.leecode_cn.dynamic_programming;

import java.util.*;

/**  每日一题2020.11.01
 140. 单词拆分 II
 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。

 说明：

 分隔时可以重复使用字典中的单词。
 你可以假设字典中没有重复的单词。
 示例 1：

 输入:
 s = "catsanddog"
 wordDict = ["cat", "cats", "and", "sand", "dog"]
 输出:
 [
 "cats and dog",
 "cat sand dog"
 ]
 示例 2：

 输入:
 s = "pineapplepenapple"
 wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 输出:
 [
 "pine apple pen apple",
 "pineapple pen apple",
 "pine applepen apple"
 ]
 解释: 注意你可以重复使用字典中的单词。
 示例 3：

 输入:
 s = "catsandog"
 wordDict = ["cats", "dog", "sand", "and", "cat"]
 输出:
 []
 * @author ljp
 * @date 2020/11/1 13:38
 */
public class _困难_140_单词拆分II {
    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<String>(Arrays.asList(new String[]{"cat", "cats", "and", "sand", "dog"}));
        List<String> ans = new _困难_140_单词拆分II().wordBreak(s, wordDict);
        ans.size();
    }

    /**
     * 超时
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        WordNode root = create(wordDict);
        List<String> ans = new ArrayList<>();
        dfs(ans, new StringBuilder(), s, 0, root);
        return ans;
    }
    private void dfs(List<String> ans, StringBuilder sb, String s, int index, WordNode root){
        if(index == s.length()){
            ans.add(sb.substring(0, sb.length() - 1));
            return;
        }
        int i = index;
        WordNode p = root;
        while(p != null && i < s.length()){
            int num = s.charAt(i) - 'a';
            p = p.nextWord[num];
            i++;
            if(p != null && p.end){
                sb.append(s.substring(index, i));
                sb.append(" ");
                dfs(ans, sb, s, i, root);
                sb.setLength(sb.length() == 0 ? 0 : sb.length() - s.substring(index, i).length() - 1);
            }
        }
    }
    //创建单词表
    private WordNode create(List<String> wordDict){
        WordNode root = new WordNode();
        WordNode p;
        for(String s : wordDict){
            p = root;
            for(char ch : s.toCharArray()){
                int next = ch - 'a';
                if(p.nextWord[next] == null){
                    p.nextWord[next] = new WordNode();
                }
                p = p.nextWord[next];
            }
            p.end = true;
        }
        return root;
    }
    class WordNode{
        WordNode[] nextWord = new WordNode[26];
        boolean end;
    }

    /*************分界线*********************************************************/
    /**
     * 官方题解：记忆化搜索
     * @param s
     * @param wordDict
     * @return
    执行用时：
    8 ms, 在所有 Java 提交中击败了77.56%的用户
    内存消耗：
    38.9 MB, 在所有 Java 提交中击败了81.34%的用户
     */
    public List<String> wordBreak2(String s, List<String> wordDict) {
        List<String> breakList = new ArrayList<>();
        List<List<String>> wordBreaks = backtrack(s, 0, new HashSet<>(wordDict), new HashMap<>());
        for(List<String> wordBreak : wordBreaks){
            breakList.add(String.join(" ", wordBreak));
        }
        return breakList;
    }

    /**
     *
     * @param s 字符串
     * @param index map的key
     * @param wordSet 单词表Set集合
     * @param map [index, n)能组成的句子的集合 （key为index， value为List<List<String>>)
     * @return
     */
    private List<List<String>> backtrack(String s, int index, Set<String> wordSet, Map<Integer, List<List<String>>> map){
        if(!map.containsKey(index)){//剪枝
            List<List<String>> wordBreaks = new LinkedList<>();
            if(index == s.length()){//到了终点，就新建一个List，存放句子
                wordBreaks.add(new ArrayList<>());
            }else{
                for(int i =  index + 1; i <= s.length(); i++) {
                    //提取[index, i)子串
                    String word = s.substring(index, i);
                    //如果包含word，先处理单词word后面的子串
                    if(wordSet.contains(word)){
                        List<List<String>> nextWordBreaks = backtrack(s, i, wordSet, map);
                        for(List<String> nextWordBreak : nextWordBreaks){//遍历后面的每一条路径
                            LinkedList<String> wordBreak = new LinkedList<>(nextWordBreak);
                            //在头部加上word这个单词
                            wordBreak.offerFirst(word);
                            wordBreaks.add(wordBreak);
                        }
                    }
                }
            }
            map.put(index, wordBreaks);
        }
        return map.get(index);
    }
}

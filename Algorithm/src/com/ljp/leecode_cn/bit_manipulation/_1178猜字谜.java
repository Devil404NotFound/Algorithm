package com.ljp.leecode_cn.bit_manipulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1178. 猜字谜【困难】
 外国友人仿照中国字谜设计了一个英文版猜字谜小游戏，请你来猜猜看吧。

 字谜的迷面 puzzle 按字符串形式给出，如果一个单词 word 符合下面两个条件，那么它就可以算作谜底：

 单词 word 中包含谜面 puzzle 的第一个字母。
 单词 word 中的每一个字母都可以在谜面 puzzle 中找到。
 例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage", 和 "baggage"；而 "beefed"（不含字母 "a"）以及 "based"（其中的 "s" 没有出现在谜面中）。
 返回一个答案数组 answer，数组中的每个元素 answer[i] 是在给出的单词列表 words 中可以作为字谜迷面 puzzles[i] 所对应的谜底的单词数目。



 示例：

 输入：
 words = ["aaaa","asas","able","ability","actt","actor","access"],
 puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
 输出：[1,1,3,2,4,0]
 解释：
 1 个单词可以作为 "aboveyz" 的谜底 : "aaaa"
 1 个单词可以作为 "abrodyz" 的谜底 : "aaaa"
 3 个单词可以作为 "abslute" 的谜底 : "aaaa", "asas", "able"
 2 个单词可以作为 "absoryz" 的谜底 : "aaaa", "asas"
 4 个单词可以作为 "actresz" 的谜底 : "aaaa", "asas", "actt", "access"
 没有单词可以作为 "gaswxyz" 的谜底，因为列表中的单词都不含字母 'g'。


 提示：

 1 <= words.length <= 10^5
 4 <= words[i].length <= 50
 1 <= puzzles.length <= 10^4
 puzzles[i].length == 7
 words[i][j], puzzles[i][j] 都是小写英文字母。
 每个 puzzles[i] 所包含的字符都不重复。
 */
public class _1178猜字谜 {
    public static void main(String[] args) {
        String[] words = {"aaaa","asas","able","ability","actt","actor","access"};
        String[] puzzles = {"aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"};
        List<Integer> res = findNumOfValidWords(words, puzzles);
        List<Integer> res2 = findNumOfValidWords4(words, puzzles);
        System.out.println(res.equals(res2));
        System.out.println(res2);
    }

    /**
     * 超时
     * @param words
     * @param puzzles
     * @return
     */
    public static List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        List<Integer> res = new ArrayList<>();//结果
        int[] wordsHash = new int[words.length];//words字符串转换为二进制映射
        int[] puzzlesHash = new int[puzzles.length]; //puzzles字符串转换为二进制映射
        int[] puzzlesHashHead = new int[puzzles.length];//puzzles字符串的首字母二进制映射
        //转换二进制映射
        for (int i = 0; i < words.length; i++) {
            char[] ch = words[i].toCharArray();
            for (int j = 0; j < ch.length; j++) {
                wordsHash[i] |= (1 << (ch[j] - 'a'));
            }
        }
        //转换二进制映射
        for (int i = 0; i < puzzles.length; i++) {
            char[] ch = puzzles[i].toCharArray();
            puzzlesHashHead[i] |= (1 << (ch[0] - 'a'));
            for (int j = 0; j < ch.length; j++) {
                puzzlesHash[i] |= (1 << (ch[j] - 'a'));
            }
        }
        //开始统计每个puzzles元素的谜底数量
        int count;
        for (int i = 0; i < puzzlesHash.length; i++) {
            count = 0;
            for (int j = 0; j < words.length; j++) {
                if(((puzzlesHashHead[i] & wordsHash[j]) != 0) && (puzzlesHash[i] & wordsHash[j]) == wordsHash[j]){
                    count++;
                }
            }
            res.add(count);
        }
        return res;
    }

    /**
     * 小优化失败
     * 第9个就超时（List的创建速度比数组慢了十几倍，遍历也慢）
     * @param words
     * @param puzzles
     * @return
     */
    public static List<Integer> findNumOfValidWords2(String[] words, String[] puzzles) {
        List<Integer> res = new ArrayList<>();//结果
        List<Integer> wordsHash = new ArrayList<>();//存储不同字母的数量不大于7的个数words
        
        //转换二进制映射
        int count, hash, bit;
        for (int i = 0; i < words.length; i++) {
            char[] ch = words[i].toCharArray();
            count = 0;
            hash = 0;
            for (int j = 0; j < ch.length; j++) {
                bit = (1 << (ch[j] - 'a'));
                if((bit & hash) == 0){
                    count++;
                    hash |= bit;
                }
            }if(count <= 7){
                wordsHash.add(hash);
            }
        }
        int hashHead;
        for (int i = 0; i < puzzles.length; i++) {
            //转换二进制映射
            char[] ch = puzzles[i].toCharArray();
            hashHead = (1 << (ch[0] - 'a'));
            hash = 0;
            for (int j = 0; j < ch.length; j++) {
                hash |= (1 << (ch[j] - 'a'));
            }
            //统计
            int size = wordsHash.size();
            count = 0;
            for (int j = 0; j < size; j++) {
                if((hashHead & wordsHash.get(j)) != 0 && (hash & wordsHash.get(j)) == wordsHash.get(j)){
                    count++;
                }
            }
            res.add(count);
        }
        return res;
    }

    /**
     * 结合方法的优化，再把List换成数组
     * @param words
     * @param puzzles
     * @return
     */
    public static List<Integer> findNumOfValidWords3(String[] words, String[] puzzles) {
        List<Integer> res = new ArrayList<>();//结果
        int[] wordsHash = new int[words.length];//记录words元素的掩码
        int number = 0;
        //转换二进制映射
        int count, hash, bit;
        for (int i = 0; i < words.length; i++) {
            char[] ch = words[i].toCharArray();
            count = 0;
            hash = 0;
            for (int j = 0; j < ch.length; j++) {
                bit = (1 << (ch[j] - 'a'));
                if((bit & hash) == 0){
                    count++;
                    hash |= bit;
                }
            }if(count <= 7){
                wordsHash[number++] = hash;
            }
        }
        int hashHead;
        for (int i = 0; i < puzzles.length; i++) {
            //转换二进制映射
            char[] ch = puzzles[i].toCharArray();
            hashHead = (1 << (ch[0] - 'a'));
            hash = 0;
            for (int j = 0; j < ch.length; j++) {
                hash |= (1 << (ch[j] - 'a'));
            }
            //统计
            count = 0;
            for (int j = 0; j < number; j++) {
                if((hashHead & wordsHash[j]) != 0 && (hash & wordsHash[j]) == wordsHash[j]){
                    count++;
                }
            }
            res.add(count);
        }
        return res;
    }

    /**
     * 大神解法
     * @param words
     * @param puzzles
     * @return
     * 执行用时 :
    86 ms, 在所有 Java 提交中击败了89.71%的用户
    内存消耗 :
    54.8 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public static List<Integer> findNumOfValidWords4(String[] words, String[] puzzles) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> mapbit = new HashMap<>(); //key表示words出现该模式的整数表示，value表示出现的次数
        int mask, count;
        //统计整个word的每个掩码的次数
        for (int i = 0; i < words.length; i++) {
            mask = 0;
            count = 0;
            char[] ch = words[i].toCharArray();
            for (int j = 0; j < ch.length; j++) {
                mask |= 1 << (ch[j] - 'a');
            }
            count = mapbit.getOrDefault(mask, 0) + 1;
            mapbit.put(mask, count);
        }
        for (int i = 0; i < puzzles.length; i++) {
            char[] ch = puzzles[i].toCharArray();
            mask = 0;
            //将puzzle也转换成掩码
            for (int j = 0; j < ch.length; j++) {
                mask |= 1 << (ch[j] - 'a');
            }
            //遍历puzzle的子掩码，累加
            count = 0;
            for (int j = mask; j != 0; j = (j - 1) & mask) {
                //判断子掩码是否首字母为puzzle的首字母（即只取有首字母的子掩码）
                if((1 << (ch[0] - 'a') & j) != 0){
                    count += mapbit.getOrDefault(j,0);
                }
            }
            res.add(count);
        }
        return res;
    }
}

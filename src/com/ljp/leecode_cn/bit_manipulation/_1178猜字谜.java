package com.ljp.leecode_cn.bit_manipulation;

import java.util.*;

/** 每日一题 2021.02.26
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
            }
            if(count <= 7){
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

    /**
     * 2021.02.26每日一题 （超时）
     * @param words
     * @param puzzles
     * @return
     */
    public List<Integer> findNumOfValidWords5(String[] words, String[] puzzles) {
        int n = words.length;
        int[] hashPuzzles = new int[puzzles.length];//puzzles掩码表示
        int[] firstChar = new int[n]; //记录puzzles首字母
        int[] hashWords = new int[n]; //words掩码表示
        //words转变为掩码
        for(int i = 0; i < n; ++i) {
            for(char c : words[i].toCharArray()) {
                hashWords[i] |= 1 << (c - 'a');
            }
        }
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < hashPuzzles.length; ++i) {
            //puzzles转变为掩码
            firstChar[i] |= 1 << (puzzles[i].charAt(0) - 'a');
            for(char c : puzzles[i].toCharArray()) {
                hashPuzzles[i] |= 1 << (c - 'a');
            }
            //统计word可以作为谜底的个数
            int num = 0;
            for(int j = 0; j < hashWords.length; ++j) {
                if(((firstChar[i] & hashWords[j]) != 0) && ((hashWords[j] & hashPuzzles[i]) == hashWords[j])) {
                    num++;
                }
            }
            ans.add(num);
        }
        return ans;
    }

    /**
     * 官方题解一：二进制的状态压缩
     * @param words
     * @param puzzles
     * @return
    执行用时：
    99 ms, 在所有 Java 提交中击败了33.70%的用户
    内存消耗：
    56.4 MB, 在所有 Java 提交中击败了14.13%的用户
     */
    public List<Integer> findNumOfValidWords6(String[] words, String[] puzzles) {
        Map<Integer, Integer> frequency = new HashMap<Integer, Integer>();

        for (String word : words) {
            int mask = 0;
            for (int i = 0; i < word.length(); ++i) {
                char ch = word.charAt(i);
                mask |= (1 << (ch - 'a'));
            }
            if (Integer.bitCount(mask) <= 7) {
                frequency.put(mask, frequency.getOrDefault(mask, 0) + 1);
            }
        }

        List<Integer> ans = new ArrayList<Integer>();
        for (String puzzle : puzzles) {
            int total = 0;

            // 枚举子集方法一
            // for (int choose = 0; choose < (1 << 6); ++choose) {
            //     int mask = 0;
            //     for (int i = 0; i < 6; ++i) {
            //         if ((choose & (1 << i)) != 0) {
            //             mask |= (1 << (puzzle.charAt(i + 1) - 'a'));
            //         }
            //     }
            //     mask |= (1 << (puzzle.charAt(0) - 'a'));
            //     if (frequency.containsKey(mask)) {
            //         total += frequency.get(mask);
            //     }
            // }

            // 枚举子集方法二
            int mask = 0;
            for (int i = 1; i < 7; ++i) {
                mask |= (1 << (puzzle.charAt(i) - 'a'));
            }
            int subset = mask;
            do {
                int s = subset | (1 << (puzzle.charAt(0) - 'a'));
                if (frequency.containsKey(s)) {
                    total += frequency.get(s);
                }
                subset = (subset - 1) & mask;
            } while (subset != mask);

            ans.add(total);
        }
        return ans;
    }
    TrieNode root;

    /**
     * 官方题解二：字典树
     * @param words
     * @param puzzles
     * @return
    执行用时：
    66 ms, 在所有 Java 提交中击败了98.91%的用户
    内存消耗：
    69.2 MB, 在所有 Java 提交中击败了8.69%的用户
     */
    public List<Integer> findNumOfValidWords7(String[] words, String[] puzzles) {
        root = new TrieNode();

        for (String word : words) {
            // 将 word 中的字母按照字典序排序并去重
            char[] arr = word.toCharArray();
            Arrays.sort(arr);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < arr.length; ++i) {
                if (i == 0 || arr[i] != arr[i - 1]) {
                    sb.append(arr[i]);
                }
            }
            // 加入字典树中
            add(root, sb.toString());
        }

        List<Integer> ans = new ArrayList<Integer>();
        for (String puzzle : puzzles) {
            char required = puzzle.charAt(0);
            char[] arr = puzzle.toCharArray();
            Arrays.sort(arr);
            ans.add(find(new String(arr), required, root, 0));
        }
        return ans;
    }

    public void add(TrieNode root, String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            if (cur.child[ch - 'a'] == null) {
                cur.child[ch - 'a'] = new TrieNode();
            }
            cur = cur.child[ch - 'a'];
        }
        ++cur.frequency;
    }

    // 在回溯的过程中枚举 puzzle 的所有子集并统计答案
    // find(puzzle, required, cur, pos) 表示 puzzle 的首字母为 required, 当前搜索到字典树上的 cur 节点，并且准备枚举 puzzle 的第 pos 个字母是否选择（放入子集中）
    // find 函数的返回值即为谜底的数量
    public int find(String puzzle, char required, TrieNode cur, int pos) {
        // 搜索到空节点，不合法，返回 0
        if (cur == null) {
            return 0;
        }
        // 整个 puzzle 搜索完毕，返回谜底的数量
        if (pos == 7) {
            return cur.frequency;
        }

        // 选择第 pos 个字母
        int ret = find(puzzle, required, cur.child[puzzle.charAt(pos) - 'a'], pos + 1);

        // 当 puzzle.charAt(pos) 不为首字母时，可以不选择第 pos 个字母
        if (puzzle.charAt(pos) != required) {
            ret += find(puzzle, required, cur, pos + 1);
        }

        return ret;
    }

    class TrieNode {
        int frequency;
        TrieNode[] child;

        public TrieNode() {
            frequency = 0;
            child = new TrieNode[26];
        }
    }
}

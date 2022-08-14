package com.ljp.leecode_cn.string;

import java.util.*;

/** 每日一题 2020.11.05
 * @author ljp
 * @date 2020/11/5 9:26
127. 单词接龙
给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：

每次转换只能改变一个字母。
转换过程中的中间单词必须是字典中的单词。
说明:

如果不存在这样的转换序列，返回 0。
所有单词具有相同的长度。
所有单词只由小写字母组成。
字典中不存在重复的单词。
你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
示例 1:

输入:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

输出: 5

解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
返回它的长度 5。
示例 2:

输入:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

输出: 0

解释: endWord "cog" 不在字典中，所以无法进行转换。
 */
public class _中等_127_单词接龙 {
    public static void main(String[] args) {
        String beginWord = "qa";
        String endWord = "sq";
        List<String> wordList = new ArrayList<>(Arrays.asList(new String[]{"si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"}));
//        int ans = new _中等_127_单词接龙().ladderLength(beginWord, endWord, wordList);
//        System.out.println(ans);
        int ans = new _中等_127_单词接龙().ladderLength2(beginWord, endWord, wordList);
        System.out.println(ans);
    }

    /**
     * DFS 超时
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        int count = solve(beginWord, endWord, wordList, wordSet);
        return count < wordList.size() ? count : 0;
    }
    private int solve(String beginWord, String endWord, List<String> wordList, Set<String> wordSet) {
        if(!wordSet.contains(endWord)){
            return wordList.size() + 1;
        }
        if(isChange(beginWord, endWord)){
            return 2;
        }
        wordSet.remove(endWord);
        Set<String> preSet = getPreSet(endWord, wordSet);
        int count = wordList.size();
        Iterator<String> it = preSet.iterator();
        while(it.hasNext()){
            String preWord = it.next();
            count = Math.min(count, solve(beginWord, preWord, wordList, wordSet));
        }
        wordSet.add(endWord);
        return count + 1;
    }
    //获取endWord只差一个字母的单词集合
    private Set<String> getPreSet(String endWord, Set<String> wordSet){
        Set<String> preSet = new HashSet<>();
        Iterator<String> it = wordSet.iterator();
        while(it.hasNext()){
            String preWord = it.next();
            if(isChange(endWord,preWord)){
                preSet.add(preWord);
            }
        }
        return preSet;
    }
    //判断两个单词是否只相差一个字母
    private boolean isChange(String str1, String str2){
        if(str1.length() != str2.length()){
            return false;
        }
        int count = 0, i = 0;
        while(count <= 1 && i < str1.length()){
            if(str1.charAt(i) != str2.charAt(i)){
                count++;
            }
            i++;
        }
        return count == 1;
    }

    /**
     * 构建邻接矩阵使用BFS连接最小路径
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
    执行用时：
    597 ms, 在所有 Java 提交中击败了31.45%的用户
    内存消耗：
    154.9 MB, 在所有 Java 提交中击败了5.09%的用户
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord);
        int len = wordList.size();
        int[][] dp = new int[len][len];
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(wordList.get(i), i);
        }
        if(!map.containsKey(endWord)){
            return 0;
        }
        for (int i = 0; i < len; i++) {
            String str1 = wordList.get(i);
            for (int j = i + 1; j < len; j++) {
                String str2 = wordList.get(j);
                if(isChange(str1, str2)){
                    int x = map.get(str1);
                    int y = map.get(str2);
                    dp[x][y] = dp[y][x] = 1;
                }
            }
        }
        //计算路径
        int start = map.get(beginWord);
        int end = map.get(endWord);
        int count = 1;
        Deque<Integer> deque = new LinkedList<>();
        boolean[] flag = new boolean[len];
        deque.add(start);
        while(!deque.isEmpty()){
            int size = deque.size();
            count++;
            for (int i = 0; i < size; i++) {
                int next = deque.remove();
                for (int j = 0; j < len; j++) {
                    if(dp[next][j] != 0 && !flag[j]){
                        deque.offer(j);
                        flag[j] = true;
                        if(map.get(endWord) == j){
                            return count;
                        }
                    }
                }
            }
        }
        return 0;
    }

    /**
     * 官方题解一：广度优先搜索 + 优化建图
    执行用时：
    31 ms, 在所有 Java 提交中击败了86.40%的用户
    内存消耗：
    48.5 MB, 在所有 Java 提交中击败了5.09%的用户
     */
    Map<String, Integer> wordId = new HashMap<String, Integer>();
    List<List<Integer>> edge = new ArrayList<List<Integer>>();
    int nodeNum = 0;

    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);
        if (!wordId.containsKey(endWord)) {
            return 0;
        }
        //记录路径
        int[] dis = new int[nodeNum];
        Arrays.fill(dis, Integer.MAX_VALUE);
        int beginId = wordId.get(beginWord), endId = wordId.get(endWord);
        dis[beginId] = 0;

        Queue<Integer> que = new LinkedList<Integer>();
        que.offer(beginId);
        while (!que.isEmpty()) {
            int x = que.poll();
            if (x == endId) {
                return dis[endId] / 2 + 1;//多了虚拟节点，得到的距离为实际距离的两倍，同时并未计算起点对答案的贡献
            }
            for (int it : edge.get(x)) {
                if (dis[it] == Integer.MAX_VALUE) {
                    dis[it] = dis[x] + 1;
                    que.offer(it);
                }
            }
        }
        return 0;
    }


    public void addEdge(String word) {
        addWord(word);
        int id1 = wordId.get(word);
        char[] array = word.toCharArray();
        int length = array.length;
        for (int i = 0; i < length; ++i) {
            char tmp = array[i];
            array[i] = '*';
            String newWord = new String(array);
            addWord(newWord);
            int id2 = wordId.get(newWord);
            edge.get(id1).add(id2);
            edge.get(id2).add(id1);
            array[i] = tmp;
        }
    }

    public void addWord(String word) {
        if (!wordId.containsKey(word)) {
            wordId.put(word, nodeNum++);
            edge.add(new ArrayList<Integer>());
        }
    }

    /**
     * 官方题解二：双向广度优先遍历搜索
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
    执行用时：
    28 ms, 在所有 Java 提交中击败了87.03%的用户
    内存消耗：
    46.8 MB, 在所有 Java 提交中击败了8.85%的用户
     */
    public int ladderLength4(String beginWord, String endWord, List<String> wordList) {
            for(String word : wordList){
                addEdge(word);
            }
            addEdge(beginWord);
            if(!wordId.containsKey(endWord)){
                return 0;
            }
            int[] disBegin = new int[nodeNum];
            Arrays.fill(disBegin, Integer.MAX_VALUE);
            int beginId = wordId.get(beginWord);
            disBegin[beginId] = 0;
            Deque<Integer> dequeBegin = new LinkedList<>();
            dequeBegin.offer(beginId);

            int[] disEnd = new int[nodeNum];
            Arrays.fill(disEnd, Integer.MAX_VALUE);
            int endId = wordId.get(endWord);
            disEnd[endId] = 0;
            Deque<Integer> dequeEnd = new LinkedList<>();
            dequeEnd.offer(endId);
            while(!dequeBegin.isEmpty() && !dequeEnd.isEmpty()){
                int dequeBeginSize = dequeBegin.size();
                for (int i = 0; i < dequeBeginSize; i++) {
                    int nodeBegin = dequeBegin.remove();
                    if(disEnd[nodeBegin] != Integer.MAX_VALUE){
                        return (disBegin[nodeBegin] + disEnd[nodeBegin]) / 2 + 1;
                    }
                    for(int next : edge.get(nodeBegin)){
                        if(disBegin[next] == Integer.MAX_VALUE){
                            disBegin[next] = disBegin[nodeBegin] + 1;
                            dequeBegin.offer(next);
                        }
                    }
                }

                int dequeEndSize = dequeEnd.size();
                for (int i = 0; i < dequeEndSize; i++) {
                    int nodeEnd = dequeEnd.remove();
                    if(disBegin[nodeEnd] != Integer.MAX_VALUE){
                        return (disBegin[nodeEnd] + disEnd[nodeEnd]) / 2 + 1;
                    }
                    for(int next : edge.get(nodeEnd)){
                        if(disEnd[next] == Integer.MAX_VALUE){
                            disEnd[next] = disEnd[nodeEnd] + 1;
                            dequeEnd.offer(next);
                        }
                    }
                }
            }
            return 0;
    }
}

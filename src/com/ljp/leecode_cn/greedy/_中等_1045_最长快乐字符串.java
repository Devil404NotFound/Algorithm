package com.ljp.leecode_cn.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1405. 最长快乐字符串
 如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。

 给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：

 s 是一个尽可能长的快乐字符串。
 s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。
 s 中只含有 'a'、'b' 、'c' 三种字母。
 如果不存在这样的字符串 s ，请返回一个空字符串 ""。



 示例 1：

 输入：a = 1, b = 1, c = 7
 输出："ccaccbcc"
 解释："ccbccacc" 也是一种正确答案。
 示例 2：

 输入：a = 2, b = 2, c = 1
 输出："aabbc"
 示例 3：

 输入：a = 7, b = 1, c = 0
 输出："aabaa"
 解释：这是该测试用例的唯一正确答案。


 提示：

 0 <= a, b, c <= 100
 a + b + c > 0
 */
public class _中等_1045_最长快乐字符串 {
    /**
     *贪心算法
     * 总结：
     * 很多类似求长度的length()，charAt()等方法，直接调用，不要想着怎么优化这些方法。否则做题太难受了，先做出来，再想优化
     * @param a
     * @param b
     * @param c
     * @return
     * 执行用时 :
    1 ms, 在所有 Java 提交中击败了84.24%的用户
    内存消耗 :
    36.9 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public String longestDiverseString(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        int[][] arr = {{a, 0}, {b, 1}, {c, 2}};
        int num = 0;
        char ch;
        char last = 'a';
        Comparator<int[]> myComparator = new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o2[0] - o1[0];
            }};
        while(true){
            Arrays.sort(arr, myComparator);
            ch = (char)(arr[0][1] + 'a');
            if(sb.length() > 1 && sb.charAt(sb.length() - 1) == ch && sb.charAt(sb.length() - 2) == ch){
                if(arr[1][0] > 0){
                    arr[1][0]--;
                    sb.append((char)(arr[1][1] + 'a'));
                }else{
                    break;
                }
            }else{
                if(arr[0][0] > 0){
                    arr[0][0]--;
                    sb.append((char)(arr[0][1] + 'a'));
                }else{
                    break;
                }
            }
        }
        return sb.toString();
    }
}

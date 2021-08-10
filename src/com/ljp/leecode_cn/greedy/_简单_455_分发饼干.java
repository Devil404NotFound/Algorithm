package com.ljp.leecode_cn.greedy;

import java.util.Arrays;

/** 每日一题 2020.12.25
 * 455. 分发饼干
 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。对每个孩子 i ，都有一个胃口值 gi ，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j ，都有一个尺寸 sj 。如果 sj >= gi ，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。

 注意：

 你可以假设胃口值为正。
 一个小朋友最多只能拥有一块饼干。

 示例 1:

 输入: [1,2,3], [1,1]

 输出: 1

 解释:
 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
 所以你应该输出1。
 示例 2:

 输入: [1,2], [1,2,3]

 输出: 2

 解释:
 你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
 你拥有的饼干数量和尺寸都足以让所有孩子满足。
 所以你应该输出2.
 */
public class _简单_455_分发饼干 {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;//这里其实i就是答案，不必再开一个count来存孩子数
        int i = 0, j = 0;
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j]) {
                count++;
                i++;
                j++;
            } else {
                j++;
            }
        }
        return count;
    }

    /**
     * 每日一题时想到的方法，贪心
     * @param g
     * @param s
     * @return
    执行用时：
    8 ms, 在所有 Java 提交中击败了88.98%的用户
    内存消耗：
    39.2 MB, 在所有 Java 提交中击败了68.07%的用户
     */
    public int findContentChildren2(int[] g, int[] s) {
        int n = Math.min(g.length, s.length);
        int max= 0;
        Arrays.sort(g);
        Arrays.sort(s);
        for(int i = 0, j = 0; i < g.length; ++i) {
            while(j < s.length && g[i] > s[j]) {
                ++j;
            }
            if(j == s.length) {
                break;
            }
            ++max;
            ++j;
        }
        return max;
    }
}

package com.ljp.leecode_cn.greedy;

/**
 * 1221. 分割平衡字符串
 在一个「平衡字符串」中，'L' 和 'R' 字符的数量是相同的。

 给出一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。

 返回可以通过分割得到的平衡字符串的最大数量。



 示例 1：

 输入：s = "RLRRLLRLRL"
 输出：4
 解释：s 可以分割为 "RL", "RRLL", "RL", "RL", 每个子字符串中都包含相同数量的 'L' 和 'R'。
 示例 2：

 输入：s = "RLLLLRRRLR"
 输出：3
 解释：s 可以分割为 "RL", "LLLRRR", "LR", 每个子字符串中都包含相同数量的 'L' 和 'R'。
 示例 3：

 输入：s = "LLLLRRRR"
 输出：1
 解释：s 只能保持原样 "LLLLRRRR".


 提示：

 1 <= s.length <= 1000
 s[i] = 'L' 或 'R'
 分割得到的每个字符串都必须是平衡字符串。
 */

public class _简单_1221_分割平衡字符串 {
    public static void main(String[] args) {
        String s = "RLRRLLRLRL";
        System.out.println(balancedStringSplit(s));
    }

    /**
     *
     * @param s
     * @return
     * 执行用时 :
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗 :
    38 MB, 在所有 Java 提交中击败了5.00%的用户
     */
    public static int balancedStringSplit(String s) {
        char[] ch = s.toCharArray();
        int l = 0, r = 0;//还可以优化，只用n，左就+，右就-
        int sum = 0;
        for (int i = 0; i < ch.length; i++) {
            if(ch[i] == 'L'){
                l++;
            }else {
                r++;
            }
            if(l == r){
                sum++;
                l = 0;
                r = 0;
            }
        }
        return sum;
    }
}

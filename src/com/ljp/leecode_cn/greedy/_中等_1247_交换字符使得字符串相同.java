package com.ljp.leecode_cn.greedy;

/**
 * 1247. 交换字符使得字符串相同
 有两个长度相同的字符串 s1 和 s2，且它们其中 只含有 字符 "x" 和 "y"，你需要通过「交换字符」的方式使这两个字符串相同。

 每次「交换字符」的时候，你都可以在两个字符串中各选一个字符进行交换。

 交换只能发生在两个不同的字符串之间，绝对不能发生在同一个字符串内部。也就是说，我们可以交换 s1[i] 和 s2[j]，但不能交换 s1[i] 和 s1[j]。

 最后，请你返回使 s1 和 s2 相同的最小交换次数，如果没有方法能够使得这两个字符串相同，则返回 -1 。



 示例 1：

 输入：s1 = "xx", s2 = "yy"
 输出：1
 解释：
 交换 s1[0] 和 s2[1]，得到 s1 = "yx"，s2 = "yx"。
 示例 2：

 输入：s1 = "xy", s2 = "yx"
 输出：2
 解释：
 交换 s1[0] 和 s2[0]，得到 s1 = "yy"，s2 = "xx" 。
 交换 s1[0] 和 s2[1]，得到 s1 = "xy"，s2 = "xy" 。
 注意，你不能交换 s1[0] 和 s1[1] 使得 s1 变成 "yx"，因为我们只能交换属于两个不同字符串的字符。
 示例 3：

 输入：s1 = "xx", s2 = "xy"
 输出：-1
 示例 4：

 输入：s1 = "xxyyxyxyxx", s2 = "xyyxyxxxyx"
 输出：4


 提示：

 1 <= s1.length, s2.length <= 1000
 s1, s2 只包含 'x' 或 'y'。
 */
public class _中等_1247_交换字符使得字符串相同 {
    /**
     *
     * @param s1
     * @param s2
     * @return
     * 执行用时 :
    1 ms, 在所有 Java 提交中击败了85.33%的用户
    内存消耗 :
    37.1 MB, 在所有 Java 提交中击败了50.00%的用户
     */
    public int minimumSwap(String s1, String s2) {
        /*
        //如果先转换，耗时0ms
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();*/
        int count = 0;
        int countx = 0;//如果需要交换的x为单数，则需要多交换一次
        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)){
                count++;
                if(s1.charAt(i) == 'x'){
                    countx++;
                }
            }
        }
        if((count & 1) == 1){
            return -1;
        }else{
            return (count >> 1) + (countx & 1);
        }
    }
}

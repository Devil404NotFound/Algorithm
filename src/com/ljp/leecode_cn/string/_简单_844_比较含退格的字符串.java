package com.ljp.leecode_cn.string;

/** 每日一题 2020.10.19
 844. 比较含退格的字符串
 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。

 注意：如果对空文本输入退格字符，文本继续为空。



 示例 1：

 输入：S = "ab#c", T = "ad#c"
 输出：true
 解释：S 和 T 都会变成 “ac”。
 示例 2：

 输入：S = "ab##", T = "c#d#"
 输出：true
 解释：S 和 T 都会变成 “”。
 示例 3：

 输入：S = "a##c", T = "#a#c"
 输出：true
 解释：S 和 T 都会变成 “c”。
 示例 4：

 输入：S = "a#c", T = "b"
 输出：false
 解释：S 会变成 “c”，但 T 仍然是 “b”。


 提示：

 1 <= S.length <= 200
 1 <= T.length <= 200
 S 和 T 只含有小写字母以及字符 '#'。


 进阶：

 你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
 * @author ljp
 * @date 2020/10/19 23:08
 */
public class _简单_844_比较含退格的字符串 {
    public static void main(String[] args) {
        String S = "y#fo##f";
        String T = "y#f#o##f";
        new _简单_844_比较含退格的字符串().backspaceCompare2(S,T);
    }
    /**
     * 暴力解法
     * @param S
     * @param T
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了96.52%的用户
    内存消耗：
    36.4 MB, 在所有 Java 提交中击败了99.30%的用户
     */
    public boolean backspaceCompare(String S, String T) {
        return build(S).equals(build(T));
    }
    private String build(String str){
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()){
            if(c == '#'){
                sb.setLength(sb.length() == 0 ? 0 : sb.length() - 1);
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
    /**
     *一一匹配，添加一个边界
     * @param S
     * @param T
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了96.52%的用户
    内存消耗：
    36.4 MB, 在所有 Java 提交中击败了99.59%的用户
     */
    public boolean backspaceCompare2(String S, String T){
        //预处理：自定义一个边界
        S  = "a" + S;
        T = "a" + T;
        char[] chS = S.toCharArray();
        char[] chT = T.toCharArray();
        int i = chS.length - 1;
        int j = chT.length - 1;
        int count1 = 0, count2 = 0;
        while(i >= 0 && j >= 0){
            //下标0为边界，不用等于0
            while(i > 0 && (chS[i] == '#' || count1 > 0)){
                if(chS[i] == '#'){
                    count1++;
                }else{
                    count1--;
                }
                i--;
            }
            while(j > 0 && (chT[j] == '#' || count2 > 0)){
                if(chT[j] == '#'){
                    count2++;
                }else{
                    count2--;
                }
                j--;
            }
            if(i < 0 || j < 0 || chS[i] != chT[j]){
                break;
            }
            i--;
            j--;
        }
        return i == -1 && j == -1;
    }
}

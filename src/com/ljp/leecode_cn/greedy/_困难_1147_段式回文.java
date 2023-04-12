package com.ljp.leecode_cn.greedy;

/** 每日一题 2023.04.12
 * @author lijunpeng
 * @date 2023-04-12 20:09
 * @Description
1147. 段式回文
你会得到一个字符串 text 。你应该把它分成 k 个子字符串 (subtext1, subtext2，…， subtextk) ，要求满足:

subtexti 是 非空 字符串
所有子字符串的连接等于 text ( 即subtext1 + subtext2 + ... + subtextk == text )
对于所有 i 的有效值( 即 1 <= i <= k ) ，subtexti == subtextk - i + 1 均成立
返回k可能最大值。

示例 1：
输入：text = "ghiabcdefhelloadamhelloabcdefghi"
输出：7
解释：我们可以把字符串拆分成 "(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)"。

示例 2：
输入：text = "merchant"
输出：1
解释：我们可以把字符串拆分成 "(merchant)"。

示例 3：
输入：text = "antaprezatepzapreanta"
输出：11
解释：我们可以把字符串拆分成 "(a)(nt)(a)(pre)(za)(tpe)(za)(pre)(a)(nt)(a)"。

提示：
1 <= text.length <= 1000
text 仅由小写英文字符组成
 */
public class _困难_1147_段式回文 {
    public static void main(String[] args) {
        String text = "ghiabcdefhelloadahelloabcdefghi";
        int i = new _困难_1147_段式回文().longestDecomposition2(text);
        System.out.println(i);
    }

    /**
     * 贪心算法
     执行用时：
     1 ms, 在所有 Java 提交中击败了65.47%的用户
     内存消耗：
     41.5 MB, 在所有 Java 提交中击败了20.63%的用户
     */
    public int longestDecomposition(String text) {
        int count = 0;
        int k = 0;
        int len = text.length();
        while(k * 2 < len) {
            int charLen = 1;
            int leftBegin = k;
            int rightEnd = len - k;
            // 左边结束位置不能大于右边开始位置（可以重叠，因为字符串截取规则是左开右闭）
            while(leftBegin + charLen <= rightEnd - charLen) {
                int rightBegin = rightEnd - charLen;
                int leftEnd = leftBegin + charLen;
                String left = text.substring(leftBegin, leftEnd);
                String right = text.substring(rightBegin, rightEnd);
                // 对比两个是否相同
                if(left.equals(right)) {
                    count += 1;
                    break;
                }
                charLen++;
            }
             count += 1;
            k += charLen;
        }
        return count;
    }

    /**
     执行用时：
     15 ms, 在所有 Java 提交中击败了5.38%的用户
     内存消耗：
     41.8 MB, 在所有 Java 提交中击败了6.73%的用户
     */
    public int longestDecomposition2(String text) {
        int ans = 0;
        int len = text.length();
        String left = "";
        String right = "";
        for (int i = 0; i < (len >> 1); i++) {
            left = left + text.charAt(i);
            right = text.charAt(len - i - 1) + right;
            if(left.equals(right)) {
                ans += 2;
                left = "";
                right = "";
            }
        }
        if((len & 1) == 1 || !"".equals(left)) {
            ++ans;
        }
        return ans;
    }
}

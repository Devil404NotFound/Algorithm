package com.ljp.leecode_cn.string;

import java.util.Deque;
import java.util.LinkedList;

/**
  每日一题 201.03.09
 * @author lijunpeng
 * @date 2021/3/9 0:32
 *
1047. 删除字符串中的所有相邻重复项
给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。

在 S 上反复执行重复项删除操作，直到无法继续删除。

在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。



示例：

输入："abbaca"
输出："ca"
解释：
例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。


提示：

1 <= S.length <= 20000
S 仅由小写英文字母组成。
 */
public class _简单_1047_删除字符串中的所有相邻重复项 {
    /**
     *
     * @param S
     * @return
    执行用时：
    25 ms, 在所有 Java 提交中击败了57.66%的用户
    内存消耗：
    39.2 MB, 在所有 Java 提交中击败了30.05%的用户
     */
    public String removeDuplicates(String S) {
        Deque<Character> deque = new LinkedList<>();
        char[] charArray = S.toCharArray();
        int i = 0;
        int len = charArray.length;
        while(i < len) {
            while(i < len && !deque.isEmpty() && deque.peek() == charArray[i]) {
                deque.pop();
                ++i;
            }
            if(i < len) {
                deque.push(charArray[i]);
            }
            ++i;
        }
        StringBuilder sb = new StringBuilder();
        while(!deque.isEmpty()) {
            sb.append(deque.pop());
        }
        return sb.reverse().toString();
    }
}

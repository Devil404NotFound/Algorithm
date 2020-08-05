package com.ljp.leecode_cn.offer;

/**
 * 剑指 Offer 05. 替换空格
 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。



 示例 1：

 输入：s = "We are happy."
 输出："We%20are%20happy."


 限制：

 0 <= s 的长度 <= 10000
 */
public class _简单_05_替换空格 {
    /**
     *
     * @param s
     * @return
     * 执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    37.4 MB, 在所有 Java 提交中击败了76.65%的用户
     */
    public String replaceSpace(String s) {
        return s.replace(" ", "%20");
    }
}

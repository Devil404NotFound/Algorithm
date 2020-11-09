package com.ljp.leecode_cn.math;

/**
 67. 二进制求和
 给你两个二进制字符串，返回它们的和（用二进制表示）。

 输入为 非空 字符串且只包含数字 1 和 0。



 示例 1:

 输入: a = "11", b = "1"
 输出: "100"
 示例 2:

 输入: a = "1010", b = "1011"
 输出: "10101"


 提示：

 每个字符串仅由字符 '0' 或 '1' 组成。
 1 <= a.length, b.length <= 10^4
 字符串如果不是 "0" ，就都不含前导零。
 * @author ljp
 * @date 2020/10/31 21:19
 */
public class _简单_67_二进制求和 {
    /**
     *
     * @param a
     * @param b
     * @return
    执行用时：
    2 ms, 在所有 Java 提交中击败了99.01%的用户
    内存消耗：
    37.3 MB, 在所有 Java 提交中击败了78.49%的用户
     */
    public String addBinary(String a, String b) {
        if("0".equals(a)){
            return b;
        }
        if("0".equals(b)){
            return a;
        }
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int c = 0;
        while(i >= 0 || j >= 0){
            c += i >= 0 ? a.charAt(i--) - '0' : 0;
            c += j >= 0 ? b.charAt(j--) - '0' : 0;
            sb.append(c % 2);
            c  /= 2;
        }
        if(c != 0){
            sb.append(c);
        }
        return sb.reverse().toString();
    }
}

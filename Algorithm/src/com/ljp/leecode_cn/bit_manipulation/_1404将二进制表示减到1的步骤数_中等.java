package com.ljp.leecode_cn.bit_manipulation;

/**
 * 1404. 将二进制表示减到 1 的步骤数【中等】
 给你一个以二进制形式表示的数字 s 。请你返回按下述规则将其减少到 1 所需要的步骤数：

 如果当前数字为偶数，则将其除以 2 。

 如果当前数字为奇数，则将其加上 1 。

 题目保证你总是可以按上述规则将测试用例变为 1 。



 示例 1：

 输入：s = "1101"
 输出：6
 解释："1101" 表示十进制数 13 。
 Step 1) 13 是奇数，加 1 得到 14
 Step 2) 14 是偶数，除 2 得到 7
 Step 3) 7  是奇数，加 1 得到 8
 Step 4) 8  是偶数，除 2 得到 4
 Step 5) 4  是偶数，除 2 得到 2
 Step 6) 2  是偶数，除 2 得到 1
 示例 2：

 输入：s = "10"
 输出：1
 解释："10" 表示十进制数 2 。
 Step 1) 2 是偶数，除 2 得到 1
 示例 3：

 输入：s = "1"
 输出：0


 提示：

 1 <= s.length <= 500
 s 由字符 '0' 或 '1' 组成。
 s[0] == '1'
 */
public class _1404将二进制表示减到1的步骤数_中等 {
    /**
     * 执行用时 :
     0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗 :
     37.7 MB, 在所有 Java 提交中击败了100.00%的用户
     * @param s
     * @return
     */
    public int numSteps(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        boolean carry = false;
        int count = 0;
        char bit = '1';
        int i = s.length() - 1;
        while (i > 0) {
            if (s.charAt(i) == '1') {
                count += 2;
                i--;
                carry = true;
                break;
            }
            count++;
            i--;
        }
        if (i == 0 && !carry) {
            return count;
        }
        while (i >= 0) {
            if (s.charAt(i) == '0') {
                count += 2;
            } else {
                count++;
            }
            i--;
        }
        return count;
    }
}

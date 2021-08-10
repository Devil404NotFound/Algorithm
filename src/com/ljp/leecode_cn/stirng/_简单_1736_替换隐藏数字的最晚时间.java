package com.ljp.leecode_cn.stirng;

/** 每日一题 2021.07.24
 * @author lijunpeng
 * @date 2021/7/24 18:41
 * @Description
1736. 替换隐藏数字得到的最晚时间
给你一个字符串 time ，格式为 hh:mm（小时：分钟），其中某几位数字被隐藏（用 ? 表示）。

有效的时间为 00:00 到 23:59 之间的所有时间，包括 00:00 和 23:59 。

替换 time 中隐藏的数字，返回你可以得到的最晚有效时间。



示例 1：

输入：time = "2?:?0"
输出："23:50"
解释：以数字 '2' 开头的最晚一小时是 23 ，以 '0' 结尾的最晚一分钟是 50 。
示例 2：

输入：time = "0?:3?"
输出："09:39"
示例 3：

输入：time = "1?:22"
输出："19:22"


提示：

time 的格式为 hh:mm
题目数据保证你可以由输入的字符串生成有效的时间
通过次数21,156提交次数47,773
 */
public class _简单_1736_替换隐藏数字的最晚时间 {
    /**
     * ？？？
     * @param time
     * @return

     */
    public String maximumTime(String time) {
        char[] chars = time.toCharArray();
        String ans = "";
        if('?' == chars[0]) {
            ans += (chars[1] < '4' || chars[1] == '?' ) ? '2' : '1';
        } else{
            ans += chars[0];
        }
        if('?' == chars[1]) {
            ans += ans.charAt(0) == '2' ? '3' : '9';
        }else {
            ans += chars[1];
        }
        ans += chars[2];
        if('?' == chars[3]) {
            ans += '5';
        }else {
            ans += chars[3];
        }
        if('?' == chars[4]) {
            ans += '9';
        }else {
            ans += chars[4];
        }
        return ans;
    }

    /**
     *   官方题解：贪心
     * @param time
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    36.6 MB, 在所有 Java 提交中击败了63.18%的用户
     */
    public String maximumTime2(String time) {
        char[] timeArray = time.toCharArray();
        if('?' == timeArray[0]) {
            timeArray[0] = (timeArray[1] > '3' && timeArray[1] <= '9') ? '1' :'2';
        }
        if('?' == timeArray[1]) {
            timeArray[1] = timeArray[0] == '2' ? '3' : '9';
        }
        if('?' == timeArray[3]) {
            timeArray[3] = '5';
        }
        if('?' == timeArray[4]) {
            timeArray[4] = '9';
        }
        return new String(timeArray);
    }


}

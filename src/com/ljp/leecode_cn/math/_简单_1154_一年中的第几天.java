package com.ljp.leecode_cn.math;

import sun.text.resources.FormatData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 每日一题 2020.10.31
 1154. 一年中的第几天
 给你一个按 YYYY-MM-DD 格式表示日期的字符串 date，请你计算并返回该日期是当年的第几天。

 通常情况下，我们认为 1 月 1 日是每年的第 1 天，1 月 2 日是每年的第 2 天，依此类推。每个月的天数与现行公元纪年法（格里高利历）一致。



 示例 1：

 输入：date = "2019-01-09"
 输出：9
 示例 2：

 输入：date = "2019-02-10"
 输出：41
 示例 3：

 输入：date = "2003-03-01"
 输出：60
 示例 4：

 输入：date = "2004-03-01"
 输出：61


 提示：

 date.length == 10
 date[4] == date[7] == '-'，其他的 date[i] 都是数字。
 date 表示的范围从 1900 年 1 月 1 日至 2019 年 12 月 31 日。
 * @author ljp
 * @date 2020/10/31 17:43
 */
public class _简单_1154_一年中的第几天 {
    public int dayOfYear(String date) {
        /**
         执行用时：
         2 ms, 在所有 Java 提交中击败了83.13%的用户
         内存消耗：
         37 MB, 在所有 Java 提交中击败了59.72%的用户
         */
        String[] strDate = date.split("-");
        int year = Integer.valueOf(strDate[0]);
        int month = Integer.valueOf(strDate[1]);
        int day = Integer.valueOf(strDate[2]);
        int num = 0;
        switch(month){
            case 12:
                num += 30;
            case 11:
                num += 31;
            case 10:
                num += 30;
            case 9:
                num += 31;
            case 8:
                num += 31;
            case 7:
                num += 30;
            case 6:
                num += 31;
            case 5:
                num += 30;
            case 4:
                num += 31;
            case 3:
                num += isLeap(year) ? 29 : 28;
            case 2:
                num += 31;
        }
        return num + day;
    }
    private boolean isLeap(int year){
        if(year % 400 == 0) {
            return true;
        }else if(year % 4 == 0 && year % 100 != 0){
            return true;
        }
        return false;
    }

    /**
     * 方法二
     * @param date
     * @return
    执行用时：
    2 ms, 在所有 Java 提交中击败了83.13%的用户
    内存消耗：
    36.7 MB, 在所有 Java 提交中击败了88.66%的用户
     */
    public int dayOfYear2(String date) {
        String[] strDate = date.split("-");
        int year = Integer.valueOf(strDate[0]);
        int month = Integer.valueOf(strDate[1]);
        int day = Integer.valueOf(strDate[2]);
        int[] months = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30};
        int num = 0;
        for (int i = 1; i < month; i++) {
            num += months[i];
        }
        if(month > 2 && isLeap(year)){
            num++;
        }
        return num + day;
    }
}

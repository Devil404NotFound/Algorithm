package com.ljp.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author lijunpeng
 * @date 2021/5/30 10:50
 * @Description
字符串分隔
描述
•连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组；
•长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。

输入描述：
连续输入字符串(输入多次,每个字符串长度小于100)

输出描述：
输出到长度为8的新字符串数组

示例1
输入：
abc
123456789
复制
输出：
abc00000
12345678
90000000

 */
public class _04字符串分隔 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            list.add(sc.nextLine());
        }
        List<String> result = addZone(list);
        for(String str : result) {
            System.out.println(str);
        }
    }
    private static List<String> addZone(List<String> list) {
        String zone = "00000000";
        List<String> result = new ArrayList<>();
        for(String str : list) {
            int len = str.length();
            int i = 0;
            while(i + 8 < len) {
                result.add(str.substring(i, i + 8));
                i += 8;
            }
            result.add(str.substring(i) + zone.substring(0, 8 - len + i));
        }
        return result;
    }
}

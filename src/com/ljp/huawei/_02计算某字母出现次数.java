package com.ljp.huawei;

import java.util.Scanner;

/**
 * @author lijunpeng
 * @date 2021/5/30 9:55
 * @Description 计算某字母出现次数
 *
描述
写出一个程序，接受一个由字母、数字和空格组成的字符串，和一个字母，然后输出输入字符串中该字母的出现次数。不区分大小写，字符串长度小于500。

输入描述：
第一行输入一个由字母和数字以及空格组成的字符串，第二行输入一个字母。

输出描述：
输出输入字符串中含有该字符的个数。

示例1
输入：
ABCabc
A
复制
输出：
2

 */
public class _02计算某字母出现次数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String match = sc.nextLine();
        System.out.println(countStr(str, match));

    }
    public static int countStr(String str, String match) {
        int count = 0;
        char[] chars = str.toLowerCase().toCharArray();
        char mat = match.toLowerCase().toCharArray()[0];
        for(int i = 0; i < chars.length; ++i) {
            if(chars[i] == mat) {
                ++count;
            }
        }

        return count;
    }
}

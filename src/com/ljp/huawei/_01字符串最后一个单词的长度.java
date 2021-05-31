package com.ljp.huawei;

import java.util.Scanner;

/**
 * @author lijunpeng
 * @date 2021/5/30 9:38
 * @Description 字符串最后一个单词的长度
描述
计算字符串最后一个单词的长度，单词以空格隔开，字符串长度小于5000。

输入描述：
输入一行，代表要计算的字符串，非空，长度小于5000。

输出描述：
输出一个整数，表示输入字符串最后一个单词的长度。

示例1
输入：
hello nowcoder
复制
输出：
8
复制
说明：
最后一个单词为nowcoder，长度为8
 */
public class _01字符串最后一个单词的长度 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        System.out.println(input.length() - input.lastIndexOf(" ") - 1);
    }
}

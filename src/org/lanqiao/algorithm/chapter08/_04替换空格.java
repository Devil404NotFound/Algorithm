package org.lanqiao.algorithm.chapter08;

/**
 * 请编写一个方法,将字符串中的空格全部替换为“%20”。
 * 假定该字符串有足够的空间存放新增的字符,并且知道字符串的真突长度(小于等于1000),
 * 同时保证字符串由大小写的英文字母组成。
 * 给定一个 string inistring为原始的串,以及串的长度 int len,返回替换后的 string。
 * 测试样例:Mr John Smith”,13
 * 返回:"Mr%20john%20Smith
 *
 * Hello  World”,12
 * 返回:" Hello%20%20World
 */
public class _04替换空格 {
    public static void main(String[] args) {
        String str = "Hello  World";
        String result = replaceMent(str);
        System.out.println(result);
    }

    private static String replaceMent(String str) {
        //正常匹配
//        return str.replace(" ","%20");
        //正则匹配
        return  str.replaceAll("\\s","%20");
    }
}

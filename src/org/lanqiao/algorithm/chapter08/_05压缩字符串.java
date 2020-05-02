package org.lanqiao.algorithm.chapter08;

/**
 * 利用字符重复出现的次数,编写一个方法,实现基本的字符串压缩功能
 * 比如,字符串“ aabcccccaaa"经压缩会变成“"a2b1c5a3”
 * 若压缩后的字符书没有变短,则返回原先的字符串
 * 给定一个 string inistring为待压缩的串(长度小于等于10000)
 * 保证串内字符均由大小写英文字母组成,返回一个 string为所求的压缩后就未変化的
 * 测试样例aabcccccaaa返回:"a2b1c5a3"
 */
public class _05压缩字符串 {
    public static void main(String[] args) {
        String str = "ab";
        String result = zipper(str);
        System.out.println(result);
    }

    private static String zipper(String str) {
        if("".equals(str)){
            return str;
        }
        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        int count = 1;
        char last = chars[0];
        sb.append(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            if(chars[i]==last){
                count++;
            }else{
                sb.append(count).append(chars[i]);
                count = 1;
            }
            last = chars[i];
        }
        sb.append(count);
        if(sb.length() >= chars.length){
            return str;
        }
        return sb.toString();
    }
}

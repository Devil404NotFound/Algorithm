package org.lanqiao.algorithm.chapter08;

/**
 * 移字符串中连续出现的K个可以用扫描字符数组的解法,
 * 但是用正则表达式更为快
 */
public class _09去掉连续出现k次的0 {
    public static void main(String[] args) {
        String str = "A00000000";
        String result = removeKZero(str, 3);
        System.out.println(result);
    }

    public static String removeKZero(String str, int k){
        //这个正则不起作用输出help="0{3}"
        //String help = "0{"+ k +"}";
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < k; i++) {
            sb.append("0");
        }
        return str.replace(sb.toString(), "");
    }
}

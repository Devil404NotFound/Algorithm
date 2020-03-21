package org.lanqiao.algorithm.chapter08;

/**
 * 将字符串按单词转,如 here you are翻转成 are you here
 */
public class _08将字符串中按字符翻转 {
    public static void main(String[] args) {
        String str = "here you are";
        String result = worldReverse2(str);
        System.out.println(result);
    }

    /**
     * 第一种方法，直接按空格切割，然后逆序遍历
     * @param str
     * @return
     */
    public static String worldReverse(String str) {
        if("".equals(str)){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String[] strArr = str.split(" ");
        for (int i = strArr.length - 1; i > 0; i--) {
            sb.append(strArr[i]).append(" ");
        }
        sb.append(strArr[0]);
        return sb.toString();
    }
    /**
     * 第二种方法，整体翻转一次，再按单词翻转一次
     */
    public static String worldReverse2(String str){
        StringBuilder sb = new StringBuilder(str);
        String[] strArr = sb.reverse().toString().split(" ");
        sb = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            sb.append(new StringBuilder(strArr[i]).reverse()).append(" ");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}

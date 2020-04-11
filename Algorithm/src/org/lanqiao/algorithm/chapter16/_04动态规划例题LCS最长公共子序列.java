package org.lanqiao.algorithm.chapter16;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.lanqiao.Utils.Util;

/**
 *
 */

public class _04动态规划例题LCS最长公共子序列 {
    public static void main(String[] args) {
        String s1 = "CBDCAaDEF";
        String s2 ="BCADaEG";
        System.out.println(LCS(s1,s2));
    }
    public static String LCS(String s1, String s2){
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        int len1 = ch1.length;
        int len2 = ch2.length;
        int[][] arr = new int[len1 + 1][len2 + 1];
        StringBuilder sb = new StringBuilder();
        int first = s1.indexOf(s2.charAt(0));

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if(ch1[i - 1] == ch2[j - 1]){
                    arr[i][j]  = arr[i - 1][j - 1] + 1;
                }else{
                    arr[i][j] = arr[i][j - 1] > arr[i - 1][j] ? arr[i][j - 1] : arr[i - 1][j];
                }
            }
        }
//        Util.print(arr);
        int i = len1;
        int j = len2;
        while(i > 0 && j > 0 && arr[i][j] > 0){
            if(arr[i][j] == arr[i - 1][j]){
                i--;
            }else if(arr[i][j] == arr[i][j - 1]){
                j--;
            }else{
                sb.append(ch2[j - 1]);
                i--;
                j--;
            }
        }
        return sb.reverse().toString();
    }
}

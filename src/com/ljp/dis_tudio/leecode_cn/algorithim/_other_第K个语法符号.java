package com.ljp.dis_tudio.leecode_cn.algorithim;

public class _other_第K个语法符号 {
    public static void main(String[] args) {
        int N = 10;
        int K = 5;
        int res = kthGrammar(N,K);
        System.out.println(res);
        System.out.println(kthGrammar2(N, K));
    }
    public static int kthGrammar2(int N, int K){
        return Integer.bitCount(K - 1) % 2;
    }
    public static int kthGrammar(int N, int K) {
        if(N < 1 || K < 1){
            return -1;
        }
        if(N == 1){
            return 0;
        }
        String s = "0";
        StringBuilder sb = new StringBuilder(s);
        if(N < 2){
            return Integer.parseInt("" + sb.charAt(K - 1));
        }else{
            for (int i = 2; i <= N; i++) {
                sb = new StringBuilder();
                for (int j = 0; j < s.length(); j++) {
                    if(s.charAt(j) =='0'){
                        sb.append("01");
                    }else{
                        sb.append("10");
                    }
                }
//                s = sb.toString();
                System.out.println(s);
            }
            return Integer.parseInt("" + sb.charAt(K - 1));
        }
    }
}

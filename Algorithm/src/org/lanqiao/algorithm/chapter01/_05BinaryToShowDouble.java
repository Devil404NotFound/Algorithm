package org.lanqiao.algorithm.chapter01;

/**
 * 【0-1间浮点实数的二进制表示】
 * 给定一个介于0和1之间的实数，（如：6.25），类型为double，打印它的二进制表示（0.101）
 * （小数点后的二进制分别表示0.5,0,25，0.125......）
 * 如果该数字无法精确用32位以内的二进制数表示，则输出“ERROR”
 */

public class _05BinaryToShowDouble {
    public static void main(String[] args) {
        double n = 0.625;
        StringBuilder sb = new StringBuilder();
        sb.append("0.");
        while(n > 0){
            n = n * 2;
            if(n >= 1){
                sb.append(1);
                n = n - 1;
            }else {
                sb.append(0);
            }
            if(sb.length() >= 34){
                System.out.println("ERROR");
                return;
            }
        }
        System.out.println(sb);
    }
}

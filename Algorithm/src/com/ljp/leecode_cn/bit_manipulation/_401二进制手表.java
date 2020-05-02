package com.ljp.leecode_cn.bit_manipulation;

import java.util.ArrayList;
import java.util.List;

/**
 * 401. 二进制手表
 * 二进制手表顶部有 4 个 LED 代表小时（0-11），底部的 6 个 LED 代表分钟（0-59）。
 * <p>
 * 每个 LED 代表一个 0 或 1，最低位在右侧。
 * <p>
 * <p>
 * <p>
 * 例如，上面的二进制手表读取 “3:25”。
 * <p>
 * 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
 * <p>
 * 案例:
 * <p>
 * 输入: n = 1
 * 返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 * <p>
 * <p>
 * 注意事项:
 * <p>
 * 输出的顺序没有要求。
 * 小时不会以零开头，比如 “01:00” 是不允许的，应为 “1:00”。
 * 分钟必须由两位数组成，可能会以零开头，比如 “10:2” 是无效的，应为 “10:02”。
 * <p>
 * <p>
 * 执行用时 :
 * 12 ms, 在所有 Java 提交中击败了64.62%的用户
 * 内存消耗 :
 * 38.3 MB, 在所有 Java 提交中击败了10.00%的用户
 */
public class _401二进制手表 {
    public static void main(String[] args) {
        int n = 1;
        System.out.println(readBinaryWatch(n));
        System.out.println(readBinaryWatch2(n));
        System.out.println(readBinaryWatch3(n));
    }

    //第一种
    // 执行用时：12 ms
    //内存消耗：38.3 MB
    public static List<String> readBinaryWatch(int num) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 2048; i++) {
            if (Integer.bitCount(i) == num) {
                int minute = i >> 4;
                int hour = i & 0b1111;
                String time = "";
                if (hour < 12 && minute < 60) {
                    time += hour + ":";
                    time += minute < 10 ? "0" : "";
                    time += minute;
                    list.add(time);
                }
            }
        }
        return list;
    }

    //效率差不多？
    // 执行用时：14 ms
    //内存消耗：38.4 MB
    public static List<String> readBinaryWatch2(int num) {
        List<String> list = new ArrayList<>();
        readBinaryWatch2Core(list, 9, num, 0);
        return list;
    }

    public static void readBinaryWatch2Core(List<String> list, int digit, int num, int bin) {
        if (digit + 1 < num) {
            return;
        }
        if (num == 0) {
            int minute = bin >> 4;
            int hour = bin & 0b1111;
            if (hour < 12 && minute < 60) {
                String time = hour + ":" + (minute < 10 ? "0" : "") + minute;
                list.add(time);
            }
            return;
        }
        readBinaryWatch2Core(list, digit - 1, num, bin);
        bin |= 1 << digit;
        readBinaryWatch2Core(list, digit - 1, num - 1, bin);
        return;
    }

    /**
     * 大佬做法
     * @param num
     * @return
     * 执行用时 :
    17 ms, 在所有 Java 提交中击败了29.50%的用户
    内存消耗 :
    38.7 MB, 在所有 Java 提交中击败了10.00%的用户
     */
    public static List<String> readBinaryWatch3(int num) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if(Integer.bitCount(i) + Integer.bitCount(j) == num){
                    String time = i + ":" + (j < 10 ? "0" : "") + j;
//                    String time =  String.format("%d:%02d",i,j);
                    list.add(time);
                }
            }
        }
        return list;
    }
}

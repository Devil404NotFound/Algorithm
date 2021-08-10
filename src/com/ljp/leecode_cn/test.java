package com.ljp.leecode_cn;

import com.ljp.leecode_cn.dynamic_programming._中等_474_一和零;

/**
 * 一个日常测试类
 * @author ljp
 * @date 2020/10/14 10:38
 */
public class test {
    public static void main(String[] args) {
        String[] strs = new String[]{"10", "0001", "111001", "1", "0"};
        int m = 5, n = 4;
        _中等_474_一和零 test = new _中等_474_一和零();
        System.out.println(test.findMaxForm(strs, m, n));
    }
}

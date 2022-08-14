package com.ljp.leecode_cn.math;

import java.util.ArrayList;
import java.util.List;

/** 每日一题 2022.07.27
 *  @author lijunpeng
 * @date 2022/7/27 22:59
 * @description
592. 分数加减运算
给定一个表示分数加减运算的字符串 expression ，你需要返回一个字符串形式的计算结果。

这个结果应该是不可约分的分数，即最简分数。 如果最终结果是一个整数，例如 2，你需要将它转换成分数形式，其分母为 1。所以在上述例子中, 2 应该被转换为 2/1。



示例 1:

输入: expression = "-1/2+1/2"
输出: "0/1"
示例 2:

输入: expression = "-1/2+1/2+1/3"
输出: "1/3"
示例 3:

输入: expression = "1/3-1/2"
输出: "-1/6"


提示:

输入和输出字符串只包含 '0' 到 '9' 的数字，以及 '/', '+' 和 '-'。
输入和输出分数格式均为 ±分子/分母。如果输入的第一个分数或者输出的分数是正数，则 '+' 会被省略掉。
输入只包含合法的最简分数，每个分数的分子与分母的范围是  [1,10]。 如果分母是1，意味着这个分数实际上是一个整数。
输入的分数个数范围是 [1,10]。
最终结果的分子与分母保证是 32 位整数范围内的有效整数。
 * */

public class _中等_592_分数加减运算 {
    public static void main(String[] args) {
        String expression = "1/3-1/2";
        String result = new _中等_592_分数加减运算().fractionAddition(expression);
        System.out.println(result);
    }
    /** 通过
     * @Author lijunpeng
     * @Date 2022/7/27 23:57
     */
    public String fractionAddition(String expression) {
        List<int[]> list = parseExpression(expression);

        // 获取公共最小公倍数
        int commonDenominator = 1;
        for (int[] ints : list) {
            commonDenominator = lcm(commonDenominator, ints[1]);
        }

        // 通分, 相加
        int sum = 0;
        for (int[] ints : list) {
            sum += ints[0] * (commonDenominator / ints[1]);
        }

        if(sum == 0) {
            return "0/1";
        }
        // 约分
        int maxCommonDivisor = Math.abs(gcd(sum, commonDenominator));
        return new StringBuilder().append(sum / maxCommonDivisor).append("/").append(commonDenominator / maxCommonDivisor).toString();
    }
    // 解析表达式
    private List<int[]> parseExpression(String expression) {
        String newExpression = expression.replace("-", "+-");
        String[] expressionNum = newExpression.split("\\+");
        List<int[]> list = new ArrayList<>();
        for (String s : expressionNum) {
            if(s.isEmpty()) {
                continue;
            }
            String[] nums = s.split("/");
            list.add(new int[]{Integer.valueOf(nums[0]), Integer.valueOf(nums[1])});
        }
        return list;
    }
    // 求最大公约数（辗转相除法）
    private int gcd(int a, int b) {
        int remainder = a % b;
        while(remainder != 0) {
            a = b;
            b = remainder;
            remainder = a % b;
        }
        return b;
    }

    // 求最小公倍数
    private int lcm(int a, int b) {
        // a * b除以最大公约数，就是最小公倍数
        return a * b / gcd(a, b);
    }
}

package com.ljp.leecode_cn.string;

/**
 * 43. 字符串相乘
 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

 示例 1:

 输入: num1 = "2", num2 = "3"
 输出: "6"
 示例 2:

 输入: num1 = "123", num2 = "456"
 输出: "56088"
 说明：

 num1 和 num2 的长度小于110。
 num1 和 num2 只包含数字 0-9。
 num1 和 num2 均不以零开头，除非是数字 0 本身。
 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 */
public class _中等_43_字符串相乘 {
    /**
     *
     * @param num1
     * @param num2
     * @return
     * 执行用时：
    2 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    39.7 MB, 在所有 Java 提交中击败了74.82%的用户
     */
    public String multiply(String num1, String num2) {
        if("0".equals(num1) || "0".equals(num2)){
            return "0";
        }
        char[] ch1 = num1.toCharArray();
        char[] ch2 = num2.toCharArray();
        int[] array1 = new int[ch1.length];
        int[] array2 = new int[ch2.length];
        int[] result = new int[ch1.length + ch2.length];
        //转换为int数组
        for(int i = 0; i < array1.length; i++){
            array1[i] = ch1[i] - '0';
        }
        for(int i = 0; i < array2.length; i++){
            array2[i] = ch2[i] - '0';
        }
        //保存乘积结果
        for(int i = 0; i < array1.length; i++){
            for(int j = 0; j < array2.length; j++){
                result[i + j] += array1[i] * array2[j];
            }
        }
        int digit = 0, temp;
        StringBuilder sb = new StringBuilder();
        for(int i = array1.length + array2.length - 2; i >= 0; i--){
            temp = result[i] + digit;
            sb.append(temp % 10);
            digit = temp / 10;
        }
        if(digit != 0){
            sb.append(digit);
        }
        return sb.reverse().toString();
    }
}

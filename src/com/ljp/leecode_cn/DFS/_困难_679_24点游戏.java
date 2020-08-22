package com.ljp.leecode_cn.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 679. 24 点游戏
 你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。

 示例 1:

 输入: [4, 1, 8, 7]
 输出: True
 解释: (8-4) * (7-1) = 24
 示例 2:

 输入: [1, 2, 1, 2]
 输出: False
 注意:

 除法运算符 / 表示实数除法，而不是整数除法。例如 4 / (1 - 2/3) = 12 。
 每个运算符对两个数进行运算。特别是我们不能用 - 作为一元运算符。例如，[1, 1, 1, 1] 作为输入时，表达式 -1 - 1 - 1 - 1 是不允许的。
 你不能将数字连接在一起。例如，输入为 [1, 2, 1, 2] 时，不能写成 12 + 12 。
 */
public class _困难_679_24点游戏 {
    /**
     * 官方题解
     * 执行用时：
     5 ms, 在所有 Java 提交中击败了60.35%的用户
     内存消耗：
     40 MB, 在所有 Java 提交中击败了13.48%的用户
     */
    static final int TARGET = 24;
    static final double EPSILON = 1e-6;//1*10的负6次方
    static final int ADD = 0, MULTIPLY = 1, SUBTRACT = 2, DIVIDE = 3;
    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            list.add((double)nums[i]);
        }
        return solve(list);
    }
    private boolean solve(List<Double> list){
        if(list.size() == 0){
            return false;
        }
        //判断是否等于24
        if(list.size() == 1){
            return Math.abs(list.get(0) - TARGET) < EPSILON;
        }
        int size = list.size();
        for(int i = 0; i < size; ++i){
            for(int j = 0; j < size; ++j){
                if(i != j){
                    List<Double> list2 = new ArrayList<>();
                    for(int k = 0; k < size; ++k){
                        if(k != i && k != j){
                            list2.add(list.get(k));
                        }
                    }
                    //加减乘除
                    for (int k = 0; k < 4; ++k) {
                            //优化，去重复
                        if(k < 2 && i > j){
                            continue;
                        }
                        switch (k) {
                            case ADD:
                                list2.add(list.get(i) + list.get(j));
                                break;
                            case SUBTRACT:
                                list2.add(list.get(i) - list.get(j));
                                break;
                            case MULTIPLY:
                                list2.add(list.get(i) * list.get(j));
                                break;
                            case DIVIDE:
                                if (Math.abs(list.get(j)) < EPSILON) {
                                    continue;
                                } else {
                                    list2.add(list.get(i) / list.get(j));
                                }
                        }
                        if (solve(list2)) {
                            return true;
                        }
                        list2.remove(list2.size() - 1);
                    }
                }
            }
        }
        return false;
    }
}

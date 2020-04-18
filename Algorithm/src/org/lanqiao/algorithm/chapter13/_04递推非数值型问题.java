package org.lanqiao.algorithm.chapter13;

import java.util.HashSet;
import java.util.Set;

/**
 * 实现一种算法,打印n对括号的全部有效组合(即左右括号正确配对)
 * 示例输入:3
 * 输出:((())), (()()), (())(), ()(()) ()()()
 */
public class _04递推非数值型问题 {
    public static void main(String[] args) {
        int n = 4;
        Set<String> set = parenthisis(n);
        System.out.println(set);
        set = parenthisis2(n);
        System.out.println(set);
    }

    /**
     * 传统递归方法
     * @param n
     * @return
     */
    public static Set<String> parenthisis(int n) {
        Set<String> set = new HashSet<>();
        if(n == 1){
            set.add("()");
            return set;
        }
        Set<String> s_1 = parenthisis(n - 1);
        for (String str : s_1) {
            set.add("()" + str);
            set.add(str + "()");
            set.add("(" + str + ")");
        }
        return set;
    }

    /**
     * 顺序迭代方式（不会栈溢出）
     * @param n
     * @return
     */
    public static Set<String> parenthisis2(int n){
        Set<String> set = new HashSet<String>();
        set.add("()");
        if(n == 1){
            return  set;
        }
        Set<String> res = null;
        for (int i = 1; i < n; i++) {
            res = new HashSet<>();
            for (String str : set){
                res.add("()" + str);
                res.add(str + "()");
                res.add("(" + str +")");
            }
            set = res;
        }
        return res;
    }
}

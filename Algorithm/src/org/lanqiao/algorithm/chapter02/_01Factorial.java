package org.lanqiao.algorithm.chapter02;

/**
 * *
 * 1. 找重复
 * 2. 找变化：一般是传进去的参数
 * 3. 找边界
 *
 */
public class _01Factorial {
    public static void main(String[] args) {
        //阶乘测试
//        int n = 5;
//        int result = funl(n);
//        System.out.println(result);
        //数组求和测试
//        System.out.println(fun2(new int[]{1,2,3,4,2}, 0));
        //字符串翻转测试
//        String str = "abcdef";
//        System.out.println(fun3(str,str.length() - 1));
        //斐波那契序列测试
//        int n = 7;
//        System.out.println(fun4(n));
        //最大公约数测试
//        System.out.println(fun5(36,15));

        //插入排序测试
        int[] num = new int[]{5,4,3,2,6,7,8,19,1,10};
        fun6(num,num.length-1);
        for (int i = 0; i < num.length; i++) {
            System.out.println(num[i]);
        }

    }

    /**
     * 递归实现阶乘
     * @param n
     * @return
     */
    public static int funl(int n){
        if(n == 1) {
            return 1;
        }
        return  n * funl(n -1);
    }

    /**
     * 递归实现数组求和
     * @param num
     * @param begin
     * @return
     */
    public static int fun2(int num[], int begin){
        if(begin == num.length - 1){
            return num[begin];
        }
        return num[begin] + fun2(num, begin+1);
    }

    public static String fun3(String str, int end){
        if(end <= 0) {
            return "" + str.charAt(end);
        }
        return str.charAt(end) + fun3(str,end -1);
    }

    /**
     * 递归实现斐波那契序列
     * @param n
     * @return
     */
    public static int fun4(int n){
        if(n <= 2){
            return 1;
        }
        return fun4(n-1) + fun4(n - 2);
    }

    /**
     * 递归实现最大公约数
     * @param n
     * @param m
     * @return
     */
    public static int fun5(int n, int m){
        if(m == 0){
            return n;
        }
        return fun5(m, n%m);
    }

    /**
     * 递归实现插入排序（为了练习）
     * @param arr
     * @param index
     */
    public static void fun6(int[] arr, int index){
        if(index == 0){
            return;
        }
        fun6(arr,index - 1);
        int x = arr[index];
        int i = index - 1;
        while(i > -1 && x < arr[i]){
            arr[i + 1] = arr[i];
            i--;
        }
        arr[i+1] = x;
    }
}

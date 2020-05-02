package org.lanqiao.algorithm.chapter16;
/*
Serling公司购买长钢条，将其切割为短钢条出售，切割工序本身没有成本支出。公司管理层希望知道最佳的切割方案。
假定我们知道Serling公司出售一段长为i英寸的钢条的价格为pi（i=1,2,3，...）钢条的长度均为整英寸

长度：1 2 3  4  5  6  7  8  9 10
价格：1 5 8 16 10 17 17 20 24 30

 */
public class _02钢条切割 {
    public static void main(String[] args) {
        int[] p = {2, 5, 8, 16, 10, 17, 17, 20, 24, 30};
        int res = maxValue2(p);
        System.out.println(res);
    }
    //栈溢出
    public static int maxValue(int[] p, int length){
        if(length == 0){
            return 0;
        }
        int res = 0;
        int v;
        for (int i = 1; i <= p.length; i++) {
            v = p[i - 1] + maxValue(p, length - i);
            if(res < v){
                res = v;
            }
        }
        return res;
    }

    public static int maxValue2(int[] p){
        int[] arr = new int[p.length + 1];
        for (int i = 1; i <= p.length; i++) {
            //切割，p[j - 1]表示先截j段，剩下的取之前最优的（arr[i - j]
            for (int j = 1; j <= i; j++) {
                int v = p[j - 1] + arr[i - j];
                if(arr[i] < v){
                    arr[i] = v;
                }
            }
        }
        return arr[p.length];
    }
}

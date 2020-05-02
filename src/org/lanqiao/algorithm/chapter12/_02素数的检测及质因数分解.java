package org.lanqiao.algorithm.chapter12;

import java.util.HashMap;
import java.util.Map;

public class _02素数的检测及质因数分解 {
    public static void main(String[] args) {
        long n = 100;
        Map<Integer, Integer> map = primeFactor(n);
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            System.out.print(entry.getKey() + "==" + entry.getValue()+" ");
        }
    }

    /**
     * 判断是否为素数
     * @param num
     * @return
     */
    public static boolean isPrime(long num){
        for (int i = 2; i * i <= num; i++) {
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否为素数，埃氏筛法（借助辅助数组，排除法）没有写实现方法
     * @return
     */
    private boolean isPrimeA() {
        return false;
    }

    /**
     * 质因分解 8 = 2*2*2
     * @param num
     * @return map,key=质数，value=指数
     */
    public static Map<Integer,Integer> primeFactor(long num){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 2; i * i <= num ; i++) {
                while(num % i == 0){
                    Integer v = map.get(i);
                    if(v == null){
                        map.put(i, 1);
                    }else{
                        map.put(i, v+ 1);
                    }
                    num /= i;
                }
        }
        return map;
    }
}

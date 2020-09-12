package org.lanqiao.algorithm.chapter11;


/**
 * 两只青蛙在网上相识了，它们聊得很开心，于是觉得很有必要见一面。
 * 它们很高兴地发现它们住在同一条纬度线上，于是它们约定各自朝西跳，直到碰面为止。
 * 可是它们出发之前忘记了一件很重要的事情，既没有问清楚对方的特征，也没有约定见面的具体位置。
 * 不过青蛙们都是很乐观的，它们觉得只要一直朝着某个方向跳下去，总能碰到对方的。
 * 但是除非这两只青蛙在同一时间跳到同一点上，不然是永远都不可能碰面的。
 * 为了帮助这两只乐观的青蛙，你被要求写一个程序来判断这两只青蛙是否能够碰面，会在什么时候碰面。
 我们把这两只青蛙分别叫做青蛙A和青蛙B，并且规定纬度线上东经0度处为原点，
 由东往西为正方向，单位长度1米，这样我们就得到了一条首尾相接的数轴。
 设青蛙A的出发点坐标是x，青蛙B的出发点坐标是y。青蛙A一次能跳m米，青蛙B一次能跳n米，两只青蛙跳一次所花费的时间相同。纬度线总长L米。
 现在要你求出它们跳了几次以后才会碰面。
 */
import java.util.Scanner;
public class _05青蛙的约会 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long x = sc.nextLong();
        long y = sc.nextLong();
        long m = sc.nextLong();
        long n = sc.nextLong();
        long L = sc.nextLong();
        long res = 0;
        try {
            res = solve(x, y, m, n, L);
            System.out.println(res);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        sc.close();
    }
    public static long solve(long x, long y,long m,long n, long l) throws Exception{
        long d = LinearEquation.linearEquation(m - n, l, y - x);
        long b = l;
        b /= d;
        if(b < 0){
             b = -b;
        }
        long res = (LinearEquation.x%b + b) % b;
        return res;
    }

    /**
     * 定义一个内部类
     */
    private static class LinearEquation{
        static long x;
        static long y;
        static long ext_gcd(long m, long n){
            if(n == 0){
                x = 1;
                return m;
            }
            long res = ext_gcd(n, m%n);
            long x1 = x;
            x = y;
            y = x1 - m / n * y;
            return res;
        }
        static long linearEquation(long a, long b, long m) throws Exception{
            long d = ext_gcd(a, b);
            if(m % d != 0){
                throw new Exception("Impossible");
            }
            long n = m / d;
            x *= n;
            y *= n;
            return d;
        }
    }


    /**
     * 很明显，暴力解法会超时
     * @param x
     * @param y
     * @param m
     * @param n
     * @param l
     * @return
     */
    private static long fun(long x, long y, long m, long n, long l) {
        long count = 0;
        while(x != y){
            x += m;
            y += n;
            x = x%l;
            y = y%l;
            count++;
        }
        return count;
    }

}

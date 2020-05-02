package org.lanqiao.algorithm.chapter11;

/**
 * 对任何整数a、b和它们的最大公约数d,关于未知数X和y的线性丢番图方程(称为表蜀等式)
 * ax+by=m有整数解时当且仅当m是d的倍数。
 * 裴蜀等式有解时必然有无穷多个整数解,
 * 每组解X、y都称为裴蜀数可用扩展欧几里得算法( Extended Euclidean algorithm)求得.
 * 方程12x+42y=6有解
 * 特別地,方程aX+by=1有整数解当且仅当整数a和b互素
 * x = y1
 * y = x1 - a/by1
 *
 */

/**应用：
 * 从昏迷中醒来,小明发现自己被关在X星球的废矿车里矿车停在平直的废弃的轨道上。
 * 他的
 * 才態把矿车准确地停在前方1米远的西前是两个按钮,分别写着“F”和“8”。
 * 小明突然记起来,这两个按钮可以控制矿车在轨道上前进和后退。
 * 按F,会前进97米。按B会后退127米
 * 透过昏暗的灯光,小明看到自己前方1米远正好有个监控探头。
 * 他必须设法使得矿车正好停在摄头的下方,才有机会争取同伴的援助
 * 或许,通过多次操作F和B可以办到。
 * 矿车上的动力已经不太足,黄色的警示灯在默默闪烁
 * 每次进行F就B操作都会消耗一定的能量
 * 小明飞快地计算,至少要多少次操作,地方。
 */
public class _04裴蜀公式 {
    private static long x;
    private static long y;
    public static void main(String[] args) {
        try {
            linearEquation(3,3,1);
            System.out.println(x +" " + y);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 欧几里得扩展
     * @param a
     * @param b
     * @return
     */
    public static long ext_gcd(long a, long b){
        if(b == 0){
            x = 1;
            y = 0;
            return a;
        }
        long res = ext_gcd(b, a%b);
        long x1 = x;
        x = y;
        y = x1 - a / b * y;
        return res;
    }

    /**
     * 线性方程
     * @param a
     * @param b
     * @param m
     * @return d 返回a和b的公约数
     * @throws Exception
     */
    public static long linearEquation(long a, long b, long m) throws Exception{
        long d = ext_gcd(a, b);
        if( m % d != 0){
           throw  new Exception("无解");
        }
        long n = m / d;
        x *= n;
        y *= n;
        return d;
    }
}

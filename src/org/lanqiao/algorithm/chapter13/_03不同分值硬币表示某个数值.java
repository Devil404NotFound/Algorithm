package org.lanqiao.algorithm.chapter13;

/**
 * 假设我们有8种不同面值的硬币(1,2,5,16,20,50,100,200,
 * 用这些硬币组合构成一个给定的数值。
 * 例如n=200,那么一种可能的组合方式为200=3*1+1ォ2+1ォ5+2ォ20+1*50+1*100
 * 问总共有多少种可能的组合方式?
 *
 * (这道题目来自著名编程网站 Projecteuler)类似的题目还有
 *
 * 「华为面试题]1分2分5分的硬币三种,组合成1角,共有多少种组合
 *
 * [新工厂笔试题]有1分,2分,5分,10分四种硬币,每种硬币数量无限,给定分钱,有多少组合可以组成n分钱
 */
public class _03不同分值硬币表示某个数值 {
    public static void main(String[] args) {
        int n = 50;
//        System.out.println(solve(n));
        System.out.println(countWays(n));
        System.out.println(countWays2(n));
    }

    /**
     * 这样无法求结果，当n==6时，1+5算一种，5+1算一种，这就重复了
     * @param n
     * @return
     */
    public static int solve(int n){
        if(n < 0){
            return 0;
        }
        if(n == 0 || n == 1){
            return 1;
        }
        return solve(n - 1) + solve(n - 5) + solve(n - 10) + solve(n - 25);
    }

    /**
     * 传统递归写法
     * @param n
     * @return
     */
    public static int countWays(int n){
        if(n <= 0){
            return 0;
        }
        return countWaysCore(n, new int[]{1,5,10, 25}, 3);
    }
    private static int countWaysCore(int n, int[] coins, int cur){
//        当只剩下一种可以选，就返回1
        if(cur == 0){
            return 1;
        }

        int surplus;
        int res = 0;
        for (int i = 0; i * coins[cur] <= n; i++) {
            surplus = n - i * coins[cur];
            res += countWaysCore(surplus, coins, cur - 1);
        }
        return res;
    }

    /**
     * 二维数组递推写法（动态规划）
     * @param n
     * @return
     */
    public static int countWays2(int n){
        int[][] arr = new int[4][n + 1];
        for (int i = 0; i <= n; i++) {
            arr[0][i] = 1;
        }
        arr[1][0] = 5;
        arr[2][0] = 10;
        arr[3][0] = 25;
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j <= n; j++) {
                int k = j - arr[i][0];
                if(k > 0){
                    //arr[i][0]使用0次和使用1次
                    arr[i][j] = arr[i-1][j] + arr[i][k];
                }else{
                    arr[i][j] = arr[i - 1][j];
                }
                //当刚好减去arr[i][0]==0时，加一个arr[i][0]就是一种情况
                if(k == 0){
                    arr[i][j]++;
                }
            }
        }
        return arr[3][n];
    }

}

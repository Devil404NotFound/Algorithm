package com.ljp.leecode_cn.greedy;

import java.util.Arrays;

/** 每日一题 2020.12.05
 * 621. 任务调度器
 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。

 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。

 你需要计算完成所有任务所需要的最短时间。



 示例 ：

 输入：tasks = ["A","A","A","B","B","B"], n = 2
 输出：8
 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
 在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。


 提示：

 任务的总个数为 [1, 10000]。
 n 的取值范围为 [0, 100]。
 */
public class _中等_621_任务调度器 {
    public static void main(String[] args) {
//        char[] tasks = {'A','A','A','B','B','B','C'};
        char[] tasks = {'A','A','A','A','A','A','B','C','D','E','F','G'};
        int n = 2;
        System.out.println(leastInterval(tasks, n));
        System.out.println(leastInterval2(tasks, n));
        System.out.println(leastInterval3(tasks, n));
    }

    /**
     * 不通过
     * ["A","A","A","A","A","A","B","C","D","E","F","G"]
     * 2
     * @param tasks
     * @param n
     * @return
     */
    public static int leastInterval(char[] tasks, int n) {
        int[] hash = new int[26];
        for(int i = 0; i < tasks.length; i++){
            hash[tasks[i] - 'A']++;
        }
        Arrays.sort(hash);
        int last = hash.length - 1;
        int count = 0;
        while(hash[last] != 0){
            int i = last;
            while(i >= 0 && hash[i] > 0){
                hash[i]--;
                i--;
            }
            if(hash[last] != 0) {
                count += (last - i - 1) > n ? last - i : n + 1;
            }else{
                count += last - i;
            }
        }
        return count;
    }

    /**
     *
     * @param tasks
     * @param n
     * @return
     * 执行用时 :
    5 ms, 在所有 Java 提交中击败了58.05%的用户
    内存消耗 :
    41.7 MB, 在所有 Java 提交中击败了11.11%的用户
     */
    public static int leastInterval2(char[] tasks, int n) {
        int[] hash = new int[26];//映射统计字符数量
        for(int i = 0; i < tasks.length; i++){
            hash[tasks[i] - 'A']++;
        }
        Arrays.sort(hash);//排序
        int last = hash.length - 1;
        int count = 0;
        while(hash[last] != 0){
            int i = last;
            while(i >= 0 && hash[i] > 0){
                hash[i]--;
                i--;
                if(i > 0 && last - i - 1 == n && hash[i] < hash[last]){//当第一个任务冷却期过了，而且数量比当前的任务数量大，就回去再运行第一个任务
                    break;
                }
            }
            //判断是否结束
            if(hash[last] != 0) {
                count += (last - i) > n ? last - i : n + 1;
            }else{
                count += last - i;
            }
            Arrays.sort(hash);
        }
        return count;
    }

    /**排序
     * 官方解法一：思路比我好太多了，太优美
     * @param tasks
     * @param n
     * @return
     * 执行用时 :
    7 ms, 在所有 Java 提交中击败了53.03%的用户
    内存消耗 :
    41.1 MB, 在所有 Java 提交中击败了11.11%的用户
     */
    public static int leastInterval3(char[] tasks, int n) {
        //1. 统计每个字母的频率
        int[] map = new  int[26];
        for (int i = 0; i < tasks.length; i++) {
            map[tasks[i] - 'A']++;
        }
        //2. 排序
        Arrays.sort(map);
        //3. 从最大一个开始，直到最大一个都为0
        int time  = 0;
        while(map[25] > 0){
            int i = 0;
            //4. 循环一个周期
            while(i <= n){
                //没有任务就结束
                if(map[25] == 0){
                    break;
                }
                if(i < 26 && map[25 - i] > 0){
                    map[25 - i]--;
                }
                time++;
                i++;
            }
            //5. 再排序
            Arrays.sort(map);
        }
        return time;
    }
    //队列方法不写了
    /**
     * 官方方法三：设计
     * @param tasks
     * @param n
     * @return
     * 执行用时 :
    2 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗 :
    40.9 MB, 在所有 Java 提交中击败了11.11%的用户
     */
    public static int leastInterval4(char[] tasks, int n) {
        //统计每个出现频率
        int[] map = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            map[tasks[i] - 'A']++;
        }
        //排序
        Arrays.sort(map);
        //当最大频率为p，则至少需要时间（p-1) * (n + 1) + 1
        int max_val = map[25] - 1;
        int idle_slots = max_val * n;//安排好最大频率任务后，剩下的空闲时间
        for (int i = 24; i >= 0 ; i--) {
            idle_slots -= Math.min(map[i], max_val);//防止存在多个最大频率时
        }
        return idle_slots > 0 ? idle_slots + tasks.length : tasks.length;
    }

    /**
     * 设计，评论区大佬思路
     * @param tasks
     * @param n
     * @return
     * 执行用时 :
    2 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗 :
    41.7 MB, 在所有 Java 提交中击败了11.11%的用
     */
    public static int leastInterval5(char[] tasks, int n) {
        //统计词频
        int[] map = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            map[tasks[i] - 'A']++;
        }
        //排序
        Arrays.sort(map);
        //找到最大词频的出现次数
        int maxCount = 0;
        for (int i = 25; i >= 0; i--) {
            if(map[i] != map[25]){
                break;
            }
            maxCount++;
        }
        return Math.max((map[25] - 1) * (n + 1) + maxCount, tasks.length);
    }
}

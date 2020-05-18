package com.ljp.leecode_cn.greedy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 649. Dota2 参议院
 Dota2 的世界里有两个阵营：Radiant(天辉)和 Dire(夜魇)

 Dota2 参议院由来自两派的参议员组成。现在参议院希望对一个 Dota2 游戏里的改变作出决定。他们以一个基于轮为过程的投票进行。在每一轮中，每一位参议员都可以行使两项权利中的一项：

 禁止一名参议员的权利：

 参议员可以让另一位参议员在这一轮和随后的几轮中丧失所有的权利。

 宣布胜利：

 如果参议员发现有权利投票的参议员都是同一个阵营的，他可以宣布胜利并决定在游戏中的有关变化。



 给定一个字符串代表每个参议员的阵营。字母 “R” 和 “D” 分别代表了 Radiant（天辉）和 Dire（夜魇）。然后，如果有 n 个参议员，给定字符串的大小将是 n。

 以轮为基础的过程从给定顺序的第一个参议员开始到最后一个参议员结束。这一过程将持续到投票结束。所有失去权利的参议员将在过程中被跳过。

 假设每一位参议员都足够聪明，会为自己的政党做出最好的策略，你需要预测哪一方最终会宣布胜利并在 Dota2 游戏中决定改变。输出应该是 Radiant 或 Dire。



 示例 1:

 输入: "RD"
 输出: "Radiant"
 解释:  第一个参议员来自  Radiant 阵营并且他可以使用第一项权利让第二个参议员失去权力，因此第二个参议员将被跳过因为他没有任何权利。然后在第二轮的时候，第一个参议员可以宣布胜利，因为他是唯一一个有投票权的人
 示例 2:

 输入: "RDD"
 输出: "Dire"
 解释:
 第一轮中,第一个来自 Radiant 阵营的参议员可以使用第一项权利禁止第二个参议员的权利
 第二个来自 Dire 阵营的参议员会被跳过因为他的权利被禁止
 第三个来自 Dire 阵营的参议员可以使用他的第一项权利禁止第一个参议员的权利
 因此在第二轮只剩下第三个参议员拥有投票的权利,于是他可以宣布胜利


 注意:

 给定字符串的长度在 [1, 10,000] 之间.
 */
public class _中等_649_Dota2参议院    {
    public static void main(String[] args) {
        String senate = "DRRDRDRDRDDRDRDR";
        System.out.println(predictPartyVictory(senate));
    }

    /**
     * 报错
     * @param senate
     * @return
     */
    public static String predictPartyVictory(String senate) {
        //新建2个队列存议员的下标
        Queue<Integer> qR = new LinkedList<>();
        Queue<Integer> qD = new LinkedList<>();
        char[] ch = senate.toCharArray();
        //初始化队列
        for (int i = 0; i < ch.length; i++) {
            if(ch[i] == 'R'){
                qR.offer(i);
            }else if(ch[i] == 'D'){
                qD.offer(i);
            }
        }
        boolean[] flag = new boolean[ch.length];//标记是否被禁
        while(!qR.isEmpty() && !qD.isEmpty()){
            for (int i = 0; i < ch.length; i++) {
                if(flag[i]){
                    continue;
                }
                if(ch[i] == 'R'){
                    if(qD.isEmpty()){
                        break;
                    }else{
                        int j = qD.poll();
                        flag[j] = true;
                    }
                }else if(ch[i] == 'D'){
                    if(qR.isEmpty()){
                        break;
                    }else{
                        int j = qR.poll();
                        flag[j] = true;
                    }
                }
            }
        }
        return qR.isEmpty() ? "Dire" : "Radiant";
    }

    /**
     * 评论区大佬方法
     * @param senate
     * @return
     * 执行用时 :
    12 ms, 在所有 Java 提交中击败了48.67%的用户
    内存消耗 :
    40.6 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public static String predictPartyVictory2(String senate) {
        Queue<Integer> radiant = new LinkedList<>();
        Queue<Integer> dire = new LinkedList<>();
        char[] ch = senate.toCharArray();
        int n = ch.length;
        for (int i = 0; i < n; i++) {
            if(ch[i] == 'R'){
                radiant.offer(i);
            }else{
                dire.offer(i);
            }
        }
        while(!radiant.isEmpty() && !dire.isEmpty()){
            int r = radiant.poll();
            int d = dire.poll();
            if(r < d){
                radiant.offer(r + n);
            }else{
                dire.offer(d + n);
            }
        }
        return radiant.isEmpty() ? "Dire" : "Radiant";
    }

    /**0代表Radiant, 1代表Dire ，people是人数，bans是禁令数
     * 参考官方题解，不好理解，但是很精妙
     * @param senate
     * @return
     * 执行用时 :
    11 ms, 在所有 Java 提交中击败了58.67%的用户
    内存消耗 :
    40.3 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public static String predictPartyVictory3(String senate) {
        Queue<Integer> queue = new LinkedList<>();
        int[] people = {0,0};
        int[] bans = {0,0};
        char[] person = senate.toCharArray();
        //初始化队列
        for (int i = 0; i < person.length; i++) {
            int x = person[i] == 'R' ? 0 : 1;
            people[x]++;
            queue.offer(x);
        }
        while(people[0] > 0 && people[1] > 0){
            int x = queue.poll();
            if(bans[x] > 0){
                //有禁令，禁令减1，人数减1
                bans[x]--;
                people[x]--;
            }else{
                //无禁令，那就给对方一个禁令，自己再回到队列末尾
                bans[x ^ 1]++;
                queue.offer(x);
            }
        }
        return people[0] > 0 ? "Radiant" : "Dire";
    }
}

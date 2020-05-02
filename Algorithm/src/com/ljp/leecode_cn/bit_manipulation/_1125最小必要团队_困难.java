package com.ljp.leecode_cn.bit_manipulation;

import org.lanqiao.Utils.Util;

import java.util.*;

/**
 * 1125. 最小的必要团队
 作为项目经理，你规划了一份需求的技能清单 req_skills，并打算从备选人员名单 people 中选出些人组成一个「必要团队」（ 编号为 i 的备选人员 people[i] 含有一份该备选人员掌握的技能列表）。

 所谓「必要团队」，就是在这个团队中，对于所需求的技能列表 req_skills 中列出的每项技能，团队中至少有一名成员已经掌握。

 我们可以用每个人的编号来表示团队中的成员：例如，团队 team = [0, 1, 3] 表示掌握技能分别为 people[0]，people[1]，和 people[3] 的备选人员。

 请你返回 任一 规模最小的必要团队，团队成员用人员编号表示。你可以按任意顺序返回答案，本题保证答案存在。



 示例 1：

 输入：req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],["nodejs","reactjs"]]
 输出：[0,2]
 示例 2：

 输入：req_skills = ["algorithms","math","java","reactjs","csharp","aws"], people = [["algorithms","math","java"],["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
 输出：[1,2]


 提示：

 1 <= req_skills.length <= 16
 1 <= people.length <= 60
 1 <= people[i].length, req_skills[i].length, people[i][j].length <= 16
 req_skills 和 people[i] 中的元素分别各不相同
 req_skills[i][j], people[i][j][k] 都由小写英文字母组成
 本题保证「必要团队」一定存在
 */
public class _1125最小必要团队_困难 {
    static List<Integer> minList;
    public static void main(String[] args) {
        String[] req_skills = {"java","nodejs","reactjs"};
        List<List<String>> people = new ArrayList<>();
        List<String> skills = new ArrayList<>();
        skills.add("java");
        skills.add("nodejs");
        people.add(skills);
        skills = new ArrayList<>();
        skills.add("nodejs");
        skills.add("reactjs");
        people.add(skills);
        int[] res = smallestSufficientTeam(req_skills,people);
        Util.print(res);
    }

    /**
     * 位运算+递归，超时
     * @param req_skills
     * @param people
     * @return
     */
    public static int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        Map<String, Integer> map = new HashMap<>();
        int req_skills_bit = 0;
        //建立技能位图映射，key为技能名称，value为技能所在的二进制位置
        for (int i = 0; i < req_skills.length; i++) {
            map.put(req_skills[i],1 << i);
            req_skills_bit |= 1 << i;
        }
        int[] peopleBit = new int[people.size()];
        for (int i = 0; i < peopleBit.length; i++) {
            int mask = 0;
            List<Integer> list = new ArrayList<>();
            for(String skill : people.get(i)){
                mask |= map.getOrDefault(skill,0);
            }
            peopleBit[i] = mask;
        }

        minList = new ArrayList<>();
        smallestSufficientTeamCore(new ArrayList<Integer>(), req_skills_bit, peopleBit,0);
        int[] res = new int[minList.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = minList.get(i);
        }
        return res;
    }

    private static void smallestSufficientTeamCore(List<Integer> list, int req_skills_bit, int[] peopleBit,int cur) {
        //出口
        if(req_skills_bit == 0){
            if(minList.size() == 0 || minList.size() > list.size()){
                minList.clear();
                minList.addAll(list);
            }
        }
        if(cur == peopleBit.length){
            return;
        }
        //不选
        smallestSufficientTeamCore(list, req_skills_bit, peopleBit, cur + 1);
        //选
        if(peopleBit[cur] != 0){
            req_skills_bit ^= (req_skills_bit & peopleBit[cur]);
            list.add(cur);
            smallestSufficientTeamCore(list,req_skills_bit, peopleBit, cur + 1);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 大神做法，位运算+dp动态规划，下标是reqSkills的二进制位
     * @param reqSkills
     * @param peoSkills
     * @return
     */
    public int[] smallestSufficientTeam2(String[] reqSkills, List<List<String>> peoSkills) {
        if (reqSkills == null || reqSkills.length == 0) {
            return new int[0];
        }
        int n = reqSkills.length;
        Map<String, Integer> map = new HashMap<>(n);
        // 技能、序号映射
        for (int i = 0; i < reqSkills.length; i++) {
            map.put(reqSkills[i], i);
        }
        int[] dp = new int[(1 << n)];
        Arrays.fill(dp, -1);
        // 技能集合i所需的最小团队编号列表
        List<List<Integer>> team = new ArrayList<>(1 << n);
        for (int i = 0; i < (1 << n); i++) {
            team.add(new LinkedList<>());
        }
        // 集合0表示的技能的最小花费是0
        dp[0] = 0;
        for (int i = 0; i < peoSkills.size(); i++) {
            int now = 0;
            for (String s : peoSkills.get(i)) {
                int x = map.get(s);
                now |= (1 << x);
            }
            for (int j = 0; j < (1 << n); j++) {
                // 更新已经计算过的集合
                if (dp[j] >= 0) {
                    // 将要更新的集合x
                    int x = now | j;
                    // 如果集合没有计算过或者可以优化
                    if (dp[x] == -1 || dp[x] > dp[j] + 1) {
                        dp[x] = dp[j] + 1;
                        team.get(x).clear();
                        team.get(x).addAll(team.get(j));
                        team.get(x).add(i);
                    }
                }
            }
        }
        // team[(1<<n)-1]即最终团队
        int[] ans = new int[team.get((1 << n) - 1).size()];
        int i = 0;
        for (int idx : team.get((1 << n) - 1)) {
            ans[i++] = idx;
        }
        return ans;
    }
}

package com.ljp.leecode_cn.sliding_window;

import java.util.Deque;
import java.util.LinkedList;

/** 每日一题 2022.03.29
 * @author lijunpeng
 * @date 2022/3/29 23:13
 * @Description
2024. 考试的最大困扰度
一位老师正在出一场由 n 道判断题构成的考试，每道题的答案为 true （用 'T' 表示）或者 false （用 'F' 表示）。老师想增加学生对自己做出答案的不确定性，方法是 最大化 有 连续相同 结果的题数。（也就是连续出现 true 或者连续出现 false）。

给你一个字符串 answerKey ，其中 answerKey[i] 是第 i 个问题的正确结果。除此以外，还给你一个整数 k ，表示你能进行以下操作的最多次数：

每次操作中，将问题的正确答案改为 'T' 或者 'F' （也就是将 answerKey[i] 改为 'T' 或者 'F' ）。
请你返回在不超过 k 次操作的情况下，最大 连续 'T' 或者 'F' 的数目。


 */
public class _中等_2024_考试的最大困扰度 {
    /**
    * @Author lijunpeng
    * @Date 2022/3/29 23:13
    * @Description 滑动窗口
    执行用时：19 ms
    内存消耗：47.8 MB
     **/
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int maxF = 0, maxT = 0, kf = k, kt = k;
        int lastF = 0, lastT = 0;
        char[] answerChars = answerKey.toCharArray();
        Deque<Integer> dequeF = new LinkedList<>();
        Deque<Integer> dequeT = new LinkedList<>();
        for(int i = 0; i< answerChars.length; ++i) {
            char ch = answerChars[i];
            if(ch == 'T') {
                dequeF.offer(i);
                --kf;
            }else{
                dequeT.offer(i);
                --kt;
            }
            if(kf < 0) {
                lastF = dequeF.poll() + 1;
                ++kf;
            }
            if(kt < 0) {
                lastT = dequeT.poll() + 1;
                ++kt;
            }
            maxF = Math.max(maxF, i - lastF + 1);
            maxT = Math.max(maxT, i - lastT + 1);
        }
        return Math.max(maxF, maxT);
    }
    /**
    * @Author lijunpeng
    * @Date 2022/3/29 23:17
    * @Description 官方题解：滑动窗口
    **/
    public int maxConsecutiveAnswers2(String answerKey, int k) {
        return Math.max(maxConsecutiveChar(answerKey, k, 'T'), maxConsecutiveChar(answerKey, k, 'F'));
    }

    public int maxConsecutiveChar(String answerKey, int k, char ch) {
        int n = answerKey.length();
        int ans = 0;
        for (int left = 0, right = 0, sum = 0; right < n; right++) {
            sum += answerKey.charAt(right) != ch ? 1 : 0;
            while (sum > k) {
                sum -= answerKey.charAt(left++) != ch ? 1 : 0;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}

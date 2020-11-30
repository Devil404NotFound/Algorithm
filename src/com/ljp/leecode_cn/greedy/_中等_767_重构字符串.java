package com.ljp.leecode_cn.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/** 每日一题 2020.11.30
 * 767. 重构字符串
 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。

 若可行，输出任意可行的结果。若不可行，返回空字符串。

 示例 1:

 输入: S = "aab"
 输出: "aba"
 示例 2:

 输入: S = "aaab"
 输出: ""
 注意:

 S 只包含小写字母并且长度在[1, 500]区间内。
 */
public class _中等_767_重构字符串 {
    public static void main(String[] args) {
        _中等_767_重构字符串 test = new _中等_767_重构字符串();
        String S = "aab";
        System.out.println(test.reorganizeString4(S));
    }
    /**
     * 哈希映射+排序+贪心
     * @param S
     * @return
    执行用时：
    3 ms, 在所有 Java 提交中击败了47.67%的用户
    内存消耗：
    36.7 MB, 在所有 Java 提交中击败了64.89%的用户
     */
    public String reorganizeString(String S) {
        if("".equals(S)){
            return "";
        }
        //定义一个映射表，hash[i][0]表示这个元素的数量，hash[i][1]表示这个元素的字母ASCII值
        int n = S.length();
        int[][] hash = new int[26][2];
        //统计每个字母的数量
        for(char ch : S.toCharArray()){
            int i = ch - 'a';
            ++hash[i][0];
            hash[i][1] = ch;
        }
        Arrays.sort(hash, (a, b) -> b[0] - a[0]);
        int len = (n + 1) / hash[0][0];//分为hash[0][0]组，每一组最多为len
        if(len < 2){//len小于2意味着最多的字母多于字符串长度的一半
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while(hash[0][0] > 0){
            for(int i = 0; hash[i][0] > 0 && i < len; ++i){
                --hash[i][0];
                sb.append((char)hash[i][1]);
            }
            Arrays.sort(hash, (a, b) -> b[0] - a[0]);//重新排序
        }
        return sb.toString();
    }

    /**
     * 自己实现的大顶堆排序(未测试）
     * @param S
     * @return
     */
    public String reorganizeString4(String S) {
        if(S.length() < 2) {
            return S;
        }
        int[][] counts = new int[26][2];
        int maxCounts = 0;
        for(char c : S.toCharArray()) {
            ++counts[c - 'a'][0];
            counts[c - 'a'][1] = c;
            maxCounts = Math.max(maxCounts, counts[c - 'a'][0]);
        }
        if(maxCounts > (S.length() + 1) / 2){
            return "";
        }
        int n = counts.length;
        //初始化大顶堆
        for(int i = n / 2 - 1; i >= 0; --i) {
            maxHeap(counts, i, n);
        }
        StringBuilder sb = new StringBuilder();
        while(counts[0][0] > 0) {
            sb.append((char)counts[0][1]);
            --counts[0][0];
            swap(counts, 0,n - 1);
            maxHeap(counts, 0, n -1);
            if(counts[0][0] > 0){
                sb.append((char)counts[0][1]);
                --counts[0][0];
                swap(counts, 0, n - 1);
                maxHeap(counts, 0, n - 1);
            }
        }
        return sb.toString();
    }
    private void swap(int[][] nums, int i, int j) {
        int[] temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    //大顶堆
    private void maxHeap(int[][] nums, int i, int length){
        int j = 2 * i + 1;
        int[] temp = nums[i];
        while(j < length) {
            if(j + 1 < length && nums[j + 1][0] > nums[j][0]) {
                ++j;
            }
            if(temp[0] < nums[j][0]) {
                nums[i] = nums[j];
                i = j;
                j = 2 * i + 1;
            }else{
                break;
            }
        }
        nums[i] = temp;
    }

    /********************************************一下是官方题解**********************/
    /**
     * 官方题解一：基于最大堆贪心算法
     * @param S
     * @return
    执行用时：
    3 ms, 在所有 Java 提交中击败了48.41%的用户
    内存消耗：
    37.3 MB, 在所有 Java 提交中击败了19.44%的用户
     */
    public String reorganizeString2(String S) {
        if(S.length() < 2) {
            return S;
        }
        int maxCount = 0;
        int[] count = new int[26];
        for(char x : S.toCharArray()) {
            ++count[x - 'a'];
            maxCount = Math.max(maxCount, count[x - 'a']);
        }
        //如果最大值大于字符串长度+1的一半，就无法重构
        if(maxCount > ((S.length() + 1) >> 1)){
            return "";
        }
        //利用优先队列实现最大堆
        PriorityQueue<Character> queue = new PriorityQueue<>(new Comparator<Character>(){
            @Override
            public int compare(Character o1, Character o2) {
               return count[o2 - 'a'] - count[o1 - 'a'];
           }
        });
        //初始化最大堆
        for(char c = 'a'; c <= 'z'; ++c) {
            if(count[c - 'a'] > 0) {
                queue.offer(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while(queue.size() > 1){
            //找出数量最多的两个字母
            Character c1 = queue.poll();
            Character c2 = queue.poll();
            //放到结果字符串中
            sb.append(c1).append(c2);
            //同时给这两个字母的数量-1
            int index1 = c1 - 'a';
            int index2 = c2 - 'a';
            --count[index1];
            --count[index2];
            //如果这两个字母还有，就重新加入到最大堆中
            if(count[index1] > 0){
                queue.offer(c1);
            }
            if(count[index2] > 0) {
                queue.offer(c2);
            }
        }
        //检测最后一个字母是不是还没放进去
        if(queue.size() > 0){
            sb.append(queue.poll());
        }
        return sb.toString();
    }

    /**
     * 官方题解二：基于计数的贪心算法
     * @param S
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了97.65%的用户
    内存消耗：
    36.4 MB, 在所有 Java 提交中击败了97.16%的用户
     */
    public String reorganizeString3(String S){
        if(S.length() < 2) {
            return S;
        }
        //统计字符
        int[] counts = new int[26];
        int maxCounts = 0;
        for(char c : S.toCharArray()) {
            ++counts[c - 'a'];
            maxCounts = Math.max(maxCounts, counts[c - 'a']);
        }
        int length = S.length();
        if(maxCounts > (length + 1) / 2) {
            return "";
        }
        //按照奇位偶位插入字母
        int evenIndex = 0, oddIndex = 1, halfLength = length / 2;
        char[] reorganizeArray = new char[length];
        for(int i = 0; i < 26; i++) {
            char c = (char)('a' + i);
            //首先加奇位，如果counts[i]大于length的一半，就一定要放在偶位上
            while(counts[i] > 0 && counts[i] <= halfLength && oddIndex < length) {
                reorganizeArray[oddIndex] = c;
                --counts[i];
                oddIndex += 2;
            }
            while(counts[i] > 0) {
                reorganizeArray[evenIndex] = c;
                --counts[i];
                evenIndex += 2;
            }
        }
        return String.valueOf(reorganizeArray);
    }
}

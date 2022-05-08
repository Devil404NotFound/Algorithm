package com.ljp.leecode_cn.BFS;

import java.util.*;

/**
 * @author lijunpeng
 * @date 2022/5/7 21:19
 * @description
433. 最小基因变化
基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。

假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。

例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。

给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使 start 变化为 end 所需的最少变化次数。如果无法完成此基因变化，返回 -1 。

注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。



示例 1：

输入：start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
输出：1
示例 2：

输入：start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
输出：2
示例 3：

输入：start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
输出：3


提示：

start.length == 8
end.length == 8
0 <= bank.length <= 10
bank[i].length == 8
start、end 和 bank[i] 仅由字符 ['A', 'C', 'G', 'T'] 组成
 **/

public class _中等_433_最小基因变化 {
    /** 通过
    * @Author lijunpeng
    * @Date 2022/5/7 22:54
    * @Description
    */
    public int minMutation(String start, String end, String[] bank) {
        if(start.equals(end)) {
            return 0;
        }
        int[] flag = new int[bank.length];
        List<String> list = new ArrayList<>();
        for(int i = 0; i < bank.length; ++i) {
            list.add(bank[i]);
        }
        Deque<String> deque = new LinkedList<>();
        deque.offer(start);
        int count = 0;
        while(!deque.isEmpty()) {
            int size = deque.size();
            ++count;
            for(int i = 0; i < size; ++i) {
                String cur = deque.poll();
                ListIterator<String> it = list.listIterator();
                while(it.hasNext()) {
                    String next = it.next();
                    if(isStep(cur, next)) {
                        if(next.equals(end)) {
                            return count;
                        }
                        it.remove();
                        deque.offer(next);
                    }
                }
            }
        }
        return -1;
    }
    private boolean isStep(String start, String end) {
        int count = 0;
        if(start.length() != end.length()) {
            return false;
        }
        int len = start.length();
        for(int i = 0; i < len; ++i) {
            if(start.charAt(i) != end.charAt(i)) {
                count++;
                if(count > 1) {
                    return false;
                }
            }
        }
        return count == 1;
    }
}

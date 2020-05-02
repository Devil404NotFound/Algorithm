package com.ljp.dis_tudio.leecode_cn.gram184weekly;

import org.lanqiao.Utils.Util;

import java.util.*;

/**
 * 给你一个待查数组 queries ，数组中的元素为 1 到 m 之间的正整数。 请你根据以下规则处理所有待查项 queries[i]（从 i=0 到 i=queries.length-1）：

 一开始，排列 P=[1,2,3,...,m]。
 对于当前的 i ，请你找出待查项 queries[i] 在排列 P 中的位置（下标从 0 开始），然后将其从原位置移动到排列 P 的起始位置（即下标为 0 处）。注意， queries[i] 在 P 中的位置就是 queries[i] 的查询结果。
 请你以数组形式返回待查数组  queries 的查询结果。

  

 示例 1：

 输入：queries = [3,1,2,1], m = 5
 输出：[2,1,2,1]
 解释：待查数组 queries 处理如下：
 对于 i=0: queries[i]=3, P=[1,2,3,4,5], 3 在 P 中的位置是 2，接着我们把 3 移动到 P 的起始位置，得到 P=[3,1,2,4,5] 。
 对于 i=1: queries[i]=1, P=[3,1,2,4,5], 1 在 P 中的位置是 1，接着我们把 1 移动到 P 的起始位置，得到 P=[1,3,2,4,5] 。
 对于 i=2: queries[i]=2, P=[1,3,2,4,5], 2 在 P 中的位置是 2，接着我们把 2 移动到 P 的起始位置，得到 P=[2,1,3,4,5] 。
 对于 i=3: queries[i]=1, P=[2,1,3,4,5], 1 在 P 中的位置是 1，接着我们把 1 移动到 P 的起始位置，得到 P=[1,2,3,4,5] 。
 因此，返回的结果数组为 [2,1,2,1] 。
 示例 2：

 输入：queries = [4,1,2,2], m = 4
 输出：[3,1,2,0]
 示例 3：

 输入：queries = [7,5,5,8,3], m = 8
 输出：[6,5,0,7,5]
  

 提示：

 1 <= m <= 10^3
 1 <= queries.length <= m
 1 <= queries[i] <= m

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/queries-on-a-permutation-with-key
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _02_5381_查询带键的排列 {
    public static void main(String[] args) {
        int[] queries = {3,1,2,1};
        int[] res = processQueries(queries, 5);
        Util.print(res);
        res = processQueries2(queries, 5);
        Util.print(res);

    }
    public static int[] processQueries(int[] queries, int m){
        int[] dp = new int[queries.length];
        Set<Integer> set = new HashSet<>();
        for(int i = 1; i < queries.length; ++i){
            set.clear();
            for(int j = i - 1; j >= 0; --j){
                if(queries[i] < queries[j]){
                    if(set.add(queries[j])){
                        ++dp[i];
                    }
                }else if(queries[i] == queries[j]){
                    set.clear();
                    dp[i] = 0;
                    ++j;
                    while(j < i){
                        if(set.add(queries[j])){
                            ++dp[i];
                        }
                        ++j;
                    }
                    dp[i] -= queries[i] - 1;
                    break;
                }
            }
        }
        int[] res = new int[queries.length];
        for(int i = 0; i < queries.length; ++i){
            res[i] = queries[i] - 1 + dp[i];
        }
        return res;
    }

    /**
     * 暴力解决，比上面的还快
     * @param queries
     * @param m
     * @return
     */
    public static int[] processQueries2(int[] queries, int m){
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i <= m; i++) {
            list.add(i);
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            res[i] = list.indexOf(queries[i]);
            Integer remove = list.remove(res[i]);
            list.addFirst(remove);
        }
        return res;
    }
}

package com.ljp.leecode_cn.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1090. 受标签影响的最大值
 我们有一个项的集合，其中第 i 项的值为 values[i]，标签为 labels[i]。

 我们从这些项中选出一个子集 S，这样一来：

 |S| <= num_wanted
 对于任意的标签 L，子集 S 中标签为 L 的项的数目总满足 <= use_limit。
 返回子集 S 的最大可能的 和。



 示例 1：

 输入：values = [5,4,3,2,1], labels = [1,1,2,2,3], num_wanted = 3, use_limit = 1
 输出：9
 解释：选出的子集是第一项，第三项和第五项。
 示例 2：

 输入：values = [5,4,3,2,1], labels = [1,3,3,3,2], num_wanted = 3, use_limit = 2
 输出：12
 解释：选出的子集是第一项，第二项和第三项。
 示例 3：

 输入：values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 1
 输出：16
 解释：选出的子集是第一项和第四项。
 示例 4：

 输入：values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 2
 输出：24
 解释：选出的子集是第一项，第二项和第四项。


 提示：

 1 <= values.length == labels.length <= 20000
 0 <= values[i], labels[i] <= 20000
 1 <= num_wanted, use_limit <= values.length
 */
public class _中等_1090_受标签影响的最大值 {
    /**
     *
     * @param values
     * @param labels
     * @param num_wanted
     * @param use_limit
     * @return
     * 执行用时 :
    16 ms, 在所有 Java 提交中击败了95.71%的用户
    内存消耗 :
    41.4 MB, 在所有 Java 提交中击败了25.00%的用户
     */
    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        int labelsMax = 0;
        for(int i = 0; i < labels.length; i++){
            labelsMax = Math.max(labelsMax, labels[i]);
        }
        //建立一个标记数组，统计选中的label的数量
        int[] flag = new int[labelsMax + 1];
        //转化为二维数组（需要排序）
        int[][] help = new int[values.length][2];
        for (int i = 0; i < values.length; i++) {
            help[i][0] = values[i];
        }
        for (int i = 0; i < labels.length; i++) {
            help[i][1] = labels[i];
        }
        Arrays.sort(help, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });
        int res = 0;
        int count = 0;
        for (int i = 0; i < help.length; i++) {
            if(flag[help[i][1]] < use_limit){
                res += help[i][0];
                count++;
                flag[help[i][1]]++;
                if(count == num_wanted){
                    return res;
                }
            }
        }
        return res;
    }
}

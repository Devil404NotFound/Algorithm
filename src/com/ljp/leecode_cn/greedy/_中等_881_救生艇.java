package com.ljp.leecode_cn.greedy;

import java.util.Arrays;

/**每日一题 2021.08.26
 * @author lijunpeng
 * @date 2021/8/26 23:13
 * @Description
 * 881. 救生艇
 第 i 个人的体重为 people[i]，每艘船可以承载的最大重量为 limit。

 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。

 返回载到每一个人所需的最小船数。(保证每个人都能被船载)。



 示例 1：

 输入：people = [1,2], limit = 3
 输出：1
 解释：1 艘船载 (1, 2)
 示例 2：

 输入：people = [3,2,2,1], limit = 3
 输出：3
 解释：3 艘船分别载 (1, 2), (2) 和 (3)
 示例 3：

 输入：people = [3,5,3,4], limit = 5
 输出：4
 解释：4 艘船分别载 (3), (3), (4), (5)
 提示：

 1 <= people.length <= 50000
 1 <= people[i] <= limit <= 30000
 */
public class _中等_881_救生艇 {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int i = 0;
        int j = people.length - 1;
        int count = 0;
        while(i <= j){
            if(people[j] + people[i] <= limit){
                j--;
                i++;
            }else if(people[j] <= limit){
                j--;
            }else{
                return -1;
            }
            count++;
        }
        return count;
    }
    /** 改进一些
    * @Author lijunpeng
    * @Date 2021/8/26 23:19
    * @Description
    执行用时：
    18 ms, 在所有 Java 提交中击败了41.68%的用户
    内存消耗：
    47.4 MB, 在所有 Java 提交中击败了23.56%的用户
     **/
    public int numRescueBoats2(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0, right = people.length - 1;
        int ans = 0;
        while(left <= right) {
            if(people[left] + people[right] <= limit) {
                ++left;
            }
            --right;
            ++ans;
        }
        return ans;
    }
}

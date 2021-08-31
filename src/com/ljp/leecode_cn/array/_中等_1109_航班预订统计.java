package com.ljp.leecode_cn.array;

/** 每日一题 2021.08.31
 * @author lijunpeng
 * @date 2021/8/31 22:32
 * @Description
1109. 航班预订统计
这里有 n 个航班，它们分别从 1 到 n 进行编号。

有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。

请你返回一个长度为 n 的数组 answer，其中 answer[i] 是航班 i 上预订的座位总数。



示例 1：

输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
输出：[10,55,45,25,25]
解释：
航班编号        1   2   3   4   5
预订记录 1 ：   10  10
预订记录 2 ：       20  20
预订记录 3 ：       25  25  25  25
总座位数：      10  55  45  25  25
因此，answer = [10,55,45,25,25]
示例 2：

输入：bookings = [[1,2,10],[2,2,15]], n = 2
输出：[10,25]
解释：
航班编号        1   2
预订记录 1 ：   10  10
预订记录 2 ：       15
总座位数：      10  25
因此，answer = [10,25]


提示：

1 <= n <= 2 * 104
1 <= bookings.length <= 2 * 104
bookings[i].length == 3
1 <= firsti <= lasti <= n
1 <= seatsi <= 104
 */
public class _中等_1109_航班预订统计 {
    /** 前缀和
    * @Author lijunpeng
    * @Date 2021/8/31 22:39
    * @Description
    执行用时：
    3 ms, 在所有 Java 提交中击败了78.66%的用户
    内存消耗：
    53.5 MB, 在所有 Java 提交中击败了49.46%的用户
     **/
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] start = new int[n + 1]; //统计开始位置的票数
        int[] end = new int[n + 1]; //统计结束位置的票数
        for (int[] booking : bookings) {
            int first = booking[0], last = booking[1], seats = booking[2];
            start[first] += seats;
            end[last] += seats;
        }
        int[] ans = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += start[i + 1] - end[i]; //包含结束位置的票数，因此是i，不是i+1
            ans[i] = sum;
        }
        return ans;
    }
    /** 官方题解：差分
    * @Author lijunpeng
    * @Date 2021/8/31 22:48
    * @Description
    执行用时：
    3 ms, 在所有 Java 提交中击败了78.66%的用户
    内存消耗：
    53 MB, 在所有 Java 提交中击败了81.44%的用户
    **/
    public int[] corpFlightBookings2(int[][] bookings, int n) {
        int[] nums = new int[n];
        for(int[] booking: bookings) {
            nums[booking[0] - 1] += booking[2];
            if(booking[1] < n) {
                nums[booking[1]] -= booking[2];
            }
        }
        for (int i = 1; i < n; i++) {
            nums[i] += nums[i  - 1];
        }
        return nums;
    }
}

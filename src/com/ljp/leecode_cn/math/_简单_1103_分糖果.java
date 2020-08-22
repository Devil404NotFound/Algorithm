package com.ljp.leecode_cn.math;

/**
 * 1103. 分糖果 II
 排排坐，分糖果。

 我们买了一些糖果 candies，打算把它们分给排好队的 n = num_people 个小朋友。

 给第一个小朋友 1 颗糖果，第二个小朋友 2 颗，依此类推，直到给最后一个小朋友 n 颗糖果。

 然后，我们再回到队伍的起点，给第一个小朋友 n + 1 颗糖果，第二个小朋友 n + 2 颗，依此类推，直到给最后一个小朋友 2 * n 颗糖果。

 重复上述过程（每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始），直到我们分完所有的糖果。注意，就算我们手中的剩下糖果数不够（不比前一次发出的糖果多），这些糖果也会全部发给当前的小朋友。

 返回一个长度为 num_people、元素之和为 candies 的数组，以表示糖果的最终分发情况（即 ans[i] 表示第 i 个小朋友分到的糖果数）。



 示例 1：

 输入：candies = 7, num_people = 4
 输出：[1,2,3,1]
 解释：
 第一次，ans[0] += 1，数组变为 [1,0,0,0]。
 第二次，ans[1] += 2，数组变为 [1,2,0,0]。
 第三次，ans[2] += 3，数组变为 [1,2,3,0]。
 第四次，ans[3] += 1（因为此时只剩下 1 颗糖果），最终数组变为 [1,2,3,1]。
 示例 2：

 输入：candies = 10, num_people = 3
 输出：[5,2,3]
 解释：
 第一次，ans[0] += 1，数组变为 [1,0,0]。
 第二次，ans[1] += 2，数组变为 [1,2,0]。
 第三次，ans[2] += 3，数组变为 [1,2,3]。
 第四次，ans[0] += 4，最终数组变为 [5,2,3]。


 提示：

 1 <= candies <= 10^9
 1 <= num_people <= 1000
 */
public class _简单_1103_分糖果 {
    public static void main(String[] args) {
        System.out.println(new _简单_1103_分糖果().distributeCandies2(40, 4));
    }
    /**
     * 暴力算法
     * @param candies
     * @param num_people
     * @return
     * 执行用时：
    2 ms, 在所有 Java 提交中击败了46.88%的用户
    内存消耗：
    37.3 MB, 在所有 Java 提交中击败了48.10%的用户
     */
    public int[] distributeCandies(int candies, int num_people) {
        int[] nums = new int[num_people];
        int i = 0;
        while(true){
            if(candies >= i + 1){
                nums[i % num_people] += i + 1;
                candies -= i + 1;
            }else{
                nums[i % num_people] += candies;
                break;
            }
            ++i;
        }
        return nums;
    }

    /**
     * 数学方法
     * @param candies
     * @param num_people
     * @return
     * 执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    37.2 MB, 在所有 Java 提交中击败了67.42%的用户
     */
    public int[] distributeCandies2(int candies, int num_people) {
        //ax^2 + bx + c = 0
        //x = (-b + √(b^2 - 4ac))/2a（舍去另一个负根）
        //这里等差数列求和 s = n(n + 1)/2 得 n^2 + n - 2s = 0
        double a = 1, b = 1, c = -2 * candies;
        int n = (int)((-b + Math.sqrt(b * b - 4 * a * c)) / 2 * a);//分糖果的总次数
        int mod = n % num_people;//可以多分一次的小朋友的个数
        int num = n / num_people;//每个小朋友可以分到的次数
        int[] ans = new int[num_people];
        for(int i = 0; i < ans.length; ++i){
            int num1 = num;
            //如果在余mod之前的小朋友，说明还能多拿一次糖
            if(i < mod){
                num1 = num + 1;
            }
            //Sn = a1 * n + n(n - 1)d
            ans[i] = (i + 1) * num1 + num1 * (num1 - 1) * num_people / 2;
            //最后剩下的糖果给最后一个小朋友
            if(i == mod){
                //Sn = n(a1 + a1 + (n-1)*d)/2 （没化简）（前面所有小朋友分到的糖果是d=1，a1=1的等差数列
                ans[i] += candies - n * (1 + 1 + n - 1) / 2;
            }
        }
        return ans;
    }
}

package com.ljp.leecode_cn.dynamic_programming;

/**
 * 546. 移除盒子
 给出一些不同颜色的盒子，盒子的颜色由数字表示，即不同的数字表示不同的颜色。
 你将经过若干轮操作去去掉盒子，直到所有的盒子都去掉为止。每一轮你可以移除具有相同颜色的连续 k 个盒子（k >= 1），这样一轮之后你将得到 k*k 个积分。
 当你将所有盒子都去掉之后，求你能获得的最大积分和。



 示例：

 输入：boxes = [1,3,2,2,2,3,4,3,1]
 输出：23
 解释：
 [1, 3, 2, 2, 2, 3, 4, 3, 1]
 ----> [1, 3, 3, 4, 3, 1] (3*3=9 分)
 ----> [1, 3, 3, 3, 1] (1*1=1 分)
 ----> [1, 1] (3*3=9 分)
 ----> [] (2*2=4 分)


 提示：

 1 <= boxes.length <= 100
 1 <= boxes[i] <= 100
 */
public class _困难_546_移除盒子 {
    /**
     *官方方法
     * @param boxes
     * @return
     * 优化前：
    执行用时：
    149 ms, 在所有 Java 提交中击败了5.04%的用户
    内存消耗：
    49.6 MB, 在所有 Java 提交中击败了88.70%的用户
     优化后：
    执行用时：
    47 ms, 在所有 Java 提交中击败了56.45%的用户
    内存消耗：
    49.7 MB, 在所有 Java 提交中击败了80.87%的用户
     */
    public int removeBoxes(int[] boxes){
        int[][][] dp = new int[100][100][100];
        return calculatePoint(boxes, dp, 0, boxes.length - 1, 0);
    }
    public int calculatePoint(int[] boxes, int[][][] dp, int l, int r, int k){
        if(l > r){
            return 0;
        }
        if(dp[l][r][k] != 0){
            return dp[l][r][k];
        }
        //优化，不要更容易理解
        /*while(l < r && boxes[r] == boxes[r - 1]){
            k++;
            r--;
        }*/
        dp[l][r][k] = calculatePoint(boxes, dp, l, r - 1, 0) + (k + 1) * (k + 1);
        for (int i = l; i < r; i++) {
            if(boxes[i] == boxes[r]){
                dp[l][r][k] = Math.max(dp[l][r][k], (calculatePoint(boxes, dp, l, i, k + 1) + calculatePoint(boxes, dp, i + 1, r - 1, 0)));
            }
        }
        return dp[l][r][k];
    }
}

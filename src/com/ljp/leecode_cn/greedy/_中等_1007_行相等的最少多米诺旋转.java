package com.ljp.leecode_cn.greedy;

/**
 * 1007. 行相等的最少多米诺旋转
 在一排多米诺骨牌中，A[i] 和 B[i] 分别代表第 i 个多米诺骨牌的上半部分和下半部分。（一个多米诺是两个从 1 到 6 的数字同列平铺形成的 —— 该平铺的每一半上都有一个数字。）

 我们可以旋转第 i 张多米诺，使得 A[i] 和 B[i] 的值交换。

 返回能使 A 中所有值或者 B 中所有值都相同的最小旋转次数。

 如果无法做到，返回 -1.



 示例 1：



 输入：A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
 输出：2
 解释：
 图一表示：在我们旋转之前， A 和 B 给出的多米诺牌。
 如果我们旋转第二个和第四个多米诺骨牌，我们可以使上面一行中的每个值都等于 2，如图二所示。
 示例 2：

 输入：A = [3,5,1,2,3], B = [3,6,3,3,4]
 输出：-1
 解释：
 在这种情况下，不可能旋转多米诺牌使一行的值相等。


 提示：

 1 <= A[i], B[i] <= 6
 2 <= A.length == B.length <= 20000
 */
public class _中等_1007_行相等的最少多米诺旋转 {
    /**
     * 贪心算法，实现的笨方法，四种有解的情况都遍历一遍，找最少的一种，否则返回-1
     * @param A
     * @param B
     * @return
     * 执行用时 :
    5 ms, 在所有 Java 提交中击败了75.35%的用户
    内存消耗 :
    47.5 MB, 在所有 Java 提交中击败了33.33%的用
     */
    public int minDominoRotations(int[] A, int[] B) {
        int countA = 0;
        int countB = 0;
        for(int i = 1; i < A.length; i++){
            if(A[0] == A[i]){
                continue;
            }
            if(A[0] == B[i]){
                countA++;
            }else{
                countA = Integer.MAX_VALUE;
                break;
            }
        }
        for(int i = 1; i < B.length; i++){
            if(B[0] == B[i]){
                continue;
            }
            if(B[0] == A[i]){
                countB++;
            }else{
                countB = Integer.MAX_VALUE;
                break;
            }
        }
        int min = Math.min(countA,countB);
        countA = 1;
        for(int i = 1; i < B.length; i++){
            if(B[0] == A[i]){
                continue;
            }
            if(B[0] == B[i]){
                countA++;
            }else{
                countA = Integer.MAX_VALUE;
                break;
            }
        }
        countB = 1;
        for(int i = 1; i < B.length; i++){
            if(A[0] == B[i]){
                continue;
            }
            if(A[0] == A[i]){
                countB++;
            }else{
                countB = Integer.MAX_VALUE;
                break;
            }
        }
        min = Math.min(min, Math.min(countA, countB));
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    /**
     * 官方解法：贪心算法
     * @param A
     * @param B
     * @return
     * 执行用时 :
    4 ms, 在所有 Java 提交中击败了99.07%的用户
    内存消耗 :
    47.6 MB, 在所有 Java 提交中击败了33.33%的用户
     */
    public int minDominoRotations2(int[] A, int[] B) {
        int rotations1 = check(A[0], A, B);
        int rotations2 = check(B[0], A, B);
        if (rotations1 == -1) {
            return rotations2;
        } else if (rotations2 == -1) {
            return rotations1;
        } else {
            return Math.min(rotations1, rotations2);
        }
    }
    public int check(int x, int[] A, int[] B){
        int rotations_a = 0, rotations_b = 0;
        for(int i = 1; i < A.length; i++){
            if(A[i] != x && B[i] != x){
                return -1;
            }else if(A[i] != x){
                rotations_a++;
            }else if(B[i] != x){//这里必须写，否则有可能是A和B都等于x的情况
                rotations_b++;
            }
        }
        return Math.min(rotations_a, rotations_b);
    }
}

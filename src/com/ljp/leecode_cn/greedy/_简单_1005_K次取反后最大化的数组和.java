package com.ljp.leecode_cn.greedy;

/**
 * 1005. K 次取反后最大化的数组和
 给定一个整数数组 A，我们只能用以下方法修改该数组：我们选择某个个索引 i 并将 A[i] 替换为 -A[i]，然后总共重复这个过程 K 次。（我们可以多次选择同一个索引 i。）

 以这种方式修改数组后，返回数组可能的最大和。



 示例 1：

 输入：A = [4,2,3], K = 1
 输出：5
 解释：选择索引 (1,) ，然后 A 变为 [4,-2,3]。
 示例 2：

 输入：A = [3,-1,0,2], K = 3
 输出：6
 解释：选择索引 (1, 2, 2) ，然后 A 变为 [3,1,0,2]。
 示例 3：

 输入：A = [2,-3,-1,5,-4], K = 2
 输出：13
 解释：选择索引 (1, 4) ，然后 A 变为 [2,3,-1,5,4]。


 提示：

 1 <= A.length <= 10000
 1 <= K <= 10000
 -100 <= A[i] <= 100
 */
public class _简单_1005_K次取反后最大化的数组和 {
    public static void main(String[] args) {
        int[] A = {2,-3,-1,5,-4};
        int k = 2;
        System.out.println(largestSumAfterKNegations2(A, k));
    }
    /**
     *
     * @param A
     * @param K
     * @return
     * 执行用时 :
    5 ms, 在所有 Java 提交中击败了30.40%的用户
    内存消耗 :
    39.8 MB, 在所有 Java 提交中击败了16.67%的用户
     */
    public int largestSumAfterKNegations(int[] A, int K) {
        //循环k次
        for (int i = 0; i < K; i++) {
            int min = Integer.MAX_VALUE, minIndex = 0;
            //每次找到A数组的最小值，进行反转
            for (int j = 0; j < A.length; j++) {
                if(min > A[j]){
                    min = A[j];
                    minIndex = j;
                }
            }
            A[minIndex] = -A[minIndex];
        }
        int sum = 0;
        //累加
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
        }
        return sum;
    }

    /**
     * 利用小顶堆完成
     * @param A
     * @param K
     * @return
     * 执行用时 :
    2 ms, 在所有 Java 提交中击败了86.44%的用户
    内存消耗 :
    39.5 MB, 在所有 Java 提交中击败了16.67%的用
     */
    public static int largestSumAfterKNegations2(int[] A, int K) {
        //创建小顶堆
        for (int i = A.length / 2 - 1; i >= 0 ; i--) {
            minHeap(A,i,A.length);
        }
        for (int i = 0; i < K; i++) {
            A[0] = -A[0];
            minHeap(A,0, A.length);
        }
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
        }
        return sum;
    }
    private static void minHeap(int[] A, int i, int length){
        int j = 2 * i + 1;
        int temp = A[i];
        while(j < length){
            //找孩子中小的那个
            if(j + 1 < length && A[j] > A[j + 1]){
                j++;
            }
            if(temp > A[j]){
                A[i] = A[j];
                i = j;
                j = 2 * i + 1;
            }else{
                break;
            }
        }
        A[i] = temp;
    }
}

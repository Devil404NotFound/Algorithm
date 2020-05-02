package com.ljp.leecode_cn.bit_manipulation;

import org.lanqiao.Utils.Util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 1356. 根据数字二进制下 1 的数目排序【简单】
 * 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。

 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。

 请你返回排序后的数组。

  

 示例 1：

 输入：arr = [0,1,2,3,4,5,6,7,8]
 输出：[0,1,2,4,8,3,5,6,7]
 解释：[0] 是唯一一个有 0 个 1 的数。
 [1,2,4,8] 都有 1 个 1 。
 [3,5,6] 有 2 个 1 。
 [7] 有 3 个 1 。
 按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7]
 示例 2：

 输入：arr = [1024,512,256,128,64,32,16,8,4,2,1]
 输出：[1,2,4,8,16,32,64,128,256,512,1024]
 解释：数组中所有整数二进制下都只有 1 个 1 ，所以你需要按照数值大小将它们排序。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/sort-integers-by-the-number-of-1-bits
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _1356根据数字二进制下1的数目排序 {
    public static void main(String[] args) {
        int[] arr = {10000,10000};
        Util.print(sortByBits(arr));
    }

    /**
     *直接调用List的排序，重写排序规则
     * @param arr
     * @return
     * 执行用时 :
    10 ms, 在所有 Java 提交中击败了81.45%的用户
    内存消耗 :
    40.2 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public static int[] sortByBits(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int bitCount1 = Integer.bitCount(o1);
                int bitCount2 = Integer.bitCount(o2);
                if(bitCount1 == bitCount2){
                    return o1 - o2;
                }
                return bitCount1 - bitCount2;
            }
        });
        int[] res = new int[arr.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}

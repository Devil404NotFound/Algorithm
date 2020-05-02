package com.ljp.leecode_cn.bit_manipulation.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题 08.04. 幂集
幂集。编写一种方法，返回某集合的所有子集。集合中不包含重复的元素。

 说明：解集不能包含重复的子集。

 示例:

 输入： nums = [1,2,3]
 输出：
 [
 [3],
   [1],
   [2],
   [1,2,3],
   [1,3],
   [2,3],
   [1,2],
   []
 ]

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/power-set-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _0804幂集 {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(subsets(nums));
    }
    //二进制解法
    public static List<List<Integer>> subsets(int[] nums) {
        int n = 1<<nums.length;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list;
        for(int i = 0; i < n; i++){
            list = new ArrayList<>();
            for(int j = 0; j < nums.length; j++){
                if(((i >> j) & 1) == 1){
                    list.add(nums[j]);
                }
            }
            res.add(list);
        }
        return res;
    }

    class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            if(nums == null || nums.length == 0){
                return new ArrayList<List<Integer>>();
            }
            List<List<Integer>> oldList = new ArrayList<>();
            oldList.add(new ArrayList<Integer>());
            List<Integer> list0 = new ArrayList<>();
            list0.add(nums[0]);
            oldList.add(list0);

            List<List<Integer>> res = oldList;
            List<Integer> clone = null;
            for(int i = 1; i < nums.length; ++i){
                res = new ArrayList<>();
                res.addAll(oldList);
                for(List list : oldList){
                    clone = (List<Integer>) ((ArrayList<Integer>)list).clone();
                    clone.add(nums[i]);
                    res.add(clone);
                }
                oldList = res;
            }
            return res;
        }

    }
}

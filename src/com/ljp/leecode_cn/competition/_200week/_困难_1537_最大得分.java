package com.ljp.leecode_cn.competition._200week;

import java.util.ArrayList;
import java.util.List;

/**
 * 1537. 最大得分
 你有两个 有序 且数组内元素互不相同的数组 nums1 和 nums2 。

 一条 合法路径 定义如下：

 选择数组 nums1 或者 nums2 开始遍历（从下标 0 处开始）。
 从左到右遍历当前数组。
 如果你遇到了 nums1 和 nums2 中都存在的值，那么你可以切换路径到另一个数组对应数字处继续遍历（但在合法路径中重复数字只会被统计一次）。
 得分定义为合法路径中不同数字的和。

 请你返回所有可能合法路径中的最大得分。

 由于答案可能很大，请你将它对 10^9 + 7 取余后返回。



 示例 1：



 输入：nums1 = [2,4,5,8,10], nums2 = [4,6,8,9]
 输出：30
 解释：合法路径包括：
 [2,4,5,8,10], [2,4,5,8,9], [2,4,6,8,9], [2,4,6,8,10],（从 nums1 开始遍历）
 [4,6,8,9], [4,5,8,10], [4,5,8,9], [4,6,8,10]  （从 nums2 开始遍历）
 最大得分为上图中的绿色路径 [2,4,6,8,10] 。
 示例 2：

 输入：nums1 = [1,3,5,7,9], nums2 = [3,5,100]
 输出：109
 解释：最大得分由路径 [1,3,5,100] 得到。
 示例 3：

 输入：nums1 = [1,2,3,4,5], nums2 = [6,7,8,9,10]
 输出：40
 解释：nums1 和 nums2 之间无相同数字。
 最大得分由路径 [6,7,8,9,10] 得到。
 示例 4：

 输入：nums1 = [1,4,5,8,9,11,19], nums2 = [2,3,4,11,12]
 输出：61


 提示：

 1 <= nums1.length <= 10^5
 1 <= nums2.length <= 10^5
 1 <= nums1[i], nums2[i] <= 10^7
 nums1 和 nums2 都是严格递增的数组。
 */
public class _困难_1537_最大得分 {
    public static void main(String[] args) {
        int[] nums1 = {599,609,628,633,637,639,652,656,671,672,685,686,691,708,722,740,755,767,787,789,794,813,818,819,833,838,840,857,860};
        int[] nums2 = {486,502,506,509,522,523,524,529,547,552,554,569,573,583,586,597,598,611,624,640,657,667,681,691,693,703,716,721,724,740,743,745,765,766,778,796,801,802,803,807,809,812,822,824,827,830,833,853,856,860,876};
        System.out.println(maxSum(nums1, nums2));
    }
    public static int maxSum(int[] nums1, int[] nums2) {
        int mod = 1000000007;
        //预处理记录nums1和nums2相同元素的下标
        List<int[]> equal = new ArrayList<>();
        int i = 0, j = 0;
        while(i < nums1.length && j < nums2.length){
            if(nums1[i] > nums2[j]){
                j++;
            }else if(nums1[i] < nums2[j]){
                i++;
            }else{
                equal.add(new int[]{i, j});
                i++;
                j++;
            }
        }
        equal.add(new int[]{nums1.length - 1, nums2.length - 1});//添加最后一个下标，比较最后一个相同的数到结尾的片段哪个大
        //比较相同元素之间的片段哪个大
        long max = 0;
        int last1 = 0, last2 = 0;//从0开始到第一次出现相同的数
        int size = equal.size();
        for(i = 0; i < size; i++){
            int[] temp = equal.get(i);
            long sumNums1 = 0, sumNums2 = 0;
            for(j = last1; j <= temp[0]; j++){
                sumNums1 += nums1[j];
            }
            for(j = last2; j <= temp[1]; j++){
                sumNums2 += nums2[j];
            }
            max += Math.max(sumNums1, sumNums2);//比较哪个片段累加大
            max = max % mod;
            last1 = temp[0] + 1;
            last2 = temp[1] + 1;
        }
        return (int)(max % mod);
    }
}

package org.lanqiao.algorithm.chapter06;

import org.lanqiao.Utils.Util;

/**所有员工年龄排序
 * 员工数n（1<=n<=1000000），年龄age的范围为(1<=age<=100)
 */
public class _04所有员工年龄排序 {
    public static void main(String[] args) {
        int n = 100000;
        int[] num = Util.getRandomArr(n,1,100);
        countSort(num);
        Util.print(num);

    }
    public static void countSort(int[] arr){
        int max = 101;
//        新建一个辅助数组（用于计数）
        int[] helpArr = new int[max];
//        对数组arr计数
        for (int i = 0; i < arr.length; i++) {
            helpArr[arr[i]]++;
        }
        int current = 0;
//        根据辅助数组，给数组arr从小到大重新赋值
        for (int i = 0; i < helpArr.length; i++) {
            while(helpArr[i] > 0){
                arr[current++] = i;
                helpArr[i]--;
            }
        }
    }
}

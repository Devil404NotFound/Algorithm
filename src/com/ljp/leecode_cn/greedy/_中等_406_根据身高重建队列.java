package com.ljp.leecode_cn.greedy;

import org.lanqiao.Utils.Util;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 406. 根据身高重建队列
 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。

 注意：
 总人数少于1100人。

 示例

 输入:
 [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

 输出:
 [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
public class _中等_406_根据身高重建队列 {
    public static void main(String[] args) {
        int[][] people = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        Util.print(reconstructQueue(people));
    }

    /**
     *
     * @param people
     * @return
     * 执行用时 :
    14 ms, 在所有 Java 提交中击败了27.90%的用户
    内存消耗 :
    40.8 MB, 在所有 Java 提交中击败了83.33%的用户
     */
    public static int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o2[0] - o1[0];
            }
        });
        for (int i = 1; i < people.length; i++) {
            int num = people[i][1];
            int[] temp = people[i];
            int j = i;
            while(j > num){
                people[j] = people[j - 1];
                j--;
            }
            people[j] = temp;
        }

        return people;
    }
}

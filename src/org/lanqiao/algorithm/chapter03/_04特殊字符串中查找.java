package org.lanqiao.algorithm.chapter03;

/**
 * 有个排序后胡字符串数组，其中散布着一些空字符串，编写一个方法，找出给定字符串（肯定不是空字符串）的索引
 */
public class _04特殊字符串中查找 {
    public static void main(String[] args) {
        String[] arr = {"a", "", "ab", "ac", "", "ba", "bb", "", "cd"};
        int result = indexOf(arr, "ac");
        System.out.println(result);

    }
    public static int indexOf(String[] arr,String p){
        int begin = 0;
        int end = arr.length - 1;
        int mid;
        while(begin < end){
            mid = begin + ((end - begin) >> 1);
            while(arr[mid].equals("")){
                mid++;
            }
            if(arr[mid].compareTo(p) > 0){
                end = mid;
            }else if(arr[mid].compareTo(p) < 0){
                begin = mid;
            }else {
                return mid;
            }
        }
        return -1;
    }

}

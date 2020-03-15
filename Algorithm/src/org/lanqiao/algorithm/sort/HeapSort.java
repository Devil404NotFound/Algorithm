package org.lanqiao.algorithm.sort;

import org.lanqiao.Utils.Util;

/**
 * 堆排序
 * 1. 建立最大/最小堆
 * 2. 对换最后一个和根元素位置
 * 3. 排除最后一个元素重新更新堆（最后一个元素已经是最大/最小）
 *
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {5,3,2,1,6,4,9,8};
        heapSort(arr);
        Util.print(arr);

    }

    public static void heapSort(int[] arr) {
        //建立最大堆
        for (int i = arr.length/2 - 1; i >= 0; i--) {//此处需要i=0,！
            maxHeap(arr,i, arr.length);
        }
        for (int i = arr.length - 1; i > 0; i--){
            Util.swap(arr, 0, i);
            maxHeap(arr, 0, i);
        }

    }

    public static void maxHeap(int[] arr, int i, int length) {
        int j = 2*i + 1;
        int temp = arr[i];
        while(j < length){
            if( j + 1 < length && arr[j+1] > arr[j]){//选择左右孩子中大的那个
                j++;
            }
            if(arr[j] > temp){//如果大的孩子比自己大
                arr[i] = arr[j];
                i = j;
                j = 2 * i + 1;
            }else{
                break;
            }
        }
        arr[i] = temp;
    }
}

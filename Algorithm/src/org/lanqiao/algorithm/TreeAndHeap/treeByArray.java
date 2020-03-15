package org.lanqiao.algorithm.TreeAndHeap;

/**
 * 用顺序数组做二叉树
 * 若i为根节点下标，则左孩子为2*i+1，右孩子为2*i+2
 * 并实现先序、中序、后序输出
 */
public class treeByArray {
    public static void main(String[] args) {
        int[] arr = {0,1,2,3,4,5,6,7,8};
        preOrder(arr, 0);
        System.out.println();
        inOrder(arr, 0);
        System.out.println();
        afterOrder(arr, 0);
    }
    public static void preOrder(int[] arr, int i){
        if(i < arr.length){
            System.out.print(arr[i] + " ");
            preOrder(arr, 2 * i + 1);
            preOrder(arr, 2* i + 2);
        }
    }
    public static void inOrder(int[] arr, int i){
        if(i < arr.length){
            inOrder(arr, 2 * i + 1);
            System.out.print(arr[i] + " ");
            inOrder(arr, 2 * i + 2);
        }
    }
    public static void afterOrder(int[] arr, int i){
        if(i < arr.length){
            afterOrder(arr, 2 * i + 1);
            afterOrder(arr, 2 * i + 2);
            System.out.print(arr[i] + " ");
        }
    }
}

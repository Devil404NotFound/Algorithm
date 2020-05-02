package org.lanqiao.algorithm.chapter14;
/*
回溯
    递归调用代表开启一个分支,如果希望这个分支返回后某些数据恢复到分支
    开启前的状态以便重新开始,就要使用回溯技巧
    全排列的交换法,数独,部分和,用到了回溯

 剪枝
    深搜时,如已明确从当前状态无论如何转移都不会存在(更优)解,
    就应该中断往下的继续搜索,这种方法称为剪枝
    数独里面有剪枝
    部分和里面有剪枝

    ========经典n皇后问题==================
    请设计一种算法,解决著名的η皇后问题。
    这里的n皇后问题指在一个n*n的棋盘上放置n个棋子,
    使得每行每列和每条对角线上都只有一个棋子,求其摆放的方法数。

    技巧
    只需要一个以为数组，下标记录行，值记录列
    主对角线（左上-右下）row+col相同
    副对角线（右上-左下）row+col相同
 */
public class _04回溯和剪枝_N皇后问题 {
    static int count = 0;
    static int[] arr;
    public static void main(String[] args) {
        int n = 8;
        arr = new int[n];
        nQueen(0);
        System.out.println(count);
    }
    public static void nQueen(int row){
        if(row == arr.length){
            count++;
            /*System.out.println("======方式"+count);
            for (int i = 0; i < row; i++) {
                System.out.println(i+","+arr[i]);
            }*/
            return;
        }
        //row行，col列， i，检查的行，arr[i]检查的列
        for (int col = 0; col < arr.length; col++) {
            boolean check = true;
            for (int i = 0; i < row; i++) {
                if(col == arr[i] || i + arr[i] == row + col || i - arr[i] == row - col){
                    check = false;
                    break;
                }
            }
            if(check){
                arr[row] = col;
                nQueen(row+1);
//                arr[row] = 0;//这里不必恢复原状
            }
        }
    }
}

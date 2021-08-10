package com.ljp.leecode_cn.offer;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author ljp
 * @date 2020/12/16 22:55
剑指 Offer 09. 用两个栈实现队列
用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )



示例 1：

输入：
["CQueue","appendTail","deleteHead","deleteHead"]
[[],[3],[],[]]
输出：[null,null,3,-1]
示例 2：

输入：
["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
[[],[],[5],[2],[],[]]
输出：[null,-1,null,null,5,2]
提示：

1 <= values <= 10000
最多会对 appendTail、deleteHead 进行 10000 次调用
 */
public class _简单_09_用两个栈实现队列 {
    /**
     执行用时：
     58 ms, 在所有 Java 提交中击败了49.72%的用户
     内存消耗：
     47.5 MB, 在所有 Java 提交中击败了15.32%的用户
     */
    class CQueue {
        Deque<Integer> deque1, deque2;
        public CQueue() {
            deque1 = new LinkedList<>();
            deque2 = new LinkedList<>();
        }

        public void appendTail(int value) {
            deque1.push(value);
        }

        public int deleteHead() {
            if(deque2.isEmpty()) {
                while(!deque1.isEmpty()) {
                    deque2.push(deque1.poll());
                }
            }
            return deque2.isEmpty() ? -1 : deque2.poll();
        }
    }
    /**
     * Your CQueue object will be instantiated and called as such:
     * CQueue obj = new CQueue();
     * obj.appendTail(value);
     * int param_2 = obj.deleteHead();
     */
}

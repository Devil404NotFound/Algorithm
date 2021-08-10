package com.ljp.leecode_cn.design;

import java.util.Deque;
import java.util.LinkedList;

/** 每日一题 2021.03.05
 * @author lijunpeng
 * @date 2021/3/5 23:58
 */
public class _简单_232_用栈实现队列 {
    /**
     * 官方题解：
     执行用时：
     0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：
     36.5 MB, 在所有 Java 提交中击败了28.92%的用户
     */
    class MyQueue {
        Deque<Integer> inStack;
        Deque<Integer> outStack;

        public MyQueue() {
            inStack = new LinkedList<Integer>();
            outStack = new LinkedList<Integer>();
        }

        public void push(int x) {
            inStack.push(x);
        }

        public int pop() {
            if (outStack.isEmpty()) {
                in2out();
            }
            return outStack.pop();
        }

        public int peek() {
            if (outStack.isEmpty()) {
                in2out();
            }
            return outStack.peek();
        }

        public boolean empty() {
            return inStack.isEmpty() && outStack.isEmpty();
        }

        private void in2out() {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }

}

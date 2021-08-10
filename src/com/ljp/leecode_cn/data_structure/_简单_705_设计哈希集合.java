package com.ljp.leecode_cn.data_structure;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author lijunpeng
 * @date 2021/3/13 11:49
 */
public class _简单_705_设计哈希集合 {
}

/**
 执行用时：
 21 ms, 在所有 Java 提交中击败了45.90%的用户
 内存消耗：
 44.6 MB, 在所有 Java 提交中击败了88.10%的用户
 */
class MyHashSet {
    private LinkedList[] data;
    private static final int BASE = 769; //尽可能避免冲突用一个质数

    /** Initialize your data structure here. */
    public MyHashSet() {
        data = new LinkedList[BASE];
        for(int i = 0; i < BASE; ++i) {
            data[i] = new LinkedList<Integer>();
        }
    }

    public void add(int key) {
        int h = hash(key);
        Iterator<Integer> it = data[h].iterator();
        while(it.hasNext()) {
            Integer element = it.next();
            if(element == key) {
                return;
            }
        }
        data[h].add(key);
    }

    public void remove(int key) {
        int h = hash(key);
        Iterator<Integer> it = data[h].iterator();
        while(it.hasNext()) {
            Integer element = it.next();
            if(element == key) {
                data[h].remove(element);
                return;
            }
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int h = hash(key);
        Iterator<Integer> it = data[h].iterator();
        while(it.hasNext()) {
            Integer element = it.next();
            if(element == key) {
                return true;
            }
        }
        return false;
    }
    private static int hash(int key) {
        return key % BASE;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */

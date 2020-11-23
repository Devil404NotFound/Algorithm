package com.ljp.leecode_cn.hash;

import java.util.*;

/** 每日一题 2020.10.31
 381. O(1) 时间插入、删除和获取随机元素 - 允许重复
 设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。

 注意: 允许出现重复元素。

 insert(val)：向集合中插入元素 val。
 remove(val)：当 val 存在时，从集合中移除一个 val。
 getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
 示例:

 // 初始化一个空的集合。
 RandomizedCollection collection = new RandomizedCollection();

 // 向集合中插入 1 。返回 true 表示集合不包含 1 。
 collection.insert(1);

 // 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
 collection.insert(1);

 // 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
 collection.insert(2);

 // getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
 collection.getRandom();

 // 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
 collection.remove(1);

 // getRandom 应有相同概率返回 1 和 2 。
 collection.getRandom();


 * @author ljp
 * @date 2020/10/31 17:07
 */
public class _困难_381_O1时间插入删除和获取随机元素_允许重复 {

}

/**
 执行用时：
 15 ms, 在所有 Java 提交中击败了79.86%的用户
 内存消耗：
 45.7 MB, 在所有 Java 提交中击败了37.02%的用户
 */
class RandomizedCollection {
    List<Integer> nums;
    Map<Integer, Set<Integer>> idx;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        nums = new ArrayList<>();
        idx = new HashMap<>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        nums.add(val);
        Set<Integer> set = idx.getOrDefault(val, new HashSet<>());
        set.add(nums.size() - 1);
        idx.put(val, set);
        return set.size() == 1;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!idx.containsKey(val)){
            return false;
        }
        //求val的下标
        Iterator<Integer> it = idx.get(val).iterator();
        int index = it.next();
        //求最后一个值
        int lastNum = nums.get(nums.size() - 1);
        nums.set(index, lastNum);
        //删除idx里val的下标
        idx.get(val).remove(index);
        //将最后一个数的下标移动到index的位置
        if(index < nums.size() - 1){
            idx.get(lastNum).remove(nums.size() - 1);
            idx.get(lastNum).add(index);
        }
        if(idx.get(val).size() == 0){
            idx.remove(val);
        }
        nums.remove(nums.size() - 1);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return nums.get((int)(Math.random() * nums.size()));
    }
}

/**

 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */


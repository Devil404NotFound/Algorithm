package com.ljp.leecode_cn.sliding_window;

import java.util.*;

/** 每日一题 2021.02.03
 * @author lijunpeng
 * @date 2021/2/3 22:00
480. 滑动窗口中位数
中位数是有序序列最中间的那个数。如果序列的长度是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。

例如：

[2,3,4]，中位数是 3
[2,3]，中位数是 (2 + 3) / 2 = 2.5
给你一个数组 nums，有一个长度为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。



示例：

给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。

窗口位置                      中位数
---------------               -----
[1  3  -1] -3  5  3  6  7       1
1 [3  -1  -3] 5  3  6  7      -1
1  3 [-1  -3  5] 3  6  7      -1
1  3  -1 [-3  5  3] 6  7       3
1  3  -1  -3 [5  3  6] 7       5
1  3  -1  -3  5 [3  6  7]      6
因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。



提示：

你可以假设 k 始终有效，即：k 始终小于输入的非空数组的元素个数。
与真实值误差在 10 ^ -5 以内的答案将被视作正确答案。
 */
public class _困难_480_滑动窗口中位数 {
    /**
     * 官方题解：双端队列 + 延迟删除
     * @param nums
     * @param k
     * @return
    执行用时：
    29 ms, 在所有 Java 提交中击败了84.92%的用户
    内存消耗：
    40.6 MB, 在所有 Java 提交中击败了47.05%的用户
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        DualHeap dh = new DualHeap(k);
        for (int i = 0; i < k; i++) {
            dh.insert(nums[i]);
        }
        double[] ans = new double[nums.length - k + 1];
        ans[0] = dh.getMedian();
        for (int i = k; i < nums.length; i++) {
            dh.insert(nums[i]);
            dh.erase(nums[i - k]);
            ans[i - k + 1] = dh.getMedian();
        }
        return ans;
    }
    //双优先队列
    //方法一：inset(num)插入元素
    //方法二：erase(num)删除元素
    //方法三：getMedian()返回中位数
    private class DualHeap {
        // 大根堆，维护较小的一半元素
        private PriorityQueue<Integer> small;
        // 小根堆，维护较大的一半元素
        private PriorityQueue<Integer> large;
        // 哈希表，记录「延迟删除」的元素，key 为元素，value 为需要删除的次数
        private Map<Integer, Integer> delayed;

        private int k;
        // small 和 large 当前包含的元素个数，需要扣除被「延迟删除」的元素
        private int smallSize, largeSize;

        public DualHeap(int k) {
            this.small = new PriorityQueue<Integer>(new Comparator<Integer>() {
                public int compare(Integer num1, Integer num2) {
                    return num2.compareTo(num1);
                }
            });
            this.large = new PriorityQueue<Integer>(new Comparator<Integer>() {
                public int compare(Integer num1, Integer num2) {
                    return num1.compareTo(num2);
                }
            });
            this.delayed = new HashMap<Integer, Integer>();
            this.k = k;
            this.smallSize = 0;
            this.largeSize = 0;
        }

        public double getMedian() {
            return (k & 1) == 1 ? small.peek() : ((double) small.peek() + large.peek()) / 2;
        }

        public void insert(int num) {
            if (small.isEmpty() || num <= small.peek()) {
                small.offer(num);
                ++smallSize;
            } else {
                large.offer(num);
                ++largeSize;
            }
            makeBalance();
        }

        public void erase(int num) {
            delayed.put(num, delayed.getOrDefault(num, 0) + 1);
            if (num <= small.peek()) {
                --smallSize;
                if (num == small.peek()) {
                    prune(small);
                }
            } else {
                --largeSize;
                if (num == large.peek()) {
                    prune(large);
                }
            }
            makeBalance();
        }

        // 不断地弹出 heap 的堆顶元素，并且更新哈希表
        private void prune(PriorityQueue<Integer> heap) {
            while (!heap.isEmpty()) {
                int num = heap.peek();
                if (delayed.containsKey(num)) {
                    delayed.put(num, delayed.get(num) - 1);
                    if (delayed.get(num) == 0) {
                        delayed.remove(num);
                    }
                    heap.poll();
                } else {
                    break;
                }
            }
        }

        // 调整 small 和 large 中的元素个数，使得二者的元素个数满足要求
        private void makeBalance() {
            if (smallSize > largeSize + 1) {
                // small 比 large 元素多 2 个
                large.offer(small.poll());
                --smallSize;
                ++largeSize;
                // small 堆顶元素被移除，需要进行 prune
                prune(small);
            } else if (smallSize < largeSize) {
                // large 比 small 元素多 1 个
                small.offer(large.poll());
                ++smallSize;
                --largeSize;
                // large 堆顶元素被移除，需要进行 prune
                prune(large);
            }
        }
    }

    /********官方题解下面回复我评论的大佬（AVL平衡二叉树解决********/
    /**
     *
     * @param nums
     * @param k
     * @return
    执行用时：
    25 ms, 在所有 Java 提交中击败了96.06%的用户
    内存消耗：
    40.3 MB, 在所有 Java 提交中击败了77.07%的用户
     */
    public double[] medianSlidingWindow2(int[] nums, int k) {
        List<Double> list = new ArrayList<>();
        IndexedAVL avl = new IndexedAVL();
        // 初始化
        int n = nums.length;
        for(int i=0;i<k-1;i++){
            avl.add(nums[i]);
        }
        int l = 0 ,r = k-1;
        while(r<n){
            avl.add(nums[r++]);
            if(k%2!=0){
                list.add(avl.getItemByRank(k/2+1)*1.0);
            }else{
                list.add(((double)avl.getItemByRank(k/2)+avl.getItemByRank(k/2+1))/2.0);
            }
            avl.erase(nums[l++]);

        }

        double[] ans = new double[list.size()];
        for(int i=0;i<list.size();i++){
            ans[i] = list.get(i);
        }
        return ans;
    }
    class IndexedAVL{

        private class Node{
            // 节点存储的真实的数据
            int val;
            // size 是这节点统辖的树的所有元素的总个数，cnt这个节点存储val出现的次数, height是节点的高度
            int size,cnt,height;
            Node left,right;

            public Node(int val) {
                this.val = val;
                this.cnt = this.height = this.size = 1;
            }
        }

        private int size;

        public int getSize() {
            return size;
        }

        private Node root;

        private int h(Node node){
            return node==null?0:node.height;
        }

        private int getSize(Node p){
            return p==null?0:p.size;
        }

        private void pushUp(Node p){
            p.height = Math.max(h(p.left),h(p.right))+1;
            p.size = p.cnt + getSize(p.left) + getSize(p.right);
        }

        // 右旋
        private Node zig(Node p){
            Node q = p.left;
            p.left = q.right;
            q.right = p;
            pushUp(p);
            pushUp(q);
            return q;
        }

        // 左旋
        private Node zag(Node q){
            Node p = q.right;
            q.right = p.left;
            p.left = q;
            pushUp(q);
            pushUp(p);
            return p;
        }

        private Node LL(Node t){
            return zig(t);
        }

        private Node LR(Node t){
            t.left = zag(t.left);
            return zig(t);
        }

        private Node RR(Node t){
            return zag(t);
        }

        private Node RL(Node t){
            t.right = zig(t.right);
            return zag(t);
        }

        private Node insert(Node t,int value) {
            if(t == null){
                return new Node(value);
            }

            Node newRoot = t;

            // 插入完成之后，要将搜索路径上的点依次进行调整 height,调整 size的大小

            if(value < t.val){
                t.left =  insert(t.left,value);

                int leftH = h(t.left);
                int rightH = h(t.right);

                if(leftH - rightH > 1){
                    // LL型
                    if(value <= t.left.val){
                        newRoot =  LL(t);
                        // LR型
                    }else{
                        newRoot =  LR(t);
                    }
                }

            }else if(value > t.val){
                t.right =  insert(t.right,value);

                int leftH = h(t.left);
                int rightH = h(t.right);

                if(rightH -leftH >1){
                    // RR型
                    if(value >= t.right.val){
                        newRoot = RR(t);
                        // RL型
                    }else{
                        newRoot = RL(t);
                    }
                }

            }else {
                t.cnt++;
            }
            pushUp(newRoot);
            return newRoot;
        }

        private Node remove(Node t,int value) {
            if(t == null) return null;

            Node newRoot = t;

            if(value < t.val){

                t.left = remove(t.left,value);
                // 删除左子树的节点，唯一可能导致"失衡" 的情况是 bf由 -1 变成-2
                int leftH = h(t.left);
                int rightH = h(t.right);

                if(rightH - leftH > 1){
                    if( h(t.right.right) >= h(t.right.left) ){
                        newRoot = RR(t);
                    }else{
                        newRoot = RL(t);
                    }
                }

            } else if(value > t.val){

                t.right = remove(t.right,value);
                int leftH = h(t.left);
                int rightH = h(t.right);
                // 删除右子树的节点，唯一可能导致"失衡" 的情况是 bf由 1 变成 2
                if(leftH - rightH > 1){
                    if(h(t.left.left) >= h(t.left.right)){
                        newRoot = LL(t);
                    }else{
                        newRoot = LR(t);
                    }
                }
            }else{

                if(t.cnt>1){
                    t.cnt--;
                }else{
                    // 下面细分成 3种情况 (左右子树都为空，一棵为空另一棵不为空，都不为空)
                    if(t.left == null && t.right == null){

                        return null;
                    }else if(t.left != null && t.right == null){
                        return t.left;
                    }else if(t.left == null){
                        return t.right;
                    }else{
                        // 用前驱的值代替（后继也是一样）
                        Node cur = t.left;
                        while(cur.right != null){
                            cur = cur.right;
                        }
                        t.val = cur.val;
                        t.left = remove(t.left,cur.val);
                        // 这个地方仍然要有形态的调整
                        // 删除左子树的节点，唯一可能导致"失衡" 的情况是 bf由 -1 变成-2
                        int leftH = h(t.left);
                        int rightH = h(t.right);

                        if(rightH - leftH > 1){
                            if( h(t.right.right) >= h(t.right.left) ){
                                newRoot = RR(t);
                            }else{
                                newRoot = RL(t);
                            }
                        }
                    }
                }

            }
            pushUp(newRoot);
            return newRoot;
        }

        private int getItemByRank(Node node,int rank){
            if(node == null) return Integer.MIN_VALUE;
            if(getSize(node.left) >= rank) return getItemByRank(node.left,rank);
            if(getSize(node.left)+node.cnt >= rank) return node.val;
            return getItemByRank(node.right,rank-getSize(node.left)-node.cnt);
        }

        public int getItemByRank(int rank){
            return getItemByRank(root,rank);
        }

        public void add(int value){
            root = insert(root,value);
            this.size++;
        }

        public void erase(int value){
            root = remove(root,value);
            this.size--;
        }

    }

}

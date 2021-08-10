package com.ljp.leecode_cn.graph;

import java.util.*;

/**
 * @author lijunpeng
 * @date 2021/1/11 23:52
 */
public class _中等_1202_交换字符串的元素 {
    public static void main(String[] args) {
        _中等_1202_交换字符串的元素 test = new _中等_1202_交换字符串的元素();
        String s = "dcab";
        List<List<Integer>> pairs = new ArrayList<>();
        pairs.add(Arrays.asList(0, 3));
        pairs.add(Arrays.asList(1, 2));
        pairs.add(Arrays.asList(0, 2));
        String result = test.smallestStringWithSwaps(s, pairs);
        System.out.println(result);
    }
    Map<Integer, PriorityQueue<Character>> map = new HashMap<>(); //下标和优先队列的映射（一个图对应一个优先队列）
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[] parent = new int[n];
        for(int i = 0; i < n; ++i) {
            parent[i] = i;
        }
        //联合
        int size = pairs.size();
        for(int i = 0; i < size; ++i) {
            List<Integer> list = pairs.get(i);
            int index1 = list.get(0);
            int index2 = list.get(1);
            union(parent, index1, index2);
        }
        //使用优先队列分别给每个不连通的图做排序
        for(int i = 0; i < n; ++i) {
            addMap(parent, i, chars[i]);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; ++i) {
            sb.append(map.get(i).poll());
        }
        return sb.toString();
    }
    private void union(int[] parent, int index1, int index2) {
        if(index1 != index2) {
            parent[find(parent, index1)] = find(parent, index2);
        }
    }
    private int find(int[] parent, int index) {
        if(parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }
    private void addMap(int[] parent, int i, char c) {
        int root = find(parent, i);
        PriorityQueue priority = map.getOrDefault(root, new PriorityQueue<Character>());
        priority.offer(c);
        map.put(root, priority);//这里需要把第一次创建出来的优先队列也存进去啊！！！
        map.put(i, priority);
    }

    /**
     * 官方题解：（还没看）
     *
     * @param s
     * @param pairs
     * @return
     */
    public String smallestStringWithSwaps2(String s, List<List<Integer>> pairs) {
        if (pairs.size() == 0) {
            return s;
        }

        // 第 1 步：将任意交换的结点对输入并查集
        int len = s.length();
        UnionFind unionFind = new UnionFind(len);
        for (List<Integer> pair : pairs) {
            int index1 = pair.get(0);
            int index2 = pair.get(1);
            unionFind.union(index1, index2);
        }

        // 第 2 步：构建映射关系
        char[] charArray = s.toCharArray();
        // key：连通分量的代表元，value：同一个连通分量的字符集合（保存在一个优先队列中）
        Map<Integer, PriorityQueue<Character>> hashMap = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
//            if (hashMap.containsKey(root)) {
//                hashMap.get(root).offer(charArray[i]);
//            } else {
//                PriorityQueue<Character> minHeap = new PriorityQueue<>();
//                minHeap.offer(charArray[i]);
//                hashMap.put(root, minHeap);
//            }
            // 上面六行代码等价于下面一行代码，JDK 1.8 以及以后支持下面的写法
            hashMap.computeIfAbsent(root, key -> new PriorityQueue<>()).offer(charArray[i]);
        }

        // 第 3 步：重组字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
            stringBuilder.append(hashMap.get(root).poll());
        }
        return stringBuilder.toString();
    }

    private class UnionFind {

        private int[] parent;
        /**
         * 以 i 为根结点的子树的高度（引入了路径压缩以后该定义并不准确）
         */
        private int[] rank;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
                this.rank[i] = 1;
            }
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            if (rank[rootX] == rank[rootY]) {
                parent[rootX] = rootY;
                // 此时以 rootY 为根结点的树的高度仅加了 1
                rank[rootY]++;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
                // 此时以 rootY 为根结点的树的高度不变
            } else {
                // 同理，此时以 rootX 为根结点的树的高度不变
                parent[rootY] = rootX;
            }
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
    }
}

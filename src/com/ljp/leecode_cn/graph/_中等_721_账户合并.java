package com.ljp.leecode_cn.graph;

import java.util.*;

/**每日一题 2021.01.18
 * @author lijunpeng
 * @date 2021/1/18 18:18
721. 账户合并
给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该账户的邮箱地址。

现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。

合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按顺序排列的邮箱地址。账户本身可以以任意顺序返回。



示例 1：

输入：
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
输出：
[["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
解释：
第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。
第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
可以以任何顺序返回这些列表，例如答案 [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的。


提示：

accounts的长度将在[1，1000]的范围内。
accounts[i]的长度将在[1，10]的范围内。
accounts[i][j]的长度将在[1，30]的范围内。
 */
public class _中等_721_账户合并 {
    /**
     * 我的方法：并查集
     执行用时：
     31 ms, 在所有 Java 提交中击败了95.65%的用户
     内存消耗：
     43.4 MB, 在所有 Java 提交中击败了28.23%的用户
     */
    int index = -1;
    Map<String, Integer> union = new HashMap<>();//邮箱和下标的映射
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        UnionFind unionFind = new UnionFind(accounts.size());
        for(List<String> list : accounts) {
            ++index;
            String name = list.get(0);
            for(int i = 1; i < list.size(); ++i) {
                String email = list.get(i);
                if(union.containsKey(email)) {
                    unionFind.union(union.get(email), index);
                }else{
                    union.put(email, index);
                }
            }
        }
        Map<Integer, List<String>> retMap = new HashMap<>();//返回结果列表的映射
        List<List<String>> ret = new ArrayList<>();
        Set<String> emailSet = new HashSet<>();
        for(int i = 0; i <= index; ++i) {
            List<String> list = accounts.get(i);
            int root = unionFind.find(i);
            List<String> retList = retMap.get(root);
            if(retList == null) {
                retList = new LinkedList<>();
                retMap.put(root, retList);
                ret.add(retList);
            }
            for(int j = 1; j < list.size(); ++j) {
                if(emailSet.add(list.get(j))){
                    retList.add(list.get(j));
                }
            }
        }
        for(List<String> list : ret) {
            Collections.sort(list);
            Integer num = union.get(list.get(0));
            String name = accounts.get(num).get(0);
            list.add(0, name);
        }
        return ret;
    }

    private class UnionFind {
        private int[] parent;
        public UnionFind(int n) {
            parent = new int[n];
            for(int i = 0 ; i < n; ++i) {
                parent[i] = i;
            }
        }
        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
        public int find(int index) {
            if(parent[index] != index) {
                parent[index] = find(parent[index]);
            }
            return parent[index];
        }
    }
}

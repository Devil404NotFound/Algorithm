package com.ljp.leecode_cn.bit_manipulation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 187. 重复的DNA序列
 所有 DNA 都由一系列缩写为 A，C，G 和 T 的核苷酸组成，例如：“ACGAATTCCG”。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。

 编写一个函数来查找 DNA 分子中所有出现超过一次的 10 个字母长的序列（子串）。



 示例：

 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 输出：["AAAAACCCCC", "CCCCCAAAAA"]

 执行用时 :
 17 ms, 在所有 Java 提交中击败了98.13%的用户
 内存消耗 :
 48.5 MB, 在所有 Java 提交中击败了25.00%的用户
 */
public class _187重复的DNA序列 {

    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> set = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        List<String> list = new ArrayList<>();
        int k = s.length() - 10;
        for(int i = 0; i <= k; ++i){
            if(!set.add(s.substring(i,i + 10))){
                set2.add(s.substring(i, i + 10));
            }
        }
        for(String str : set2){
            list.add(str);
        }
        return list;
    }
}

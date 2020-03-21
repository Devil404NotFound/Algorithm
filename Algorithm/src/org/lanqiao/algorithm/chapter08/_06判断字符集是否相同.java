package org.lanqiao.algorithm.chapter08;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 判断字符集是否相同
 */
public class _06判断字符集是否相同 {
    public static void main(String[] args) {
        String str1 = "hello";
        String str2 = "hell";
        System.out.println(hasSameCharSet2(str1, str2 ));
    }

    /**
     * 这个判断s1有，而s2没有的情况时结果错误，如s1=hello，s2=hell，返回的是true（正确应该是false）
     * @param str1
     * @param str2
     * @return
     */
    public static boolean hasSameCharSet2(String str1, String str2){
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < str1.length(); i++) {
            char c = str1.charAt(i);
            if(map.get(c) == null){
                map.put(c,1);
            }
        }
        for (int i = 0; i < str2.length(); i++) {
            char c = str2.charAt(i);
            if(map.get(c) == null){
                return false;
            }
        }
        return true;
    }
    /**
     * 利用Set
     * @param str1
     * @param str2
     * @return
     */
    private static boolean hasSameCharSet(String str1, String str2) {
        Set<Character> set = new HashSet<>();
        Set<Character> sethlep = new HashSet<>();
        int length = str1.length();
        for (int i = 0; i < length; i++) {
            set.add(str1.charAt(i));
        }
        length = str2.length();
        int count = 0;
        for (int i = 0; i < length; i++) {
            //测验str2中是否有str1没有的字符
            if(set.add(str2.charAt(i))) {
                return false;
            }else{
                //存str2的字符集
                sethlep.add(str2.charAt(i));
            }
        }
        //比较两个字符集是否相同，不同那么就代表str1有的字符而str2没有
        if(set.size() != sethlep.size()){
            return false;
        }
        return true;
    }
}

package com.ljp.leecode_cn.dict_tree;

import java.util.Collections;
import java.util.List;

/**
 * @author lijunpeng
 * @date 2022/7/7 23:51
 * @description
 **/

public class _中等_648_单词替换 {
    /**
    * @Author lijunpeng
    * @Date 2022/7/7 23:56
    * @Description 暴力算法
    */
    public String replaceWords(List<String> dictionary, String sentence) {
        Collections.sort(dictionary, (a, b) -> (a.length() - b.length()));
        StringBuilder sb = new StringBuilder();
        for(String word : sentence.split(" ")) {
            String curWord = word;
            for(String dict : dictionary) {
                if(word.startsWith(dict)) {
                    curWord = dict;
                    break;
                }
            }
            sb.append(curWord).append(" ");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    /**
    * @Author lijunpeng
    * @Date 2022/7/7 23:59
    * @Description 字典树
    */
    public String replaceWords2(List<String> dictionary, String sentence) {
        Dict dict = new Dict().buildDict(dictionary);
        StringBuilder sb = new StringBuilder();
        for(String word: sentence.split(" ")) {
            String curWord = matchWord(word, dict);
            sb.append(curWord).append(" ");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
    private String matchWord(String word, Dict dict) {
        StringBuilder sb = new StringBuilder();
        for(char ch : word.toCharArray()) {
            if(dict != null) {
                if(dict.isLeaf) {
                    break;
                }
                dict = dict.chars[ch - 'a'];
            }
            sb.append(ch);
        }
        return sb.toString();
    }
}

class Dict {
    private static final int CHARS_SIZE = 26;
    Dict[] chars;
    boolean isLeaf;
    public Dict() {
        this.chars = new Dict[CHARS_SIZE];
    }

    public Dict buildDict(List<String> dictionary) {
        for(String words : dictionary) {
            Dict node = this;
            for(char word : words.toCharArray()) {
                int idx = word - 'a';
                if(node.chars[idx] == null) {
                    node.chars[idx] = new Dict();
                }
                node = node.chars[idx];
            }
            node.isLeaf = true;
        }
        return this;
    }
}

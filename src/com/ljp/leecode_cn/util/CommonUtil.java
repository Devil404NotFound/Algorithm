package com.ljp.leecode_cn.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijunpeng
 * @date 2022/5/23 22:04
 * @description
 **/

public class CommonUtil {
    public static List<Integer> ArrayToList(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int num : array) {
            list.add(num);
        }
        return list;
    }
}

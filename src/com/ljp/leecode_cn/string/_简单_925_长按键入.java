package com.ljp.leecode_cn.string;

/** 每日一题 2020.10.21
 925. 长按键入
 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。

 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。



 示例 1：

 输入：name = "alex", typed = "aaleex"
 输出：true
 解释：'alex' 中的 'a' 和 'e' 被长按。
 示例 2：

 输入：name = "saeed", typed = "ssaaedd"
 输出：false
 解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
 示例 3：

 输入：name = "leelee", typed = "lleeelee"
 输出：true
 示例 4：

 输入：name = "laiden", typed = "laiden"
 输出：true
 解释：长按名字中的字符并不是必要的。


 提示：

 name.length <= 1000
 typed.length <= 1000
 name 和 typed 的字符都是小写字母。
 * @author ljp
 * @date 2020/10/21 15:07
 */
public class _简单_925_长按键入 {
    /**
     * 双指针+边界
     * @param name
     * @param typed
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了86.83%的用户
    内存消耗：
    36.7 MB, 在所有 Java 提交中击败了63.24%的用户
     */
    public boolean isLongPressedName(String name, String typed) {
        //添加边界
        typed = typed + '#';
        name += '#';
        char[] nameCh = name.toCharArray();
        char[] typedCh = typed.toCharArray();
        int i = 0, j = 0, count = 0;
        while(i < nameCh.length && j < typedCh.length){
            if(nameCh[i] == typedCh[j]){
                i++;
                j++;
            }else if(j > 0 && typedCh[j - 1] == typedCh[j]){
                j++;
            }else{
                return false;
            }
        }
        return true;
    }

    /**
     * 官方题解：双指针（不用加边界）
     * @param name
     * @param typed
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了86.83%的用户
    内存消耗：
    36.6 MB, 在所有 Java 提交中击败了75.05%的用户
     */
    public boolean isLongPressedName2(String name, String typed) {
        int i = 0, j = 0;
        while(j < typed.length()){
            if(i < name.length() && name.charAt(i) == typed.charAt(j)){
                i++;
                j++;
            }else if(j > 0 && typed.charAt(j - 1) == typed.charAt(j)){
                j++;
            }else{
                return false;
            }
        }
        return i == name.length();
    }
}

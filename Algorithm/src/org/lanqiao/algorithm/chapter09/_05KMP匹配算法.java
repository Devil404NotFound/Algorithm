package org.lanqiao.algorithm.chapter09;

public class _05KMP匹配算法 {
    public static void main(String[] args) {
        String s1 = "abaabbababcab";
        String s2 = "ababc";
        int result = KMP(s1,s2);
        System.out.println(result);
    }
    /**
     * KMP字符串匹配算法
     * @param s
     * @param t
     * @return
     */
    public static int KMP(String s, String t){
        if(s.length() <= 0){
            return 0;
        }
        //计算每个位置的前缀匹配个数
        int[] next = getNext(t);
        char[] charArr1 = s.toCharArray();
        char[] charArr2 = t.toCharArray();
        int i = 0, j = 0;
        while(i < charArr1.length){
            if(j == -1 || charArr1[i]== charArr2[j]){
                i++;
                j++;
            }else{
                j = next[j];
            }
            if(j == charArr2.length){
                return i - j;
            }
        }

        return -1;
    }
    //预处理
    public static int[] getNext(String str){
        char[] charArr = str.toCharArray();
        int[] next = new int[charArr.length];
        next[0] = -1;
        int k = -1;
        int j = 0;
        while(j <charArr.length - 1){
            if(k == -1 || charArr[j] == charArr[k]){
                j++;
                k++;
                next[j] = k;
            }else{
                k = next[k];
            }
        }
        return next;
    }

    public static int bruteForce(String s, String t){
        char[] charArr2 = t.toCharArray();
        if(charArr2.length == 0){
            return 0;
        }
        char[] charArr1 = s.toCharArray();
        int max = charArr1.length - charArr2.length;
        int i = 0;
        while(i < max){
            int k = 0;
            int j = i;
            while(j < charArr1.length) {
                if(charArr1[j]==charArr2[k]){
                    ++j;
                    ++k;
                }else{
                    break;
                }
                if(k == charArr2.length){
                    return j - k;
                }
            }
            i++;
        }
        return -1;
    }
}

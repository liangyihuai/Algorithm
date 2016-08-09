package com.huai.search;

/**
 * Created by liangyh on 5/20/16.
 */
public class KMPAlgorithm {

    /**
     * 判断是否匹配
     * @param target 目标文本串
     * @param mode 模式串
     * @return 匹配结果
     */
    public static boolean matchString(String target, String mode){
        return matchString(target, mode, 1);
    }

    /**
     * 判断是否匹配
     * @param target 目标文本串
     * @param mode 模式串
     * @param begin begin index, start from 0
     * @return 匹配结果
     */
    public static boolean matchString(String target, String mode, int begin) {
//        begin ++;
//        if(begin < 1 || begin > target.length())
//            return false;

        //为了和算法保持一致，使index从1开始，增加一前缀
        String newTarget = target;
        String newMode =mode;

        int[] K = calculateK(mode);

//        int i = 1;
        int i = begin;
        int j = 0;
        while(i < target.length() && j < mode.length()) {
            if (j == 0 || newTarget.charAt(i) == newMode.charAt(j)) {
                i++;
                j++;
            } else {
                j = K[j];
            }
        }

        if (j > mode.length()) {
            return true;
        }
        return false;
    }

    /*
     * 计算K值
     */
    private static int[] calculateK(String mode) {
        //为了和算法保持一致，使index从1开始，增加一前缀
        String newMode = mode;
        int[] K = new int[newMode.length()];
        int i = 0;
        K[0] = 0;
        int j = 0;

        while(i < mode.length()) {
            if (j == 0 || newMode.charAt(i) == newMode.charAt(j)){
                i++;
                j++;
                K[i] = j;
            } else {
                j = K[j];
            }
        }

        return K;
    }


    public static void main(String[] args) {
        String a = "wewwewewi";
        String b = "wi";//"ababbaaba";//
        System.out.println(KMPAlgorithm.matchString(a, b, 7));

    }

}

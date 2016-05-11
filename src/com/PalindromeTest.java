package com;

/**
 * 找出某个字符串中最长的回文子字符串。
 * Created by liangyh on 4/4/16.
 */
public class PalindromeTest {

    public String buildPalindrome(String str){
        if(str == null) return str;

        int len = str.length();
        int start = 0;
        int end = len-1;

        String stringStore = "";//用于存储目前最长的回文字符串。

        StringBuilder builder = null;//用于临时存储不完整的回文字符串
        for (int i = 0; i < len; i++) {
            builder = new StringBuilder();
            int builderIndex = 0;

            int startIndex = i;
            int endIndex = end;
            char startChar = str.charAt(startIndex);
            while(endIndex > startIndex){
                char tempEndChar = str.charAt(endIndex);//后面的字符
                if(startChar == tempEndChar){//前面和后面的相等。
                    builder.insert(builderIndex, startChar);//插入前面的
                    builderIndex++;
                    builder.insert(builderIndex, tempEndChar);//插入后面的

                    startIndex++;

                    //重置前面的字符。
                    startChar = str.charAt(startIndex);
                }
                endIndex--;
            }
            if(startIndex == endIndex)//如果中间存在不成对的字符串，选择第一个。
                builder.insert(builderIndex, str.charAt(startIndex));

            if(builder.length() > stringStore.length()){//更新最大的回文字符串。
                stringStore = builder.toString();
            }
        }
        return stringStore;
    }

    public static void main(String[] args) {
        PalindromeTest test = new PalindromeTest();
        String str = test.buildPalindrome("uiuiuyiuyiiuiuiu");
        System.out.println(str);
    }
}

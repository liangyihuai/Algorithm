package com.huai.recursion;

/**
 * Created by liangyh on 4/1/16.
 */
public class ListAll {

    public void listAll(String str, int startIndex){
        if(str == null || "".equals(str)) return ;

        if(startIndex >= str.length()) return ;

        int len = str.length();

        char[] c = new char[len];

        for (int i = 0; i < len; i++) {
            c[startIndex] = str.charAt(i);

            listAll(str, startIndex+1);
        }


    }

    private void exchange(){

    }
}

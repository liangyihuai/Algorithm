package com.huai.search.version2;

/**
 * Created by liangyh on 5/20/16.
 */
public class Main2 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        FindWord2 findWord = new FindWord2();
        findWord.doCount("/home/liangyh/Documents/hitch2.txt");
        findWord.printInfo();
        long end = System.currentTimeMillis();

        System.out.println("total time : "+(end - start)+" ms");
    }
}

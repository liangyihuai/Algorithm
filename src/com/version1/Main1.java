package com.version1;

/**
 * Created by liangyh on 5/20/16.
 */
public class Main1 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        FindWord1 findWord = new FindWord1();
        findWord.doCount("/home/liangyh/Documents/hitch2.txt");
        findWord.printInfo();

        long end = System.currentTimeMillis();

        System.out.println("total time : "+(end - start)+" ms");
    }
}

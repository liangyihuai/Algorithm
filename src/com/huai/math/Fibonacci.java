package com.huai.math;

/**
 * Created by liangyh on 3/13/16.
 */
public class Fibonacci {

    /**
     * 斐波那契数列，面试题9
     * @param n
     * @return
     */
    public int doFibonacci(int n){
        if(n < 0) return -1;

        if(n == 0) return 0;
        if(n == 1) return 1;

        int a = 0;
        int b = 1;

        for (int i = 2; i < n; i++) {
            int temp = a;
            a = b;
            b = b+temp;

        }
        return a+b;
    }

    public static void main(String[] a){
        Fibonacci f = new Fibonacci();
        int i = f.doFibonacci(4);
        System.out.println(i);
    }
}

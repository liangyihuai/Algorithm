package com.huai.math;

/**
 * Created by liangyh on 1/27/16.
 */
public class GCDTest {

    /**
     *  get greatest common divisor
     * @param a
     * @param b
     * @return
     */
    public long getGCDNum(long a, long b){
        if(a <= 0 || b <= 0) return -1;

        long c = 0;
        while(b != 0){
            c = a%b;
            a = b;
            b = c;
        }

        return a;
    }

    /**
     * get least common multiple
     * @param a
     * @param b
     * @return
     */
    public long getLCMNum(long a, long b){
        if(a <= 0 || b <= 0) return -1;

        long c = getGCDNum(a, b);

        return a*b/c;
    }

    public static void main(String[] args) {
        GCDTest test = new GCDTest();
        long a = test.getLCMNum(4, 6);
//        long a = test.getGCDNum(18, 12);
        System.out.println(a);
    }
}

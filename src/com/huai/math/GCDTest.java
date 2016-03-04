package com.huai.math;

/**
 * Created by liangyh on 1/27/16.
 */
public class GCDTest {

    /**
     *  get greatest common divisor
     *  Euclid
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
     * Euclid
     * @param a
     * @param b
     * @return
     */
    public long getLCMNum(long a, long b){
        if(a <= 0 || b <= 0) return -1;

        long c = getGCDNum(a, b);

        return a*b/c;
    }

    /**
     *  get greatest common divisor
     *  Stein
     * @param a
     * @param b
     * @return
     */
    public long getGCDNum_Stein(long a, long b){
        if(a <= 0 || b <= 0) return -1;

        long c = 1;

        while(a != 0 && b != 0){
            while((a & 0x1) == 0 && (b & 0x1) == 0){
                a = a>>1;
                b = b>>1;
                c *= 2;
            }
            while((a & 0x1)==0){
                a = a>>1;
            }
            while((b & 0x1)==0){
                b = b>>1;
            }
            if((a & 0x1) == 1 && (b & 0x1) == 1){
                b = Math.min(a, b);
                a = Math.abs(a-b);
            }
        }
        if(a == 0) return b*c;
        if(b == 0) return a*c;
        return -1;
    }

    public static void main(String[] args) {
        GCDTest test = new GCDTest();
        int a = 225;
        int b = 25;

        long c = test.getGCDNum(a, b);
        System.out.println(c);

        long cc = test.getGCDNum_Stein(a, b);
        System.out.println(cc);
    }
}

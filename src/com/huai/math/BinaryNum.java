package com.huai.math;

import org.jcp.xml.dsig.internal.SignerOutputStream;

/**
 * Created by liangyh on 3/13/16.
 */
public class BinaryNum {

    /**
     * 判断某一个数的二进制数中的1的个数，包括符号位。
     * @param a
     * @return
     */
    public int getOneNum(int a){

        int count = 0;
        for (int i = 0; i < 32; i++) {
            if((a&1) == 1){
                count++;
            }
            a = a>>1;
        }
        return count;
    }

    /**
     * 这个方法的效率和第一种方法是一样的.
     * @param a
     * @return
     */
    public int getOneNum2(int a){
        int count = 0;
        int flag = 1;

        while(flag > 0){
            if((a & flag) == 1){
                count++;
            }
            flag = flag << 1;
        }
        return count;
    }

    /**
     * 三种方法中最好的一种。
     * @param a
     * @return
     */
    public int getOneNum3(int a){
        int count = 0;

        while(a != 0){
            count++;
            a = (a - 1) & a;
        }
        return count;
    }

    public static void main(String[] args) {
        BinaryNum test = new BinaryNum();
        int in = -1;
        int i = test.getOneNum(in);
        System.out.println(i);

        int ii = test.getOneNum(in);
        System.out.println(ii);

        int iii = test.getOneNum(in);
        System.out.println(iii);

        System.out.println(Double.MAX_VALUE);
        System.out.println(Double.MIN_VALUE);

        test.powerWithUnsignedExponent(3, 7);
    }

    /**
     * 求解base的exponent次方。
     * @param base
     * @param exponent
     * @return
     */
    public double powerWithUnsignedExponent(double base, int exponent){
        if(exponent == 0){
            return  1;
        }
        if(exponent == 1){
            return base;
        }

        double result = powerWithUnsignedExponent(base, exponent>>1);
        result *= result;
        if((exponent & 0x1) == 1){
            result *= base;
        }
        return result;
    }
}

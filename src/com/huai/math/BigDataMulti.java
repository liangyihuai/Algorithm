package com.huai.math;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created by liangyh on 1/27/16.
 */
public class BigDataMulti {

    public int[] doMuti(int[] a, int[] b){
        if(a == null || b == null) return null;

        int aLen = a.length;
        int bLen = b.length;

        int array[] = new int[aLen+bLen];

        //相乘
        for (int i = 0; i < aLen; i++) {
            for (int j = 0; j < bLen; j++) {
                array[i+j] += a[i]*b[j];
            }

            //进位
            for (int j = 0; j < (aLen + bLen - 1); j++) {
                if(array[j] >= 10){
                    array[j+1] += array[j]/10;
                    array[j] = array[j]%10;
                }
            }
        }
        return array;
    }

    private int[] removeZero(int [] a){
        if(a == null) return null;
        boolean flag = false;
        for (int i = 0; i < a.length; i++) {
            if(a[i] > 0)
                flag = true;
        }
        if(!flag) return null;

        int [] targetArray = null;
        for (int i = a.length-1; i >= 0 ; i--) {
            if(a[i] > 0){
                targetArray = new int[i+1];
                for (int j = 0; j < i + 1; j++) {
                    targetArray[j] = a[j];
                }
                break;
            }
        }
        return targetArray;
    }

    public String toString(int [] a){
        if(a == null) return null;

        StringBuffer buf = new StringBuffer();
        for(int i = 0; i < a.length; i++){
            buf.append(a[i]);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        BigDataMulti test = new BigDataMulti();

        int[] a = {9,9,9,6,5,4,3,5};
        int[] b = {9,9,6,7,8,5,4};

        BigDecimal ba = new BigDecimal(test.toString(a));
        BigDecimal bb = new BigDecimal(test.toString(b));
        System.out.println(ba.multiply(bb));

        int c[] = test.doMuti(new int[]{5, 3, 4, 5, 6, 9, 9, 9}, new int[]{4, 5, 8, 7, 6, 9, 9});
        for (int i = c.length-1; i >= 0 ; i--) {
            System.out.print(c[i]);
        }
        System.out.println();


        int[] aa = {1,2,3,4,0,0,1,0,0};
        int [] bbb = test.removeZero(aa);
        for (int i = 0; i < bbb.length; i++) {
            System.out.print(bbb[i]+"; ");
        }
    }
}

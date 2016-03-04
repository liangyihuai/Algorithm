package com.huai.math;

/**
 * Created by liangyh on 1/28/16.
 */
public class BigDataDelete {
    public int[] doDelete(int[] a, int [] b) throws Exception {
        if(a == null || b == null) return null;
        if(isZero(b)) throw new Exception("the denominator must not be zero");



        return null;
    }

    private boolean isZero(int[] num){
        if(num == null) throw new NullPointerException("the input args is null");

        for (int i = 0; i < num.length; i++) {
            if(num[i] != 0){
                return false;
            }
        }
        return true;
    }

}

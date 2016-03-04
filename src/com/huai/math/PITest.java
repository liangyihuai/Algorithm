package com.huai.math;

import java.io.PrintStream;

/**
 * Created by liangyh on 1/28/16.
 */
public class PITest {

    public void getPI(){

        int numerator = 1, denominator = 3;
        double temp = 2, pi = 2;
        while(temp > 0.000000000001){
            temp = temp*numerator/denominator;
            pi += temp;
            numerator++;
            denominator += 2;
        }

        System.out.println("PI = " + pi);
    }

    public static void main(String[] args) {
        PITest test = new PITest();
        test.getPI();
    }
}

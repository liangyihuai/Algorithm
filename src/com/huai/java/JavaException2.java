package com.huai.java;

/**
 * Created by liangyh on 3/30/16.
 */
public class JavaException2 {

    public boolean testE1(){
        boolean ret = true;
        try {
            ret = testE2();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public boolean testE2() throws Exception{
        boolean ret = true;

        int b = 12;
        int c;

        try {
            throw new NullPointerException("null-liangyihuai");
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("catch");
        } finally {
            System.out.println("finally");
        }
        System.out.println("end");
        return ret;

    }

    public static void main(String[] args) {
        JavaException2 test = new JavaException2();
        try {
            test.testE1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

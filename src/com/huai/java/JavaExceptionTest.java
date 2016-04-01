package com.huai.java;

/**
 * Created by liangyh on 3/30/16.
 */
public class JavaExceptionTest {


    public boolean testE()throws Exception{
        boolean ret = true;
        try {
            ret = testE1();
        } catch (Exception e) {
            System.out.println("testE, catch exception");
            ret = false;
            throw e;
        } finally {
            System.out.println("testE, finally; return value = "+ret);
            return ret;
        }

    }

    public boolean testE1() throws Exception{
        boolean ret = true;

        try {
            ret = testE2();
            if(!ret){
                return false;
            }
            System.out.println("testE1, at the end of try");
            return ret;
        } catch (Exception e) {
            System.out.println("testE1, catch exception!");
            ret = false;
            throw e;
        } finally {
            System.out.println("testE1, finally; return value = "+ret);
            return ret;
        }
    }

    public boolean testE2() throws Exception{
        boolean ret = true;

        try {
            int b = 12;
            int c;
            for(int i = 2; i >= -2; i--){
                c = b/i;
                System.out.println("i = "+i);
            }
            return true;
        } catch (Exception e) {
            System.out.println("testE2, catche exception!");
            ret = false;
            throw e;
        } finally {
            System.out.println("testE2, finally; return value = "+ret);
            return ret;
        }
    }

    public static void main(String[] args) {
        JavaExceptionTest test = new JavaExceptionTest();
        try {
            test.testE();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.huai.java;

/**
 * Created by liangyh on 3/29/16.
 */
public class LoadedClass {
    static {
        System.out.println("this is static area");
    }

    public LoadedClass(){
        System.out.println("this is constructor method");
    }

    public void doMethod(){
        System.out.println("this is common method");
    }
}

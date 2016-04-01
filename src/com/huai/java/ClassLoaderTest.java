package com.huai.java;

/**
 * Created by liangyh on 3/29/16.
 */
public class ClassLoaderTest {

    public void doForNameTest(){
        try {
//            不会执行static代码块。
//            ClassLoaderTest.class.forName("com.huai.java.LoadedClass", false, ClassLoader.getSystemClassLoader());
            ClassLoaderTest.class.forName("com.huai.java.LoadedClass");//会执行static代码块
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void doClassLoaderTest(){
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try {
            System.out.println("load the class, but not process the static area!");
            Class clz = classLoader.loadClass("com.huai.java.LoadedClass");
            System.out.println("have an instance of the class");
            LoadedClass loadedClass = (LoadedClass) clz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        ClassLoaderTest test = new ClassLoaderTest();
        test.doForNameTest();
//        test.doClassLoaderTest();
    }
}

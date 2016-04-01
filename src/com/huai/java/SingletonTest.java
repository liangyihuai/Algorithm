package com.huai.java;

/**
 * Created by liangyh on 3/5/16.
 */
public class SingletonTest {
}


/**
 * 单例模式，同时考虑到多线程
 * 缺点是相对复杂，在内存使用方面比下面的好
 */
class Singleton{
    private static Singleton  singleton;
    private static Object object = new Object();

    private Singleton(){}

    public static Singleton getInstance(){
        if(singleton != null) return singleton;

        synchronized (object){
            if(singleton == null)
                singleton = new Singleton();
        }
        return singleton;
    }
}

/**
 * 这种方法会过早实例化对象，降低了内存的使用率，优点是简单。
 */
class Singleton2{
    private static Singleton2 singleton;

    static{
        singleton = new Singleton2();
    }

    private Singleton2(){}

    public static Singleton2 instance(){
        return singleton;
    }
}

/**
 * 最好的一种，当调用instance方法的时候才会加载静态内部类
 */
class Singleton3{

    private Singleton3(){}

    public static Singleton3 instance(){
        return  Nested.instance;
    }

    static class Nested{
        static Singleton3 instance = new Singleton3();
    }
}
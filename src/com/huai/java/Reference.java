package com.huai.java;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.*;

/**
 * Created by liangyh on 4/13/16.
 */
public class Reference {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<String>();

        list.add("a");
        list.add("b");

        List<Object> list1 = (List)list;

        System.out.println(list1.get(0));
        System.out.println(list1.get(1));


    }

    public void doLinkedHashMapTest(){
        //Map<Integer, Integer> map = new java.util.LinkedHashMap<>();
        Map<Integer, Integer> map = new HashMap<>();
        map.put(2, 2);
        map.put(5, 5);
        map.put(1, 1);

        Set set = map.keySet();
        Iterator<Integer> ite = set.iterator();
        while(ite.hasNext()){
            System.out.println(ite.next());
        }

    }


}




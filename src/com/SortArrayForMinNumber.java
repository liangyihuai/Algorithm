package com;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * 输入一个正整数数组，把数组里所有的数字拼接起来排程一个数，打印能拼接的所有数字中最小的一个。
 * Created by liangyh on 4/5/16.
 */
public class SortArrayForMinNumber {
    public void doSortArrayForMinNumber(int[] arr){
        if(arr == null || arr.length == 0) return;

        int len = arr.length;

        String[] strings = new String[len];
        for (int i = 0; i < len; i++) {
            strings[i] = ""+arr[i];
        }

        Arrays.sort(strings, new MyComparator());
        for (int i = 0; i < len; i++) {
            System.out.print(strings[i]);
        }
    }

    /**
     * 定义比较规则
     */
    public class MyComparator implements Comparator<String>{
        @Override
        public int compare(String str1, String str2) {
            String temp1 = str1+str2;
            String temp2 = str2+str1;
            return temp1.compareTo(temp2);
        }
    }

    public static void main(String[] args) {
        SortArrayForMinNumber test = new SortArrayForMinNumber();

        int[] arr = {3, 32, 321};
        test.doSortArrayForMinNumber(arr);
    }
}

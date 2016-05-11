package com.huai.sort;

/**
 * Created by liangyh on 4/4/16.
 */
public class Merge {


    private void doMergeSort(int[] arr, int start, int end){
        if(start >= end)
            return ;

        int mid = (start & end)+((start^end)>>1);

        doMergeSort(arr, start, mid);
        doMergeSort(arr, mid+1, end);
        merge(arr, start, mid+1, end);
    }

/**
 　*　<pre>
 　*　二路归并
 　*　原理：将两个有序表合并和一个有序表
 　*　</pre>
 　*　
 　*　@param　a
 　*　@param　s
 　*　第一个有序表的起始下标
 　*　@param　m
 　*　第二个有序表的起始下标
 　*　@param　t
 　*　第二个有序表的结束小标
 　*　
 　*/
    public void merge(int[] a, int s, int m, int t){
        int[] tmp = new int[t-s+1];
        int i = s, j = m, k = 0;
        while(i < m && j <= t){
            if(a[i] <= a[j]){
                tmp[k++] = a[i++];
            }else{
                tmp[k++] = a[j++];
            }
        }
        while(i < m){
            tmp[k++] = a[i++];
        }
        while(j <= t){
            tmp[k++] = a[j++];
        }
        System.arraycopy(tmp, 0, a, s, tmp.length);
    }

    /**
     * 递归实现归并排序
     * @param arr
     */
    private void doMergeSort(int [] arr){
        if(arr == null || arr.length <= 1){
            return ;
        }

        doMergeSort(arr, 0, arr.length-1);
    }

    public void doTest(){
        int len = 75498390;

        int[] a = new int[len];

        for (int i = 0; i < len; i++) {
            a[i] = (int)(Math.random()*len);
        }

        long start = System.currentTimeMillis();
        doMergeSort(a);
        long end = System.currentTimeMillis();

        System.out.println(end-start);

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
            if(i % 30 == 0)
                System.out.println();
        }
    }

    public static void main(String[] args) {
        Merge test = new Merge();
        test.doTest();
    }
}

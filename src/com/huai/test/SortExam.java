package com.huai.test;

import org.junit.Test;

/**
 * Created by liangyh on 8/8/16.
 */
public class SortExam {

    public void quickSort(int [] arr){
        if(arr == null || arr.length == 0) return ;
        quickSort(arr, 0, arr.length - 1);

    }

    public void quickSort(int [] arr, int start, int end){
        if(start >= end) return;

        int mid = potition(arr, start, end);

        if(mid == -1)return;

        quickSort(arr, start, mid-1);
        quickSort(arr, mid+1, end);
    }

    private int potition(int [] arr, int start, int end){
        if(arr == null || start >= end) return -1;
        int a = start;
        int b = end;

        int base = arr[a];

        while(a < b){
            while(a < b && base <= arr[b]){
                b--;
            }
            arr[a] = arr[b];
            while(a < b && base >= arr[a]){
                a++;
            }
            arr[b] = arr[a];
        }
        arr[a] = base;
        return a;
    }


    public void print(int [] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    //*******************************************

    public void doMergeSort(int [] arr){
        if(arr == null) return;

        doMergeSort(arr, 0, arr.length-1);
    }

    private void doMergeSort(int[] arr, int start, int end){
        if(start >= end) return;

        int mid = (start & end) + ((start ^ end) >> 1);

        doMergeSort(arr, start, mid);

        doMergeSort(arr, mid+1, end);

        merge(arr, start, mid+1, end);

    }

    public void merge(int[] arr, int start, int mid, int end){
        if(arr == null) return;
        if(start >= end) return;

        int [] tempArr = new int[end - start + 1];

        int i = 0;

        int a = start;
        int b = mid;

        while(a < mid && b <= end){
            if(arr[a] > arr[b]){
                tempArr[i++] = arr[b++];
            }else{
                tempArr[i++] = arr[a++];
            }
        }

        while(a < mid){
            tempArr[i++]  = arr[a++];
        }

        while(b <= end){
            tempArr[i++] = arr[b++];
        }

       System.arraycopy(tempArr, 0, arr, start, tempArr.length);
    }


    @Test
    public void doTest(){
        int [] arr = {1, 4,5,6,2,3,7,8,3,1};
        SortExam sortExam = new SortExam();
        sortExam.doMergeSort(arr);
        sortExam.print(arr);

    }
}

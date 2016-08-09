package com.huai.sort.quick;

/**
 * 快速排序算法
 *
 * 时间复杂度：0(n*log2(n))
 * Created by liangyh on 16-1-18.
 */
public class QuickSortTest {
    public static void main(String[] args) {
       QuickSortTest test = new QuickSortTest();
        test.doTest2();
    }



    public void quictSort(int[] array){
        if(array == null) return ;

        doSort(array, 0, array.length-1);
    }

    private void doSort(int[] array, int ii, int jj){
        if(array == null) return;
        if(jj - ii < 1) return ;

        int i = ii;
        int j = jj;
        int base = array[i];//设置第一个数为基数

        while(i < j){
            while(i < j && array[j] >= base){
                j--;
            }
            array[i] = array[j];

            while(i < j && array[i] <= base){
                i++;
            }
            array[j] = array[i];
        }

        array[i] = base;
        //递归
        doSort(array, ii, i-1);//基数的前面部分的元素
        doSort(array, i+1, jj);//基数的后面部分的元素
    }


    //*************************************************

    public void quickSort(int []arr){
        if(arr == null) return;
        quickSort(arr, 0, arr.length-1);
    }

    private void quickSort(int []arr, int start, int end){
        if(start >= end) return ;
        int mid = potition(arr, start, end);
        if(mid == -1) return ;

        quickSort(arr, start, mid);
        quickSort(arr, mid+1, end);
    }

    private int  potition(int[] arr, int start, int end){
        if(start >= end) return -1;
        int i = start;
        int j = end;
        int base = arr[i];
        while(i < j){
            while(i < j && base <= arr[j])
                j--;
            arr[i] = arr[j];
            while(i < j && base >= arr[i])
                i++;
            arr[j] = arr[i];
        }
        arr[i] = base;
        return i;
    }




    public void doTest2(){

        int len = 75498390;

        int[] arr = new int[len];

        for (int i = 0; i < len; i++) {
            arr[i] = (int)(Math.random()*len);
        }

        int [] a = arr;

        long start = System.currentTimeMillis();

        quickSort(a);

        long end = System.currentTimeMillis();

        System.out.println(end-start);

//        for (int i = 0; i < len; i++) {
//            System.out.print(a[i] +" ");
//            if(i % 30 == 0)
//                System.out.println();
//        }
    }
}

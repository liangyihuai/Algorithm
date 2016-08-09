package com.huai.sort;

/**
 * Created by liangyh on 4/29/16.
 */
public class Heap {

    //把start为root的树调整为“跟”比左右孩子大。
    private void heapAdjust(int[] arr, int start, int end){
        int startValue = arr[start];
        //2*start+1表示左孩子
        for (int j = 2*start+1; j <= end; j = j*2 +1){
            if(j < end && arr[j] < arr[j+1])//j记录了最大孩子的下标
                j++;
            if(startValue >= arr[j])
                break;
            arr[start] = arr[j];
            start = j;
        }
        arr[start] = startValue;
    }

    private void createHeap(int arr[]){
        if(arr == null)return ;
        int len = arr.length;
        if(len <= 1) return ;
        for(int i = len/2-1; i >= 0; --i){
            heapAdjust(arr, i, len-1);
        }
    }

    public void heapSort(int arr[]){
        if(arr == null || arr.length <= 1)return ;

        createHeap(arr);

        for(int i = arr.length-1; i >= 1; i--){
            arr[0] ^= arr[i];
            arr[i] ^= arr[0];
            arr[0] ^= arr[i];

            heapAdjust(arr, 0, i-1);
        }
    }

    public void doTest(){

        int[] a = {3,4,5,2,3,9,8,0,6,7,6,5,3,12,3,23,5};
        createHeap(a);
        heapSort(a);

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");

        }
    }

    public static void main(String[] args) {
        Heap heap = new Heap();
        heap.doTest();
    }

}

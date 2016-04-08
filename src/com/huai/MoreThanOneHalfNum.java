package com.huai;

/**
 * Created by liangyh on 4/4/16.
 */
public class MoreThanOneHalfNum {

    /**
     * 某一个数多于总数的一半，找出概数。
     * @param arr
     * @return
     */
    public int moreThanOneHalfNum(int []arr){
        if(arr == null) return -1;

        int start = 0;
        int end = arr.length-1;
        int midIndex = arr.length/2;
        int index = partition(arr, start, end);

        while(index != midIndex){
            if(index < midIndex){
                start = index+1;
            }else{
                end = index-1;
            }
            index = partition(arr, start, end);
        }
        return arr[index];
    }

    public int partition(int arr[], int startIndex, int endIndex){
        if(startIndex > endIndex) return -1;
        if(startIndex == endIndex) return startIndex;

        int start = startIndex;
        int end = endIndex;
        int base = arr[start];
        while(start < end){
            while(start < end && base <= arr[end])
                end--;
            arr[start] = arr[end];
            while(start < end && base >= arr[start])
                start++;
            arr[end] = arr[start];
        }
        arr[start] = base;
        return start;
    }

    public int moreThanOneHalfNum2(int[] arr){
        if(arr == null) return -1;
        if(arr.length == 0) return -1;

        int num = arr[0];
        int count = 1;

        for (int i = 1; i < arr.length; i++) {
            if(count == 0){
                num = arr[i];
                count++;
            }else{
                if(arr[i] == num){
                    count++;
                }else{
                    count--;
                }
            }
        }
        return num;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,3,4,3,3,3,3};
        MoreThanOneHalfNum test = new MoreThanOneHalfNum();
        int i = test.moreThanOneHalfNum2(arr);
        System.out.println(i);
    }
}

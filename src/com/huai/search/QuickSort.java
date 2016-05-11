package com.huai.search;

/**
 * Created by liangyh on 4/1/16.
 */
public class QuickSort {

    /**
     * 在一个数组中有一个数的个数超过一半以上，求出这个数。
     * 方法一。
     * @param num
     * @param len
     * @return
     */
    public int findMoreThanHalfNum(int[] num, int len){
        if(num == null || len <= 0) return 0;

        int mid = len>>1;
        int start = 0;
        int end = len-1;

        int index = partition(num, start, end);

        while(mid != index){
            if(index < mid){
                start = index + 1;
                index = partition(num, start, end);
            }else{
                end = index - 1;
                index = partition(num, start, end);
            }
        }

        if(!check(num, num[index], len)){
            return 0;
        }

        return num[index];
    }

    private int partition(int[] num, int start, int end){
        int base = num[start];
        while(start < end){
            while(start < end && base <= num[end]){
                end--;
            }
            num[start] = num[end];
            while(start < end && base >= num[end]){
                start++;
            }
            num[end] = num[start];
        }
        num[start] = base;
        return start;
    }

    /**
     * 方法二，和方法1的思路一样。
     * @param num
     * @param a
     * @param b
     * @param mid
     * @return
     */
    private int partition2(int[] num, int a, int b, int mid){
        if(a >= b) return 0;

        int start = a;
        int end = b;
        int base = num[start];
        while(start < end){
            while(start < end && base <= num[end]){
                end--;
            }
            num[start] = num[end];
            while(start < end && base >= num[start]){
                start++;
            }
            num[end] = num[start];
        }

        if(start == mid)
            return num[start];
        num[start] = base;

        int result = partition2(num, a, start-1, mid);
        if(result != 0)
            return num[result];

        result = partition2(num, start+1, b, mid);
        if(result != 0)
            return num[result];

        return 0;
    }

    private boolean check(int[] num, int target, int len){
        int count = 0;

        for (int i = 0; i < len; i++) {
            if(num[i] == target)
                count++;
        }
        if(count >= len>>1){
            return true;
        }
        return false;
    }

    public void doTest(){
        int[] num = {1,2,3,2,2,2,5,4,2};
//        int result = findMoreThanHalfNum(num, num.length);
        int mid = num.length>>1;
        int result = partition2(num, 0, num.length-1, mid);
        System.out.println(result);
    }


    //***********************************************************

    public static void main(String[] args) {
        QuickSort test = new QuickSort();
        test.doTest();
    }
}

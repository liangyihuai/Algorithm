package com;

/**
 * 在数组中的两个数字如果前面一个数字大于后面的数字，则这两个数字组成
 * 一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 *时间复杂度为归并算法的时间复杂度，nlog（n）
 * Created by liangyh on 4/5/16.
 */
public class InversePairs {

    public int doInversePaires(int [] arr, int start, int end){
        if(arr == null || arr.length == 0)
            return 0;

        if(start >= end)
            return 0;

        int mid = (start+end)/2;

        int count1= doInversePaires(arr, start, mid);
        int count2 = doInversePaires(arr, mid+1, end);
        int count = insertSort(arr, start, mid, end);
        return count+count1+count2;
    }

    //插入排序的思路。
    private int insertSort(int[] arr, int start, int mid, int end){
        if(arr == null)
            return 0;

        if(start >= end)
            return 0;

        int count = 0;

        int midIndex = mid;
        int endIndex = end;

        //统计个数
        for (int i = mid; i >= start ; i--) {
            for (int j = end; j >= mid+1 ; j--) {
                if(arr[i] > arr[j]){
                    count += j-mid;
                    break;
                }
            }
        }

        //排序
        for (int i = start+1; i <= end; i++) {
            int index = i;
            while(index > start){
                if(arr[index] < arr[index-1]){
                    arr[index] ^= arr[index-1];
                    arr[index-1] ^= arr[index];
                    arr[index] ^= arr[index-1];

                    index--;
                }else{
                    break;
                }
            }
        }
        return count;
    }

    public void doTest(){
        int[] a = new int[]{};
        int result = doInversePaires(a, 0, a.length-1);

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
        }
        System.out.println();
        System.out.println("result = "+result);
    }

    public static void main(String args[]){
        InversePairs test = new InversePairs();
        test.doTest();
    }
}

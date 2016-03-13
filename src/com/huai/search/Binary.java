package com.huai.search;

/**
 * Created by liangyh on 3/13/16.
 */
public class Binary {
    /**
     * 采用循环的方式进行数组的二分查找。注意的是数组在传入之前必须是有序的。
     * @param a
     * @param target
     * @return
     */
    public int doBinarySearch(int [] a, int target){

        if(a == null) return -1;

        int first = 0;
        int last = a.length-1;

        while(first <= last){
            int mid = (first&last)+((first^last)>>1);
            if(target == a[mid]){
                return mid;
            }else if(target > a[mid]){
                first = mid+1;
            }else if(target < a[mid]){
                last = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 采用递归的方式查找数组的某元素。
     * @param a
     * @param target
     * @param first 数组的第一个元素的下标
     * @param last 数组的第二个元素的下标
     * @return
     */
    public int doBinarySearch(int[] a, int target, int first, int last){
        if(a == null) return -1;

        if(first > last) return -1;

//        int mid = (first+last)/2;
        int mid = (first&last)+((first^last)>>1);
        if(target  == a[mid])
            return mid;
        else if (target > a[mid])
            return doBinarySearch(a, target, mid + 1, last);
        else if(target < a[mid])
            return doBinarySearch(a, target, first, mid - 1);

        return -1;
    }


    /**
     * 这个是《剑指offer》中的面试习题8，旋转数组的最小值的解法。
     * @param a
     * @return
     */
    public int getMin(int[] a){
        if(a == null) return -1;

        //检查第一个元素是不是最小的。
        if(a[0] < a[a.length-1]) return a[0];

        int first = 0;
        int last = a.length-1;
        int mid;

        while(last - first > 1){
            mid = (first&last)+((first^last)>>1);
            if(a[mid] == a[first]){
                int temp = a[first];
                for (int i = first; i <= last; i++) {
                    if(temp > a[i])
                        temp = a[i];
                }
                return temp;
            }else if(a[mid] > a[0]){
                first = mid;
            }else if(a[mid] < a[0]){
                last = mid;
            }
        }
        return a[last];
    }



    public static void main(String[] args) {
        Binary test = new Binary();
//        int result = test.doBinarySearch(new int[]{1,3,5,7,8,9,9,10,23}, 5, 0, 8);

        int result = test.getMin(new int[]{0,1,1,0});
        System.out.println(result);
    }
}

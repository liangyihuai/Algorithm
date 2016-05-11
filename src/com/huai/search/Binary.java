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
                first = mid + 1;
            }else if(a[mid] < a[0]){
                last = mid - 1;
            }
        }
        return a[last];
    }

    /**
     * 使用二分查找的方法，在一个顺序的数组中查找某个元素第一次出现的位置。
     * @param arr
     * @param target
     * @return
     */
    public int findMin(int arr[], int target){
        if(arr == null || arr.length == 0) return -1;

        int start = 0;
        int end = arr.length-1;

        while(start <= end){
            int mid = (start&end)+((start^end)>>1);

            if(arr[mid] == target){
                if(mid == 0)
                    return 0;
                if(arr[mid-1] < target){
                    return mid;
                }
                end = mid;
            }
            else if(arr[mid] < target){
                start = mid+1;
            }else if(arr[mid] > target){
                end = mid-1;
            }
        }
        return -1;
    }

    /**
     * 使用二分查找的方法，在一个顺序的数组中查找某个元素最后出现的位置。
     * @param arr
     * @param target
     * @return
     */
    public int findMax(int[]arr, int target){
        if(arr == null || arr.length == 0)
            return -1;

        int start = 0;
        int end = arr.length-1;
        while(start <= end){
            int mid = (start&end)+((start^end)>>1);

            if(target < arr[mid]){
                end = mid-1;
            }else if(target > arr[mid]){
                start = mid+1;
            }else if(target == arr[mid]){
                if(mid == arr.length-1){
                    return mid;
                }
                if(arr[mid+1] > target){
                    return mid;
                }
                start = mid;
            }
        }
        return -1;
    }

    public void doTest_findMin(){
        int[] arr = {1,2,3,4,5,5,5,6,7};

        int[] arr2 = {4};
        int i = findMin(arr2, 2);
        System.out.println(i);
    }

    public void doTest_findMax(){
        int[] arr = {1,2,3,4,5,5,5,6,7};
        int [] arr2 = {2};
        int i = findMax(arr2, 6);
        System.out.println(i);
    }
    public static void main(String[] args) {
        Binary test = new Binary();

        test.doTest_findMin();
        test.doTest_findMax();

    }
}

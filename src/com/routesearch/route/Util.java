package com.routesearch.route;

/**
 * Created by liangyh on 4/3/16.
 */
public class Util {

    /**
     * 排序关键节点
     * 使用了快排算法。
     * @param keyVertexSet
     */
    public static void sortKeyVertexSet(int[] keyVertexSet){
        if(keyVertexSet == null)
            return ;

        doNestedSort(keyVertexSet, 0, keyVertexSet.length-1);
    }

    private static void doNestedSort(int[] keyVertexSet, int startIndex, int endIndex){
        if(startIndex >= endIndex)
            return ;

        int start = startIndex;
        int end = endIndex;
        int base = keyVertexSet[start];
        while(start < end){
            while(start < end && base <= keyVertexSet[end]){
                end--;
            }
            keyVertexSet[start] = keyVertexSet[end];
            while(start< end && base >= keyVertexSet[start]){
                start++;
            }
            keyVertexSet[end] = keyVertexSet[start];
        }
        keyVertexSet[start] = base;
        doNestedSort(keyVertexSet, startIndex, end-1);
        doNestedSort(keyVertexSet, start+1, endIndex);
    }

    /**
     * 二分查找
     * @param array
     * @param target
     * @return
     */
    public static int search_binary(int[] array, int target){
        if(array == null){
            System.err.println("the array is null !");
        }

        int start = 0;
        int end = array.length-1;
        int mid = 0;

        while(start <= end){
            mid = (start&end)+((start^end)>>1);
            if(array[mid] == target){
                return mid;
            }else if(array[mid] > target){
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return -1;
    }
}

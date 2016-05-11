package com;
import java.util.*;

/**
 * Created by liangyh on 4/12/16.
 */
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        if(nums == null)return null;

        int len = nums.length;
        int[] arr = null;
        arr = java.util.Arrays.copyOf(nums, len);

        java.util.Arrays.sort(arr);

        int a = 0, b = 0;

        int start = 0;
        int end = len-1;
        while(start < end){
            int sum = arr[start]+arr[end];
            if(target == sum){
                a = arr[start];
                b = arr[end];
                break;
            }
            if(target > sum){
                start++;
            }else if(target < sum){
                end--;
            }
        }

        int aa = 0;
        int bb = 0;
        boolean isAAssign = true;
        boolean isBAssign = true;
        for(int i = 0; i < len; i++){
            if(isAAssign && nums[i] == a){
                aa = i;
                isAAssign = false;
            }else if(isBAssign && nums[i] == b){
                bb = i;
                isBAssign = false;
            }
        }

        return new int[]{aa, bb};
    }

    public void doTest(){
        int [] a = {2,1,9,4,4,56,90,3};
        int[] aa = twoSum(a, 8);
        System.out.println(aa[0]+", "+aa[1]);

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.doTest();
    }
}

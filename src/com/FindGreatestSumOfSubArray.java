package com;

/**
 * Created by liangyh on 4/5/16.
 */
public class FindGreatestSumOfSubArray {

    private boolean invalidInput = true;

    /**
     * 1
     * @param arr
     * @return
     */
    public int findGreatestSumOfSubArray(int[] arr){
        if(!invalidInput) invalidInput = true;
        if(arr == null || arr.length <= 0){
            invalidInput = false;
            return 0;
        }

        int greatestSum = 0x80000000;
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            if(sum < 0){
                greatestSum = arr[i];
                sum = arr[i];
            }else{
                sum += arr[i];
                if(sum > greatestSum){
                    greatestSum = sum;
                }
            }
        }
        return greatestSum;
    }

    public void doTest(){
        int [] arr = new int[]{1,-2,3,10,-4,7,2,-5};
        int result = findGreatestSumOfSubArray(arr);
        System.out.println(result);
    }

    public static void main(String args[]){
        FindGreatestSumOfSubArray test = new FindGreatestSumOfSubArray();
        test.doTest();
    }
}

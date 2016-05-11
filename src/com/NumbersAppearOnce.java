package com;

/**
 * Created by liangyh on 4/6/16.
 */
public class NumbersAppearOnce {

    /**
     * 一个整型数组里面除了两个数字之外，其他的数字都出现了两次。
     * 找出这两个只出现一次的数字，要求时间复杂度为0（n）， 空间复杂度为0（1）
     * 步骤：
     * 1、对所有的数进行异或运算。
     * 2、根据第1步的结果，转换成二进制数，找到它的第一个1的位置。
     * 3、根据每一个整数的二进制数第一个1的位置，把整型二维数组分成两部分。（这个时候，两个只出现一次的数不会在同一个数组中）
     * 4、分别对两个数组进行异或运算，所得到的结果就是两个只出现一次的整数。
     */
    public void numversAppearOnce(int[] arr){
        if(arr == null) return ;

        if(arr.length < 2) return ;
        int result = arr[0];
        for (int i = 1; i < arr.length; i++) {
            result = result^arr[i];
        }

        if(result == 0)return ;

        int index = 0;
        while(true){
            if((result & 0x1) == 1){
                break;
            }
            result = result >> 1;
            index++;
        }

        int flag = 0x1 << index;

        int result1 =0, result2 = 0;

        for(int i = 0; i < arr.length; i++){
            if((arr[i] & flag) != 0){
                result1 = result1^arr[i];
            }else if((arr[i] & flag) == 0){
                result2 = result2^arr[i];
            }
        }

        System.out.println(result1+"; "+result2);
    }

    public void doTest(){
        int [] a = new int[]{1,2,3,1,3, 4, 5, 4, 5,6};
        numversAppearOnce(a);
    }

    public static void main(String args[]){
        NumbersAppearOnce test = new NumbersAppearOnce();
        test.doTest();
    }
}

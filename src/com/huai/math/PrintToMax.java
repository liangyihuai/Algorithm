package com.huai.math;

/**
 * Created by liangyh on 3/14/16.
 */
public class PrintToMax {

    /**
     * 《剑指offer面试题12》，打印1到n的最大位数。
     * 思路：大数递增。
     * @param n
     */
    public void printToMax(int n){
        if(n < 1)return ;

        int [] num = new int[n];
        while (true) {
            increaceOne(num);
            if(!print(num)){
                break;
            }
        }
    }

    /**
     * 第二种解法，递归全排序。
     * @param n
     */
    public void printToMax2(int n){
        if(n < 1) return ;

        int[] num = new int[n+1];

        printToMax_Recurisively(num, 0);
    }

    private void printToMax_Recurisively(int[] num, int index){
        if(index == num.length-1){
            print(num);
            return;
        }

        //index == 1的元素是没有用的。
        for (int i = 0; i < 10; i++) {
            num[index+1] = i;
            printToMax_Recurisively(num, index+1);
        }
    }

    private void increaceOne(int[] num){
        int index = num.length - 1;
        num[index] += 1;

        while(num[index]  == 10){
            num[index] = 0;
            index--;
            num[index] +=1;
        }
    }

    /**
     *
     * @param num
     * @return 如果到了最大的数，就返回false，否则返回true
     */
    private boolean print(int[] num){
        int len = num.length;

        int index = 0;
        while(index < len && num[index] == 0){
            index++;
        }

        int count = 0;
        for (int i = index; i < len; i++) {
            int a = num[i];

            if(a == 9)
                count++;

            System.out.print(a);
        }
        System.out.println();

        if(count == len) {
            return false;
        }

        return true;

    }

    public static void main(String[] args) {
        PrintToMax test = new PrintToMax();
        test.printToMax2(6);
    }
}

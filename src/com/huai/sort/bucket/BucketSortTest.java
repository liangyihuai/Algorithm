package com.huai.sort.bucket;

/**
 * 基数排序,或者桶子排序
 * <p>桶子的意思是一个二维数组</p>
 * <p>对于某一位（如个位）相同的数，被放到相同的桶子里面</p>
 * Created by liangyh on 16-1-19.
 */
public class BucketSortTest{
    public static void sort(int[] number, int d) {//d表示最大的数有多少位
        int k = 0;
        int n = 1;
        int m = 1; //控制键值排序依据在哪一位
        int[][]temp = new int[10][number.length]; //数组的第一维表示可能的余数0-9
        int[]order = new int[10]; //数组order[i]用来表示该位是i的数的个数
        while(m <= d){
            //对于某一位（如个位）相同的数，被放到相同的桶子里面
            for(int i = 0; i < number.length; i++){
                int lsd = ((number[i] / n) % 10);
                temp[lsd][order[lsd]] = number[i];
                order[lsd]++;
            }
            //
            for(int i = 0; i < 10; i++){
                if(order[i] != 0)
                    for(int j = 0; j < order[i]; j++){
                        number[k] = temp[i][j];
                        k++;
                    }
                order[i] = 0;
            }
            n *= 10;
            k = 0;
            m++;
        }
    }

    public static void print(int [] data){
        for(int i = 0; i < data.length; i++){
            System.out.print(data[i] + " ");
        }
    }

    public static void main(String[] args){
        int[]data = {73, 22, 93, 43, 55, 14, 28, 65, 39, 81, 33, 100};
        BucketSortTest.sort(data, 3);
        BucketSortTest.print(data);
    }
}
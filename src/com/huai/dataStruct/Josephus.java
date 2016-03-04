package com.huai.dataStruct;

/**
 * 约瑟夫环问题。
 * Created by liangyh on 3/4/16.
 */
public class Josephus {
    /**
     *
     * @param people 多少个人
     * @param begin 从哪个人开始, 第一个人从1开始
     * @param num 一个数几个
     * @param left 最终剩下几个人
     */
    public int[] doJosephus(int people, int begin, int num, int left){
        if(people <= 0 || left <= 0 || num <= 0) return null;
        if(begin >= people) return null;

        int [] array = new int[people];

        int out = 0;//已经出局的人数
        int index = begin-1;
        int count = 0;//记录是否数到了num个人
        while(people - out > left){

            if(array[index] == 0){
                count++;
                if(count == num){//如果数够了num个人
                    array[index] = 1;//表示第index位置的人出局
                    count = 0;
                    out++;
                }
            }
            index++;
            if(index == people){
                index = 0;
            }
        }
        return array;
    }

    public static void main(String[] a){
        Josephus josephus = new Josephus();
        int[] aa = josephus.doJosephus(8, 3, 3, 2);
        for (int i = 0; i < aa.length; i++) {
            System.out.print(aa[i]+"; ");
        }
    }
}

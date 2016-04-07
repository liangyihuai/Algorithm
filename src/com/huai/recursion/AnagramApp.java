package com.huai.recursion;

import java.util.Scanner;

/**
 * 应用递归实现变位数，即将一个字符串进行全排列
 * Created by liangyh on 4/4/16.
 */
public class AnagramApp {

    public static char[] arrChar = new char[100];
    public static int size = 0;
    public static int count = 0;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
//		System.out.print("Enter a String: ");
//		String s = in.nextLine();

        String s = "cost";

        size = s.length();
        for (int i = 0; i < size; i++) {
            arrChar[i] = s.charAt(i);
        }

        System.out.println("===========字符串全排列=============");
        doAnagram(size);
    }



    //将右边指定个数的字符进行翻转
    public static void rotate(int newSize) {
        int position = size - newSize;   //获得要翻转的字符串的首字母
        char temp = arrChar[position];    //保存首字母，以便之后将其置于字符串最后
        int i = 0;

        for(i=position; i<size-1; i++){
            arrChar[i] = arrChar[i+1];           //除首字母外，后面的字符都向前移位
        }

        arrChar[i] = temp;

    }

    //输出字符串
    public static void displayStr() {
        //进行格式控制
        if (count < 99)
            System.out.print(" ");
        if (count < 9)
            System.out.print(" ");
        System.out.print(++count + " ");

        for (int i = 0; i < size; i++) {
            System.out.print(arrChar[i]);
        }
        System.out.print("	");

        //6个单词一行
        if (count%6 == 0) {
            System.out.println("");
        }
    }

    /**
     * 梁义怀
     * @param newSize
     */
    public static void doAnagram(int newSize){

        if(newSize <= 1)
            return ;

        for (int i = 0; i < newSize; i++) {//重复下面步骤n次。
            doAnagram(newSize - 1);//全排列最右边n-1个。
            if(newSize == 2){
                displayStr();
            }
            rotate(newSize);//轮换所有n个字母。
        }
    }
}

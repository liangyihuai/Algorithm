package com.ccf.year2016;

import java.util.Scanner;

/**
 * 2016/4/10 CCF第二题
 * Created by liangyh on 4/11/16.
 */
public class CCF2016_4_10_2 {

    private static final int row = 15;
    private static final int col = 10;
    private static final int modelLen = 4;

    private static final int [][] dya = new int[row][col];
    private static final int[][] dyaB = new int[modelLen][modelLen];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int inputIndex = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dya[i][j] = scanner.nextInt();
            }
            scanner.nextLine();
        }

        for (int i = 0; i < modelLen; i++) {
            for (int j = 0; j < modelLen; j++) {
                dyaB[i][j] = scanner.nextInt();
            }
            scanner.nextLine();
        }

        //
        inputIndex = scanner.nextInt();
        int startIndex_model = 0;

        boolean isFound = false;
        for (int i = 0; i < modelLen; i++) {//col
            for (int j = 0; j < modelLen; j++) {//row
                if(dyaB[j][i] == 1){
                    startIndex_model = i;
                    isFound = true;
                    break;
                }
            }
            if(isFound)
                break;
        }

        int temp = row-modelLen+1;
        for (int i = 0; i < temp; i++) {
            if(isTough(i, inputIndex, startIndex_model)){
                fill(i-1, inputIndex, startIndex_model);
                break;
            }
            if(i == temp-1){
                fill(i, inputIndex, startIndex_model);
                break;
            }
        }

        print();

        scanner.close();
    }

    /**
     *是否碰撞
     * @param rowIndex 大二位数组的起始行下标。
     * @param colIndex 大二维数组的起始列下标。
     * @param startCol 模板的开始的列
     * @return
     */
    private static boolean isTough(int rowIndex, int colIndex,  int startCol){
        for (int i = modelLen-1; i >= 0; i--) {
            int tempIndex = 0;
            for (int j = startCol; j < modelLen; j++) {
                if(dyaB[i][j] == 1 && dya[rowIndex + i][colIndex+tempIndex] == dyaB[i][j]){
                    return true;
                }
                tempIndex++;
            }
        }
        return false;
    }

    /**
     * 填充
     * @param rowIndex
     * @param colIndex
     * @param startCol
     */
    private static void fill(int rowIndex, int colIndex, int startCol){
        for (int i = modelLen-1; i >= 0; i--) {
            int tempIndex = 0;
            for (int j = startCol; j < modelLen; j++) {
                if(dyaB[i][j] == 1){
                    dya[rowIndex + i][colIndex+tempIndex] = 1;
                }
                tempIndex++;
            }
        }
    }

    /**
     * 打印
     */
    private static void print(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col-1; j++) {
                System.out.print(dya[i][j]+" ");
            }
            System.out.print(dya[i][col-1]);
            System.out.println();
        }
    }

}

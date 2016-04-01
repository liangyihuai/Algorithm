package com.huai.java;

/**
 * Created by liangyh on 3/31/16.
 */
public class DyadicTest {

    int[][] dya = new int[][]{
            {1,  2,  3,  4,  5},
            {6,  7,  8,  9,  10},
            {11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20},
            {21, 22, 23, 24, 25}
    };

    /**
     * 顺时针从里到外打印矩阵。
     * @param dya
     */
    public void traverseDyadic(int[][] dya){
        if(dya == null) return ;

        int row = dya.length;
        int column = dya[0].length;

        int start = 0;
        while(start * 2 < row && start * 2 < column){
            traverseCircle(dya, row, column, start);
            start++;
        }
    }

    private void traverseCircle(int[][] dya, int row, int column, int start){

        int columnEnd = column - start - 1;
        int rowEnd = row - start - 1;

        //
        for (int i = start; i <= columnEnd; i++) {
            System.out.print(dya[start][i]+" ");
        }

        if(rowEnd - start >= 0){
            for (int i = start+1; i <= rowEnd; i++) {
                System.out.print(dya[i][columnEnd]+" ");
            }
        }

        if(rowEnd - start >= 1){
            for (int i = columnEnd-1; i >= start ; i--) {
                System.out.print(dya[rowEnd][i]+" ");
            }
        }

        if(columnEnd - start >= 1){
            for (int i = rowEnd-1; i >= start+1 ; i--) {
                System.out.print(dya[i][start]+" ");
            }
        }
    }

    public void doTest(){
        traverseDyadic(dya);
    }

    public static void main(String[] args) {
        DyadicTest test = new DyadicTest();
        test.doTest();
    }
}

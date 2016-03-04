package com.huai.math;

/**
 * Created by liangyh on 1/28/16.
 */
public class MatrixMulti {

    public int[][] doMatrixMulti(int[][] a, int[][] b){
        if(a == null || b == null) return null;
        if(a.length != b[0].length || a[0].length != b.length) return null;

        int [][] c = new int[a.length][a.length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {

                for (int k = 0; k < b.length; k++) {
                    c[i][j] += a[i][k]*b[k][j];
                }
            }
        }
        return c;
    }

    public int[][] doMatrixAdd(int[][] a, int[][]b){
        if(a == null || b == null) return null;
        if(a.length != a[0].length || b.length != b[0].length || a.length != b.length) return null;

        int[][] c = new int[a.length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                c[i][j] = a[i][j]+b[i][j];
            }
        }
        return c;
    }

    public int[][] doTranspose(int[][] a){
        if(a == null) return null;

        int[][] b = new int[a[0].length][a.length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                b[j][i] = a[i][j];
            }
        }
        return b;
    }

    public static void print(int[][] c){
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[0].length; j++) {
                System.out.print(c[i][j]+" ");
            }
            System.out.println();
        }
    }

    public int[] changeDyadicToLine(int[][] d){
        if(d == null) return null;

        int[] a = new int[d.length*d[0].length];
        int index = 0;
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[0].length; j++) {
                a[index] = d[i][j];
                index++;
            }
        }
        return a;
    }

    public static void main(String[] args) {
        MatrixMulti test = new MatrixMulti();
        int[][] a = {{5,8,3},{11,0,5}};
        int[][] b = {{1,18},{2,11},{10,3}};

        int[] c = test.changeDyadicToLine(b);

        for (int i = 0; i < c.length; i++) {
            System.out.print(c[i]+" ");
        }
    }
}

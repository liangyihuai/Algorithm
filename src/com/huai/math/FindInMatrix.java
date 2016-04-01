package com.huai.math;

/**
 * 在一个二位数组中，每一行都按照从左到右的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 输入一个二位数组和一个整数，判断数组中是否含有该整数。
 * <p>思路：先从右上角的元素开始，如果大于num， 则这一列都不等于，如果小于，就这一行都不等于。</p>
 * Created by liangyh on 3/5/16.
 */
public class FindInMatrix {

    public boolean doFindInMatrix(int[][] matrix, int num){
        if(matrix == null) return false;

        if(matrix.length ==0 || matrix[0].length == 0) return false;

        int rows = matrix.length;
        int columns = matrix[0].length;
        int row = 0;
        int column = columns-1;

        while(row <= rows-1 && column >= 0){
            if(matrix[row][column] == num){
                return true;
            }else if(matrix[row][column] > num){
                column--;
            }else if(matrix[row][column] < num){
                row++;
            }
        }
        return false;
    }

    public static void main(String args[]){
        int[][] matrix = {{1,2,3,4,5},{2,3,4,5,6},{4,5,6,7,8}};

        FindInMatrix test = new FindInMatrix();
        boolean flag = test.doFindInMatrix(matrix, 3);
        System.out.println(flag);
    }
}

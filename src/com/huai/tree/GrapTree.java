package com.huai.tree;

/**
 * 判断一个联通图是否为一棵树。
 *
 * Created by liangyh on 3/28/16.
 */
public class GrapTree {

    private int[][] dyadic = new int[][]{
            {0, 1, 0, 0, 1},
            {1, 0, 1, 1, 0},
            {0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {1, 0, 0, 0, 0}};

    public boolean check(int[][] dyadic){
        if(dyadic == null) return false;

        //标记是否已经划分入某个域A。用0和1来区分。数组的下标表示节点编号。
        int[] array = new int[dyadic.length];

        for (int i = 0; i < dyadic.length; i++) {
            array[i] = 1;//划分入域A
            for (int j = i; j < dyadic[0].length; j++) {
                if(dyadic[i][j] == 1){
                    if(array[j] == 1){
                        //因为该节点已经划分入了域A，所以表示该联通图有环。
                        return false;
                    }
                    //划分入域A
                    array[j] = 1;
                }
            }

        }
        //存在孤立的点，该图不是联通图。
        for (int i = 0; i < array.length; i++) {
            if(array[i] == 0)
                return false;
        }
        return true;
    }


    public void doTest(){
        boolean flag = check(dyadic);
        System.out.println(flag);
    }

    public static void main(String[] args) {
        GrapTree test = new GrapTree();
        test.doTest();
    }
}

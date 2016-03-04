package com.huai.sort;

/**
 * Created by liangyh on 3/1/16.
 */
public class ShellTest {


    public void doTest2(int[] a, int[] dis){
        if(a == null || a.length <= 1) return ;

        for (int i = dis.length-1; i >= 0; i--) {
            for (int x = 0; x < a.length-dis[i]; x++) {
                int j = x;
                while(j < a.length-dis[i] && a[j+dis[i]] < a[j]){
                    a[j+dis[i]] ^= a[j];
                    a[j] ^= a[j+dis[i]];
                    a[j+dis[i]] ^= a[j];
                    j += dis[i];
                }
            }
        }
    }

    public static void main(String[] args) {
        ShellTest test = new ShellTest();
        int [] a = {5,6,3,1,7,2, 6,9,4,0};
        int [] dis = {1,2,3,4};


        test.doTest2(a, dis);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+"; ");
        }

//        test.insertSort(a, 1, 9);
//
//        for (int i = 0; i < a.length; i++) {
//            System.out.print(a[i]+", ");
//        }

    }
}

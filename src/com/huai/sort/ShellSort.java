package com.huai.sort;

/**
 * 时间复杂度在o（nlogn）-o(n^2),但是小于o(n^2).
 * 在排序的过程中比较的次数比交换的次数多。一般在排序的早期先使用shell排序，再结合其他排序算法。
 * Created by liangyh on 3/1/16.
 */
public class ShellSort {

    /**
     * 第一种方式
     * @param a
     * @param dis
     * @param size
     */
    public void doShellSort(int[] a, int [] dis, int size){
        if(a == null || a.length <= 1 || dis == null || size == 0) return ;

        for(int i = size-1; i >= 0; i--){
            for (int j = dis[i]; j < a.length; j++) {
                int k = j;
                int temp;
                int cur = a[j];
                while((temp = k -dis[i]) >= 0){

                    count++;

                    if(a[k] < a[temp]){
                        a[k] = a[temp];

                        k -= dis[i];
                    }else{
                        break;
                    }
                }
                a[k] = cur;
            }
        }
    }

    /**
     * 增量为dk的一次希尔排序
     * @param a
     * @param dk
     */

    int count = 0;
     void doShellSort(int[] a,  int dk){
        if(dk <= 0) return ;

        for (int j = dk; j < a.length; j++) {
            int k = j;
            int temp;
            int cur = a[j];
            while((temp = k -dk) >= 0){

                if(cur < a[temp]){//注意，这里比较的是cur
                    a[k] = a[temp];

                    k -= dk;
                }else{
                    break;
                }
            }
            if(k != j)
                a[k] = cur;
        }
    }

    /**
     * 第二种方式
     * @param a
     */
    public void doShellSort(int[] a){
        if(a == null || a.length <= 1)return;
        int[] dis = new int[64];
        int i = 0;
        int size = 0;
        while(i*3+1 < a.length){
            i = i*3+1;
            dis[size++] = i;
        }

        for (int j = size-1; j >= 0; j--) {
            doShellSort(a, dis[j]);
        }
    }



    public static void main(String[] args) {
        ShellSort test = new ShellSort();


        int a[] = {6,5,4,3,2,1};
        test.doShellSort(a, new int[]{1,4}, 2);

        System.out.println("*************"+test.count+"**************");

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
    }
}

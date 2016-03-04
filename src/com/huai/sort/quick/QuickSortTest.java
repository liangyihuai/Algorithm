package com.huai.sort.quick;

/**
 * 快速排序算法
 *
 * 时间复杂度：0(n*log2(n))
 * Created by liangyh on 16-1-18.
 */
public class QuickSortTest {
    public static void main(String[] args) {
        int [] a = {4,3,2,1};
        QuickSortTest quickSortTest = new QuickSortTest();
        quickSortTest.doSort(a, 0, a.length-1);
        quickSortTest.print(a);
    }

    public void print(int[] a){
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }

    /**
     *
     * @param array 将要排序的数组
     * @param ii 数据的第一个元素的标
     * @param jj 数组的最后一个元素的下表
     */
    public void doSort(int[] array, int ii, int jj){
        if(array == null) return;
        if(jj - ii < 1) return ;

        int i = ii;
        int j = jj;
        int base = array[i];//设置第一个数为基数

        while(i < j){
            while(i < j && array[j] >= base){
                j--;
            }
            array[i] = array[j];

            while(i < j && array[i] <= base){
                i++;
            }
            array[j] = array[i];
        }

        array[i] = base;
        //递归
        doSort(array, ii, i-1);//基数的前面部分的元素
        doSort(array, i+1, jj);//基数的后面部分的元素
    }
}

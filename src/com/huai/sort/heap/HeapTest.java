package com.huai.sort.heap;

/**
 * Created by liangyh on 16-1-19.
 */
public class HeapTest {

    /**
     * 根据二叉树构造堆
     * <p>
     *      思路：假设有节点a， 那么比节点a的编号大的节点已经满足堆的条件了（父节点比子节点大），
     * 现在我们只需要让根节点a和它的子节点满足堆的条件即可。我们需要找到节点a的最大子节点b，
     * 如果子节点b不比父节点a大，那么以a为跟节点的堆已经构建好；
     * 否则，a和b交换，再以原来b所在的位置节点为跟节点进行上面重复操作。（因为以原来b节点的位置的堆可以已经被破坏了）</p>
     * <p>二叉树的表示方法：数组</p>
     * @param array 用来表示二叉树的数组
     * @param len 节点的个数
     */
    public void adjuctTree(int array[], int len){
        if(array == null || array.length <= 1 ||len <= 1 || array.length < len) return;

        for(int i = len-1; i >=0; i--){
            int nextIndex = i;
            do{
                nextIndex = exchange(nextIndex, array, len);
            }while(nextIndex != -1);
        }
    }


    /**
     * 交换父节点和比父节点大的最大的子节点
     * @param fatherIndex 父节点的数组下表
     * @param array
     * @param len 实际节点的个数
     * @return
     */
    private int exchange(int fatherIndex, int[] array, int len){
        int i = fatherIndex;
        int max = 0;

        int nextIndex = -1;

        int leftIndex = 2*i+1;
        int rightIndex = 2*i+2;

        //判断是否有子节点的下表（或者判断子节点的下表是否越界）
        if(rightIndex <= len-1){
            max = rightIndex;
            if(array[leftIndex] > array[rightIndex]){
                max = leftIndex;
            }
        }else if(leftIndex <= len-1){
            max = leftIndex;
        }else{
            return -1;
        }

        if(array[max] > array[i]){
            swap(array, i, max);
            nextIndex = max;
        }

        return nextIndex;
    }

    /**
     * 交换数组的两个元素
     * @param a 数组
     * @param i1 第一个元素的下标
     * @param i2 第二个元素的下标
     */
    private void swap(int [] a, int i1, int i2){
        a[i1] ^= a[i2];
        a[i2] ^= a[i1];
        a[i1] ^= a[i2];
    }

    /**
     * 堆排序
     * <p>思路：堆的根节点和数组中的最后一个元素交换，堆的节点减少1，以根节点为父节点进行堆调整，重复上面的步骤</p>
     * @param a 表示二叉树的数组
     * @param len 节点的个数
     */
    public void heapSort(int[] a, int len){

        int mark = len-1;//用来标记“堆”中的最后一个元素
        while(mark >= 1){
            //把最大的元素（跟节点）放到最后
            swap(a, 0, mark);

            //调整
            int nextIndex = 0;
            do{
                nextIndex = exchange(nextIndex, a, mark);
            }while(nextIndex != -1);

            mark--;
        }
    }

    public void print(int[] a){
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        HeapTest heapTest = new HeapTest();
        int [] a = new int[]{3,2,5,6,7,4,1,-2,8,-5,18,11,18,8,19};


        heapTest.adjuctTree(a, a.length);

        heapTest.print(a);

        heapTest.heapSort(a, a.length);

        heapTest.print(a);
    }
}

package com.huai.math;

/**
 * Created by liangyh on 1/28/16.
 */
public class BigDataDelete {
    public int[] doDelete(int[] a, int [] b) throws Exception {
        if(a == null || b == null) return null;

        removeZero(a);
        removeZero(b);

        int bLen = b.length;
        int count = 0;

        return null;
    }

    /**
     * a减b
     * @param a
     * @param b
     * @return
     * @throws Exception
     */
    private int[] bigSub(int[] a, int [] b) throws Exception {
        return bigSub(a, a.length,b);
    }

    /**
     * a减b
     * @param a
     * @param aLen 在数组a中用来减b的部分。
     * @param b
     * @return
     * @throws Exception
     */
    private int[] bigSub(int[] a, int aLen, int [] b) throws Exception {
        if(a == null || b == null) return null;

        //检出参数是否符合要求
        boolean flag = doBigSubCheck(a, b);
        if(!flag) return null;

        int[] c = new int[aLen];
        for (int i = 0; i < b.length; i++) {
            if(a[i] >= b[i]){//如果被减数大
                c[i] = a[i]-b[i];
            }else{//如果被减数小
                if(i >= aLen-1) throw new Exception("a is less than b");
                c[i] = a[i]+10 - b[i];
                //高位数减1
                int cc = i+1;
                while(cc < b.length && c[cc] == 0){//如果位数高的有连续的0
                    a[cc] = 9;
                    cc++;
                }
                a[cc] -= 1;
            }
        }
        //把被减数多余的部分（这部分对应的减数是空的）添加到结果数组中
        for (int i = b.length; i < aLen; i++) {
            c[i] = a[i];
        }
        return c;
    }

    public boolean doBigSubCheck(int[] a, int [] b){
        System.out.println("checked");
        return true;
    }

    private int[] removeZero(int [] a){
        if(a == null) return null;
        boolean flag = false;
        for (int i = 0; i < a.length; i++) {
            if(a[i] > 0)
                flag = true;
        }
        if(!flag) return null;

        int [] targetArray = null;
        for (int i = a.length-1; i >= 0 ; i--) {
            if(a[i] > 0){
                targetArray = new int[i+1];
                for (int j = 0; j < i + 1; j++) {
                    targetArray[j] = a[j];
                }
                break;
            }
        }
        return targetArray;
    }

    public void print(int[] a){
        if(a == null) return ;

        System.out.println();
        for(int i = a.length-1; i >= 0; i--){
            System.out.print(a[i]);
        }
    }

    public static void main(String[] args) throws Exception {
        BigDataDelete test = new BigDataDelete();
        int[] a = {3,2,4, 8,1};
        int[] b = {3,4,9};
//        int[] c = test.doDelete(a, b);
        int [] c = test.bigSub(a,5, b);
        test.print(c);
    }
}

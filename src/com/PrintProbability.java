package com;

/**
 * 把n个shai子仍在地上，所有shai子朝上一面的点数之和为s.
 * 输入s，打印出s的所有可能值的概率。
 * Created by liangyh on 4/6/16.
 */
public class PrintProbability {

    private final int max = 6;

    public void printProbability(int number){
        if(number < 1)
            return ;

        int len = max*number-number+1;

        int[] arr = new int[len];
        probability(arr, 1, number, number);

        for (int i = 0; i < len; i++) {
            if(arr[i] != 0){
                System.out.print(arr[i]+" ");
            }
        }
        System.out.println();
//        double sum = Math.pow(6, number);
//        for (int i = 0; i < len; i++) {
//            System.out.printf("%.6f  ", arr[i]/sum);
//        }


    }

    /**
     *
     * @param arr 存储和的数量
     * @param sum 和
     * @param leave 剩下的shai子。
     * @param number
     */
    private void probability(int[] arr, int sum, int leave, int number){
        if(leave == 1){
            arr[sum-number] = sum;
            return ;
        }
        for (int i = 1; i <= 6; i++) {
            probability(arr, sum+i, leave-1, number);
        }
    }


    public void printProbability_while(int number){
        if(number < 1)
            return ;
        int len = number*6+1;
        int[] arr = new int[len];

        for (int i = 1; i <= 6; i++) {
            arr[i] = 1;
        }

        for (int i = 1; i < number; i++) {
            for (int j = 0; j < len-6; j++) {
                for (int k = 1; k <= 6; k++) {
                    if(arr[j] != 0){
                        int temp = j+k;
                        arr[temp] += 1;
                    }
                }
            }
        }

        for (int i = 0; i < len; i++) {
            if(arr[i] != 0)
                System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public void doTest_printProbability(){
        int number = 6;
        printProbability(number);
        printProbability_while(number);
    }

    public static void main(String[] args) {
        PrintProbability test = new PrintProbability();
        test.doTest_printProbability();
    }
}

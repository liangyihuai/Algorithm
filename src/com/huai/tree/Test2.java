package com.huai.tree;

import java.util.*;

/**
 *
 6 2
 B 10 3
 S 50 2
 S 40 1
 S 50 6
 B 20 4
 B 25 10
 * Created by liangyh on 16-1-18.
 */
public class Test2 {

    public void doTest(){
        Scanner scanner = new Scanner(System.in);
        String firstLine = scanner.nextLine();

        String[] strs = firstLine.split(" ");
        int num = Integer.valueOf(strs[0]);
        int sNum = Integer.valueOf(strs[1]);

        List<int[]> bList = new ArrayList<int[]>();//存储买的
        List<int[]> sList = new ArrayList<int[]>();//存储卖的
        for (int i = 0; i < num; i++) {
            String tempStrs[] = scanner.nextLine().split(" ");
            int[] temp = new int[2];
            temp[0] = Integer.valueOf(tempStrs[1]);
            temp[1] = Integer.valueOf(tempStrs[2]);
            if("B".equals(tempStrs[0])){
                bList.add(temp);
            }else{
                sList.add(temp);
            }
        }

        int bLen = bList.size();
        int sLen = sList.size();
        StringBuilder builder = new StringBuilder();
        int index = 0;
        if(sLen > 0){
            if(sLen == 1){
                builder.append("S "+sList.get(0)[0]+" "+sList.get(0)[1]+"\n");
            }else{
                Collections.sort(sList, new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        return o2[0] - o1[0];
                    }
                });

                //合并价格相同的
                for (int i = 0; i < sLen-1; i++) {
                    if(sList.get(i)[0] == sList.get(i+1)[0]){
                        sList.get(i+1)[1] += sList.get(i)[1];
                        sList.get(i)[1] = 0;//把前一个的数量设为0
                    }
                }
                if(sList.get(sLen-2)[0] == sList.get(sLen-1)[0]){
                    sList.get(sLen-1)[1] += sList.get(sLen-2)[1];
                    sList.get(sLen-2)[1] = 0;//把前一个的数量设为0
                }

                index = 0;
                for (int i  = 0; i < sLen; i++) {
                    if(index >= sNum){
                        break;
                    }
                    int[] temp = sList.get(i);
                    if(temp[1] == 0)
                        continue;
                    builder.append("S "+temp[0]+" "+temp[1]+"\n");

                    index++;
                }
            }
        }

        if(bLen > 0){
            if(bLen == 1){
                builder.append("B "+bList.get(0)[0]+" "+bList.get(0)[1]+"\n");
            }else{

                //排序
                Collections.sort(bList, new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        return o1[0]-o2[0];
                    }
                });

                //合并价格相同的
                for (int i = 0; i < bLen-1; i++) {
                    if(bList.get(i)[0] == bList.get(i+1)[0]){
                        bList.get(i+1)[1] += bList.get(i)[1];
                        bList.get(i)[1] = 0;//把前一个的数量设为0
                    }
                }
                if(bList.get(bLen-2)[0] == bList.get(bLen-1)[0]){
                    bList.get(bLen-1)[1] += bList.get(bLen-2)[1];
                    bList.get(bLen-2)[1] = 0;//把前一个的数量设为0
                }

                //买,截取
                index = 0;
                for (int i = bLen-1; i >= 0 ; i--) {
                    if(index >= sNum){
                        break;
                    }
                    int [] temp = bList.get(i);
                    if(temp[1] == 0)
                        continue;
                    builder.append("B "+temp[0]+" "+temp[1]+"\n");

                    index++;
                }
            }
        }

        System.out.println(builder.toString());
    }


    public static void main(String[] args) {
        Test2 test = new Test2();
        test.doTest();
    }
}

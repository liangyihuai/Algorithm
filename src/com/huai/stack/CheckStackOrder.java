package com.huai.stack;

import java.util.Stack;

/**
 * Created by liangyh on 4/1/16.
 */
public class CheckStackOrder {

    /**
     * 输入序列是入栈序列，那么out序列是否符合出栈的顺序。
     * @param in
     * @param out
     * @return
     */
    public boolean checkStackOrder(int[] in, int [] out){
        if(in == null || out == null) return false;
        if(in.length == 0 || in.length != out.length)return false;

        //辅助栈
        Stack s = new Stack();

        int index = 0;
        for (int i = 0; i < in.length; i++) {
            if(in[i] != out[index]){
                s.push(in[i]);
                continue;
            }
            index++;

            while(!s.isEmpty() && s.peek() == out[index]){
                s.pop();
                index++;
            }
        }
        if(s.isEmpty() && index == out.length){
            return true;
        }else{
            return false;
        }
    }

    public void doTest(){
        int[] in = {2,3,4,5,8,7,6};
        int[] out = {4,3,2,6,7,8,5};
        boolean flag = checkStackOrder(null, null);
        System.out.println(flag);

    }

    public static void main(String args[]){
        CheckStackOrder test = new CheckStackOrder();
        test.doTest();
    }
}

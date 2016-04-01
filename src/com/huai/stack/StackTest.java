package com.huai.stack;

/**
 * 在栈中构造min函数，获取栈中最小的元素，使得pop、push和min方法的时间复杂度都是0（1）。
 * Created by liangyh on 3/31/16.
 */
public class StackTest {

    private java.util.Stack<Integer> stack = new java.util.Stack<>();
    //辅助栈
    private java.util.Stack<Integer> viceStack = new java.util.Stack<>();

    public void push(int data){

        if(!viceStack.isEmpty()){
            if(data <= viceStack.peek()){
                viceStack.push(data);
            }
        }else{
            viceStack.push(data);
        }
        stack.push(data);
    }

    public int pop(){
        int data = stack.pop();
        if(data == viceStack.peek()){
            viceStack.pop();
        }
        return data;
    }

    public int min() throws Exception{
        if(viceStack.isEmpty()){
           throw new Exception("the viceStack is empty!");
        }
        return viceStack.peek();
    }

    public void doTest(){
        try {
            push(5);
            System.out.println(min());
            push(6);
            System.out.println(min());
            push(4);
            System.out.println(min());
            push(4);
            System.out.println(min());
            push(1);
            System.out.println(min());
            pop();
            System.out.println(min());
            pop();
            System.out.println(min());
            pop();
            System.out.println(min());
            pop();
            System.out.println(min());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        StackTest test = new StackTest();
        test.doTest();
    }
}

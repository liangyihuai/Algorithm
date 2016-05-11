package com.huai.test;

/**
 * Created by liangyh on 4/15/16.
 */
public class A {

    //--------------------1----------------------
    public boolean checkString(String str){
        if(str == null || "".equals(str))return false;
        if(str.length() == 1)return true;

        return doCheck(str, 0, str.length()-1);
    }

    private boolean doCheck(String str, int start, int end){

        if(start >= end)return true;
        boolean flag = true;
        if(str.charAt(start) != str.charAt(end))
            return false;

        flag = doCheck(str, start+1, end-1);
        return flag;
    }

    //-------------2、倒置一个双向链表。-----------------------
    class Node{
        int data;
        Node prior;
        Node next;
        public Node(int data){
            this.data = data;
        }
    }

    public Node converse(Node root){
        if(root == null)return null;

        if(root.next == null)return root;

        Node tailNode = null;

        while(root.next != null){
            root = root.next;
        }

        tailNode = root;

        Node curNode = tailNode;
        Node pre = tailNode.prior;
        Node temp = null;

        while(pre.prior != null) {
            temp = pre.prior;

            curNode.next = pre;
            pre.prior = curNode;

            curNode = pre;
            pre = temp;
        }

        curNode.next = pre;
        pre.prior = curNode;
        pre.next = null;
        tailNode.prior = null;

        return tailNode;
    }

    public void doTest2(){
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);

        n1.next = n2;
//        n2.next = n3;
//        n3.prior = n2;
        n2.prior = n1;

        Node node = converse(n1);
        System.out.println("H");
    }

    //----------3、倒转一句话---比如 hello world-->world hello----------------

    public String converseStr(String str){
        if(str == null || str.length() <= 1)return str;


        char[] chars = str.toCharArray();
        converseStr(chars, 0, chars.length-1);

        int start = 0, end = 0;

        for(int i = 0; i < chars.length; i++){
            if(i == chars.length-1){
                end = i;
                converseStr(chars, start, end);
                break;
            }

            if(chars[i] == ' '){
                end = i-1;
                converseStr(chars, start, end);
                while(i < chars.length && chars[i] == ' '){
                    i++;
                }
                if(i >= chars.length) break;
                start = i;
            }
        }
        return new String(chars);

    }

    private void converseStr(char [] str, int start, int end){
        if(str == null) return ;

        if(start >= end) return ;
        while(start < end){
            str[start] ^= str[end];
            str[end] ^= str[start];
            str[start] ^= str[end];

            start++;
            end--;
        }
    }


    public void doTest3(){
        String result = converseStr("  ");
        System.out.println("result = "+result);
    }

    //-------------------4、右移数组n位-----------------

    public void move(int[] arr, int n){
        if(arr == null || n < 1)return ;

        for (int i = 0; i < n; i++) {
            move(arr);
        }
    }
    private void move(int[] arr){
        if(arr == null)return ;

        if(arr.length <= 1)return ;

        int temp = arr[arr.length-1];
        for(int i = arr.length-2; i >= 0; i--){
            arr[i+1] = arr[i];
        }
        arr[0] = temp;
    }

    public void doTest4(){
        int [] arr = {0,1,2,3,4,5,6,7,8,9};
        move(arr, 4);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] +" ");
        }
    }

    //---------------5、大数相乘---------------
    public String bigDataTime(String a, String b){
        if(a == null || a.length() < 1 || b == null || b.length() < 1) return null;

        boolean isNagetive = false;
        int aS = 0;
        char temp = a.charAt(0);
        if(temp == '-' || temp == '+'){
            if(a.length() == 1)return null;
            aS = 1;
            if(temp == '-') isNagetive = !isNagetive;
        }

        int bS = 0;
        temp = b.charAt(0);
        if(temp == '-' || temp == '+'){
            if(b.length() == 1)return null;
            bS = 1;
            if(temp == '-') isNagetive = !isNagetive;
        }

        int aLen = a.length();
        int bLen = b.length();

        int[] arr1 = new int[aLen-aS];
        int[] arr2 = new int[bLen-bS];

        int[] result = new int[aLen-aS + bLen - bS];

        int index = 0;
        for(int i = aLen-1; i >= aS; i--){
            arr1[index] = a.charAt(i)-'0';
            index++;
        }

        index = 0;
        for(int i = bLen-1; i >= bS; i--){
            arr2[index] = b.charAt(i)-'0';
            index++;
        }

        for (int i = 0; i < arr1.length; i++) {

            for (int j = 0; j < arr2.length; j++) {
                result[i+j] += arr1[i]*arr2[j];
            }
            for (int k = 0; k < result.length-1; k++) {
                if(result[k] > 9){
                    result[k+1] += result[k]/10;
                    result[k] = result[k]%10;
                }
            }
        }

        index = result.length-1;
        while(index >= 0 && result[index] == 0){
            index--;
        }

        StringBuilder builder = new StringBuilder();
        if(isNagetive)
            builder.append('-');

        for (int i = index; i >= 0; i--) {
            builder.append(result[i]);
        }

        return builder.toString();
    }

    public void doTest5(){
        String result = bigDataTime("-88", "-88");
        System.out.println(result);
    }

    public static void main(String[] args) {
        A a = new A();

        a.doTest5();
    }


}

package com;

/**
 * Created by liangyh on 4/6/16.
 */
public class ReverseWordInSentence {

    /**
     * 翻转一条句子。i am a student. -- > student. a am i
     * @param str
     * @return
     */
    public String reverseWordInSentense(String str){
        if(str == null) return null;

        int len = str.length();
        if(len == 1) return str;

        int start = 0;
        int end = 0;

        char c[] = reverse(str.toCharArray(), start, len-1);

        while(start < len){

            if(end == len-1){
                reverse(c, start, end);
                break;
            }

            if(c[end] == ' '){
                reverse(c, start, end-1);

                start = end+1;
                while(c[start] == ' '){
                    start++;
                }
                end = start;
            }else{
                end++;
            }

        }
        return new String(c);
    }

    private char[] reverse(char[] strs, int start, int end){
        if(strs == null) return null;

        while(start < end){
            strs[start] ^= strs[end];
            strs[end] ^= strs[start];
            strs[start] ^= strs[end];

            start++;
            end--;
        }
        return strs;
    }

    public void doTest_reverseWordInSentense(){
        String result = reverseWordInSentense("i am a student.");
        System.out.println(result);
    }

    public static void main(String[] args) {
        ReverseWordInSentence test = new ReverseWordInSentence();
        test.doTest_reverseWordInSentense();
    }

}

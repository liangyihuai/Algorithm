package com;

/**
 * Created by liangyh on 4/13/16.
 */
public class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        if(s == null)
            return 0;
        int len = s.length();
        if(len == 0)
            return 0;

        int maxLen = 0;
        int[] arr = new int[256];
        for(int i = 0; i < 256; i++){
            arr[i] = -1;
        }

        int a = 0;
        int b = 0;
        for(int i = 0; i < len; i++){

            char c = s.charAt(i);

            if(arr[c] >= a){
                b++;
                a = arr[c]+1;
                arr[c] = i;

            }else{
                int temp = b-a + 1;
                if(temp > maxLen){
                    maxLen = temp;
                }

                arr[c] = i;
                b++;

            }
            if(a >= len || b >= len){
                break;
            }
        }
        return maxLen;
    }

    public void doTest(){
        String s = "tmmzuxt";
        int result = lengthOfLongestSubstring(s);
        System.out.println(result);
    }

    public static void main(String[] args) {
        Solution3 test = new Solution3();
        test.doTest();
    }
}

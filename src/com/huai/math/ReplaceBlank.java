package com.huai.math;

/**
 * Created by liangyh on 3/5/16.
 */
public class ReplaceBlank {

    public String doReplaceBlank(String string) throws NoSuchMethodException {
        if(string == null) throw new NoSuchMethodException();

        char[] chars = string.toCharArray();

        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if(' ' == chars[i]){
                count++;
            }
        }

        char[] cs = new char[chars.length+count*2];

        int index = 0;
        for (int i = 0; i < cs.length; i++) {
            if(' ' == (chars[index])){
                cs[i] = '%';
                cs[++i] = '2';
                cs[++i] = '0';
            }else{
                cs[i] = chars[index];
            }
            index++;
        }
        return String.valueOf(cs);
    }

    public static void main(String args[]) throws NoSuchMethodException {
        ReplaceBlank test = new ReplaceBlank();
        String string = test.doReplaceBlank("hello world i am is liang yi huai zhong guo ren de  ,shi de");
        System.out.println(string);
    }
}

package com.huai.math;

/**
 * Created by liangyh on 4/8/16.
 */
public class ToIntValue {

    /**
     * 将字符串转换成整数。
     */
    private boolean isValidated = true;
    public int toIntValue(String str){
        if(str != null && str.length() != 0){

            int len = str.length();

            int startIndex = 0;
            boolean minus = false;
            if(str.charAt(0) == '+' || str.charAt(0) == '-'){
                startIndex = 1;
                if(str.charAt(0) == '-')
                    minus = true;
            }else{
                startIndex = 0;
            }

            long result = 0;
            for (int i = startIndex; i < len; i++) {
                char c = str.charAt(i);
                if((c - '0') < 10 && (c - '0') >= 0){
                    result = result*10 + str.charAt(i)-'0';
                    if((minus && result< 0x8000_0000) || (!minus && result > 0x7FFF_FFFF)){
                        isValidated = false;
                        return -1;
                    }
                }else{
                    isValidated = false;
                    return -1;
                }
            }

            if(minus){
                return (int)-result;
            }
            return (int)result;
        }

        isValidated = false;
        return -1;
    }

    public void doTest(){
        long result = toIntValue("-92");
        System.out.println(result+"; "+isValidated);
    }

    public static void main(String[] args) {
        ToIntValue test = new ToIntValue();
        test.doTest();

        System.out.println();
        System.out.println(Long.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(0x7FFFFFFF);
    }
}

package com.huai.math;

/**
 * Created by liangyh on 3/13/16.
 */
public class CharacterTest {

    /**
     * 一个26进制的数，用A-Z表示。将这样的数转换成十进制的数。
     * @param array
     * @return
     * @throws Exception
     */
    public long getColumnNum(char[] array) throws Exception {
        if(array == null) return -1;

        if(!check(array))
            return -1;

        long columnNum = 0;
        int count = 0;
        for (int i = array.length-1; i >= 0 ; i--) {
            columnNum += (array[i]-'A'+1) * (Math.pow(26, count));
            count++;
        }
        return columnNum;
    }

    private boolean check(char[] array){
        int a = 'A';
        int b = 'Z';

        for (int i = 0; i < array.length; i++) {
            if(array[i]< a || array[i] > b)
                return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {


        CharacterTest test = new CharacterTest();
        long result = test.getColumnNum(new char[]{'A','A','A','A','A','Z','Z','Z'
                ,'Z','A','A','Z'});
        System.out.println(result);
    }
}

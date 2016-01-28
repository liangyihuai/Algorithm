package com.huai.math;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created by liangyh on 1/27/16.
 */
public class BigDataMulti {

    public int[] doMuti(int[] a, int[] b){
        if(a == null || b == null) return null;

        int[] result = new int[a.length+b.length];

        int count = 0;
        int resultIndex = 0;
        for(int i = a.length-1; i >= 0; i--){
            resultIndex = count;
            for(int j = b.length-1; j >= 0; j--){
                int temp = a[i]*b[j];
                result[resultIndex] += temp;
                resultIndex++;
            }
            for(int k = count; k <= resultIndex; k++){
                int tempResult = result[k];
                if(tempResult >= 10){
                    result[k] = tempResult%10;
                    result[k+1] += tempResult/10;
                }
            }
            count++;
        }

        reverse(result);

        return result;
    }

    /**
     * better method than the method above
     * @param a
     * @param b
     * @return
     */
    public int[] doMuti2(int[] a, int[] b){
        if(a == null || b == null) return null;

        int[] result = new int[a.length+b.length];

        int i = 0;
        int j = 0;
        for(i = a.length-1; i >= 0; i--){
            for(j = b.length-1; j >= 0; j--){
                int temp = a[i]*b[j];
                result[i+j+1] += temp;
            }
            for(int k = i+b.length; k > i; k--){
                int tempResult = result[k];
                if(tempResult >= 10){
                    result[k] = tempResult%10;
                    result[k-1] += tempResult/10;
                }
            }
        }
//        reverse(result);

        return result;
    }

    public void  reverse(int [] result){
        if(result == null) return ;

        int e = 0;
        int f = result.length-1;
        while(e < f){
            result[e] ^= result[f];
            result[f] ^= result[e];
            result[e] ^= result[f];
            e++;
            f--;
        }
    }

    public void print(int[] results){
        if(results == null) return;

        for(int i = 0; i < results.length; i++){
            System.out.print(results[i]);
        }
        System.out.println();
    }

    public int[] bigNumFactorial(int[] max){
        if(max == null) return null;
        if(isLessThanOne(max)) return null;
        if(isOne(max)) return max;

        int[] temp = Arrays.copyOf(max, max.length);
        while(!isOne(max)){
            temp = doMuti2(temp, minusOne(max));
        }
        return temp;
    }

    public BigDecimal bigDecimalFactorial(BigDecimal bigDecimal){
        if(bigDecimal == null) return null;

        BigDecimal temp = new BigDecimal(bigDecimal.toString());

        while(bigDecimal.compareTo(new BigDecimal(1)) > 0){
            bigDecimal = bigDecimal.subtract(new BigDecimal(1));
            temp = temp.multiply(bigDecimal);
        }
        return temp;
    }

    public boolean isOne(int[] num){
        boolean isOne = true;
        for(int i = num.length-1; i >= 0; i--){
            if(i == num.length-1){
                if(num[num.length-1] == 1){
                    continue;
                }else{
                    isOne = false;
                    break;
                }
            }else{
                if(num[i] != 0){
                    isOne = false;
                    break;
                }
            }
        }
        return isOne;
    }

    public boolean isLessThanOne(int[] num){
        boolean flag = false;
        for(int i = 0; i < num.length; i++){
            if(num[i] > 0){
                flag = true;
            }else if(num[i] < 0){
                flag = false;
                break;
            }
        }
        return (!flag);
    }

    public int[] minusOne(int[] num){
        if(num == null) return null;

        for(int i = num.length-1; i >= 0; i--){

            if(num[i] != 0){
                num[i] -= 1;
                if(num[i] == 0){
                    if(i < num.length-1){
                        for(int j = i+1; j < num.length; j++){
                            num[j] = 9;
                        }
                    }
                }
            }
        }
        return num;
    }

    public int  factorial(int max){
        if(max < 1) return -1;
        if(max == 1) return 1;

        max *= factorial(max-1);

        return max;
    }

    public long factorial2(int max){
        if(max < 1) return -1;
        if(max == 1) return 1;

        long temp =max;
        while(max >= 1){
            temp = temp*(--max);
        }
        return temp;
    }

    public String toString(int [] a){
        if(a == null) return null;

        StringBuffer buf = new StringBuffer();
        for(int i = 0; i < a.length; i++){
            buf.append(a[i]);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        BigDataMulti test = new BigDataMulti();

        int[] a = {9,9,9,6,5,4,3,5,0,9,0,7,7,8,6,7,1,4,3};
        int[] b = {9,9,6,7,8,5,4,3,5,0,0,0,0,5,0,9,6,1};

        BigDecimal ba = new BigDecimal(test.toString(a));
        BigDecimal bb = new BigDecimal(test.toString(b));
        System.out.println(ba.multiply(bb));

        int c[] = test.doMuti(a, b);
        test.print(c);

        int cc[] = test.doMuti2(a, b);
        test.print(cc);
//
//        BigDecimal aa = new BigDecimal("91234567890001");
//        BigDecimal bb = new BigDecimal("91234567890001");
//        System.out.println(aa.multiply(bb));

//        BigDataMulti test = new BigDataMulti();
//        int result = test.factorial(1234);
//        System.out.println(result);
//
//        long result2 = test.factorial(6);
//        System.out.println(result2);

//        BigDataMulti test = new BigDataMulti();
//        BigDecimal decimal = test.bigDecimalFactorial(new BigDecimal("11"));
//        System.out.println(decimal.toString());
//
//        int[] a = new int[]{1,1};
//        int[] b = test.bigNumFactorial(a);
//        test.print(b);

    }
}

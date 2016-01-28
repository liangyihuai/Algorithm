package com.huai.math;

/**
 * Created by liangyh on 1/27/16.
 */
public class PrimeNumTset {

    /**
     *
     * @param max get primes number from 2 to max
     * @return array, the array index is the prime number we expect,
     * and the array value is 1 or 0, value 1 means the index is prime number
     */
    public int[] getPrimeNum_Eratosthenes(int max){
        int [] primes = new int[max+1];
        for(int i = 2; i <= max; i++){
            primes[i] = 1;
        }

        for(int i = 2; i*i <= max; i++){
            for(int j = i*2; j <= max; j++){
                if(primes[j] == 1){
                    if(j % i == 0){
                        primes[j] = 0;
                    }
                }
            }
        }
        return primes;
    }

    public void printPrime(int[] primes){
        if(primes == null) return;

        int c = 0;
        for(int i = 2; i < primes.length; i++){
            if(primes[i] == 1){
                System.out.print(i + " ");
                c++;
            }
            if(c == 10){
                System.out.println();
                c = 0;
            }
        }
    }


    public static void main(String args[]){
        PrimeNumTset test = new PrimeNumTset();
        int[] primes = test.getPrimeNum_Eratosthenes(1000);
        test.printPrime(primes);
    }
}

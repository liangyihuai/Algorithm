package com.huai.tree;

import com.routesearch.route.Util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class Test {

    public static void main(String[] args) {
        int i = 0;
        i = i++;

        System.out.println(i);


        i = 0;
        i = ++i + i++;

        System.out.println(i);

        int k = 0;
        k = k++ + k++ + k++ + k++;
        System.out.println(k);

    }
}

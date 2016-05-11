package com.ccf.year2016;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by liangyh on 4/11/16.
 */
public class CCF2016_4_10_3 {

    private static String [] paths = null;

    private static Node root;

    public void doMain(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(reader);

        int num = scanner.nextInt();
        scanner.nextLine();

        String curPath = scanner.nextLine();

        paths = new String[num];
        String tempStr = "";
        for (int i = 0; i < num; i++) {
            paths[i] = scanner.nextLine().trim();

            tempStr = paths[i].replaceAll("/{2,}", "/");
            while(tempStr.contains("/./")){
                tempStr = tempStr.replace("/./", "/");
            }
//            tempStr = tempStr.replaceAll("/./", "/");
            int strLen = tempStr.length();

            if(strLen > 1 && tempStr.endsWith("/")){
                tempStr = tempStr.substring(0, strLen-1);
            }

            if(".".equals(tempStr)) {
                tempStr = curPath;
                paths[i] = tempStr;
                continue;
            }

            if(tempStr.startsWith("./"))
                tempStr = curPath+tempStr.substring(1);

            if(tempStr.endsWith("/.")){
                tempStr = tempStr.substring(0, strLen-2)+curPath;
            }

            paths[i] = tempStr;
        }

        print();

        scanner.close();
    }

    private void buildTree(String path){
        if(path == null)
            return ;
        String[] strings = path.split("/");



    }

    private void print(){
        if(paths == null)
            return ;
        for (int i = 0; i < paths.length; i++) {
            System.out.println(paths[i]);
        }
    }

    class Node{
        Node parent;
        String label = "";
        List<Node> childs = new LinkedList<Node>();

        public Node(){

        }

        public Node(String label, Node parent){
            this.label = label;
            this.parent = parent;
        }

        public void addChild(String label){
            if(label != null){
                childs.add(new Node(label, this));
            }
        }
    }

    public static void main(String[] args) {

    }

}

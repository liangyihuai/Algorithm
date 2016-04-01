package com.huai.math;

/**
 * 根据前序遍历和中序遍历构建树关系
 * Created by liangyh on 3/5/16.
 */
public class BuiltTree {

    public Node doBuildTree(int[] preOrder, int[] inOrder){

        if(preOrder == null || inOrder == null) throw new NullPointerException(" the before or middle is null");

        if(preOrder.length == 0){
            return null;
        }

        Node node = new Node(preOrder[0]);

        if(preOrder.length == 1){
            node.leftChild = null;
            node.rightChild = null;
            return node;
        } else if(preOrder.length == 2){
            if(inOrder[0] == preOrder[0]){
                node.rightChild = new Node(inOrder[1]);
            }else{
                node.leftChild = new Node(inOrder[0]);
            }
            return node;
        }

        //查找根节点的下标
        int rootIndex = -1;
        for (int i = 0; i < inOrder.length; i++) {
            if(inOrder[i] == preOrder[0]){
                rootIndex = i;
                break;
            }
        }

        //
        int[] a = new int[rootIndex];
        int[] b = new int[rootIndex];
        for (int i = 0; i < rootIndex; i++) {
            a[i] = preOrder[i+1];
            b[i] = inOrder[i];
        }
        Node left = doBuildTree(a, b);

        int[] aa = new int[inOrder.length-rootIndex-1];
        int[] bb = new int[inOrder.length-rootIndex-1];
        for (int i = 0; i < aa.length; i++, rootIndex++) {
            aa[i] = preOrder[rootIndex+1];
            bb[i] = inOrder[rootIndex+1];
        }
        Node right = doBuildTree(aa, bb);

        node.leftChild = left;
        node.rightChild = right;

        return node;
    }


    public class Node{
        int data;
        Node leftChild;
        Node rightChild;

        public Node(){}

        public Node(int data){
            this.data = data;
        }
    }


    public void doTest(){
        int a [] = {1,2,4,7,3,5,6,8};
        int b [] = {4,7,2,1,5,3,8,6};

//        Node root = doBuildTree(a, b);

        Node root = doBuildTree(a, b);
        inTravel(root);
    }

    public void preTravel(Node node){
        if(node != null){
            System.out.print(node.data+" ");
            preTravel(node.leftChild);
            preTravel(node.rightChild);
        }
    }

    public void inTravel(Node node){
        if (node != null){
            inTravel(node.leftChild);
            System.out.print(node.data + " ");
            inTravel(node.rightChild);
        }
    }

    public void postTravel(Node node){
        if(node != null){
            postTravel(node.leftChild);
            postTravel(node.rightChild);
            System.out.print(node.data + " ");
        }
    }


    public static void main(String args[]){
        BuiltTree test = new BuiltTree();
        test.doTest();
    }
}

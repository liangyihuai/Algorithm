package com.huai.tree;

/**
 * Created by liangyh on 16-1-21.
 */
public class CommonBinaryTree {

    public static void main(String args[]){
        CommonBinaryTree binaryTree = new CommonBinaryTree();
        binaryTree.doTest();
    }

    public void doTest(){
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);


        n1.left = n2;
        n1.right = n3;

        n2.left = n4;
        n2.right = n5;
        n4.left = n6;
        n6.left = n7;

        int i = getDepth(n1);
        System.out.println(i);
    }

    /**
     * 获取二叉树的深度
     * @param node
     * @return
     */
    public int getDepth(Node node){
        int leftDepth = 0;
        int rightDepth = 0;

        if(node.left != null){
            leftDepth = getDepth(node.left);
        }
        if(node.right != null){
            rightDepth = getDepth(node.right);
        }
        if(leftDepth > rightDepth){
            return leftDepth + 1;
        }else{
            return rightDepth + 1;
        }
    }

    public class Node{
        Node left;
        Node right;
        int data;

        public Node(int data){
            this.data = data;
        }
    }
}

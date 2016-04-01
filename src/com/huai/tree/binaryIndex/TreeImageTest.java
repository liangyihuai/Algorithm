package com.huai.tree.binaryIndex;

import java.util.Stack;

/**
 * Created by liangyh on 3/30/16.
 */
public class TreeImageTest {

    /**
     * 输出某一棵树的镜像。recursion
     * @param root
     * @return
     */
    public Node getImage(Node root){
        if(root != null){
            Node temp = root.left;
            root.left = root.right;
            root.right = temp;

            getImage(root.left);
            getImage(root.right);
        }
        return root;
    }

    /**
     * 使用循环遍历一棵二叉树。这里我在节点中增加了一个boolean类型的变量。
     * @param root
     */
    public void getImage_while(Node root){
        if(root == null) return ;

        Stack<Node> s = new Stack<>();
        s.push(root);
        System.out.println(root.data);
        root.isVisited = true;

        while(!s.isEmpty()){
            Node temp = s.pop();

            if(temp.left != null && !temp.left.isVisited){
                s.push(temp);
                s.push(temp.left);
                temp.left.isVisited = true;
                System.out.println(temp.left.data);
            }else{
                if(temp.right != null && !temp.right.isVisited){
                    s.push(temp.right);
                    temp.right.isVisited = true;
                    System.out.println(temp.right.data);
                }
            }
        }
    }

    class Node{
        int data;
        Node left;
        Node right;
        boolean isVisited = false;

        public Node(){}

        public Node(int data){
            this.data = data;
        }
    }


    public void doTest(){
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        getImage_while(n1);

//        print(n1);
//        System.out.println();
//
//        Node node = getImage(n1);
//        print(node);
    }

    public void print(Node node){
        if(node != null){
            System.out.print(node.data+" ");
            print(node.left);
            print(node.right);
        }
    }

    public static void main(String[] args) {
        TreeImageTest test = new TreeImageTest();
        test.doTest();
    }
}

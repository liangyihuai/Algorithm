package com.huai.sort.link;

import com.huai.search.Binary;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liangyh on 3/5/16.
 */
public class LinkTest {

    /**
     * 求链表倒数第k的数
     * @param node
     * @param k
     * @return
     * @throws Exception
     */
    public Node getKthToTail(Node node, int k)throws Exception{
        if(node == null) throw new Exception("the node must not be null");

        int size = getNodeSize(node);
        if(k > size) throw new Exception("the size of node is too short");

        if(k <= 0) throw new Exception("the num k must more than 0");

        Node first = node;
        Node second = node;

        int count = 1;
        while(first.next != null){

            first = first.next;
            count++;

            if(count > k){
                second = second.next;
            }
        }
        return second;
    }

    /**
     * 求链表倒数第k的数
     * @param node
     * @param k
     * @return
     * @throws Exception
     */
    public Node getKthToTail2(Node node, int k)throws Exception{
        if(node == null) throw new Exception("the node must not be null");

        int size = getNodeSize(node);
        if(k > size) throw new Exception("the size of node is too short");

        if(k <= 0) throw new Exception("the num k must more than 0");

        Node first = node;
        Node second = node;

        for (int i = 0; i < k-1; i++) {
            first = first.next;
        }

        while(first.next != null){
            first = first.next;
            second = second.next;
        }

        return second;
    }

    private int getNodeSize(Node node){
        if(node == null) throw new NullPointerException();

        int size = 1;
        while(node.next != null){
            node = node.next;
            size++;
        }
        return size;
    }

    public class Node{
        Node next;
        int data;

        public Node(){}

        public Node(int data){
            this.data = data;
        }
    }

    public void doTest(){
        Node n1 = new Node(1);
        Node n3 = new Node(3);

        Node n5 = new Node(5);
        Node n6 = new Node(6);

        n1.next = n3;
        n3.next = n5;
        n5.next = n6;


        Node n2 = new Node(2);
        Node n4 = new Node(4);

        n2.next = n4;

       Node root = merge2(n1, n2);

        print(root);
    }

    public void print(Node root){
        if(root == null) return ;

        while(root != null){
            System.out.print(root.data+" ");

            root = root.next;
        }
    }
    //********************************************
    public Node merge(Node node1, Node node2){
        if(node1 == null && node2 == null) return null;
        if(node1 == null && node2 != null) return node2;
        if(node1 != null && node2 == null) return node1;

        Node resultHead = null;

        if(node1.data < node2.data){
            resultHead = node1;
            node1 = node1.next;
        }else{
            resultHead = node2;
            node2 = node2.next;
        }
        Node resultRoot = resultHead;

        while(node1!= null && node2 != null){
            if(node1.data < node2.data){
                resultHead.next = node1;
                resultHead = resultHead.next;
                node1 = node1.next;
            }else{
                resultHead.next = node2;
                resultHead = resultHead.next;
                node2 = node2.next;
            }
        }

        if(node1 == null)
            resultHead.next = node2;
        else
            resultHead.next = node1;

        return resultRoot;
    }

    public Node merge2(Node node1, Node node2){
        if(node1 == null && node2 == null) return null;
        if(node1 == null && node2 != null) return node2;
        if(node1 != null && node2 == null) return node1;

        Node resultRoot = null;

        if(node1.data < node2.data){
            resultRoot = node1;
            resultRoot.next = merge2(node1.next, node2);
        }else{
            resultRoot = node2;
            resultRoot.next = merge2(node1, node2.next);
        }
        return resultRoot;
    }

//********************************************

    class BinaryTreeNode{
        BinaryTreeNode leftChild;
        BinaryTreeNode rightChild;
        int data;

        public BinaryTreeNode(int data){
            this.data = data;
        }
    }


    /**
     * 判断树B是否是树A的子树。
     * @param rootA
     * @param rootB
     * @return
     */
    public boolean hasSubTree(BinaryTreeNode rootA, BinaryTreeNode rootB){
        if(rootA == null || rootB == null) return false;

        if(rootA.data == rootB.data){
            return check(rootA, rootB);
        }
        hasSubTree(rootA.leftChild, rootB);
        hasSubTree(rootA.rightChild, rootB);

        return false;
    }

    private boolean check(BinaryTreeNode rootA, BinaryTreeNode rootB){

        if(rootB == null){
            return true;
        }
        if(rootA == null){
            return false;
        }

        if(rootA.data != rootB.data)
            return false;

        return check(rootA.leftChild, rootB.leftChild) && check(rootA.rightChild, rootB.rightChild);
    }


    public void doTreeTest(){
        BinaryTreeNode n1 = new BinaryTreeNode(2);
        BinaryTreeNode n2 = new BinaryTreeNode(3);
        BinaryTreeNode n3 = new BinaryTreeNode(4);
        BinaryTreeNode n4 = new BinaryTreeNode(5);
        BinaryTreeNode n5 = new BinaryTreeNode(6);
        n1.leftChild = n2;
        n2.rightChild = n3;
        n1.rightChild = n4;
        n4.leftChild = n5;

        BinaryTreeNode n11 = new BinaryTreeNode(2);
        BinaryTreeNode n44 = new BinaryTreeNode(5);
        BinaryTreeNode n55 = new BinaryTreeNode(6);
        BinaryTreeNode n66 = new BinaryTreeNode(7);
        n11.rightChild = n44;
        n44.leftChild = n55;
        n55.rightChild = n66;

        boolean flag = hasSubTree(n1, n11);
        System.out.println(flag);


    }

//********************************************

    public static void main(String args[]){
        LinkTest test = new LinkTest();
//        test.doTest();
        test.doTreeTest();
    }
}

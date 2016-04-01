package com.huai.math;

/**
 * Created by liangyh on 3/5/16.
 */
public class LinkTest {

    //从尾到头遍历一个链表
    public void travel(Node node){
        if(node == null) throw new NullPointerException(" the node is null");

        if(node.next == null){
            System.out.println(node.data);
            return ;
        }

        Node previous = node;
        Node current = node.next;
        while(current.next != null){
            Node temp = current;
            current = current.next;
            temp.next = previous;
            previous = temp;
        }
        current.next = previous;
        node.next = null;

        while(current.next != null){
            System.out.print(current.data + ", ");
            current = current.next;
        }
        System.out.print(current.data);
    }


    public class Node{
        Node next;
        int data;

        public Node(){}

        public Node (int data){
            this.data = data;
        }
    }


    /**
     * 给定头结点和某个节点，要求删除这个节点，时间复杂度为0（1）
     * @param root
     * @param current
     * @return 头结点
     */
    public Node delete(Node root, Node current){
        if(root == null) return null;
        if(current == null) return null;

        if(root == current && root.next != null){
            root = root.next;
            return root;
        }else if(root == current && root.next == null){
            root = null;
            return root;
        }

        if(current.next == null){
            Node temp = root;
            while(temp.next != current){
                temp = temp.next;
            }
            temp.next = null;
            return root;
        }

        Node previous = current;
        current = current.next;
        previous.data = current.data;
        previous.next = current.next;
        return root;
    }

    public void print(Node root){
        if(root == null) return ;

        while(root.next != null){
            System.out.print(root.data);
            root = root.next;
        }
        System.out.print(root.data);
        System.out.println();
    }

    public void doTest(){
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        print(n1);

        Node root = delete(n1, n1);
        print(root);
    }

    public static void main(String args[]){
        LinkTest test = new LinkTest();
        test.doTest();
    }
}

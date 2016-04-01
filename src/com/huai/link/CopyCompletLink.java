package com.huai.link;

/**
 * Created by liangyh on 4/1/16.
 */
public class CopyCompletLink {


    /**
     * 复制复杂链表.
     * @param root
     * @return
     */
    public Node doCopy(Node root){
        if(root == null) return null;
        Node current = root;

        while(current != null){
            Node newNode = new Node(current.data);
            Node temp = current.next;
            current.next = newNode;
            newNode.next = temp;
            current = temp;
        }

        current = root;
        while(current != null){
            Node siblingNode = current.sibling;
            if(siblingNode != null){
                current.next.sibling = siblingNode.next;
            }
            current = current.next.next;
        }

        current = root;
        Node resultRoot = root.next;
        Node resultTemp = resultRoot;
        while(current != null){
            current.next = resultTemp.next;
            current = resultTemp.next;
            if(current != null){
                resultTemp.next = current.next;
                resultTemp = current.next;
            }
        }
        return resultRoot;
    }

    class Node{
        Node next = null;
        Node sibling = null;
        int data;

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


        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;

        n2.sibling = n6;
        n5.sibling = n3;

        Node result = doCopy(n1);
        System.out.println(result);
    }

    public static void main(String[] args) {
        CopyCompletLink test = new CopyCompletLink();
        test.doTest();
    }
}

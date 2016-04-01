package com.huai.tree.binaryIndex;

/**
 * Created by liangyh on 4/1/16.
 */
public class Convert {

    /**
     * 把一棵二叉搜索树转换成双向链表，返回双向链表的头结点。
     * @param node
     * @return
     */
    public Node doConvert(Node node){
        if(node == null) return null;

        Node[] last = new Node[1];

        convertNode(node, last);

        //把返回头结点。
        Node head = last[0];
        while(head != null && head.left != null){
            head = head.left;
        }

        return head;
    }

    /**
     *
     * @param node
     * @param last 如果是C语言或者C++，这个参数应该是指针的指针。因为函数里面，有一次赋值操作，使得last代表的变量改变了。
     */
    private void convertNode(Node node, Node[] last){
        if(node == null) return ;

        Node cur = node;

        //下面这个就是中序遍历二叉树，只是在遍历的过程中需要记住前一个遍历过的节点，用它来和当前节点进行相关连接操作。
        if(cur.left != null) {
            convertNode(cur.left, last);
        }

        //**********
        cur.left = last[0];
        if(last[0] != null) last[0].right = cur;
        last[0] = cur;
        //**********

        if(cur.right != null){
            convertNode(cur.right, last);
        }
    }

    class Node{
        int data;
        Node left;
        Node right;

        public Node(){}

        public Node(int data){
            this.data = data;
        }
    }

    public void doTest(){
        Node n1 = new Node(10);
        Node n2 = new Node(6);
        Node n3 = new Node(12);
        Node n4 = new Node(4);
        Node n5 = new Node(8);

        n1.left = n2;
        n1.right = n3;

        n2.left = n4;
        n2.right = n5;

        Node result = doConvert(n1);
        System.out.println(result.data);
    }

    public static void main(String[] args) {
        Convert test = new Convert();
        test.doTest();
    }

}

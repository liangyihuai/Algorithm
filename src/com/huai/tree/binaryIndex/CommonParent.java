package com.huai.tree.binaryIndex;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by liangyh on 4/8/16.
 */
public class CommonParent {

    /**
     * 在一个二叉树中，找到两个节点的最低公共祖先。
     * 思路：
     * 遍历找到其中的一个节点，同时记录下从跟路径到该节点的路径（使用栈来辅助）。
     * 第二个节点也是如此，在遍历栈，找到最低公共祖先。
     * @param root
     * @param node1
     * @param node2
     * @return
     */
    public Node getCommonParent(Node root, Node node1, Node node2){
        if(root == null || node1 == null || node2 == null)return null;

        Stack<Node> s1 = new Stack();
        Stack<Node> s2 = new Stack();

        getPath(s1, root, node1);
        getPath(s2, root, node2);

        Node commonParent = null;
        if(!s1.isEmpty() && !s2.isEmpty()){
            Iterator<Node> ite1 = s1.iterator();
            Iterator<Node> ite2 = s2.iterator();
            while(ite1.hasNext() && ite2.hasNext()){
                Node temp = null;
                if((temp = ite1.next()) == ite2.next())
                    commonParent = temp;
            }
        }
        return commonParent;
    }

    /**
     *
     * @param s
     * @param node 跟节点
     * @param target 所许查找的目的节点。
     * @return
     */
    private boolean getPath(Stack s, Node node, Node target){
        if(node == null) return false;
        s.push(node);
        boolean isFound = false;

        if(node == target){
            return true;
        }

        if(!isFound && node.left != null){
            isFound = getPath(s, node.left, target);
            if(!isFound)
                s.pop();
        }
        if(!isFound && node.right != null){
            isFound = getPath(s, node.right, target);
            if(!isFound)
                s.pop();

            return isFound;
        }

        return isFound;
    }


    class Node{
        int data;
        Node left;
        Node right;

        public Node(){

        }

        public Node(int data){
            this.data =data;
        }
    }

    public void doTest(){
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n4.left = n6;
        n4.right = n7;
        n5.right = n8;

        Node parent = getCommonParent(n1, n6, n7);
        if(parent != null)
            System.out.println(parent.data);
        else{
            System.out.println("null");
        }

    }

    public static void main(String[] args) {
        CommonParent test = new CommonParent();
        test.doTest();
    }
}

package com.huai.tree.binaryIndex;

/**
 * 针对中序遍历的索引二叉树
 * Created by liangyh on 16-1-18.
 */
public class IndexBinTreeTest {
    //遍历二叉树的当前节点的前一个节点
    private Node previous;

    public void buildIndex(Node head){
        if(head == null) return;

        Node current = head;

        buildIndex(current.getLeft());

        if(previous != null){
            if(previous.hasRightIndex){
                previous.setRight(current);
            }
            if(current.getLeft() == null){
                current.setLeft(previous);
                current.hasLeftIndex = true;
            }
        }
        //表示当前节点的左子树是左索引
        if(current.getLeft() == null){
            current.hasLeftIndex = true;
        }
        //表示当前节点的右子树是右索引
        if(current.getRight() == null){
            current.hasRightIndex = true;
        }
        //
        previous = current;
        //
        buildIndex(current.getRight());
    }

    /**
     * 查找当前节点的后继节点
     * @param node
     * @return
     */
    public Node nextRight(Node node){
        if(node == null) return null;

        if(node.hasRightIndex && node.getRight() != null){
            return node.getRight();
        }else{
            Node current = node.getRight();

            if(current == null) return null;

            while(current.getLeft() != null && current.hasLeftIndex == false){
//                current.setLeft(current.getLeft());//不能这样子写的效果相当于没有改变current的值
                current = current.getLeft();
            }
            return current;
        }

    }

    public void travelIndexBinTree(Node head){
        if(head == null) return ;

        Node current = head;
        //先指定到二叉树的最左边的叶子节点
        while(current.getLeft() != null && current.hasLeftIndex == false){
            current = current.getLeft();
        }

        do{
            System.out.print(current.data+ ", ");
            current = nextRight(current);
        }while(current != null);
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);

        n1.setLeft(n2);
        n1.setRight(n3);

        n2.setLeft(n4);
        n2.setRight(n5);

        n3.setLeft(n6);

        n6.setLeft(n7);
        n6.setRight(n8);

        IndexBinTreeTest treeTest = new IndexBinTreeTest();
        treeTest.buildIndex(n1);
        treeTest.travelIndexBinTree(n1);

    }
}

class Node{
    private Node left;
    private Node right;
    boolean hasLeftIndex;
    boolean hasRightIndex;
    int data;

    public Node(int data){
        this.data = data;
        this.hasLeftIndex = false;
        this.hasRightIndex = false;
        this.left = null;
        this.right = null;
    }

    public void setLeft(Node left){
        this.left = left;
    }
    public void setRight(Node right){
        this.right = right;
    }

    public Node getRight(){
        return this.right;
    }

    public Node getLeft(){
        return this.left;
    }
}


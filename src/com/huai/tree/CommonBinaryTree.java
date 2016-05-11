package com.huai.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by liangyh on 16-1-21.
 */
public class CommonBinaryTree {



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

//        int i = getDepth(n1);
//        System.out.println(i);

        traverseBroad(n1);
    }

    /**
     * 思路：递归。
     * 1、得到根节点左孩子的高度。
     * 2、得到根节点右孩子的高度。
     * 3、比较左右孩子的高度，取最大的，同时增加当前节点的高度1。
     * @param root
     * @return
     */
    public int getTreeDepth(Node root){
        if(root == null)return 0;

        int depth = 0;

        int leftDepth = getTreeDepth(root.left);//左孩子的高度

        int rightDepth = getTreeDepth(root.right);//右孩子的高度

        depth = leftDepth>rightDepth?leftDepth:rightDepth;

        return depth+1;//增加当前节点的高度。

    }


    public class Node{
        Node left;
        Node right;
        int data;

        public Node(){

        }

        public Node(int data){
            this.data = data;
        }
    }


    public void traverseBroad(Node node){
        if(node == null) return;

        java.util.Queue<Node> q = new java.util.LinkedList<>();

        q.add(node);

        while(!q.isEmpty()){
            Node current = q.poll();
            System.out.print(current.data+" ");
            if(current.left != null){
                q.add(current.left);
            }
            if(current.right != null){
                q.add(current.right);
            }
        }
    }


    /**
     * 判断一棵二叉树是不是平衡的。
     * 思路：在递归求解树的深度的时候比较当前节点的左右子树的深度是否大于1.
     * @param root
     * @return
     */
    public boolean isBalance(Node root){
        if(root == null)
            return false;

        int[] isBalance = new int[1];
        compareDepth(root, isBalance);
        if(isBalance[0] == 1){
            return false;
        } else{
            return true;
        }
    }

    /**
     * 该函数的功能是求解树的深度。
     * 时间复杂度：每一个节点只遍历一次。
     * @param node
     * @param isBalance 只包含一个元素，元素的值中，0表示是平衡树，1表示不是。作用类似于c语言的指针的指针。
     * @return
     */
    private int compareDepth(Node node, int[] isBalance){
        if(node == null) return 0;

        if(isBalance[0] == 1)return 0;

        int depth = 0;
        System.out.print(node.data+" ");
        int leftDepth = compareDepth(node.left, isBalance);
        int rightDepth = compareDepth(node.right, isBalance);

        depth = leftDepth>rightDepth?leftDepth:rightDepth;

        int temp = leftDepth-rightDepth;
        if(temp >0){
            if(temp > 1) {
                isBalance[0] = 1;
            }
        }else if(temp < 0){
            if(temp < -1){
                isBalance[0] = 1;
            }
        }

        return depth+1;
    }


    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后续遍历的结果。
     * @param squence
     * @param start
     * @param end
     * @return
     */
    public boolean verifySquenceOfBST(int[] squence, int start, int end){

        if(squence == null || end < 0 || start < 0)return false;


        int root = squence[end];


        int i = 0;
        for (; i < end; i++) {
            if(squence[i] > root){
                break;
            }
        }

        int j = i;
        for (; j < end; j++) {
            if(squence[j] < root){
                return false;
            }
        }

        boolean left = true;
        if(i > 0){
            left = verifySquenceOfBST(squence, start, i-1);
        }
        boolean right = true;
        if(j < end){
            right = verifySquenceOfBST(squence, i, end-1);
        }
        if(left && right){
            return true;
        }else{
            return false;
        }
    }

    public void doTest2(){
        int[] a = {5,7,6,11,10,8};
        int[] aa = {7,4,6,5};
        int [] aaa = {1};

//        boolean falg = verifySquenceOfBST(a, 0, a.length-1);
//        boolean falg = verifySquenceOfBST(aa, 0, aa.length-1);
        boolean falg = verifySquenceOfBST(aaa, 0, aaa.length-1);
        System.out.println(falg);
    }

    //********************************************************************************

    /**
     * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
     * 从树的根节点开始玩下一直到叶节点所经过的节点形成一条路径。
     * 节点中没有父节点的引用。
     * @param root
     * @param target
     */
    public void path(Node root, int target){
        if(root == null) return ;

        Stack<Node> stack = new Stack<>();
        int[] sum = new int[]{0};
        traverse(root, target, sum, stack);

    }

    private void traverse(Node node , int target, int[] sum, Stack<Node> stack){
        if(node == null) return ;

        if(node.left == null && node.right == null && sum[0]+node.data == target){
            Iterator<Node> iter = stack.iterator();
            while(iter.hasNext()){
                Node tempNode = iter.next();
                System.out.print(tempNode.data+" ");
            }
            System.out.println(node.data);
        }

        stack.push(node);
        sum[0] += node.data;

        if(node != null){
            traverse(node.left, target, sum, stack);
            traverse(node.right, target, sum, stack);
        }

        stack.pop();
        sum [0]-= node.data;
    }

    public void doTest3(){
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(4);
        Node n7 = new Node(7);


        n1.left = n2;
        n1.right = n3;

        n2.left = n4;
        n2.right = n5;

        n3.left = n6;
        n3.right = n7;

        path(n1, 8);
    }

//***********************************************************************

    public static void main(String args[]){
        CommonBinaryTree binaryTree = new CommonBinaryTree();
        binaryTree.doTest3();
    }
}

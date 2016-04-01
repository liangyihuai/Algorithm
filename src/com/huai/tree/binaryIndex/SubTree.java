package com.huai.tree.binaryIndex;

/**
 * Created by liangyh on 3/29/16.
 */
public class SubTree {

    /**
     * 两棵树，根节点分别为nodeA、nodeB，判断B是不是A的子树
     * @param nodeA
     * @param nodeB
     * @return 如果B不是A的子树，或者B是A的一部分，但不是子树，则返回false。
     */
    public boolean doSubTreeTest(Node nodeA, Node nodeB){
        boolean result = false;

        if(nodeA != null && nodeB != null){
            if(nodeA.data == nodeB.data){
                result = subTreeCheck(nodeA, nodeB);
            }
            if(!result){
                result = doSubTreeTest(nodeA.left, nodeB);
            }
            if(!result){
                result = doSubTreeTest(nodeA.right, nodeB);
            }
        }

        return result;
    }

    private boolean subTreeCheck(Node nodeA, Node nodeB){
        if (nodeB == null){
            if(nodeA != null){
                return false;
            }
            return true;
        }

        if(nodeA == null)
            return false;
        if(nodeA.data != nodeB.data)
            return false;

        return subTreeCheck(nodeA.left, nodeB.left) && subTreeCheck(nodeA.right, nodeB.right);
    }

    class Node {
        int data;
        Node left;
        Node right;

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
        Node n7 = new Node(7);


//        Node n22 = new Node(2);
//        Node n33 = new Node(3);
//        Node n44 = new Node(4);
//        Node n55 = new Node(5);

        Node n11 = new Node(1);
        Node n22 = new Node(2);
        Node n66 = new Node(6);

        n1.left = n2;
        n1.right = n6;
        n2.left = n3;
        n2.right = n4;
        n4.right = n5;
        n6.right = n7;

//        n22.right = n44;
//        n22.left = n33;
//        n44.right = n55;

        n11.left = n22;
        n11.right = n66;

        boolean flag = doSubTreeTest(n1, n11);
        System.out.println(flag);
    }

    public static void main(String[] args) {
        SubTree test = new SubTree();
        test.doTest();
    }
}

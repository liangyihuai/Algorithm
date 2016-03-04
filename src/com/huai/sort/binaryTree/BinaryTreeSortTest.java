package com.huai.sort.binaryTree;

/**
 * 二叉树排序算法
 * <p>数据使用链式二叉树来存储，在增加节点的时候，小的在父节点的左边，大的在父节点的右边。刚增加的数据都是叶子节点</p>
 * <p>在删除的时候，可以有三种情况，分别是：（用a表示当前将要删除的节点）
 * 第一，如果a是叶子节点，可以直接删除；
 * 第二，如果a只有一个孩子节点，那么需要把孩子节点上移到a的位置；
 * 第三，如果a有两个子节点，那么需要找到a的后继节点b（中序遍历）,把b移到a的位置，
 * 这时候需要注意一种特殊情况，那就是后继节点b是将要删除的节点a的右孩子，需要单独处理。</p>
 * Created by liangyh on 16-1-20.
 */
public class BinaryTreeSortTest {

    private Node head;

    public void print(){
        Node current = head;
        travel(head);
    }

    /**
     * 中序中序遍历
     * @param node
     */
    private void travel(Node node){
        if(node == null) return;

        travel(node.left);
        System.out.print(node.data+" ");
        travel(node.right);
    }

    /**
     * 增加一个节点，小的在父节点的左边，大的在父节点的右边
     * @param data
     */
    public void add(int data){
        Node newNode = new Node(data);

        if(head == null){
            head = newNode;
            return;
        }

        Node current = head;
        while(true){
            if(current.data < data){
                if(current.right != null) {
                    current = current.right;
                } else{
                    current.right = newNode;
                    return;
                }
            }else{
                if(current.left != null) {
                    current = current.left;
                } else {
                    current.left = newNode;
                    return;
                }
            }
        }
    }

    /**
     * 删除一个节点
     * @param data
     * @return
     */
    public boolean delete(int data){
        if(head == null) return false;
        Node parent = head;
        Node current = head;
        boolean isLeft = true;
        while(current.data != data){
            parent = current;
            if(current.data < data){
                current = current.right;
                isLeft = false;
            }else{
                current = current.left;
                isLeft = true;
            }

            if(current == null) return false;
        }

        deleteCurrentNode2(current,isLeft, parent);

        return true;
    }

    /**
     * 删除当前节点
     * @param current 将要删除的节点
     * @param previous 当前节点的前一个节点
     * @param isLeftChild 当前节点是否为左孩子
     */
    private void deleteCurrentNode(Node current, boolean isLeftChild, Node previous){
        //特殊处理，如果将要删除的数据是根节点
        if(current == head){
            if(head.left != null && head.right != null){
                previous = head;
                Node deletingNode = head;//保存将要删除的节点
                current = head.right;
                //寻找将要删除的节点的后继节点（中序遍历）
                while(current.left != null){
                    previous = current;
                    current = current.left;
                }
                deletingNode.data = current.data;
                if(previous == deletingNode)//如果后继节点是待删除节点的右子树。
                    previous.right = current.right;
                else
                    previous.left = current.right;
            }else if(head.left != null){
                head = head.left;
            }else if(head.right != null){
                head = head.right;
            }else{
                //head.left == null && head.right == null
                head = null;
            }
            return;
        }

        if(current.left != null && current.right != null){
            previous = current;
            Node deletingNode = current;//保存将要删除的节点
            current = current.right;
            //寻找将要删除的节点的后继节点（中序遍历）
            while(current.left != null){
                previous = current;
                current = current.left;
            }
            deletingNode.data = current.data;
            if(previous == deletingNode)//如果后继节点是待删除节点的右子树。
                previous.right = current.right;
            else
                previous.left = current.right;
        }else if(current.left != null){
            //current.left != null && current.right == null
            if(isLeftChild)
                previous.left = current.left;
            else
                previous.right = current.left;
        }else if(current.right != null){
            //current.left == null && current.right != null
            if(isLeftChild)
                previous.left = current.right;
            else
                previous.right = current.right;
        }else{
            //current.left == null && current.right == null
            if(isLeftChild)
                previous.left = null;
            else
                previous.right = null;
        }
    }

    /**
     * 删除当前节点
     * @param current 将要删除的节点
     * @param previous 当前节点的前一个节点
     * @param isLeftChild 当前节点是否为左孩子
     */
    private void deleteCurrentNode2(Node current, boolean isLeftChild, Node previous){
        //如果左右孩子节点都不为空
        if(current.left != null && current.right != null){
            Node deletingNode = current;//保存将要删除的节点
            previous = current;
            current = current.right;
            //寻找将要删除的节点的后继节点（中序遍历）
            while(current.left != null){
                previous = current;
                current = current.left;
            }
            deletingNode.data = current.data;
            if(previous == deletingNode)//如果后继节点是待删除节点的右子树。
                previous.right = current.right;
            else
                previous.left = current.right;
        }else if(current.left != null){
            //current.left != null && current.right == null
            if(current == head) {
//                current = current.left;
                head = head.left;
            }
            else if(isLeftChild)
                previous.left = current.left;
            else
                previous.right = current.left;
        }else if(current.right != null){
            //current.left == null && current.right != null
            if(current == head) {
//                current = current.right;
                head = head.right;
            }
            else if(isLeftChild) {
                previous.left = current.right;
            }
            else
                previous.right = current.right;
        }else{
            //current.left == null && current.right == null
            if(current == head)
                head = null;
            else if(isLeftChild)
                previous.left = null;
            else
                previous.right = null;

        }
    }

    public Node getTargetNode(int data){

        Node current = head;

        while(current.data != data){
            if(current.data < data){
                current = current.right;
            }else{
                current = current.left;
            }

            if(current == null) return null;
        }
        return current;
    }

    public class Node{
        Node left;
        Node right;
        int data;

        public Node(){
            this.left = null;
            this.right = null;
        }

        public Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }


    public static void main(String args[]){
        BinaryTreeSortTest sortTest = new BinaryTreeSortTest();
        sortTest.add(8);
        sortTest.add(5);
//        sortTest.add(6);
//        sortTest.add(3);
//        sortTest.add(9);
//        sortTest.add(4);
//        sortTest.add(7);
//        sortTest.add(7);
//        sortTest.add(7);
//        sortTest.add(8);

        sortTest.print();
        System.out.println();

        boolean flag = sortTest.delete(8);
        sortTest.print();
    }
}

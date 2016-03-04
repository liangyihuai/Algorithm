package com.huai.sort.RBTree;

import java.io.PipedInputStream;

/**
 * Created by liangyh on 16-1-22.
 */
public class RedBlackTreeTest {

    private Node head;

    /**
     * 子节点是祖父节点的外侧节点
     * @param previous 祖父节点的父节点
     * @param grand
     * @param son
     * @param parent
     */
    public void rotateRight_out(Node previous, Node grand, Node parent, Node son){
        changeColor(parent);
        changeColor(grand);

        previous.right = parent;
        parent.left = grand;
        grand.right = parent.left;
    }

    /**
     * 当前节点为祖父节点的内侧
     * @param previous
     * @param grand
     * @param son
     * @param parent
     */
    public void rotateRight_in(Node previous, Node grand, Node parent, Node son){
//        changeColor(son);
//        changeColor(grand);
        grand.right = son;
        parent.left = son.right;
        son.right = parent;

        rotateRight_out(previous, grand, parent, son);
    }

    /**
     * 当前节点为祖父节点的外侧
     * @param previous
     * @param grand
     * @param son
     * @param parent
     */
    public void rotateLeft_out(Node previous, Node grand, Node parent, Node son){
        changeColor(parent);
        previous.left = parent;
        parent.right = grand;
        grand.left = parent.right;
        changeColor(grand);


    }

    /**
     * 当前节点为祖父节点的内侧
     * @param previous
     * @param grand
     * @param son
     * @param parent
     */
    public void rotateLeft_in(Node previous, Node grand, Node parent, Node son){
//        changeColor(son);
//        changeColor(grande);
        grand.left = son;
        parent.right = son.left;
        son.left = parent;

        rotateLeft_out(previous,grand,parent,son);

    }

    /**
     * 把当前节点以及它的两个子节点的颜色翻转，如果当前节点是根节点则颜色不变
     * @param parent 当前节点
     */
    public void flipColor(Node parent){
        if(parent == null) throw new NullPointerException("parent node is null");

        if(parent != head)
            changeColor(parent);
        if(parent.left != null)
            changeColor(parent.left);
        if(parent.right != null)
            changeColor(parent.right);
    }

    /**
     * 改变节点的颜色
     * @param node 需要改变颜色的节点
     * @return 改变后的颜色是否为红色
     */
    public boolean changeColor(Node node){
        if(node == null) throw  new NullPointerException();

        if(node.isRead)
            node.isRead = false;
        else
            node.isRead = true;
        return node.isRead;
    }

    /**
     * 打印
     */
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
            head.isRead = false;
            return;
        }
        Node previous = head;//parent节点的父节点
        Node parent = head;
        Node current = head;
        while(true){
            if(current.data < data){
                if(current.right != null) {
                    previous = parent;
                    parent = current;
                    current = current.right;
                } else{
                    current.right = newNode;
                    newNode.parent = current;
                    newNode.isLeftChild = false;
                    return;
                }
            }else if(current.data > data){
                if(current.left != null) {
                    previous = parent;
                    parent = current;
                    current = current.left;
                } else {
                    current.left = newNode;
                    newNode.parent = current;
                    newNode.isLeftChild = true;
                    return;
                }
            }else{
                //不允许关键值重复
                break;
            }
        }//end while

        //如果新增加的节点是根节点的孩子
        if(previous == parent && parent == current && current == head){
            return;
        }

        //调整二叉树
        if(current.isLeftChild && newNode.isLeftChild){//左外侧
            if(previous == head && parent == head){//如果新增加的节点的祖父节点是根节点
                if(parent.right == null){
                    current.isRead = false;
                    head.isRead = true;
                    head = current;
                    head.right = parent;
                    parent.left = null;
                }else{
                    if(current.isRead)
                        flipColor(head);
                }
            }else if(current.isRead && parent.right == null ){
                rotateRight_out(previous,parent,current,newNode);
            }else if(current.isRead && parent.right != null){
                flipColor(parent);
                if(parent.isRead && previous.isRead){
                    if(previous.isLeftChild){//左外侧

                        if(previous.parent == head) {
                            changeColor(previous);
                            changeColor(previous.parent);

                            head = previous;
                            previous.parent.left = head.right;
                            head.right = previous.parent;
                            head.parent = null;
                            head.right.parent = head;
                            head.right.isLeftChild = false;
                        } else{
                            rotateLeft_out(previous.parent.parent, previous.parent, previous, parent);
                        }
                    }else{//右内侧
                        if(previous.parent == head){
                            changeColor(previous);
                            changeColor(previous.parent);

                            head = previous;
                            previous.parent.right = head.left;
                            head.left = previous.parent;
                            head.parent = null;
                            head.left.parent = head;
                            head.left.isLeftChild = true;
                        }else{
                            rotateRight_in(previous.parent.parent,previous.parent, previous,parent);
                        }
                    }
                }
            }
        }else if(current.isLeftChild && !newNode.isLeftChild){//左内侧
            if(previous == head && parent == head){
                if(parent.right == null){
                    //向左旋转
//                    current.right = newNode.left;
                    parent.left = newNode;
                    newNode.left = current;
                    current.right = null;
                    //向右旋转
                    newNode.right = parent;
                    parent.left = null;
                }else{
                    if(current.isRead)
                        flipColor(head);
                }
            }else if(current.isRead && parent.left == null){
                rotateRight_in(previous,parent,current,newNode);
            }else if(current.isRead && parent.left != null){
                //翻转三个节点的颜色
                flipColor(parent);
                if(parent.isRead && previous.isRead){
                    if(parent.isLeftChild){//左内侧
                        if(previous.parent == head){
                            head = previous;
                            previous.parent.left = head.right;
                            head.right = previous.parent;

                            head.parent = null;
                            head.right.parent = head;
                            head.right.isLeftChild = false;
                        }else{
                            rotateLeft_in(previous.parent.parent, previous.parent, previous, parent);
                        }
                    }else{//右外侧
                        if(previous.parent == head){
                            head = previous;
                            previous.parent.right = head.left;
                            head.left = previous.parent;

                            head.parent = null;
                            head.left.parent = head;
                            head.left.isLeftChild = true;
                        }else{
                            rotateRight_out(previous.parent.parent,previous.parent, previous, parent);
                        }
                    }
                }
            }
        }else if (!current.isLeftChild && !newNode.isLeftChild){//右外侧
            if(previous == head && parent == head){//如果新增加的节点的祖父节点是根节点
                if(parent.left == null){
                    current.isRead = false;
                    head.isRead = true;
                    head = current;
                    head.left = parent;
                    parent.right = null;
                }else{
                    if(current.isRead)
                        flipColor(head);
                }
            }else if(current.isRead && parent.left == null){
                rotateRight_out(previous, parent, current, newNode);
            }else if(current.isRead && parent.left != null){
                flipColor(parent);
                if(previous.isRead && parent.isRead){
                    if(parent.isLeftChild){//左内侧

                    }else{//右外侧

                    }
                }
//                if(previous.parent == head){
//                    head = previous;
//                    previous.parent.right = head.left;
//                    head.left = previous.parent;
//
//                    head.parent = null;
//                    head.left.parent = head;
//                    head.left.isLeftChild = true;
//                }else{//右外侧
//                    rotateRight_out(previous.parent.parent, previous.parent, previous, parent);
//                }
            }
        }else if(!current.isLeftChild && newNode.isLeftChild){//右内侧
            if(previous == head && parent == head){
                if(parent.left == null){
                    //向右旋转
                    parent.right = newNode;
                    newNode.right = current;
                    current.left = null;
                    //向左旋转
                    newNode.left = parent;
                    parent.right = null;
                }else{
                    if(current.isRead)
                        flipColor(head);
                }
            }else if(current.isRead && parent.right == null){
                rotateRight_in(previous,parent,current,newNode);
            }else if(current.isRead && parent.right != null){
                flipColor(parent);
                if(previous.isRead && parent.isRead){
                    if(parent.isLeftChild){//左内侧
                        if(previous.parent == head){

                        }else{

                        }
                    }else{//右外侧
                        if(previous.parent == head){

                        }else{

                        }
                    }
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
                head = head.left;
            }
            else if(isLeftChild)
                previous.left = current.left;
            else
                previous.right = current.left;
        }else if(current.right != null){
            //current.left == null && current.right != null
            if(current == head) {
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
        Node parent;
        boolean isLeftChild;
        int data;
        boolean isRead = true;

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
        RedBlackTreeTest sortTest = new RedBlackTreeTest();
        sortTest.add(8);
        sortTest.add(5);
        sortTest.add(6);
        sortTest.add(3);
        sortTest.add(9);
        sortTest.add(4);
        sortTest.add(7);
        sortTest.add(7);
        sortTest.add(7);
        sortTest.add(8);

        sortTest.print();
        System.out.println();

        boolean flag = sortTest.delete(8);
        sortTest.print();
    }
}

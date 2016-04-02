package com.huai.graph.allway;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * 算法思路：
 * 1、将起始节点压入栈中。
 * 2、查看栈顶元素top在图中有没有可以到达且没有入栈且top没有访问过的定点A。如果有，执行第3步，如果没有，执行第4步。
 * 3、把定点A入栈，并在top中设置A为top已经访问过的元素。执行第二步。
 * 4、查看栈中是否包含所有的定点，如果是，就打印栈中元素信息。
 * 5、弹出栈中的顶元素，并把该元素的访问记录（对应Vertex类的int[] visiteds;）清除。
 * 6、重复执行2-5部，直到栈为空。
 * Created by liangyh on 4/2/16.
 */
public class Graph {

    private int MAX = 10;

    /**
     * 邻接矩阵
     */
    private int[][] relation = null;

    /**
     * 图的所有定点。
     */
    private List<Vertex> vertexList  = null;

    /**
     * 辅助结构，栈
     */
    private Stack<Vertex> stack = new Stack();

    public Graph(int vertexNum){
        this.MAX = vertexNum;
        this.relation = new int[MAX][MAX];
        vertexList = new ArrayList<>(MAX);
    }

    /**
     * 增加节点之间的边。
     * @param from
     * @param to
     * @return
     */
    public boolean addAdge(int from, int to){
        if(from < 0 || to < 0 || from >= MAX || to >= MAX){
            System.err.println("out of bound");
            return false;
        }

        relation[from][to] = 1;
        return true;
    }


    /**
     * 创建一个节点。
     * @param label
     */
    public void createVertex(String label){
        int index = vertexList.size();
        if(index == MAX) {
            try {
                throw new Exception("can not add more vertex");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        vertexList.add(new Vertex(label, MAX, index));
    }

    /**
     * 查找所有路径。
     * 思路：深度遍历的思想。
     *
     */
    public void findWays(){
        int startID = getStartVertex();
System.out.println(startID);
        if(startID == -1) {
            return ;
        }

        Vertex start = vertexList.get(startID);

        stack.push(start);

        while(!stack.isEmpty()){
            Vertex topVertex = stack.peek();

            //当找到一个可用的节点的时候就返回。
            int id = hasAvailableVertex(topVertex);
            if(id != -1){
                stack.push(vertexList.get(id));
                topVertex.addVisiteds(id);
            }else{
                if(isFullWayDone()){
                    printStack();
                }

                Vertex tempVertex = stack.pop();
                tempVertex.clearVisited();
            }
        }
    }

    /**
     * 判断是否存在与topVertex相连、没有被topVertex访问过、不在栈中的节点。
     * @param topVertex 符合条件的节点。
     * @return
     */
    public int hasAvailableVertex(Vertex topVertex){
        for (int i = 0; i < MAX; i++) {
            if(relation[topVertex.getId()][i] >= 1){//相连
                if((!topVertex.isVisited(i))&&(!isInStack(i))){//没有访问过、不在栈中。
                    return i;
                }
            }
        }
        return -1;
    }

    //这条路线是否包含了所有的节点。
    private boolean isFullWayDone(){
        return stack.size()==MAX;
    }

    //打印栈中的元素信息
    private void printStack(){
        Iterator<Vertex> iter = stack.iterator();
        while(iter.hasNext()){
            Vertex v = iter.next();
            System.out.print(v.getLabel()+":"+v.getId()+"---> ");
        }
        System.out.println();
    }

    //判断某节点是否在栈中。
    private boolean isInStack(int vertexID){
        if(vertexID < 0 || vertexID >= MAX)
            return false;

        Iterator<Vertex> iter = stack.iterator();
        while(iter.hasNext()){
            Vertex v = iter.next();
            if(v.getId() == vertexID)
                return true;
        }
        return false;
    }

    /**
     * 查找开始节点。如果存在节点没有出度并且没有入度、或者两个以上节点只有出度、或者两个以上节点只有入度，就直接返回-1，
     * 表示该图不存在这样的路径。如果存在只有入度或者只有出度的节点，则返回该节点的下标。
     * 以上两种情况都不满足的，就返回第一个节点。
     */
    private int getStartVertex(){
        int startID = -1;

        int inSum = 0;
        int outSum = 0;
        for (int i = 0; i < MAX; i++) {
            int inCount = 0;
            int outCount = 0;
            for (int j = 0; j < MAX; j++) {
                if(relation[i][j] >= 1){
                    outCount++;
                }
            }

            for (int j = 0; j < MAX; j++) {
                if(relation[j][i] >= 1){
                    inCount++;
                }
            }

            if(outCount == 0 && inCount == 0)
                return -1;

            if(inCount == 1 && outCount == 0){
                inSum++;
            }
            else if(inCount == 0 && outCount == 1){
                outSum++;
                startID = i;
            }
        }
        if(outSum > 1)
            return -1;
        else if(inSum > 1)
            return -1;
        else if(startID != -1)
            return startID;
        else
            return 0;
    }
}

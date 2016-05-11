package com.huai.huawei;

import java.util.*;

import static sun.plugin.javascript.navig.JSType.Link;

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
     * 连接矩阵
     */
    private List<LinkedList<Edge>> relationLink = null;

    /**
     * 图的所有定点。
     */
    private List<Vertex> vertexList  = null;

    /**
     * 辅助结构，栈
     */
    private Stack<Vertex> stack = new Stack();

    private int[] keyVertex = null;

    private int startVertex = -1;

    private int distinationVertex = -1;

    //路径的权重和
    private int pathWeight = 0;

    //路径
    private Vertex[] path = new Vertex[MAX];

    //路径的顶点数。
    private int size = 0;

    public Graph(int vertexNum){
        this.MAX = vertexNum;
        this.relation = new int[MAX][MAX];
        vertexList = new ArrayList<>(MAX);

        relationLink = new ArrayList<>(MAX);
    }

    /**
     * 增加邻接矩阵节点之间的边。
     * @param from
     * @param to
     * @return
     */
    public boolean addEdge(int from, int to, int weight){
        if(from < 0 || to < 0 || from >= MAX || to >= MAX){
            System.err.println("out of bound");
            return false;
        }

        relation[from][to] = weight;
        return true;
    }

    /**
     * 增加连接矩阵节点之间的边。
     * @param from
     * @param to
     * @return
     */
    public boolean addEdgeLink(int from, int to, int weight){
        if(from < 0 || to < 0 || from >= MAX || to >= MAX){
            System.out.println("out of bound");
            return false;
        }

        relationLink.get(from).add(new Edge(to, weight));
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
     */
    public void findWays(){
//        int startID = getStartVertex();
        if(this.startVertex == -1) {
            System.err.println("the start vertex is null");
            return ;
        }

        Vertex start = vertexList.get(startVertex);

        stack.push(start);
        start.setInStack(true);

        while(!stack.isEmpty()){
            Vertex topVertex = stack.peek();

            //当找到一个可用的节点的时候就返回。
            int id = hasAvailableVertex(topVertex);
            if(id != -1){
                Vertex tempVertex = vertexList.get(id);
                stack.push(tempVertex);
                tempVertex.setInStack(true);
                topVertex.addVisiteds(id);

                if(id == distinationVertex){//判断是否到达了目的节点
                    if(isIncludeAllKeyVertex()){//判断是否包含了所有的关键节点。
                        if(this.size == 0 && this.pathWeight == 0){//表示之前没有计算出路径。
                            backup();
                        }else{
                            int weightTemp = countWeight();
                            if(this.countWeight() == weightTemp){//题目的要求是，如果权重一样，选择时间少的。

                            }else if(this.countWeight() > weightTemp){
                                backup();
                            }
                        }
                    }
                }
            }else{
//                if(isFullWayDone()){
//                    printStack();
//                    System.out.println(countWeight());
//                }

                Vertex tempVertex = stack.pop();
                tempVertex.setInStack(false);
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
                if((!topVertex.isVisited(i))&&
                        (!vertexList.get(i).isInStack())){//没有访问过、不在栈中。
                    return i;
                }
            }
        }
        return -1;
    }

    public int hasAvailableVertexLink(Vertex topVertex){
        LinkedList<Edge> link = relationLink.get(topVertex.getId());
        Iterator<Edge> ite = link.iterator();
        while(ite.hasNext()){
            Edge edge = ite.next();
            int distID = edge.getDistinationID();
            if(!topVertex.isVisited(distID) &&
                    (!vertexList.get(distID).isInStack())){
                return distID;
            }
        }
        return -1;
    }

    //这条路线是否包含了所有的关键节点。
    private boolean isIncludeAllKeyVertex(){
        for (int i = 0; i < keyVertex.length; i++) {
            if(!vertexList.get(keyVertex[i]).isInStack()){
                return false;
            }
        }
        return true;
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

    //统计路径的权重.
    private int countWeight(){
        if(stack.size() > 1){
            int weight = 0;
            Iterator<Vertex> iter = stack.iterator();
            int late = iter.next().getId();
            int current = iter.next().getId();
            weight += relation[late][current];
            int tempID = -1;
            while(iter.hasNext()){
                tempID = iter.next().getId();
                weight += relation[current][tempID];
                current = tempID;
            }
            return weight;
        }
        return -1;
    }

    //备份路径和该路径的权重和.
    private void backup(){
        if(stack.size() > 1){
            this.size = 0;
            int weight = 0;
            Iterator<Vertex> iter = stack.iterator();

            Vertex temp1 = iter.next();
            int late = temp1.getId();
            Vertex temp2 = iter.next();
            int current = temp2.getId();

            path[size++] = temp1;
            path[size++] = temp2;

            weight += relation[late][current];
            int tempID = -1;

            while(iter.hasNext()){
                tempID = iter.next().getId();
                weight += relation[current][tempID];
                current = tempID;

                this.path[size++] = iter.next();
            }
            this.pathWeight = weight;
        }
    }

    public void setKeyVertex(int[] keyVertex) {
        this.keyVertex = keyVertex;
    }

    public void setStartVertex(int startVertex) {
        this.startVertex = startVertex;
    }

    public void setDistinationVertex(int distinationVertex) {
        this.distinationVertex = distinationVertex;
    }

    //返回结果。
    public String outResult(){
        if(this.size == 0 || this.pathWeight == 0)
            return "NA";

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size-1; i++) {
            builder.append(path[i]+"|");
        }
        builder.append(path[size-1]);
        return builder.toString();
    }
}

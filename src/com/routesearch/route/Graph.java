package com.routesearch.route;

import java.util.*;

/**
 * 算法思路：
 * 1、将起始节点压入栈中。
 * 2、查看栈顶元素top在图中有没有可以到达且没有入栈且top没有访问过的定点A。如果有，执行第3步，如果没有，执行第4步。
 * 3、把定点A入栈，并在top中设置A为top已经访问过的元素。执行第二步。
 * 4、查看栈中是否包含所有的定点，如果是，就打印栈中元素信息。
 * 5、弹出栈中的顶元素，并把该元素的访问记录（对应Vertex类的int[] visiteds;）清除。
 * 6、重复执行2-5部，直到栈为空。
 * Created by liangyh on 4/2/16.
 *
 * /home/liangyh/Desktop/HuaWei/case1/topo.csv /home/liangyh/Desktop/HuaWei/case1/demand.csv /home/liangyh/Desktop/HuaWei/result/result.csv
 */
public class Graph {

    private int MAX = 10;

    /**
     * 邻接矩阵
     */
    private int[][] relationAdjacency = null;

    /**
     * 连接矩阵
     */
    private List<LinkedList<Edge>> relationLink = null;

    /**
     * 图的所有定点。
     */
    private List<Vertex> vertexList  = null;

    /**
     * 辅助结构，顶点栈
     */
    private Stack<Vertex> vertexStack = new Stack();

    /**
     * 辅助结构，边栈,
     * 边栈中的第一个元素是没有用的。
     */
    private Stack<Edge> edgeStack = new Stack<Edge>();

    /**
     * 关键节点集
     */
    private int[] keyVertexSet = null;
    //该数组的下标是关键节点的id，用于快速定位某一个数组。在Route类中初始化。
    private int[] keyVertex_for_quickSort = null;

    /**
     * 开始节点
     */
    private int startVertex = -1;

    /**
     * 目的节点
     */
    private int distinationVertex = -1;

    //路径的权重和
    private int pathWeight = 0;

    //路径
    private Edge[] path;

    //路径的顶点数。
    private int size = 0;

    public Graph(int vertexNum){
        this.MAX = vertexNum;
        this.relationAdjacency = new int[MAX][MAX];
        vertexList = new ArrayList<Vertex>(MAX);

        relationLink = new ArrayList<LinkedList<Edge>>(MAX);
        for (int i = 0; i < MAX; i++) {
            relationLink.add(new LinkedList<Edge>());
        }
        path = new Edge[MAX];

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

        relationAdjacency[from][to] = weight;
        return true;
    }

    /**
     * 增加连接矩阵节点之间的边。
     * @param from
     * @param to
     * @return
     */
    public boolean addEdgeLink(int from, int to, int weight, int index){
        if(from < 0 || to < 0 || from >= MAX || to >= MAX){
            System.out.println("out of bound");
            return false;
        }

        relationLink.get(from).add(new Edge(to, weight, index));
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
        if(this.startVertex == -1) {
            System.err.println("the start vertex is null");
            return ;
        }

        int checkResult = checkRoughly();
        if(checkResult < 0)
            return ;

        Vertex start = vertexList.get(startVertex);

        vertexStack.push(start);
        edgeStack.push(new Edge());//边栈中的第一个元素是没有用的。
        start.setInStack(true);

        while(!vertexStack.isEmpty()){
            //设置时间
            if(System.currentTimeMillis()-Route.startTime > 9000){
                break;
            }

            Vertex topVertex = vertexStack.peek();

            //当找到一个可用的节点的时候就返回。
            int id = hasAvailableVertexLink(topVertex);
            if(id != -1){
                Vertex tempVertex = vertexList.get(id);
                vertexStack.push(tempVertex);
                tempVertex.setInStack(true);
                topVertex.addVisiteds(id);

                if(id == distinationVertex){//判断是否到达了目的节点
                    if(isIncludeAllKeyVertex()){//判断是否包含了所有的关键节点。
                        if(this.size == 0 && this.pathWeight == 0){//表示之前没有计算出路径。
                            backupLink();
                        }else{
                            int weightTemp = countWeightLink();
                            if(this.pathWeight == weightTemp){//题目的要求是，如果权重一样，选择时间少的。

                            }else if(this.pathWeight > weightTemp){
                                backupLink();
                            }
                        }
                    }
                }
            }else{
                Vertex tempVertex = vertexStack.pop();
                tempVertex.setInStack(false);
                tempVertex.clearVisited();

                edgeStack.pop();
            }
        }
    }

    /**
     * 判断是否存在与topVertex相连、没有被topVertex访问过、不在栈中的节点。
     * (邻接矩阵)
     * @param topVertex 符合条件的节点。
     * @return
     */
    public int hasAvailableVertexAdjacency(Vertex topVertex){
        for (int i = 0; i < MAX; i++) {
            if(relationAdjacency[topVertex.getId()][i] >= 1){//相连
                if((!topVertex.isVisited(i))&&
                        (!vertexList.get(i).isInStack())){//没有访问过、不在栈中。

                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 判断是否存在与topVertex相连、没有被topVertex访问过、不在栈中的节点。
     * （连接矩阵）
     * @param topVertex 符合条件的节点。
     * @return
     */
    public int hasAvailableVertexLink(Vertex topVertex){
        LinkedList<Edge> link = relationLink.get(topVertex.getId());
        Iterator<Edge> ite = link.iterator();
        while(ite.hasNext()){
            Edge edge = ite.next();
            int distID = edge.getDistinationID();
            //没有访问过、不在栈中。
            if(!topVertex.isVisited(distID) &&
                    (!vertexList.get(distID).isInStack())){
                this.edgeStack.push(edge);
                return distID;
            }
        }
        return -1;
    }

    //这条路线是否包含了所有的关键节点。
    private boolean isIncludeAllKeyVertex(){
        for (int i = 0; i < keyVertexSet.length; i++) {
            if(!vertexList.get(keyVertexSet[i]).isInStack()){
                return false;
            }
        }
        return true;
    }

    //打印栈中的元素信息
    private void printStack(){
        Iterator<Vertex> iter = vertexStack.iterator();
        while(iter.hasNext()){
            Vertex v = iter.next();
            System.out.print(v.getLabel()+":"+v.getId()+"---> ");
        }
        System.out.println();
    }

    /**
     * 简单判断该图是否可以走通。判断思路：
     * 关键节点的出度和入度不能为0.
     * @return
     */
    private int checkRoughly(){

        for (int i = 0; i < MAX; i++) {
            int inCount = 0;
            int outCount = 0;
            for (int j = 0; j < MAX; j++) {
                if(relationAdjacency[i][j] >= 1){
                    outCount++;
                }
            }

            for (int j = 0; j < MAX; j++) {
                if(relationAdjacency[j][i] >= 1){
                    inCount++;
                }
            }

            if(outCount == 0 && inCount == 0){
                if(isKeyVertex(i) || i == startVertex || i == distinationVertex)
                    return -1;
            }
            else if(inCount >= 1 && outCount == 0){
                if((i != distinationVertex) && (isKeyVertex(i)))
                    return -1;
            }
            else if(inCount == 0 && outCount >= 1){
                if(i != startVertex && (isKeyVertex(i)))
                    return -1;
            }
        }
        return 1;
    }

    /**
     * 统计路径的权重
     * @return
     */
    private int countWeightLink(){
        int weight = 0;
        Iterator<Edge> ite = this.edgeStack.iterator();
        while (ite.hasNext()){
            Edge edge = ite.next();
            weight += edge.getWeight();
        }
        return weight;
    }


    /**
     * 备份路径：包括路径和权重
     */
    private void backupLink(){
        if(edgeStack.size() > 1){
            int weight = 0;
            this.size = 0;
            Iterator<Edge> ite = this.edgeStack.iterator();
            ite.next();
            while (ite.hasNext()){
                Edge edge = ite.next();
                this.path[this.size++] = edge;
                weight += edge.getWeight();
            }
            this.pathWeight = weight;
        }
    }

    public void setKeyVertexSet(int[] keyVertex) {
        this.keyVertexSet = keyVertex;
    }

    public void setStartVertex(int startVertex) {
        this.startVertex = startVertex;
    }

    public void setDistinationVertex(int distinationVertex) {
        this.distinationVertex = distinationVertex;
    }

    /**
     * 返回结果。
     * @return
     */
    public String outResult(){
        if(this.size == 0 || this.pathWeight == 0)
            return "NA";

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size-1; i++) {
            builder.append(path[i].getIndex()+"|");
        }
        builder.append(path[size-1].getIndex());
        return builder.toString();
    }

    /**
     * 判断某一个节点是不是关键节点
     * @param vertexID
     * @return
     */
    public boolean isKeyVertex(int vertexID){
//        int result = Util.search_binary(keyVertexSet, vertexID);
//        if(result >= 0)
//            return true;
//        else
//            return false;

        if(keyVertex_for_quickSort[vertexID] > 0)
            return true;
        else
            return false;
    }

    public void setKeyVertex_for_quickSort(int[] keyVertex_for_quickSort) {
        this.keyVertex_for_quickSort = keyVertex_for_quickSort;
    }
}

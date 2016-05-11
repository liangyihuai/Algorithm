package com.routesearch.route;

import com.huai.huawei.*;
import com.huai.huawei.Vertex;

import java.util.*;

/**
 * Created by liangyh on 4/8/16.
 */
public class SpanningPath {
    /**
     * 最小生成树的连接矩阵
     */
    private List<LinkedList<Edge>> relationLink = null;

    private Graph graph;

    private int MAX;

    public SpanningPath(Graph graph){
        this.MAX = graph.getMAX();
        this.relationLink = new ArrayList<LinkedList<Edge>>(MAX);

    }

    public void createSpanningTree(List<LinkedList<Edge>> graphTopo){
        if(graphTopo == null)return ;

        Queue<Edge> queue = new PriorityQueue<>(2*MAX, new MyComparator());

        int vertexsLen = graphTopo.size();
        for (int i = 0; i < vertexsLen; i++) {//把所有的边加到优先级队列中。
            Iterator<Edge> ite = graphTopo.get(i).iterator();
            while(ite.hasNext()){
                queue.add(ite.next());
            }
        }

        int len = queue.size();

        //表示各顶点自成一个连通分量
        int[] vexSet = new int[len];
        for(int i = 0; i < len; i++){
            vexSet[i] = i;
        }

        for(int i = 0; i < len; i++){
            Edge e1 = null;
            //队列中权重最小的边
            e1 = queue.poll();

            if(e1 != null){
                if(vexSet[e1.getStartID()] != vexSet[e1.getDistinationID()]){

                    System.out.println(e1.getStartID() +" --> "+e1.getDistinationID() +
                            "; weigth = "+e1.getWeight()+"; index = "+e1.getIndex());

                    addEdgeLink(e1);

                    int t = vexSet[e1.getDistinationID()];
                    int h = vexSet[e1.getStartID()];
                    //合并头和尾的连通分量
                    for(int j = 0; j < len; j++){
                        if(vexSet[j] == t){
                            vexSet[j] = h;
                        }
                    }
                }
            }//end if
        }//end for

    }


    /**
     * 增加连接矩阵节点之间的边。
     * @param from
     * @param to
     * @return
     */
    private boolean addEdgeLink(int from, int to, int weight, int index){
        if(from < 0 || to < 0 || from >= MAX || to >= MAX){
            System.out.println("out of bound");
            return false;
        }

        relationLink.get(from).add(new Edge(from, to, weight, index));
        return true;
    }

    private boolean addEdgeLink(Edge edge){
        if(edge != null){
            int from = edge.getStartID();
            this.relationLink.get(from).add(edge);
            return true;
        }
        return false;
    }


    class MyComparator implements Comparator<Edge>{

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.getWeight()-o2.getWeight();
        }
    }

    /**
     * 修剪最小生成树中的叶子节点。使得所有的叶子节点都是关键节点。
     */
    public void changeApanningTree(){
//        int[] kedVertexSet = this.graph.
        List<com.routesearch.route.Vertex> vertexList = this.graph.getVertexList();

        int start = 0;
        for (int i = 0; i < MAX; i++) {
            if(graph.isKeyVertex(i)){
                start = i;
                break;
            }
        }

        Stack<Integer> s = new Stack<Integer>();
        s.push(start);
        vertexList.get(start).setVisited(true);

        int currentKeyVertexID = start;
        int currentDistinationID = -1;

        while(!s.isEmpty()){
            int topVertexID = s.peek();

            LinkedList<Edge> link = relationLink.get(topVertexID);
            Iterator<Edge> ite = link.iterator();
            while(ite.hasNext()){
                Edge tempEdge = ite.next();
                int distinateID = tempEdge.getDistinationID();
                if(!vertexList.get(distinateID).isVisited()){
                    s.push(distinateID);
                    if(isKeyVetex(distinateID)){
                        currentKeyVertexID = distinateID;
                    }
                    break;//深度优先遍历，所以在搜索到一个节点的时候就跳出。
                }
            }
        }
    }

    public boolean isKeyVetex(int id){
        return this.graph.isKeyVertex(id);
    }
}

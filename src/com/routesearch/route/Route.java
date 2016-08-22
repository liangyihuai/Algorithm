/**
 * 实现代码文件
 * 
 * @author XXX
 * @since 2016-3-4
 * @version V1.0
 */
package com.routesearch.route;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class Route {
    private static Parser parser = new Parser();

    public static final long startTime = System.currentTimeMillis();

    /**
     * 功能的入口
     */
    public static String searchRoute(String graphContent, String condition) {
        Graph graph = constructGraph(graphContent, condition);
        findPath(graph);
        return outResult(graph);
    }

    /**
     *构造Graph
     * @param graphContent
     * @return Graph
     */
    private static Graph constructGraph(String graphContent, String condition){
        if(graphContent == null){
            System.err.println("the object is null !");
            return null;
        }

        List<int[]> dya = new LinkedList<int[]>();
        int maxVertex = parser.parseGraphContent(graphContent, dya);

        maxVertex += 1;
        Graph graph = new Graph(maxVertex);

        //创建顶点
        for (int i = 0; i < maxVertex; i++) {
            graph.createVertex(""+i);
        }

        //构建邻接矩阵和连接矩阵。
        Iterator<int[]> ite = dya.iterator();
        while(ite.hasNext()){
            int[] ints = ite.next();
            graph.addEdge(ints[1], ints[2], ints[3]);
            graph.addEdgeLink(ints[1], ints[2], ints[3], ints[0]);
        }
        //解析开始节点、目的节点和关键节点。
        parser.parseCondition(condition);

        int [] keyVertexSet = parser.getKeyVertexSet();
        graph.setKeyVertexSet(keyVertexSet);//构建关键点。
        graph.setStartVertex(parser.getStartVertex());//开始顶点
        graph.setDistinationVertex(parser.getDistinationVertex());//目的顶点

        //构建关键节点的辅助的快速查找数组
        int[] tempArray = new int[maxVertex];
        for (int i = 0; i < keyVertexSet.length; i++) {
            tempArray[keyVertexSet[i]] = 1;
        }
        graph.setKeyVertex_for_quickSort(tempArray);

        return graph;
    }

    /**
     * 查找路径
     * @param graph
     */
    public static void findPath(Graph graph){
        graph.findWays();
    }

    /**
     * 返回结果
     * @param graph
     * @return
     */
    public static String outResult(Graph graph){
        return graph.outResult();
    }
}
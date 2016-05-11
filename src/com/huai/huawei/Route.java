/**
 * 实现代码文件
 * 
 * @author XXX
 * @since 2016-3-4
 * @version V1.0
 */
package com.huai.huawei;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class Route {
    private static Parser parser = new Parser();

    /**
     * 功能的入口
     */
    public static String searchRoute(String graphContent, String condition) {
        constructKeyVertex(condition);
        return constructGraph(graphContent);
    }

    /**
     *
     * @param graphContent
     * @return 结果
     */
    private static String constructGraph(String graphContent){
        if(graphContent == null){
            System.err.println("the object is null !");
            return null;
        }

        List<int[]> dya = new LinkedList();
        int maxVertex = parser.parseGraphContent(graphContent, dya);

        Graph graph = new Graph(maxVertex);

        //创建顶点
        for (int i = 0; i < maxVertex; i++) {
            graph.createVertex(""+i);
        }

        //构建连接矩阵和连接矩阵。
        Iterator<int[]> ite = dya.iterator();
        while(ite.hasNext()){
            int[] ints = ite.next();
            graph.addEdge(ints[1], ints[2], ints[3]);
            graph.addEdgeLink(ints[1], ints[2], ints[3]);
        }

        graph.setKeyVertex(parser.getKeyVertex());//构建关键点。
        graph.setStartVertex(parser.getStartVertex());//开始顶点
        graph.setDistinationVertex(parser.getDistinationVertex());//目的顶点

        graph.findWays();

        return graph.outResult();
    }

    private static void constructKeyVertex(String condition){
        parser.parseCondition(condition);
    }
}
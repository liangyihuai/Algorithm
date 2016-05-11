package com.huai.tree;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by liangyh on 4/8/16.
 */
public class MinTree_KruskalTest {

}

class MinTree_Kruskal{

    private Queue<Edge> edges = null;
    private int Max = 0;

    public MinTree_Kruskal(int Max){
        this.Max = Max;
        this.edges = new PriorityQueue<Edge>(Max,new MyComparator());
    }

    public void addEdge(int head, int tail, int weigth){
        this.edges.add(new Edge(head, tail, weigth));
    }

    public void doKruskal(){
        //优先级队列，按照权重从小到大排列
        Queue<Edge> queue = new PriorityQueue<Edge>(Max,new MyComparator());
        Iterator<Edge> iter = edges.iterator();
        //复制队列
        while(iter.hasNext()){
            queue.add(iter.next());
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
                if(vexSet[e1.startID] != vexSet[e1.distinationID]){
                    System.out.println(e1.startID +" --> "+e1.distinationID +"; weigth = "+e1.weigth);
                    int t = vexSet[e1.distinationID];
                    int h = vexSet[e1.startID];
                    //合并头和尾的连通分量
                    for(int j = 0; j < len; j++){
                        if(vexSet[j] == t){
                            vexSet[j] = h;
                        }
                    }
                }
            }
        }
    }


    class Edge{
        //头顶点
        int startID;
        //尾顶点
        int distinationID;
        //两点的权重
        int weigth;

        public Edge(int head, int tail, int weigth){
            this.startID = head;
            this.distinationID = tail;
            this.weigth = weigth;
        }
    }

    /**
     * 自定义比较器
     * @author LiangYH
     *
     */
    class MyComparator implements Comparator<Edge>{

        public MyComparator(){}

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weigth - o2.weigth;
        }

    }
}
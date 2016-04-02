package com.huai.graph.allway;

/**
 * Created by liangyh on 4/2/16.
 */
public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.createVertex("a");
        graph.createVertex("b");
        graph.createVertex("c");
        graph.createVertex("d");
        graph.createVertex("e");
        graph.createVertex("f");

        graph.createVertex("g");
        graph.createVertex("h");

        graph.addAdge(0,1);
        graph.addAdge(1,2);
        graph.addAdge(2,3);
        graph.addAdge(3,4);
        graph.addAdge(2,4);
        graph.addAdge(4,0);
        graph.addAdge(1,5);
        graph.addAdge(5,2);

        graph.addAdge(4,3);
        graph.addAdge(3,2);

        graph.addAdge(6,2);
        graph.addAdge(7,6);

        graph.findWays();
    }
}

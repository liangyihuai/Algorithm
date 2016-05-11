package com.routesearch.route;

/**
 * Created by liangyh on 4/3/16.
 */
public class Edge {

    private int weight = 0;
    private int startID = -1;
    private int distinationID = -1;

    /**
     * 边的标号，也就是边的索引。
     */
    private int index = -1;

    public Edge(){}

    public Edge(int distinationID, int weight, int index) {
        this.weight = weight;
        this.distinationID = distinationID;
        this.index = index;
    }

    public Edge(int startID, int distinationID, int weight, int index){
        this(distinationID, weight, index);
        this.startID = startID;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getDistinationID() {
        return distinationID;
    }

    public void setDistinationID(int distinationID) {
        this.distinationID = distinationID;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getStartID() {
        return startID;
    }

    public void setStartID(int startID) {
        this.startID = startID;
    }
}

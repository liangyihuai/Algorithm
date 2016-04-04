package com.routesearch.route;

/**
 * Created by liangyh on 4/3/16.
 */
public class Edge {

    private int weight = 0;
    private int distinationID = -1;
    private int index = -1;

    public Edge(){}

    public Edge(int distinationID, int weight, int index) {
        this.weight = weight;
        this.distinationID = distinationID;
        this.index = index;
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
}

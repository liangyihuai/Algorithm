package com.huai.huawei;

/**
 * Created by liangyh on 4/3/16.
 */
public class Edge {

    private int weight;
    private int distinationID;

    public Edge(){}

    public Edge(int distinationID, int weight) {
        this.weight = weight;
        this.distinationID = distinationID;
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
}

package com.huai.graph.allway;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liangyh on 4/2/16.
 */
public class Vertex {

    private String label;
    private int id = -1;//表示该节点在图中的下标。也就是数组的下标。
    private int[] visiteds;

    public Vertex(int MAX){
        visiteds = new int[MAX];
        for (int i = 0; i < MAX; i++) {
            visiteds[i] = -1;
        }
    }

    public Vertex(String label, int MAX){
        this(MAX);
        this.label = label;
    }

    public Vertex(String label, int MAX, int id){
        this(label, MAX);
        this.label = label;
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVisited(int id){
        if(id < 0 || id >= visiteds.length)
            return false;
        int temp = visiteds[id];
        if(temp <= 0){
            return false;
        }else{
            return true;
        }
    }

    public void addVisiteds(int vertexID) {
        if(id < 0 || id >= visiteds.length)
            return ;
        this.visiteds[vertexID] = 1;
    }

    public void clearVisited(){
        for (int i = 0; i < visiteds.length; i++) {
            visiteds[i] = -1;
        }
    }
}

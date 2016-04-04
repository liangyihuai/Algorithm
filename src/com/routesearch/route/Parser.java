package com.routesearch.route;

import java.util.List;

/**
 * 解析输入文件
 * Created by liangyh on 4/2/16.
 */
public class Parser {

    /**
     * 关键节点
     */
    private int[] keyVertex = null;

    /**
     * 开始节点
     */
    private int startVertex = -1;

    /**
     * 目的节点
     */
    private int distinationVertex = -1;

    /**
     * 解析出起始定点、目的定点、关键定点。
     * @param condition
     */
    public void parseCondition(String condition){
        if(condition == null) return ;

        int firstIndex = condition.indexOf(',');
        if(firstIndex == -1)
            return ;

        int secondIndex = condition.indexOf(',', firstIndex+1);
        if(secondIndex == -1)
            return ;

        this.startVertex = Integer.valueOf(condition.substring(0, firstIndex));
        this.distinationVertex = Integer.valueOf(condition.substring(firstIndex+1, secondIndex));

        String commandVertex = condition.substring(secondIndex+1, condition.length()-1).trim();
        String [] vertexStr = commandVertex.split("\\|");

        keyVertex = new int[vertexStr.length];
        for (int i = 0; i < vertexStr.length; i++) {
            keyVertex[i] = Integer.valueOf(vertexStr[i]);
        }
    }

    /**
     *
     * @param graphContent
     * @param dya 二维数组
     * @return 最大的定点数
     */
    public int parseGraphContent(String graphContent, List<int[]> dya){
        if(graphContent == null || dya == null) {
            System.err.println("the graph content of dya is null !");
            return -1;
        }

        String[] pieces = graphContent.split("\n");

        int len = pieces.length;
        if(len <= 0)
            return -1;

        int maxVertex = 0;
        for (int i = 0; i < pieces.length; i++) {
            String[] temps = pieces[i].split(",");
            int [] nums = new int[4];
            nums[0] = Integer.valueOf(temps[0]);
            nums[1] = Integer.valueOf(temps[1]);
            nums[2] = Integer.valueOf(temps[2]);
            nums[3] = Integer.valueOf(temps[3]);

            dya.add(nums);

            if(nums[1] > maxVertex)
                maxVertex = nums[1];
            if(nums[2] > maxVertex)
                maxVertex = nums[2];
        }
        return maxVertex;
    }

    public int[] getKeyVertexSet() {
        return keyVertex;
    }

    public int getStartVertex() {
        return startVertex;
    }

    public int getDistinationVertex() {
        return distinationVertex;
    }
}

package com.huai.recursion;

import java.util.Stack;

/**
 * 迷宫问题。
 * Created by liangyh on 3/28/16.
 */
public class Maze {

    private static final char[][] dyadic = {
            {'+', '-', '-', '+'},
            {'|', '0', '0', '|'},
            {'|', '0', '0', '|'},
            {'|', '0', '0', '|'},
            {'+', '-', '-', '+'}};

    public void fill(Point startPoint){
        if(startPoint == null) return ;

        char c = dyadic[startPoint.x][startPoint.y];
        if(c == '+' || c == '|' || c == '-') return;

        Stack<Point> s = new Stack<>();

        s.push(startPoint);


        while(!s.isEmpty()){
            Point p = s.pop();

            //注意x和y的顺序
            dyadic[p.y][p.x] = '*';

            //left
            if(p.x > 0){
                int x = p.x-1;
                int y = p.y;
                char left = dyadic[y][x];
                if(left != '+' && left != '|' && left != '*'){
                    s.push(new Point(x, y));
                }
            }

            //right
            if(p.x < dyadic[0].length-1){
                int x = p.x+1;
                int y = p.y;
                char right = dyadic[y][x];
                if(right != '+' && right != '|' && right != '*'){
                    s.push(new Point(x, y));
                }
            }

            //up
            if(p.y > 0){
                int x = p.x;
                int y = p.y -1;
                char up = dyadic[y][x];
                if(up != '+' && up != '-' && up != '*'){
                    s.push(new Point(x, y));
                }
            }

            //down
            if(p.y < dyadic.length-1){
                int x = p.x;
                int y = p.y+1;
                char down = dyadic[y][x];
                if(down != '+' &&  down != '-' && down != '*'){
                    s.push(new Point(x, y));
                }
            }
        }
    }

    class Point{
        int x;
        int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

    }

    //do test
    public void doTest(){
        fill(new Point(2,2));

        //print
        for (int i = 0; i < dyadic.length; i++) {
            for (int j = 0; j < dyadic[0].length; j++) {
                System.out.print(dyadic[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Maze test = new Maze();
        test.doTest();
    }
}

package com.r2d2;

import java.util.ArrayList;
import java.util.List;

/**
 * A* 算法实现
 * Created by DiCaesar on 2017/9/11
 */
public class AStar {
    public static final int[][] NODES = {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 1, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 1, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 1, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 1, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    };

    public static final int STEP = 10;
    private List<Node> openList = new ArrayList<Node>();
    private List<Node> closeList = new ArrayList<Node>();

    public Node aStarSearch(Node start, Node end) {
        // 把起点加入 open list
        openList.add(start);
        //主循环，每一轮检查一个当前方格节点
        while (openList.size() > 0) {
            // 在OpenList中查找 F值最小的节点作为当前方格节点
            Node current = findMinNode();
            // 当前方格节点从open list中移除
            openList.remove(current);
            // 当前方格节点进入 close list
            closeList.add(current);
            // 找到所有邻近节点
            List<Node> neighbors = findNeighbors(current);
            for (Node node : neighbors) {
                if (!openList.contains(node)) {
                    //邻近节点不在OpenList中，标记父亲、G、H、F，并放入OpenList
                    markAndInvolve(current, end, node);
                }
            }
            //如果终点在OpenList中，直接返回终点格子
            if (find(openList, end) != null) {
                return find(openList, end);
            }
        }
        //OpenList用尽，仍然找不到终点，说明终点不可到达，返回空
        return null;
    }

    private Node findMinNode(){
        Node tmp = openList.get(0);
        for(int i=0;i<openList.size();i++){
            if(openList.get(i).H<tmp.H)
                tmp=openList.get(i);
        }
        return tmp;
    }

    private List<Node> findNeighbors(Node current){
        ArrayList<Node> neighbors = new ArrayList<Node>();
        Node up = current.nodeU();
        Node low = current.nodeLo();
        Node left = current.nodeLe();
        Node right = current.nodeR();
        if(checkArrive(up)){
            neighbors.add(up);
        }
        if(checkArrive(low)){
            neighbors.add(low);
        }
        if(checkArrive(left)){
            neighbors.add(left);
        }
        if(checkArrive(right)){
            neighbors.add(right);
        }
        return neighbors;
    }

    private void markAndInvolve(Node current,Node end,Node node){
        node.parent = current;
        openList.add(node);
    }

    private Node find(List<Node> openList,Node end){
        return end;
    }

    private int calcH(Node node, Node end) {
        int step = Math.abs(node.x - end.x) + Math.abs(node.y - end.y);
        return step * STEP;
    }

    public boolean checkArrive(Node node){
        if(node.x<0 || node.y<0){
            return false;
        }
        return true;
    }

    private static class Node{
        public int x,y;
        public int F,G,H;
        public Node parent;

        public Node(int x,int y){
            this.x=x;
            this.y=y;
        }

        //up
        public Node nodeU(){
            return new Node(this.x,this.y+1);
        }

        //low
        public Node nodeLo(){
            return new Node(this.x,this.y-1);
        }

        //left
        public Node nodeLe(){
            return new Node(this.x-1,this.y);
        }

        //right
        public Node nodeR(){
            return new Node(this.x+1,this.y);
        }

        public String toString(){
            return "X:"+String.valueOf(this.x) + " Y:"+Integer.toString(this.y);
        }

        public boolean equals(Object obj){
            if (this == obj) {
                return true;
            }
            if (obj instanceof Node) {
                Node node = (Node)obj;
                return this.x==node.x && this.y==node.y;
            }
            return false;
        }
    }

    public static void main(String a[]){
        AStar aStar=new AStar();
        Node n1=new Node(1,1);
        List<Node>  list= aStar.findNeighbors(n1);
        System.out.println(list);
    }
}

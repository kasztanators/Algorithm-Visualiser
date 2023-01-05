package com.company.pathFinding;
import java.util.ArrayList;
public class Node implements Comparable<Node>{
    private boolean visited, isStart, isEnd, isObstacle, isPath, openList;
    private double g=10e5, h = 10e5, t = g+h;
    private int row, col;

    private Node parent;
    private ArrayList<Node> edges = new ArrayList<>();

    public Node(int y, int x) {
        row = y;
        col = x;
    }
    public double getDistance(Node node) {
        return Math.sqrt((node.row - this.row)*(node.row - this.row) + (node.col - this.col)*(node.col - this.col));
    }
    public boolean isDiagonal(Node node) {
        if(node.row == this.row - 1 && node.col == this.col -1)
            return true;
        if(node.row == this.row - 1 && node.col == this.col +1)
            return true;
        if(node.row == this.row + 1 && node.col == this.col -1)
            return true;
        return node.row == this.row + 1 && node.col == this.col + 1;
    }
    public void getEdges(Node[][] nodes) {
        if(row - 1 >= 0 && col - 1 >= 0)
            edges.add(nodes[row-1][col-1]);
        if(row - 1 >= 0)
            edges.add(nodes[row-1][col]);
        if(row - 1 >= 0 && col + 1 < nodes[0].length)
            edges.add(nodes[row-1][col+1]);
        if(col - 1 >= 0)
            edges.add(nodes[row][col-1]);
        if(col + 1 < nodes[0].length)
            edges.add(nodes[row][col+1]);
        if(row + 1 < nodes.length && col - 1 >= 0)
            edges.add(nodes[row+1][col-1]);
        if(row + 1 < nodes.length)
            edges.add(nodes[row+1][col]);
        if(row + 1 < nodes.length && col + 1 < nodes[0].length)
            edges.add(nodes[row+1][col+1]);
    }
    @Override
    public int compareTo(Node node) {

        if (node.t > this.t)
            return -1;
        if (node.t < this.t)
            return 1;
        return 0;
    }
}

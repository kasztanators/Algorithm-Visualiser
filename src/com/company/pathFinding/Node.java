package com.company.pathFinding;
import java.awt.*;
import java.util.ArrayList;

public class Node implements Comparable<Node>{
    private boolean visited, isStart, isEnd, isObstacle, isPath, openList;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    private State state;
    private double g=10e5, h = 10e5, t = g+h;
    private int row, col;

    private Node parent;
    private ArrayList<Node> edges = new ArrayList<>();

    public Node(int y, int x) {
        row = y;
        col = x;
        state = State.OPEN;
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
    public boolean isNextTo(Node node) {
        int rowDiff = Math.abs(this.row - node.row);
        int colDiff = Math.abs(this.col - node.col);
        return (rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1) || (rowDiff == 1 && colDiff == 1);
    }
}

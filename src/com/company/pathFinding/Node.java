package com.company.pathFinding;
import java.util.ArrayList;

public class Node implements Comparable<Node>{
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    private State state;
    private double g=10e5, h = 10e5, t = g+h;
    private int row, col;

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public ArrayList<Node> getEdges() {
        return edges;
    }

    private Node parent;
    private ArrayList<Node> edges = new ArrayList<>();

    public Node(int y, int x) {
        row = y;
        col = x;
        state = State.OPEN;
    }
    public double getDistance(Node node) {
        int rowDiff = node.row - this.row;
        int colDiff = node.col - this.col;
        return Math.sqrt(rowDiff * rowDiff + colDiff * colDiff);
    }
    public boolean isDiagonal(Node node) {
        int rowDiff = Math.abs(node.row - this.row);
        int colDiff = Math.abs(node.col - this.col);
        return rowDiff == 1 && colDiff == 1;
    }
    public void getEdges(Node[][] nodes) {
        int rows = nodes.length;
        int cols = nodes[0].length;

        for (int rowDiff = -1; rowDiff <= 1; rowDiff++) {       // loops over all possible row and column differences (-1, 0, 1)
            for (int colDiff = -1; colDiff <= 1; colDiff++) {
                int row = this.row + rowDiff;
                int col = this.col + colDiff;

                if (row >= 0 && row < rows && col >= 0 && col < cols) {
                    edges.add(nodes[row][col]);    //adds edges to corresponding node
                }
            }
        }
    }
    @Override
    public int compareTo(Node node) {
        if (node.t > this.t) {
            return -1;
        }
        if (node.t < this.t) {
            return 1;
        }
        return 0;
    }
    public boolean isNextTo(Node node) {
        int rowDiff = Math.abs(this.row - node.row);
        int colDiff = Math.abs(this.col - node.col);
        return (rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1) || (rowDiff == 1 && colDiff == 1);
    }
}

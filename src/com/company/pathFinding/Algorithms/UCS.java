package com.company.pathFinding.Algorithms;

import com.company.pathFinding.Board;
import com.company.pathFinding.Node;
import com.company.pathFinding.State;

import javax.swing.*;
import java.util.PriorityQueue;

public class UCS {
    private final PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
    private Timer timer;
    public void findPath(Node start, Node end) {
        start.setG(0);
        start.setT(start.getG());
        priorityQueue.add(start);

        timer = new Timer(1, e -> {

            if (!priorityQueue.isEmpty()) {
                Node current = priorityQueue.poll();
                if (current.equals(end)) {
                    Board.getInstance().setGameStart(true);
                    backtrack(current);
                    timer.stop();
                }
                if (current.isVisited()) {
                    current.setVisited(true);
                    if(current.getState()!= State.START && current.getState()!= State.END )
                        current.setState(State.VISITED);
                    for (int i = 0; i < current.getEdges().size(); i++) {
                        Node edge = current.getEdges().get(i);
                        if (edge.getState() !=State.VISITED && edge.getState() !=State.OBSTACLE) {
                            double newCost;
                            if (current.isDiagonal(edge))
                                newCost = current.getG() + 1.4;
                            else
                                newCost = current.getG() + 1;
                            if (newCost < edge.getG()) {
                                edge.setG(newCost);
                                edge.setT(edge.getG());
                                edge.setParent(current);
                                priorityQueue.add(edge);
                                if (edge.getState()!= State.END) {
                                    edge.setState(State.OPEN);
                                }
                            }
                        }
                    }
                    Board.getInstance().repaint();
                }

            }
        });
        timer.start();
    }
    private void backtrack(Node end) {
        Node current = end.getParent();
        while (current.getState() != State.START) {
            current.setState(State.PATH);
            current = current.getParent();
        }
    }
}

package com.company.pathFinding.Algorithms;

import com.company.pathFinding.Board;
import com.company.pathFinding.Node;
import com.company.pathFinding.State;


import java.util.PriorityQueue;

public class UCS {
    private final PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
    public void findPath(Node start, Node end) {
        start.setG(0);
        start.setT(start.getG());
        priorityQueue.add(start);
        Board.getInstance().setGameStart(true);
        Thread pathThread = new Thread(() -> {
            while (!priorityQueue.isEmpty()) {
                Node current = priorityQueue.poll();
                if (current.equals(end)) {
                    pathFound(current);
                    break;
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
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Board.getInstance().repaint();
                }

            }
        });
        pathThread.start();
    }
    private void pathFound(Node finish) {

            Node current = finish.getParent();
            while (current.getState() != State.START) {
                current.setState(State.PATH);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Board.getInstance().repaint();
                current = current.getParent();
            }

    }
}

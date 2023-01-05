package com.company.pathFinding;

import com.company.algorithms.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Board extends JPanel implements MouseListener, MouseMotionListener {
    private int gridSize = 15;
    private static int gridNum= 40;
    private Node[][] nodes = new Node[gridNum][gridNum];
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private boolean isSetStart, isSetFinish, gameStart= false;
    private Node start;
    private Node finish;
    public Board(){
        setPreferredSize(new Dimension(HEIGHT, HEIGHT));
        setBackground(Color.GRAY);
        setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);

        for (int i = 0; i < gridNum; i++) {
            for (int j = 0; j < gridNum; j++) {
                nodes[i][j] = new Node(i,j);
            }
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(Color.GRAY);
        int x, y;
        for(int i = 0 ; i<gridNum; i++){
            for (int j = 0; j <gridNum ; j++) {
                y = i *gridSize;
                x = j *gridSize;
                State state = nodes[i][j].getState();
                if(state == State.OPEN) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x, y, gridSize, gridSize);
                }
                else if (state == State.START){
                    g.setColor(Color.BLUE);
                    g.fillRect(x, y, gridSize, gridSize);
                }
                else if (state == State.END){
                    g.setColor(Color.GREEN);
                    g.fillRect(x, y, gridSize, gridSize);
                }
                else if (state == State.OBSTACLE){
                    g.setColor(Color.GRAY);
                    g.fillRect(x, y, gridSize, gridSize);
                }
            }
        }
    }
    public void draw(){
        Thread sortThread = new Thread(() -> {
        repaint();
        });
        sortThread.start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX()/gridSize;
        int y = e.getY()/gridSize;
        if(!gameStart && e.getButton()==1&&  y >= 0 && y < nodes.length && x >= 0 && x < nodes[0].length){
            if (!isSetStart) {
                start = this.nodes[y][x];
                start.setState(State.START);
                isSetStart = true;
            } else if (!isSetFinish && this.nodes[y][x].getState() != State.START && !this.start.isNextTo(this.nodes[y][x])) {
                finish = this.nodes[y][x];
                this.finish.setState(State.END);
                isSetFinish = true;
            }
        }
        repaint();
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        int y = e.getY() /gridSize;
        int x = e.getX() / gridSize;
        if (!isSetStart) {
            start = nodes[y][x];
            start.setState(State.START);
            isSetStart = true;
        } else if (!isSetFinish && nodes[y][x].getState() != State.START && !start.isNextTo(this.nodes[y][x])) {
            finish = nodes[y][x];
            finish.setState(State.END);
            isSetFinish = true;
        }

        if (isSetStart && isSetFinish && this.nodes[y][x].getState() != State.START && this.nodes[y][x].getState() != State.END && this.nodes[y][x].getState() != State.OBSTACLE && !gameStart) {
            this.nodes[y][x].setState(State.OBSTACLE);
        }
        this.repaint();
    }
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }



    @Override
    public void mouseMoved(MouseEvent e) {

    }
}

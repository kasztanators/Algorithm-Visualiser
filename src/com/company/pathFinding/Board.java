package com.company.pathFinding;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Board extends JPanel implements MouseListener, MouseMotionListener {
    private final int gridSize = 15;
    private static final int gridNum= 40;
    private Node[][] nodes = new Node[gridNum][gridNum];
    private static final int SCREEN_SIZE = 600;
    private boolean isSetStart, isSetFinish, gameStart= false;
    private Node start;
    private Node finish;
    public Board(){
        setPreferredSize(new Dimension(SCREEN_SIZE, SCREEN_SIZE));
        setBackground(Color.GRAY);
        setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);
//      //  JButton button1 = new JButton("A Star");
//        JButton button2 = new JButton("Greedy");
//        JButton button3 = new JButton("UCS");
//        //this.add(button1);
//        JButton button1 = new JButton("A Star");
//    //    this.setLayout(new FlowLayout());
//       // button1.setBounds(600, 50, 100, 30); // x, y, width, height
//        button1.setLocation(600, 50); // x, y
//        this.add(button1);

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
                g.setColor(state.getColor());
                g.fillRect(x, y, gridSize-1, gridSize-1);
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
        checkMouseInBoard(e);
        {
            int x = e.getX() / gridSize;
            int y = e.getY() / gridSize;
            if (!gameStart && e.getButton() == 1 && y >= 0 && y < nodes.length && x >= 0 && x < nodes[0].length) {
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
    }
    private boolean checkMouseInBoard(MouseEvent e){
        int x = e.getX();
        int y = e.getY();
        if(x<0 || x>SCREEN_SIZE-5)return false;
        return y >= 0 && y <= SCREEN_SIZE - 5;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if(checkMouseInBoard(e)) {
            int y = e.getY() / gridSize;
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

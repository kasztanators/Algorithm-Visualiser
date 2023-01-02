package com.company.pathFinding;

import javax.swing.*;
import java.awt.*;

public class PathFinding extends JPanel {
    private int gridSize;
    private int gridNum;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    public PathFinding(){
        this.gridSize = 20;
        this.gridNum = this.HEIGHT / gridSize;
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(Color.GRAY);
        for(int i = 0 ; i<this.gridNum; i++){
            g.drawLine(0,i*this.gridSize, WIDTH, i*this.gridSize);
            g.drawLine(i*this.gridSize, 0, i*gridSize, WIDTH);
        }
    }
    public void draw(){
        repaint();
    }
}

package com.company.pathFinding;

import com.company.algorithms.Settings;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    private int gridSize;
    private int gridNum;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    public Board(){
        this.gridSize = 15;
        this.gridNum = this.WIDTH / gridSize;
        setPreferredSize(new Dimension(Settings.HEIGHT, Settings.HEIGHT));
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(Color.GRAY);
        for(int i = 0 ; i<this.gridNum; i++){
            g.drawLine(0,i*this.gridSize, WIDTH, i*this.gridSize);
            g.drawLine(i*this.gridSize, 0, i*gridSize, HEIGHT);
        }
    }
    public void draw(){
        Thread sortThread = new Thread(() -> {
        repaint();
        });
        sortThread.start();
    }
}

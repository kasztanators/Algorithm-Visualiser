package com.company.algorithms;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public abstract class Settings extends JPanel {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int ANIMATION_SPEED =10;
    public void setSortedIndex(int sortedIndex) {
        this.sortedIndex = sortedIndex;
    }

    private int sortedIndex=0;
    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }
    private int [] data;
    public int getBarWidth() {
        return barWidth;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Settings.BACKGROUND_COLOR);
        for (int i = 0; i < data.length; i++) {
            int barHeight = data[i] * Settings.MAX_BAR_HEIGHT / this.getNumBars();
            if(i<sortedIndex){
                g.setColor(this.SORTED_COLOR);
            }
            else{
                g.setColor(this.UNSORTED_COLOR);
            }

            g.fillRect(i * this.getBarWidth(), this.HEIGHT - barHeight, this.getBarWidth() - 1, barHeight);
        }
    }
    public void setBarWidth(int barWidth) {
        this.barWidth = barWidth;
    }

    public int getNumBars() {
        return numBars;
    }

    public void setNumBars(int numBars) {
        this.numBars = numBars;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    private int speed = 1;
    private int barWidth = 5;
    private int numBars = WIDTH / barWidth;
    public static final int MAX_BAR_HEIGHT = HEIGHT - 20;
    public static final Color BACKGROUND_COLOR = Color.BLACK;
    public static final Color SORTED_COLOR = Color.GREEN;
    public static final Color UNSORTED_COLOR = Color.RED;
    public int[] generateRandomData() {
        Random rng = new Random();
        int[] data = new int[this.getNumBars()];
        for (int i = 0; i < this.getNumBars(); i++) {
            data[i] = rng.nextInt(1, this.getNumBars());
        }
        return data;
    }
}



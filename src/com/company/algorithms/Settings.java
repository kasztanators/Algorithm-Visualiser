package com.company.algorithms;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class Settings extends JPanel {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public int getAnimationSpeed() {
        return animationSpeed;
    }

    public void setAnimationSpeed(int animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    private int animationSpeed =10;
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
                g.setColor(SORTED_COLOR);
            }
            else{
                g.setColor(new Color(125,255-data[i],255));
            }

            g.fillRect(i * this.getBarWidth(), HEIGHT - barHeight, this.getBarWidth() - 1, barHeight);
        }
    }

    public int getNumBars() {
        return WIDTH / barWidth;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    void delay(){
        try {
            TimeUnit.MILLISECONDS.sleep(getSpeed());
        } catch (InterruptedException e) {
            System.out.println("sleep_error");
        }
    }
    void finalRepaint(){
        setSpeed(getAnimationSpeed());
        for(int i =0; i< getData().length; i++){
            repaint();
            setSortedIndex(i+1);
            this.delay();
        }
    }
    private int speed = 1;
    private final int barWidth = 5;
    public static final int MAX_BAR_HEIGHT = HEIGHT - 20;
    public static final Color BACKGROUND_COLOR = Color.BLACK;
    public static final Color SORTED_COLOR = Color.GREEN;

    public int[] generateRandomData() {
        Random rng = new Random();
        int[] data = new int[this.getNumBars()];
        for (int i = 0; i < this.getNumBars(); i++) {
            data[i] = rng.nextInt(1, this.getNumBars());
        }
        return data;
    }
}



package com.company.algorithms;

import java.awt.*;
import java.util.Random;

public class Settings {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public int getBarWidth() {
        return barWidth;
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
    protected void repaint(){

    }
}



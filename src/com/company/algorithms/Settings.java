package com.company.algorithms;

import java.awt.*;

public class Settings {
    private static final int width = 800;
    private static final int HEIGHT = 600;

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
    private int numBars = width / barWidth;
    public static final int MAX_BAR_HEIGHT = HEIGHT - 20;
    public static final Color BACKGROUND_COLOR = Color.BLACK;
    public static final Color SORTED_COLOR = Color.GREEN;
    public static final Color UNSORTED_COLOR = Color.RED;

}



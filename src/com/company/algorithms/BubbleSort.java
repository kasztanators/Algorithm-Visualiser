package com.company.algorithms;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class BubbleSort extends JPanel {
    private int [] data;
    private Settings settings;
    public BubbleSort(int [] data, Settings settings){
        this.data = data.clone();
        this.settings = settings;
    }

    private void swap(int i, int j){
        int tmp = this.data[i];
        this.data[i]= this.data[j];
        this.data[j] = tmp;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(this.settings.BACKGROUND_COLOR);
        for (int i = 0; i < data.length; i++) {
            int barHeight = data[i] * this.settings.MAX_BAR_HEIGHT / this.settings.getNumBars();
            g.setColor(this.settings.UNSORTED_COLOR);
            g.fillRect(i * this.settings.getBarWidth(), HEIGHT - barHeight, this.settings.getBarWidth()-1, barHeight);
        }
    }
    public int [] sort(){
        if (this.data == null)
            return null;
        for(int i = 0; i <this.data.length; i++){
            for(int j =i +1; j <this.data.length; j++){
                if (this.data[j]<this.data[i]){
                    swap(i,j);
                    repaint();
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        // Do nothing
                    }
                }
            }
        }
        return this.data;
    }

}

package com.company.algorithms;


import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class InsertionSort extends JPanel {
    private int [] data;
    private Settings settings;
    private boolean isSorted;
    public InsertionSort(Settings settings) {
        updateSettings(settings);
        setPreferredSize(new Dimension(this.settings.WIDTH, this.settings.HEIGHT));
        isSorted = false;
        data = settings.generateRandomData();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(this.settings.BACKGROUND_COLOR);
        for (int i = 0; i < data.length; i++) {
            int barHeight = data[i] * this.settings.MAX_BAR_HEIGHT / this.settings.getNumBars();
            if(!isSorted) {
                g.setColor(this.settings.UNSORTED_COLOR);
            }
            else{
                g.setColor(this.settings.SORTED_COLOR);
            }
            g.fillRect(i * this.settings.getBarWidth(), this.settings.HEIGHT - barHeight, this.settings.getBarWidth()-1, barHeight);
        }
    }
    public void sort(){
        // a loop for finding a small item and then swapping it with other bigger items
        Thread sortThread = new Thread(() -> {
        for (int i = 1; i < this.data.length; i++) {
            int j = i;
            while (j > 0 && this.data[j] < this.data[j - 1]){
                //swap item j-1 and j
                swap(j - 1,j);
                j--;
                repaint();
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    // Do nothing
                }
            }
        }
            isSorted = true;
            repaint();
        });
        sortThread.start();
    }
    private void swap(int i, int j){ // swap item i and j in the array
        int tmp = this.data[i];
        this.data[i] = this.data[j];
        this.data[j] = tmp;
        repaint();
    }
    public void updateSettings(Settings settings){
        this.settings = settings;
    }
}

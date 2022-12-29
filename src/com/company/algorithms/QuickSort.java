package com.company.algorithms;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class QuickSort extends JPanel {
    private int [] data;

    private static boolean isSorted;
    private Settings settings;
    public QuickSort(Settings settings){
        updateSettings(settings);
        setPreferredSize(new Dimension(this.settings.WIDTH, this.settings.HEIGHT));
        this.data = settings.generateRandomData();
    }
    public void updateSettings(Settings settings){
        this.settings = settings;
    }
    private void quickSort(int left, int right){ // put pivot in the right place and 
        //do the same for the left part and right part
        if (left < right){
            int pivot = partition(left, right);
            quickSort(left, pivot - 1);
            quickSort(pivot + 1, right);
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(this.settings.BACKGROUND_COLOR);
        for (int i = 0; i < data.length; i++) {
            int barHeight = data[i] * this.settings.MAX_BAR_HEIGHT / this.settings.getNumBars();
            if(!isSorted) {g.setColor(this.settings.UNSORTED_COLOR);}
            else g.setColor(this.settings.SORTED_COLOR);
            g.fillRect(i * this.settings.getBarWidth(), HEIGHT - barHeight, this.settings.getBarWidth()-1, barHeight);
        }
    }
    private int partition(int left, int right){ // select a pivot index, put 
        //items less than the pivot value before the pivot index, and put items 
        //greater than pivot value after the pivot index
        int pivot =  ((int) (Math.random() * (right - left)) + left);
        int pivot_value = this.data[pivot];
        swap(pivot, right);
        int lastIndex = left;
        for (int i = left; i < right; i++) {
            if (this.data[i] < pivot_value){
                swap(lastIndex, i);
                repaint();
                lastIndex++;
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    // Do nothing
                }
            }
        }
        swap(lastIndex, right);
        repaint();
        return lastIndex;
    }
    private void swap(int i, int j){ // swap item i and j in the array
        int tmp = this.data[i];
        this.data[i] = this.data[j];
        this.data[j] = tmp;
    }

    public int [] sort(){
        repaint();
        quickSort(0, this.data.length - 1);
        isSorted = true;
        repaint();
        return this.data;
    }
}

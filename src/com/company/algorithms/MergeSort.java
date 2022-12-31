package com.company.algorithms;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class MergeSort extends JPanel {
    private int [] data;
    private boolean isSorted;
    private Settings settings;

    public MergeSort(Settings settings) {
        updateSettings(settings);
        setPreferredSize(new Dimension(this.settings.WIDTH, this.settings.HEIGHT));
        data = settings.generateRandomData();
        isSorted = false;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(this.settings.BACKGROUND_COLOR);
        for (int i = 0; i < data.length; i++) {

            int barHeight = data[i] * this.settings.MAX_BAR_HEIGHT / this.settings.getNumBars();
            if(!isSorted) {g.setColor(this.settings.UNSORTED_COLOR);}
            else g.setColor(this.settings.SORTED_COLOR);
            g.fillRect(i * this.settings.getBarWidth(), this.settings.HEIGHT - barHeight, this.settings.getBarWidth()-1, barHeight);
        }
    }
    private int [] merge(int left [], int right[]){
        // sort items in two arrays in a single array (merge two arrays in a sorted array)

        int [] ret = new int [right.length + left.length];
        int j = 0;
        int rightIndex = 0;
        int leftIndex = 0;
        while (j < ret.length){
            if (rightIndex < right.length && leftIndex < left.length){
                if (right[rightIndex] < left[leftIndex]){
                    ret[j] = right[rightIndex];
                    rightIndex++;
                }else {
                    ret[j] = left[leftIndex];
                    leftIndex++;
                }
            }else if (rightIndex < right.length){
                ret[j] = right[rightIndex];
                rightIndex++;
            }else {
                ret[j] = left[leftIndex];
                leftIndex++;
            }
            j++;
        }
        return ret;
    }

    public void sort(){
        Thread sortThread = new Thread(() -> {

            if (this.data != null)
           mergeSort(this.data, this.data.length);
        isSorted = true;
    });
        sortThread.start();
    }

    private int [] mergeSort(int [] data, int p){
        if (data.length == 1){ // return if the array has only one item
            return data;
        }

        return merge( // merge left part and right part
                //left part
                mergeSort(copyOfRange(data, 0, p/2), p/2),
                //right part
                mergeSort(copyOfRange(data, p/2, data.length), data.length - p/2)
        );
    }
    private int [] copyOfRange(int [] data, int i, int j){ // copy a range of an array into a new array
        int range [] = new int [j-i];
        for (int k = i; k < j; k++) {
            range [k-i] = data[k];
            repaint();
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                // Do nothing
            }
        }
        return range;
    }
    public void updateSettings(Settings settings){
        this.settings = settings;
    }
}

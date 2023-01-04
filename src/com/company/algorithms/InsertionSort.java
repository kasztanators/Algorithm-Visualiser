package com.company.algorithms;



import java.awt.*;
import java.util.concurrent.TimeUnit;

public class InsertionSort extends Settings {

    private Settings settings;

    public InsertionSort() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setData(generateRandomData());
        this.setSpeed(1);
    }
    public void sort(){
        // a loop for finding a small item and then swapping it with other bigger items
        Thread sortThread = new Thread(() -> {
        for (int i = 1; i < this.getData().length; i++) {
            int j = i;
            while (j > 0 && this.getData()[j] < this.getData()[j - 1]){
                //swap item j-1 and j
                swap(j - 1,j);
                j--;
                repaint();
                try {
                    TimeUnit.MILLISECONDS.sleep(getSpeed());
                } catch (InterruptedException e) {
                    // Do nothing
                }
            }
        }
            for(int i =0; i< getData().length; i++){
                repaint();
                setSortedIndex(i+1);
                try {
                    TimeUnit.MILLISECONDS.sleep(ANIMATION_SPEED);
                } catch (InterruptedException e) {
                    // Do nothing
                }
            }
        });
        sortThread.start();
    }
    private void swap(int i, int j){ // swap item i and j in the array
        int tmp = this.getData()[i];
        this.getData()[i] = this.getData()[j];
        this.getData()[j] = tmp;
        repaint();
    }
    public void updateSettings(Settings settings){
        this.settings = settings;
    }
}

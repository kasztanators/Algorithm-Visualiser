package com.company.algorithms;



import java.awt.*;
import java.util.concurrent.TimeUnit;

public class InsertionSort extends Settings {
    public InsertionSort() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setData(generateRandomData());
        this.setSpeed(1);
    }
    public void sort(){
        Thread sortThread = new Thread(() -> {
        for (int i = 1; i < this.getData().length; i++) {
            int j = i;
            while (j > 0 && this.getData()[j] < this.getData()[j - 1]){
                swap(j - 1,j);
                j--;
                repaint();
                try {
                    TimeUnit.MILLISECONDS.sleep(getSpeed());
                } catch (InterruptedException ignored) {

                }
            }
        }
            for(int i =0; i< getData().length; i++){
                repaint();
                setSortedIndex(i+1);
                try {
                    TimeUnit.MILLISECONDS.sleep(getAnimationSpeed());
                } catch (InterruptedException e) {
                    // Do nothing
                }
            }
        });
        sortThread.start();
    }
    private void swap(int i, int j){
        int tmp = this.getData()[i];
        this.getData()[i] = this.getData()[j];
        this.getData()[j] = tmp;
        repaint();
    }
}

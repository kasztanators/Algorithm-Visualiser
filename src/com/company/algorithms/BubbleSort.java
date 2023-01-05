package com.company.algorithms;

import java.awt.Dimension;

import java.util.concurrent.TimeUnit;

public class BubbleSort extends Settings {
    public BubbleSort() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setData(generateRandomData());
        this.setSpeed(1);
    }
    public void sort() {
        Thread sortThread = new Thread(() -> {
            for (int i = 0; i < getData().length; i++) {
                for (int j = i + 1; j < getData().length; j++) {
                    if (getData()[j] < getData()[i]) {
                        swap(i, j);

                        try {
                            TimeUnit.MILLISECONDS.sleep(getSpeed());
                        } catch (InterruptedException e) {
                            // Do nothing
                        }
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
    private void swap(int i, int j) {
        int temp = getData()[i];
        getData()[i] = getData()[j];
        getData()[j] = temp;
        repaint();
    }
}
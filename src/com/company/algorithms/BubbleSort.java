package com.company.algorithms;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.concurrent.TimeUnit;

public class BubbleSort extends Settings {

    private Settings settings;


    public BubbleSort() {
        setPreferredSize(new Dimension(this.settings.WIDTH, this.settings.HEIGHT));
        this.setData(generateRandomData());
        this.setSpeed(1);
    }
    public void updateSettings(Settings settings){
        this.settings = settings;
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
                    TimeUnit.MILLISECONDS.sleep(ANIMATION_SPEED);
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
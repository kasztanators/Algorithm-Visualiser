package com.company.algorithms;

import java.awt.Dimension;

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
                        delay();
                    }
                }
            }
            finalRepaint();

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
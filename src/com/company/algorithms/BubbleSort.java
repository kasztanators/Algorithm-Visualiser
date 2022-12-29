package com.company.algorithms;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.concurrent.TimeUnit;
import javax.swing.JPanel;
public class BubbleSort extends JPanel {
    private static boolean isSorted;
    private Settings settings;
    private int[] data;

    public BubbleSort(Settings settings) {
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
            if(!isSorted) {
                g.setColor(this.settings.UNSORTED_COLOR);
            }
            else{
                g.setColor(this.settings.SORTED_COLOR);
            }
            g.fillRect(i * this.settings.getBarWidth(), this.settings.HEIGHT - barHeight, this.settings.getBarWidth()-1, barHeight);
        }
    }
    public void updateSettings(Settings settings){
        this.settings = settings;
    }
    public void sort() {
        Thread sortThread = new Thread(() -> {
            for (int i = 0; i < data.length; i++) {
                for (int j = i + 1; j < data.length; j++) {
                    if (data[j] < data[i]) {
                        swap(i, j);

                        try {
                            TimeUnit.MILLISECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            // Do nothing
                        }
                    }
                }
            }
            isSorted = true;
            repaint();
        });
        sortThread.start();
    }
    private void swap(int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
        repaint();
    }
}
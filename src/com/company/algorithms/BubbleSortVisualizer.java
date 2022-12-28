package com.company.algorithms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.swing.JPanel;

public class BubbleSortVisualizer extends JPanel {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int BAR_WIDTH = 5;
    private static final int NUM_BARS = WIDTH / BAR_WIDTH;
    private static final int MAX_BAR_HEIGHT = HEIGHT - 20;
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color SORTED_COLOR = Color.GREEN;
    private static final Color UNSORTED_COLOR = Color.RED;
    private static boolean isSorted;
    private Settings settings;
    private int[] data;

    public BubbleSortVisualizer(Settings settings) {
        this.settings = settings;
        setPreferredSize(new Dimension(settings.WIDTH, settings.HEIGHT));
        data = generateRandomData();
        isSorted = false;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(BACKGROUND_COLOR);
        for (int i = 0; i < data.length; i++) {
            int barHeight = data[i] * MAX_BAR_HEIGHT / NUM_BARS;
            if(!isSorted) {
                g.setColor(UNSORTED_COLOR);
            }
            else{
                g.setColor(SORTED_COLOR);
            }
            g.fillRect(i * BAR_WIDTH, HEIGHT - barHeight, BAR_WIDTH-1, barHeight);
        }
    }

    public void sort() {
        Thread sortThread = new Thread(() -> {
            for (int i = 0; i < data.length; i++) {
                for (int j = i + 1; j < data.length; j++) {
                    if (data[j] < data[i]) {
                        swap(i, j);
                        repaint();
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
    }

    private int[] generateRandomData() {
        Random rng = new Random();
        int[] data = new int[NUM_BARS];
        for (int i = 0; i < NUM_BARS; i++) {
            data[i] = rng.nextInt(NUM_BARS);
        }
        return data;
    }

    public static class Settings {
        private static final int WIDTH = 800;
        private static final int HEIGHT = 600;

        public int getBAR_WIDTH() {
            return BAR_WIDTH;
        }



    }
}
package com.company.algorithms;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;

import java.util.Stack;
import java.util.concurrent.TimeUnit;

public class QuickSort extends JPanel {
    private int [] data;
    private boolean isSorted;
    private Settings settings;

    public QuickSort(Settings settings){
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
    public void updateSettings(Settings settings){
        this.settings = settings;
    }
    public void sort() {
        Thread sortThread = new Thread(() -> {
        Stack<Integer> stack = new Stack<>();
        // Push the initial start and end indices onto the stack
        stack.push(0);
        stack.push(this.data.length - 1);

        while (!stack.isEmpty()) {
            // Pop the end index off the stack
            int end = stack.pop();
            // Pop the start index off the stack
            int start = stack.pop();

            // Choose the pivot element
            int pivotIndex = partition( start, end);

            // If there are elements on the left of the pivot, push them onto the stack
            if (pivotIndex - 1 > start) {
                stack.push(start);
                stack.push(pivotIndex - 1);
            }

            // If there are elements on the right of the pivot, push them onto the stack
            if (pivotIndex + 1 < end) {
                stack.push(pivotIndex + 1);
                stack.push(end);
            }
        }
            isSorted = true;
        });
        sortThread.start();
    }

    private int partition(int start, int end) {
        // Choose the pivot element
        int pivot = this.data[end];

        // Initialize the left and right indices
        int left = start;
        int right = end - 1;

        while (left <= right) {
            // Find the first element that is greater than or equal to the pivot
            while (left <= right && this.data[left] < pivot) {
                left++;
            }

            // Find the first element that is less than or equal to the pivot
            while (left <= right && this.data[right] > pivot) {
                right--;
            }

            // Swap the elements if they are in the wrong partition
            if (left <= right) {
                int temp = this.data[left];
                this.data[left] = this.data[right];
                this.data[right] = temp;
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    // Do nothing
                }
                left++;
                right--;
                repaint();
            }
        }

        // Swap the pivot element into its correct position
        int temp = this.data[left];
        this.data[left] = this.data[end];
        this.data[end] = temp;
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            // Do nothing
        }
        repaint();
        return left;
    }
}

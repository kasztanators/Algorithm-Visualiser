package com.company.algorithms;

import java.awt.Dimension;

import java.util.Stack;
import java.util.concurrent.TimeUnit;

public class QuickSort extends Settings {

    public QuickSort(){

        setPreferredSize(new Dimension(Settings.WIDTH, Settings.HEIGHT));
        this.setData(generateRandomData());
        this.setSpeed(10);
    }

    public void sort() {
        Thread sortThread = new Thread(() -> {
        Stack<Integer> stack = new Stack<>();
        // Push the initial start and end indices onto the stack
        stack.push(0);
        stack.push(this.getData().length - 1);

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

    private int partition(int start, int end) {
        // Choose the pivot element
        int pivot = this.getData()[end];

        // Initialize the left and right indices
        int left = start;
        int right = end - 1;

        while (left <= right) {
            // Find the first element that is greater than or equal to the pivot
            while (left <= right && this.getData()[left] < pivot) {
                left++;
            }

            // Find the first element that is less than or equal to the pivot
            while (left <= right && this.getData()[right] > pivot) {
                right--;
            }

            // Swap the elements if they are in the wrong partition
            if (left <= right) {
                int temp = this.getData()[left];
                this.getData()[left] = this.getData()[right];
                this.getData()[right] = temp;
                try {
                    TimeUnit.MILLISECONDS.sleep(getSpeed());
                } catch (InterruptedException e) {
                    // Do nothing
                }
                left++;
                right--;
                repaint();
            }
        }
        // Swap the pivot element into its correct position
        int temp = this.getData()[left];
        this.getData()[left] = this.getData()[end];
        this.getData()[end] = temp;
        try {
            TimeUnit.MILLISECONDS.sleep(getSpeed());
        } catch (InterruptedException e) {
            // Do nothing
        }
        repaint();
        return left;
    }
}

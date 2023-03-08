package com.company.algorithms;

import java.awt.Dimension;

import java.util.Stack;

public class QuickSort extends Settings {

    public QuickSort(){
        setPreferredSize(new Dimension(Settings.WIDTH, Settings.HEIGHT));
        this.setData(generateRandomData());
        this.setSpeed(10);
    }

    public void sort() {
        Thread sortThread = new Thread(() -> {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(this.getData().length - 1);

        while (!stack.isEmpty()) {
            int end = stack.pop();
            int start = stack.pop();

            int pivotIndex = partition( start, end);

            if (pivotIndex - 1 > start) {
                stack.push(start);
                stack.push(pivotIndex - 1);
            }

            if (pivotIndex + 1 < end) {
                stack.push(pivotIndex + 1);
                stack.push(end);
            }
        }
        finalRepaint();
        });
        sortThread.start();
    }

    private int partition(int start, int end) {
        int pivot = this.getData()[end];

        int left = start;
        int right = end - 1;

        while (left <= right) {
            while (left <= right && this.getData()[left] < pivot) {
                left++;
            }

            while (left <= right && this.getData()[right] > pivot) {
                right--;
            }

            if (left <= right) {
                int temp = this.getData()[left];
                this.getData()[left] = this.getData()[right];
                this.getData()[right] = temp;
                this.delay();
                left++;
                right--;
                repaint();
            }
        }
        int temp = this.getData()[left];
        this.getData()[left] = this.getData()[end];
        this.getData()[end] = temp;
        this.delay();
        repaint();
        return left;
    }
}

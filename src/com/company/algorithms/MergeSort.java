package com.company.algorithms;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class MergeSort extends JPanel {
    private int [] data;
    private boolean isSorted;
    private Settings settings;

    public MergeSort(Settings settings) {
        updateSettings(settings);
        setPreferredSize(new Dimension(Settings.WIDTH, Settings.HEIGHT));
        data = settings.generateRandomData();
        isSorted = false;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Settings.BACKGROUND_COLOR);
        for (int i = 0; i < data.length; i++) {

            int barHeight = data[i] * Settings.MAX_BAR_HEIGHT / this.settings.getNumBars();
            if(!isSorted) {g.setColor(this.settings.UNSORTED_COLOR);}
            else g.setColor(this.settings.SORTED_COLOR);
            g.fillRect(i * this.settings.getBarWidth(), this.settings.HEIGHT - barHeight, this.settings.getBarWidth()-1, barHeight);
        }


    }
    public void sort() {

        Thread sortThread = new Thread(() -> {
        int array [] = this.data;
        sort(array, 0, array.length - 1);
            isSorted = true;
            repaint();
        });
        sortThread.start();
    }

    void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];

        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
            repaint();
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                // Do nothing
            }
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
            repaint();
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                // Do nothing
            }
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
            repaint();
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                // Do nothing
            }
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    void sort(int arr[], int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }
    public void updateSettings(Settings settings){
        this.settings = settings;
    }
}

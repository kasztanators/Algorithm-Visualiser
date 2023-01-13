package com.company.algorithms;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class MergeSort extends Settings {

    public MergeSort() {
        setPreferredSize(new Dimension(Settings.WIDTH, Settings.HEIGHT));
        this.setData(generateRandomData());
        this.setSpeed(5);
    }

    public void sort() {

        Thread sortThread = new Thread(() -> {
        int[] array = this.getData();
        sort(array, 0, array.length - 1);
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

    void merge(int[] arr, int l, int m, int r)
    {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];


        System.arraycopy(arr, l, L, 0, n1);

        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;

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
                TimeUnit.MILLISECONDS.sleep(getSpeed());
            } catch (InterruptedException e) {
                // Do nothing
            }
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
            repaint();
            try {
                TimeUnit.MILLISECONDS.sleep(getSpeed());
            } catch (InterruptedException e) {
                // Do nothing
            }
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
            repaint();
            try {
                TimeUnit.MILLISECONDS.sleep(getSpeed());
            } catch (InterruptedException e) {
                // Do nothing
            }
        }
    }

    void sort(int[] arr, int l, int r)
    {
        if (l < r) {
            int m = l + (r - l) / 2;
            sort(arr, l, m);
            sort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }
}

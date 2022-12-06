package com.company;

import java.util.Random;

public class Sorting {
    private int[] arr = new int[50];
    public Sorting() {
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(100);
        }
    }
}

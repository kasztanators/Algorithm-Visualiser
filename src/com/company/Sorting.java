package com.company;

import java.util.Random;

public class Sorting {
    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    private int[] arr = new int[50];

    public int[] getSwapped() {
        return swapped;
    }
    public void setSwapped(int[] swapped) {
        this.swapped = swapped;
    }

    private int[] swapped = new int[2];
    public Sorting() {
        Random rand = new Random();
        int temp[] = new int[50];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = rand.nextInt(100);
        }
        setArr(temp);
    }

}
